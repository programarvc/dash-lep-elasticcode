package com.br.agilize.dash.service.dashboardService;

import java.sql.*;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersResponse;

public class CognitoUserMigrationService {
    public static void main(String[] args) {
        String userPoolId = "us-west-2_XeTLAls5y";
        CognitoIdentityProviderClient cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_WEST_2)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("AKIAYL4QFTGJDRZIEFP3", "mqwNE3rQv/Jqof/GD/K39HUEc6bvnL2DOeYVP0wP")))
                .build();

        listAllUsers(cognitoClient, userPoolId );
        cognitoClient.close();
    }

    public static void listAllUsers(CognitoIdentityProviderClient cognitoClient, String userPoolId ) {
        String url = "jdbc:postgresql://localhost:5432/elastic-ops";
        String userdb = "postgres";
        String password = "159753";

        try (Connection conn = DriverManager.getConnection(url, userdb, password)) {
            String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users (username VARCHAR(255))";
            try (Statement statement = conn.createStatement()) {
                statement.execute(SQL_CREATE_TABLE);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            String SQL_INSERT = "INSERT INTO users(username) VALUES(?)";

            ListUsersRequest usersRequest = ListUsersRequest.builder()
                    .userPoolId(userPoolId)
                    .build();

            ListUsersResponse response = cognitoClient.listUsers(usersRequest);
            response.users().forEach(user -> {
                System.out.println("User " + user.username() + " Status " + user.userStatus() + " Created " + user.userCreateDate() );
                try (PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {
                    preparedStatement.setString(1, user.username());
                    int row = preparedStatement.executeUpdate();

                    System.out.println("Row affected " + row);
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
