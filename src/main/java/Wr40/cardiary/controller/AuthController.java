package Wr40.cardiary.controller;

import Wr40.cardiary.service.JWTService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@Slf4j
public class AuthController {
    private JWTService jwtService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/token")
    public String token(Authentication authentication) {
        log.info("Token requested for {}", authentication.getName());
        String generatedToken = jwtService.generateToken(authentication);
        return generatedToken;
    }
}
