package com.example.onlinemarket.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtil {

    public String username() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
