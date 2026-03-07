package com.flowops.auth.service;

import com.flowops.auth.dto.RegisterRequest;
import com.flowops.auth.dto.RegisterResponse;
import com.flowops.auth.entity.TenantEntity;
import com.flowops.auth.entity.UserEntity;
import com.flowops.auth.exception.EmailAlreadyExistsException;
import com.flowops.auth.repository.TenantRepository;
import com.flowops.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        userRepository.findByEmail(request.getEmail())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException(request.getEmail());
                });

        Instant now = Instant.now();

        TenantEntity tenant = TenantEntity.builder()
                .name(request.getTenantName())
                .status("ACTIVE")
                .createdAt(now)
                .updatedAt(now)
                .build();

        TenantEntity savedTenant = tenantRepository.save(tenant);

        UserEntity user = UserEntity.builder()
                .tenantId(savedTenant.getId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role("ADMIN")
                .status("ACTIVE")
                .createdAt(now)
                .updatedAt(now)
                .build();

        UserEntity savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .tenantId(savedTenant.getId())
                .tenantName(savedTenant.getName())
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .message("Tenant and admin user registered successfully")
                .build();
    }
}