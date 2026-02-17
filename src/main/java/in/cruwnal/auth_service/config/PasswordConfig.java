package in.cruwnal.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Strength 10 is the default; you can increase it (e.g., 12) for more security
        // but it will make the hashing process slower.
        return new BCryptPasswordEncoder(10);
    }
}
