package com.flowops.workflowservice.dto;

import com.flowops.workflowservice.entity.WorkflowStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateWorkflowStatusRequest {

    @NotBlank
    private WorkflowStatus status;
}