package com.br.agilize.dash.service.dashboardService;

import java.sql.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;

//@Component
public class CognitoUserMigrationService /*implements CommandLineRunner */ {

     Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    //@Override
    public void run(String... args) throws Exception{
        String userPoolId = dotenv.get("USER_POOL_ID");
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(dotenv.get("ACESS_KEY_ID"), dotenv.get("SECRET_ACESS_KEY"))))
                .build();

        listAllUsers(cognitoClient, userPoolId );
        cognitoClient.close();
    }

    public  void listAllUsers(CognitoIdentityProviderClient cognitoClient, String userPoolId ) {
        String url = "jdbc:postgresql://" + dotenv.get("DB_HOST") + ":" + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_NAME");
        String userdb = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASSWORD");

        try (Connection conn = DriverManager.getConnection(url, userdb, password)) {
            ListUsersRequest usersRequest = ListUsersRequest.builder()
                    .userPoolId(userPoolId)
                    .build();

            ListUsersResponse response = cognitoClient.listUsers(usersRequest);
            response.users().forEach(user -> {
                System.out.println("User " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
                try {
                    String SQL_SELECT = "SELECT COUNT(*) FROM userentity WHERE username = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
                    preparedStatement.setString(1, user.username());
                    ResultSet resultSet = preparedStatement.executeQuery();
            
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        String SQL_INSERT = "INSERT INTO userentity(username) VALUES(?)";
                        preparedStatement = conn.prepareStatement(SQL_INSERT);
                        preparedStatement.setString(1, user.username());
                        int row = preparedStatement.executeUpdate();
            
                        System.out.println("Row affected " + row);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (CognitoIdentityProviderException | SQLException e){
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
