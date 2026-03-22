# Lab 04 — Enterprise Patterns: Modernize PremiumCalculator

## Objective
Apply the Strategy design pattern and modernize a Java 8 legacy class to Java 17 using Copilot prompts — without writing any code by hand.

## Time
50 minutes (Part A: 25 min | Part B: 15 min | Part C optional: 10 min)

## Files Provided
| File | Purpose |
|------|---------|
| `src/main/java/com/training/legacy/PremiumCalculator.java` | Intentionally messy Java 8 code |
| `src/main/java/com/training/modern/PolicyServiceClient.java` | Feign client stub (Part C) |
| `prompts.md` | Exact Copilot prompts for each part |

## Part A: Strategy Pattern (25 min)
1. Open `PremiumCalculator.java` — scroll through it, spot the problems
2. Copy Prompt A1 from `prompts.md` as a comment above the class
3. Wait for Copilot to generate the strategy interface + implementations
4. Copy Prompt A2 to extract the discount rules

**What you should see:**
- `PremiumCalculationStrategy` interface
- `AutoPremiumStrategy`, `HomePremiumStrategy`, `LifePremiumStrategy` classes
- `DiscountRule` interface + `LoyaltyDiscountRule`, `MultiPolicyDiscountRule`

## Part B: Java 17 Modernization (15 min)
1. Add Prompt B1 above the class (or in Chat)
2. Let Copilot transform the class
3. Count lines before vs after — how much shorter is it?

**What you should see:**
- `Customer` and `Policy` converted to records
- Anonymous classes replaced with lambdas
- if-else chain replaced with switch expression
- Null checks replaced with Optional

## Part C: Feign Client (10 min — Optional)
1. Open `PolicyServiceClient.java`
2. Use Prompt C1 to convert the stub to a `@FeignClient` interface

## Common Mistakes
- If Strategy output is too complex: "Show only the interface and AutoPremiumStrategy first"
- Java 17 modernization may not change everything in one pass — run a second prompt
- Records don't have setters — check for any code that calls setters after modernization
