package com.flowops.auth.service;

import com.flowops.auth.dto.LoginRequest;
import com.flowops.auth.dto.LoginResponse;
import com.flowops.auth.dto.RegisterRequest;
import com.flowops.auth.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}