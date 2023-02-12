package com.example.onlinemarket.config;

import com.example.onlinemarket.entity.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityAuditAwareImpl implements AuditorAware<Integer> {
    @Override
    public Optional<Integer> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals("" + authentication.getPrincipal())) {
            return Optional.of(((User) authentication.getPrincipal()).getId());
        }
        return Optional.empty();
    }
}
