# Lab 06 — Testing, Security & Code Quality

## Objective
Generate a comprehensive unit test suite, identify and fix OWASP vulnerabilities, and interpret a SonarQube report — all using Copilot.

## Time
15 minutes (Part A: 7 min | Part B: 5 min | Part C: 3 min)

## Files Provided
| File | Purpose |
|------|---------|
| `src/main/java/com/training/ClaimProcessorService.java` | 6 methods — needs full test coverage |
| `src/test/java/com/training/ClaimProcessorServiceTest.java` | 2 existing tests — add 13+ more |
| `src/main/java/com/training/UserQueryService.java` | Has intentional OWASP vulnerabilities |
| `sonar-report.json` | Mock SonarQube scan results |
| `prompts.md` | Exact prompts for all parts |

## Part A: Generate Full Test Suite (7 min)
1. Open `ClaimProcessorService.java` and `ClaimProcessorServiceTest.java` side by side
2. Read the 2 existing tests to understand the style
3. Open Copilot Chat — use Prompt A1 from `prompts.md`
4. Add generated tests to `ClaimProcessorServiceTest.java`
5. Count test methods — **target: 15+**

## Part B: OWASP Security Review (5 min)
1. Open `UserQueryService.java`
2. Try to spot vulnerabilities yourself first (1 min)
3. Open Copilot Chat — use Prompt B1 (paste the class)
4. Review Copilot's findings — how many did you miss?
5. Use Prompt B2 to generate the fixed version

**Expected findings:** SQL Injection (×2), Hardcoded Credentials, Insecure Deserialization, Path Traversal, Sensitive Data Logging

## Part C: SonarQube Interpretation (3 min)
1. Open `sonar-report.json`
2. Open Copilot Chat — use Prompt C1 (paste the JSON)
3. Ask Copilot to prioritize fixes and generate code for the top 3 critical issues

## Common Mistakes
- If generated tests don't compile: paste the error into Chat — "Fix this compilation error: [paste error]"
- Security fixes may change method signatures — note this for your team
- The sonar-report.json is a simplified mock — in production, use actual SonarQube output
