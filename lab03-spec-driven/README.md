# Lab 03 — Specification-Driven Claims API

## Objective
Generate a complete Spring Boot API layer and BDD test suite from an OpenAPI specification and Gherkin feature file — using Copilot as the code generator.

## Time
60 minutes (Part A: 35 min | Part B: 25 min)

## Files Provided
| File | Purpose |
|------|---------|
| `specs/claims-api.yaml` | OpenAPI 3.x spec — your API blueprint |
| `specs/claim-submission.feature` | Gherkin BDD scenarios |
| `src/main/java/com/training/` | Empty — Copilot fills this |
| `src/test/java/com/training/` | Empty — Copilot fills this |

## Part A: OpenAPI → Spring Boot

1. Open `specs/claims-api.yaml` — read the endpoint definitions
2. Open Copilot Chat in Eclipse
3. Paste the **first 2 endpoints** from the YAML (not the whole file — start small)
4. Use Prompt A1 from `prompts.md` to generate `ClaimsController.java`
5. Use Prompt A2 to generate DTO classes + enums
6. Use Prompt A3 to generate `ClaimsService` interface + stub impl

**Tip:** Paste 2-3 endpoints at a time for better results. Add more in follow-up prompts.

## Part B: Gherkin → JUnit 5 + Cucumber

1. Open `specs/claim-submission.feature` — read one scenario aloud
2. Use Prompt B1 from `prompts.md` — paste the whole feature file into Chat
3. Generate `ClaimSubmissionSteps.java` with step definitions
4. Use Prompt B2 to generate `CucumberRunnerTest.java` + Maven dependencies

## Bonus
Ask Copilot to generate a `ClaimsServiceImpl` that makes all happy-path scenarios pass.

## Common Mistakes
- Don't paste the entire YAML spec at once — start with 2-3 endpoints
- Gherkin step text must match EXACTLY — if Copilot diverges, say "Match the step text exactly from the feature file"
- Cucumber requires specific Maven dependencies — use Prompt B2 to generate them
