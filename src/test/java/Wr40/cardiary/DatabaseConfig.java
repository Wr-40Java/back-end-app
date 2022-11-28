package Wr40.cardiary;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
@TestConfiguration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        final PostgreSQLContainer database = new PostgreSQLContainer<>("postgres:14.2")
                .withDatabaseName("postgres")
                .withUsername("admin")
                .withPassword("admin");

        database.start();
        return new SingleConnectionDataSource(database.getJdbcUrl(), database.getUsername(), database.getPassword(), true);
    }
}
