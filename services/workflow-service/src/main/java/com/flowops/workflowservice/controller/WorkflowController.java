package com.flowops.workflowservice.controller;

import com.flowops.workflowservice.dto.CreateWorkflowRequest;
import com.flowops.workflowservice.dto.WorkflowResponse;
import com.flowops.workflowservice.service.WorkflowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workflows")
@RequiredArgsConstructor
@Tag(name = "Workflows", description = "Workflow management endpoints")
public class WorkflowController {

    private final WorkflowService workflowService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new workflow")
    public WorkflowResponse createWorkflow(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @Valid @RequestBody CreateWorkflowRequest request
    ) {
        return workflowService.createWorkflow(tenantId, request);
    }

    @GetMapping
    @Operation(summary = "List workflows by tenant")
    public List<WorkflowResponse> listWorkflows(
            @RequestHeader("X-Tenant-Id") String tenantId
    ) {
        return workflowService.listWorkflows(tenantId);
    }

    @GetMapping("/{workflowId}")
    @Operation(summary = "Get workflow by id")
    public WorkflowResponse getWorkflow(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable String workflowId
    ) {
        return workflowService.getWorkflow(tenantId, workflowId);
    }
}