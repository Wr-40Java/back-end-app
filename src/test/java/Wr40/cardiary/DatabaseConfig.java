package Wr40.cardiary;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
@Testcontainers
public class DatabaseConfig {

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:14.2").withDatabaseName("postgres").withPassword("postgres").
                    withUsername("postgres").withExposedPorts(8080);

    @DynamicPropertySource
    public static void configContainer(DynamicPropertyRegistry dynamicPropertyRegistry){
        dynamicPropertyRegistry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
        dynamicPropertyRegistry.add("spring.liquibase.contexts", () -> "!prod");
        postgreSQLContainer.start();
    }



}
