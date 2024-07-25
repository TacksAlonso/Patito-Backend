package com.enterprise.patito.users.services;

import com.enterprise.patito.manager.token.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public String generateToken(String userName, String role) {
        return jwtTokenProvider.createToken(userName, role);
    }
}
