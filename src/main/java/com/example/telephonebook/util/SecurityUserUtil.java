package com.example.telephonebook.util;

import com.example.telephonebook.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static java.util.Objects.requireNonNull;

public class SecurityUserUtil {

    private SecurityUserUtil() {
    }

    public static Long authUserId() {
        return get().getId();
    }

    private static User get() {
        return requireNonNull(safeGet(), "No authorized user found");
    }

    public static User safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof User) ? (User) principal : null;
    }
}
