# Lab 02 — 5-Turn Agent Simulation Prompts

Copy each turn into Copilot Chat IN ORDER.
Wait for Copilot to respond before sending the next turn.
Log each prompt + result summary in prompt-log.md.

---

## TURN 1 — PLAN

```
Create a step-by-step plan for building a PolicyService REST API in Spring Boot 3.x.
The service manages insurance policies for the requirement described below.

For each step, list:
1. The file to create (full class name and package)
2. The purpose of that file
3. Any dependencies it needs

Requirement summary:
- CRUD REST API for insurance policies
- Fields: policyId (auto), customerId, policyType (AUTO/HOME/LIFE), premium (BigDecimal), status (ACTIVE/INACTIVE/CANCELLED), startDate, endDate, createdAt
- Endpoints: POST /api/policies, GET /api/policies/{id}, GET /api/policies, PUT /api/policies/{id}, DELETE /api/policies/{id}
- DELETE is a soft delete (sets status to CANCELLED)
- Use Spring Boot 3.x, JPA, H2, Bean Validation
```

---

## TURN 2 — SCAFFOLD

```
Based on the plan above, generate the empty class stubs only (no implementation yet) for all files in the com.training package:

- Policy.java (JPA entity)
- PolicyType.java (enum: AUTO, HOME, LIFE)
- PolicyStatus.java (enum: ACTIVE, INACTIVE, CANCELLED)
- PolicyDTO.java (request/response DTO with validation annotations)
- PolicyRepository.java (JPA repository interface)
- PolicyService.java (service class — method signatures only, no bodies)
- PolicyController.java (REST controller — method signatures only, no bodies)
- PolicyNotFoundException.java (custom exception)

Also generate pom.xml with the required Spring Boot 3.2 dependencies.
Show only stubs — no method bodies yet.
```

---

## TURN 3 — IMPLEMENT

```
Now implement the full method bodies for PolicyService.java:

- createPolicy(PolicyDTO dto): validate, save, return saved entity as DTO
- getPolicyById(Long id): find by id, throw PolicyNotFoundException if not found
- getAllPolicies(): return all policies as List<PolicyDTO>
- updatePolicy(Long id, PolicyDTO dto): find, validate not CANCELLED, update fields, save
- cancelPolicy(Long id): find, set status to CANCELLED, save (do not delete from DB)

Include:
- Proper null checks
- Business rule: throw PolicyNotFoundException for missing policies
- Business rule: throw IllegalStateException if trying to update a CANCELLED policy
- Use @Transactional where appropriate
```

---

## TURN 4 — ERROR HANDLING

```
Add comprehensive error handling to the project:

1. Create GlobalExceptionHandler.java annotated with @ControllerAdvice containing:
   - Handler for PolicyNotFoundException → 404 Not Found with message
   - Handler for IllegalStateException → 409 Conflict with message
   - Handler for MethodArgumentNotValidException → 400 Bad Request with field-level errors
   - Handler for generic Exception → 500 Internal Server Error

2. Create ErrorResponse.java record with fields: timestamp, status, message, errors (List<String>)

3. Update PolicyController.java to use proper ResponseEntity return types:
   - POST → ResponseEntity<PolicyDTO> with 201 Created
   - GET by id → ResponseEntity<PolicyDTO>
   - GET all → ResponseEntity<List<PolicyDTO>>
   - PUT → ResponseEntity<PolicyDTO>
   - DELETE → ResponseEntity<Void> with 204 No Content
```

---

## TURN 5 — DOCUMENTATION

```
Generate complete Javadoc for all public methods and classes in:
- PolicyService.java
- PolicyController.java
- PolicyDTO.java

Each method Javadoc must include:
- A one-sentence description of what the method does (business purpose, not technical)
- @param for each parameter with type and description
- @return describing the return value
- @throws listing each exception with the condition that triggers it

Keep the language professional and business-focused (avoid framework jargon).
```

---

## BONUS TURN 6 — Tests

```
Generate a JUnit 5 unit test class for PolicyService:
- Use Mockito to mock PolicyRepository
- Test: createPolicy with valid data → returns saved DTO
- Test: getPolicyById with existing id → returns DTO
- Test: getPolicyById with missing id → throws PolicyNotFoundException
- Test: cancelPolicy with ACTIVE policy → sets status CANCELLED
- Test: updatePolicy with CANCELLED policy → throws IllegalStateException
- Use @ExtendWith(MockitoExtension.class)
```

---

## prompt-log.md Template

Copy this to prompt-log.md and fill it in as you go:

| Turn | Prompt (brief description) | Result Quality (1–5) | What worked / What to improve |
|------|---------------------------|---------------------|-------------------------------|
| 1 — Plan | | | |
| 2 — Scaffold | | | |
| 3 — Implement | | | |
| 4 — Error Handling | | | |
| 5 — Docs | | | |
| 6 — Tests (bonus) | | | |
