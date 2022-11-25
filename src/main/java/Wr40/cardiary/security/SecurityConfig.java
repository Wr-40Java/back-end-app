package Wr40.cardiary.security;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
