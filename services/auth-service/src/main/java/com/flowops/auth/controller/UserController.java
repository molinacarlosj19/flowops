package com.flowops.auth.controller;

import com.flowops.auth.dto.UserProfileResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "User-related endpoints")
public class UserController {

    @GetMapping("/me")
    @Operation(
            summary = "Get current authenticated user",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public UserProfileResponse getCurrentUser() {
        var user = (com.flowops.auth.entity.UserEntity) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return UserProfileResponse.builder()
                .id(user.getId())
                .tenantId(user.getTenantId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}