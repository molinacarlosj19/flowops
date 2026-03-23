package com.flowops.workflowservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWorkflowRequest {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String triggerType;
}