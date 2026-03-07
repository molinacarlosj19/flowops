package com.flowops.auth.service;

import com.flowops.auth.dto.RegisterRequest;
import com.flowops.auth.dto.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);
}