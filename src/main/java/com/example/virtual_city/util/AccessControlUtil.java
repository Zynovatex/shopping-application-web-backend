package com.example.virtual_city.util;

import com.example.virtual_city.model.User;
import org.springframework.stereotype.Component;

@Component
public class AccessControlUtil {

    public boolean hasAccess(User user, String requiredModule) {
        if (user == null || user.getAllowedModules() == null) {
            return false;
        }

        // ✅ Super Admins bypass module checks
        if (user.isSuperAdmin()) {
            return true;
        }

        return user.getAllowedModules().stream()
                .anyMatch(m -> m.equalsIgnoreCase(requiredModule));
    }
}
