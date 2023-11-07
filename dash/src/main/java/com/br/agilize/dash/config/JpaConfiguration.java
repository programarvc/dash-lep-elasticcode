package com.br.agilize.dash.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.google.gson.Gson;

import jakarta.persistence.EntityManagerFactory;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.regions.Region;
import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class JpaConfiguration {
  private final Gson gson = new Gson();
  Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

  public JpaConfiguration() {
  }

  public DataSource dataSource() {
    String env = dotenv.get("ENV");

    var dataSource = new SimpleDriverDataSource();

    if (env == null) {
      final AwsSecret dbCredentials = getSecret();
      dataSource.setDriverClass(org.postgresql.Driver.class);
      dataSource.setUrl("jdbc:postgresql://" + dbCredentials.getHost() + ":" + dbCredentials.getPort()
          + "/" + dbCredentials.getDbInstanceIdentifier());
      dataSource.setUsername(dbCredentials.getUsername());
      dataSource.setPassword(dbCredentials.getPassword());
    } else {
      dataSource.setDriverClass(org.postgresql.Driver.class);
      dataSource.setUrl("jdbc:postgresql://" + dotenv.get("DB_HOST") + ":" + dotenv.get("DB_PORT")
          + "/" + dotenv.get("DB_NAME"));
      dataSource.setUsername(dotenv.get("DB_USER"));
      dataSource.setPassword(dotenv.get("DB_PASSWORD"));
    }

    return dataSource;
  }

  private AwsSecret getSecret() {
    Region region = Region.of(dotenv.get("AWS_REGION"));

    var secretsManagerClient = SecretsManagerClient.builder()
        .region(region)
        .build();

    String secret;

    var getSecretValueRequest = GetSecretValueRequest.builder()
        .secretId(dotenv.get("AWS_SECRET_ID"))
        .build();

    GetSecretValueResponse result = null;

    try {
      result = secretsManagerClient.getSecretValue(getSecretValueRequest);
    } catch (Exception e) {
      throw e;
    }
    if (result.secretString() != null) {
      secret = result.secretString();
      return gson.fromJson(secret, AwsSecret.class);
    }

    return null;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
    jpaVendorAdapter.setGenerateDdl(true);

    return jpaVendorAdapter;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
    lemfb.setDataSource(dataSource());
    lemfb.setJpaVendorAdapter(jpaVendorAdapter());
    lemfb.setPackagesToScan("com.br.agilize.dash");
    return lemfb;
  }
}
