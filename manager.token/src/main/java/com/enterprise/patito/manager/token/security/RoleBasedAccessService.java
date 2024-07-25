package com.enterprise.patito.manager.token.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class RoleBasedAccessService {

    public boolean hasAccessToUsersEndpoint(Authentication authentication) {
        System.out.println(authentication);
        System.out.println(authentication.getAuthorities());
        if (authentication != null && authentication.getAuthorities() != null) {
            return authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        }
        return false;
    }
}
