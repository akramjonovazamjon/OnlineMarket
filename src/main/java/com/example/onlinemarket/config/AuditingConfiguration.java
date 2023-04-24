package com.example.onlinemarket.config;

import com.example.onlinemarket.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {
    @Bean
    AuditorAware<Integer> auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals("" + authentication.getPrincipal())) {
                return Optional.of(((User) authentication.getPrincipal()).getId());
            }
            return Optional.empty();
        };
    }
}
