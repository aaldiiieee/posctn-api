package posctn.posctn_api.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Data
@Slf4j
@Configuration
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    /**
     * Secret key for signing
     */
    private String secretKey;

    /**
     * Access token expiration time in milliseconds
     */
    private long expiration;

    /**
     * Access refresh token expiration time in milliseconds
     */
    private long refreshExpiration;

    @PostConstruct
    public void logConfig() {
        log.info("====================================== JWT Config Loaded ======================================");
        log.info("Secret: {}", secretKey != null ? "SET (" + secretKey.length() + " chars)" : "NULL!");
        log.info("Expiration: {} ms ({} days)", expiration, expiration / 86400000);
        log.info("Refresh Expiration: {} ms ({} days)", refreshExpiration, refreshExpiration / 86400000);
        log.info("=============================================================================================== \n");
    }

    /**
     * Bean for signing key JWT.
     * Generate secure key for HS512 algorithm.
     */
    @Bean
    public Key jwtSigningKey() {
        // If a secret is set, use that secret
        if (secretKey != null && !secretKey.isBlank()) {
            return Keys.hmacShaKeyFor(secretKey.getBytes());
        }
        // Generate random secure key
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
