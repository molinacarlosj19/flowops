package com.flowops.workflowservice.dto;

import com.flowops.workflowservice.entity.WorkflowStatus;
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
    private WorkflowStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}