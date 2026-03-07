package com.flowops.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private String tenantId;
    private String tenantName;
    private String userId;
    private String email;
    private String role;
    private String message;
}