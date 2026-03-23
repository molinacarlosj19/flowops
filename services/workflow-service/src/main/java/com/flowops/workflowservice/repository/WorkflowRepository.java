package com.flowops.workflowservice.repository;

import com.flowops.workflowservice.entity.WorkflowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkflowRepository extends JpaRepository<WorkflowEntity, String> {

    List<WorkflowEntity> findByTenantId(String tenantId);

    Optional<WorkflowEntity> findByIdAndTenantId(String id, String tenantId);
}