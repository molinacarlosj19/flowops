# Workflow Service - Design Overview

## Purpose

The `workflow-service` manages workflow definitions in FlowOps.

A workflow represents an automation definition owned by a tenant.  
The service is responsible for creating, listing, retrieving, and updating workflow definitions.

This service does not execute workflows. Execution will be handled later by `execution-service`.

---

## Responsibilities

- Create workflow definitions
- List workflows by tenant
- Retrieve workflow details
- Activate or deactivate workflows
- Validate basic workflow configuration

## Out of Scope (for now)

- Workflow execution
- Async messaging
- Notifications
- Audit event publishing
- Advanced rule engine

---

## Multi-Tenant Model

Each workflow belongs to a tenant.

Tenant isolation is enforced using the `tenantId` claim from the JWT token issued by `auth-service`.

---

## Main Entity

### Workflow

- id
- tenantId
- name
- description
- triggerType
- status
- createdAt
- updatedAt

### Status

- ACTIVE
- INACTIVE

### Trigger Types (v1)

- MANUAL
- SCHEDULED
- EVENT

---

## API Endpoints (v1)

- POST `/api/v1/workflows`
- GET `/api/v1/workflows`
- GET `/api/v1/workflows/{id}`
- PATCH `/api/v1/workflows/{id}/status`

---

## Communication Style

Initial implementation uses synchronous REST.

Future evolution may introduce event-driven patterns for:
- workflow created
- workflow updated
- workflow execution started
- workflow execution completed

## Documentation

The service exposes OpenAPI documentation through Swagger UI for local development and testing.

Default URL:
- /swagger-ui/index.html

### Update Workflow Status

Allows enabling or disabling a workflow.

PATCH `/api/v1/workflows/{id}/status`

Body:

{
"status": "ACTIVE | INACTIVE"
}