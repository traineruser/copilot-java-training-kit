# Lab 02 — Agent Simulation: Build PolicyService

## Objective
Use a structured 5-Turn Copilot Chat sequence to build a complete PolicyService REST API entirely through prompted conversation — zero hand-written code.

## Time
45 minutes

## Setup
1. Create a new Maven project in Eclipse (or use the provided pom.xml)
2. Open `requirement.md` — read it carefully before starting
3. Open `prompts.md` — these are your 5 turns in order
4. Create `prompt-log.md` — log every prompt + result as you go

## The 5-Turn Pattern
| Turn | Job | What You Ask |
|------|-----|-------------|
| 1 | PLAN | List all files needed + their purpose |
| 2 | SCAFFOLD | Generate empty class stubs |
| 3 | IMPLEMENT | Add full method bodies |
| 4 | ERROR HANDLING | Add exceptions + GlobalExceptionHandler |
| 5 | DOCS | Generate Javadoc |

## Rules
- Send turns **in order** — each builds on the previous
- **Read** Copilot's response before sending the next turn
- **Log** each prompt in prompt-log.md as you go (don't try to remember later)
- **Iterate**: if Turn 3 output is too complex, add "Keep this simple — basic implementation only"

## Expected Output
- `PolicyService.java` — full CRUD with validation
- `PolicyController.java` — REST endpoints with proper ResponseEntity
- `Policy.java` — JPA entity
- `PolicyDTO.java` — validated DTO
- `PolicyRepository.java` — JPA repository
- `GlobalExceptionHandler.java` — error handling
- `prompt-log.md` — 5+ turns documented

## Bonus — Turn 6
```
Generate a JUnit 5 unit test class for PolicyService with Mockito.
Test: createPolicy success, getPolicyById found, getPolicyById not found,
cancelPolicy changes status, updatePolicy on CANCELLED throws exception.
```
