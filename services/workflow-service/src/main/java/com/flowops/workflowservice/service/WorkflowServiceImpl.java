package com.flowops.workflowservice.service;

import com.flowops.workflowservice.dto.CreateWorkflowRequest;
import com.flowops.workflowservice.dto.WorkflowResponse;
import com.flowops.workflowservice.entity.WorkflowEntity;
import com.flowops.workflowservice.exception.WorkflowNotFoundException;
import com.flowops.workflowservice.repository.WorkflowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowRepository workflowRepository;

    @Override
    public WorkflowResponse createWorkflow(String tenantId, CreateWorkflowRequest request) {
        WorkflowEntity workflow = WorkflowEntity.create(
                tenantId,
                request.getName(),
                request.getDescription(),
                request.getTriggerType()
        );

        WorkflowEntity saved = workflowRepository.save(workflow);
        return mapToResponse(saved);
    }

    @Override
    public List<WorkflowResponse> listWorkflows(String tenantId) {
        return workflowRepository.findByTenantId(tenantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public WorkflowResponse getWorkflow(String tenantId, String workflowId) {
        WorkflowEntity workflow = workflowRepository.findByIdAndTenantId(workflowId, tenantId)
                .orElseThrow(() -> new WorkflowNotFoundException(workflowId));

        return mapToResponse(workflow);
    }

    private WorkflowResponse mapToResponse(WorkflowEntity entity) {
        return WorkflowResponse.builder()
                .id(entity.getId())
                .tenantId(entity.getTenantId())
                .name(entity.getName())
                .description(entity.getDescription())
                .triggerType(entity.getTriggerType())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}