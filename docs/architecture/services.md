Services Overview
auth-service
workflow-service
execution-service (future)
audit-service (future)
notification-service (future)

auth-service
Responsible for:
authentication
JWT
tenant management

workflow-service
Responsible for:
workflow definitions
workflow lifecycle
tenant workflow configuration

workflow-service
│
├── controller
├── service
├── repository
├── entity
├── dto
├── mapper
├── config
└── exception