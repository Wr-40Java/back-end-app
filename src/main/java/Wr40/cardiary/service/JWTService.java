package Wr40.cardiary.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transient
public class JWTService {

    private final JwtEncoder jwtEncoder;

    public String generateToken(Authentication authentication) {

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        log.info("authorities {}", scope);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .subject(authentication.getName())
                .expiresAt(Instant.now().plus(1,ChronoUnit.HOURS))
                .claim("scope", scope)
                .build();
        log.info("claims {}",claims.getClaims().toString());


        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
