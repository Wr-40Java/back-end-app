package Wr40.cardiary;

import Wr40.cardiary.controller.SecurityTestConfig;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = DatabaseConfig.class)
public class CarserviceApplicationTests  {

	@PostConstruct
	@Test
	public void contextLoads() {
	}
}
