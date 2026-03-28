package com.flowops.workflowservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateWorkflowStatusRequest {

    @NotBlank
    private String status;
}