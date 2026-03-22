# PolicyService — Business Requirement

## Overview
Build a PolicyService REST API for an insurance company's policy management system.

## Business Context
The insurance company needs a REST API to manage customer insurance policies.
Customer service agents will use this API to create, view, update, and cancel policies.

## Required Entities

### Policy
| Field | Type | Constraints |
|-------|------|-------------|
| policyId | Long | Auto-generated, primary key |
| customerId | String | Required, max 50 chars |
| policyType | Enum | Required — AUTO, HOME, LIFE |
| premium | BigDecimal | Required, must be positive |
| status | Enum | ACTIVE, INACTIVE, CANCELLED |
| startDate | LocalDate | Required |
| endDate | LocalDate | Required, must be after startDate |
| createdAt | LocalDateTime | Auto-set on creation |

## Required API Endpoints

| Method | Path | Description | Success Code |
|--------|------|-------------|-------------|
| POST | /api/policies | Create a new policy | 201 Created |
| GET | /api/policies/{id} | Get policy by ID | 200 OK |
| GET | /api/policies | Get all policies | 200 OK |
| PUT | /api/policies/{id} | Update a policy | 200 OK |
| DELETE | /api/policies/{id} | Cancel a policy (soft delete) | 204 No Content |

## Business Rules
- PolicyId is auto-generated — never provided by client
- Status defaults to ACTIVE on creation
- DELETE sets status to CANCELLED (not physical delete)
- premium must be greater than 0
- endDate must be after startDate
- All string fields must be trimmed of whitespace

## Error Handling
- Return 404 Not Found with message when policy does not exist
- Return 400 Bad Request with field-level validation errors
- Return 409 Conflict if attempting to update a CANCELLED policy

## Non-Functional Requirements
- Standard Spring Boot 3.x project structure
- JPA repository with H2 in-memory database
- Bean Validation on all DTOs
- Proper Javadoc on all public methods and classes
