package com.flowops.auth.service;

import com.flowops.auth.entity.UserEntity;

public interface JwtService {

    String generateToken(UserEntity user);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserEntity user);

    long getExpirationTime();
}