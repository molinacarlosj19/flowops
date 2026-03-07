package com.flowops.auth.service;

import com.flowops.auth.entity.UserEntity;

public interface JwtService {

    String generateToken(UserEntity user);

    long getExpirationTime();
}