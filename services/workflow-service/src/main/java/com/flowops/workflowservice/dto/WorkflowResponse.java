package com.flowops.workflowservice.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowResponse {

    private String id;
    private String tenantId;
    private String name;
    private String description;
    private String triggerType;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}