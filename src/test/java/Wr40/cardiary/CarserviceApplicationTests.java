package Wr40.cardiary;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;

@SpringBootTest/*(classes = DatabaseConfig.class)*/
public class CarserviceApplicationTests extends DatabaseConfig {

	@Test
	@PostConstruct
	public void contextLoads() {
	}

}
