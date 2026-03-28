package com.flowops.workflowservice.service;

import com.flowops.workflowservice.dto.CreateWorkflowRequest;
import com.flowops.workflowservice.dto.WorkflowResponse;

import java.util.List;

public interface WorkflowService {

    WorkflowResponse createWorkflow(String tenantId, CreateWorkflowRequest request);

    List<WorkflowResponse> listWorkflows(String tenantId);

    WorkflowResponse getWorkflow(String tenantId, String workflowId);

    WorkflowResponse updateWorkflowStatus(
            String tenantId,
            String workflowId,
            String status
    );
}