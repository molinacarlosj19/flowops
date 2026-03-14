package com.flowops.auth.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponse {

    private String id;
    private String tenantId;
    private String fullName;
    private String email;
    private String role;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}