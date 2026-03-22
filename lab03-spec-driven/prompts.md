# Lab 03 — Specification-Driven Development Prompts

## Part A: OpenAPI Spec → Spring Boot Code

### Prompt A1 — Generate REST Controller
Paste into Copilot Chat after opening claims-api.yaml:

```
Read this OpenAPI 3.0 specification and generate a Spring Boot @RestController class called ClaimsController.

Requirements:
- Package: com.training.controller
- Class annotation: @RestController, @RequestMapping("/api/claims")
- Map each path + method from the spec to the correct @GetMapping/@PostMapping/@PutMapping/@DeleteMapping
- Use @PathVariable for {claimId}, @RequestParam for query parameters
- Use @RequestBody with @Valid for POST and PUT request bodies
- Return proper ResponseEntity types matching each endpoint's success response code
- Inject a ClaimsService (constructor injection)

OpenAPI spec:
[PASTE contents of specs/claims-api.yaml here]
```

---

### Prompt A2 — Generate DTO Classes
```
Based on this OpenAPI spec's components/schemas section, generate Java classes:

1. ClaimRequest.java — request DTO with fields matching ClaimRequest schema
   - Add Bean Validation: @NotBlank, @NotNull, @Size, @Positive, @PastOrPresent where appropriate
   - Use record or POJO style (your choice)
   - Package: com.training.dto

2. ClaimResponse.java — response DTO matching ClaimResponse schema
   - Package: com.training.dto

3. ClaimType.java — Java enum with values: AUTO_COLLISION, AUTO_THEFT, HOME_FIRE, HOME_FLOOD, HOME_THEFT, LIFE_DEATH, MEDICAL
   - Package: com.training.model

4. ClaimStatus.java — Java enum with values: SUBMITTED, UNDER_REVIEW, APPROVED, REJECTED, CANCELLED
   - Package: com.training.model

Schemas:
[PASTE the components/schemas section from claims-api.yaml]
```

---

### Prompt A3 — Generate Service Interface + Stub
```
Generate a ClaimsService.java interface in package com.training.service with method signatures matching all operations in ClaimsController:

- getAllClaims(ClaimStatus status, int page, int size): List<ClaimResponse>
- createClaim(ClaimRequest request): ClaimResponse
- getClaimById(String claimId): ClaimResponse
- updateClaim(String claimId, ClaimRequest request): ClaimResponse
- cancelClaim(String claimId): void

Also generate ClaimsServiceImpl.java with stub implementations (throw UnsupportedOperationException for now).
Include @Service on the impl class.
```

---

## Part B: Gherkin Feature File → JUnit 5 + Cucumber Tests

### Prompt B1 — Generate Step Definitions
Paste into Copilot Chat after opening claim-submission.feature:

```
Read this Gherkin feature file and generate a JUnit 5 + Cucumber step definitions class.

Requirements:
- Class name: ClaimSubmissionSteps.java
- Package: com.training.steps
- Match EVERY Given/When/Then step text EXACTLY (copy from feature file)
- Use @Given, @When, @Then annotations from io.cucumber.java.en
- Use MockMvc or RestAssured for HTTP calls (your choice)
- Store response in a field for use across steps
- Use Cucumber DataTable for scenario outline tables
- Provide reasonable stub implementations (can return mock data)

Feature file:
[PASTE contents of specs/claim-submission.feature here]
```

---

### Prompt B2 — Generate Cucumber Runner
```
Generate a JUnit 5 Cucumber test runner class:
- Class name: CucumberRunnerTest.java
- Package: com.training
- Use @Suite, @IncludeEngines("cucumber"), @ConfigurationParameter
- Configure: features = "src/test/resources/features", glue = "com.training.steps"
- Also generate the matching pom.xml dependencies for:
  - cucumber-java (7.x)
  - cucumber-junit-platform-engine (7.x)
  - cucumber-spring (7.x)
  - junit-platform-suite (1.x)
```

---

### Prompt B3 — Bonus: Generate ClaimsService Implementation
```
Looking at the ClaimSubmissionSteps test class and the ClaimsService interface,
generate a full implementation of ClaimsServiceImpl that would make all the
happy-path Cucumber scenarios pass.

Use an in-memory HashMap<String, Claim> as the data store (no JPA needed for now).
Auto-generate claimId as "CLM-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase()
Set submittedAt and lastUpdatedAt automatically.
Throw ClaimNotFoundException (create this class) when a claim is not found.
```
