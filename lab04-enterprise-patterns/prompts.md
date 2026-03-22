# Lab 04 — Enterprise Patterns Prompts

## Part A: Apply Strategy Pattern

### Prompt A1 — Strategy Pattern (paste as comment above PremiumCalculator class)
```java
// Apply the Strategy design pattern to PremiumCalculator.
// Extract each premium calculation rule into a separate class implementing PremiumCalculationStrategy interface.
// Rules to extract:
//   1. AutoPremiumStrategy  — applies AUTO_BASE_MULTIPLIER + young driver surcharge for age < 25
//   2. HomePremiumStrategy  — applies HOME_BASE_MULTIPLIER + HIGH_RISK zone surcharge
//   3. LifePremiumStrategy  — applies LIFE_BASE_MULTIPLIER only
// The interface method signature: BigDecimal calculate(BigDecimal basePremium, Customer customer)
// Update PremiumCalculator to accept a PremiumCalculationStrategy and delegate to it.
```

### Prompt A2 — Extract Discount Rules (after Strategy is done)
```java
// Extract the loyalty discount and multi-policy discount anonymous classes
// into named classes LoyaltyDiscountRule and MultiPolicyDiscountRule,
// both implementing a DiscountRule interface with method:
//   BigDecimal apply(BigDecimal premium, Customer customer)
// Store them in a List<DiscountRule> and apply them in sequence.
```

---

## Part B: Java 17 Modernization

### Prompt B1 — Modernize Full Class (paste as comment above class)
```java
// Modernize this entire class to Java 17 best practices:
// 1. Replace anonymous inner classes with lambda expressions
// 2. Replace null checks (if x == null) with Optional.ofNullable(x).orElse(default)
// 3. Replace the String policyType if-else chain with a switch expression
// 4. Convert nested data classes Customer and Policy to Java 17 records
// 5. Use var for local variables where type is obvious
// 6. Replace raw types (Map, List without generics) with properly typed generics
// Show the complete modernized class.
```

### Prompt B2 — Records for DTOs (Chat prompt)
```
Convert the Customer and Policy static inner classes in PremiumCalculator to Java 17 records.
Records should be immutable — remove all setters.
Add a compact constructor to Customer that validates age is positive and customerId is not blank.
Show the complete record definitions.
```

### Prompt B3 — Switch Expression (Chat prompt)
```
Replace this if-else chain in PremiumCalculator.calculate() with a Java 17 switch expression:

    if (policy.getPolicyType().equals("AUTO")) {
        premium = premium.multiply(...);
    } else if (policy.getPolicyType().equals("HOME")) {
        premium = premium.multiply(...);
    } else if (policy.getPolicyType().equals("LIFE")) {
        premium = premium.multiply(...);
    }

Use: BigDecimal multiplier = switch (policy.policyType()) { ... }
Then apply: premium = premium.multiply(multiplier);
```

---

## Part C (Optional): Feign Client

### Prompt C1 — Convert stub to Feign interface (paste as comment above class)
```java
// Convert PolicyServiceClient from a plain class to a Spring Cloud OpenFeign @FeignClient interface.
// @FeignClient name = "policy-service", url = "${policy.service.url}"
// Methods:
//   @GetMapping("/api/policies/{id}")    PolicyResponse getPolicy(@PathVariable Long id)
//   @PostMapping("/api/policies")        PolicyResponse createPolicy(@RequestBody PolicyRequest request)
//   @PutMapping("/api/policies/{id}")    PolicyResponse updatePolicy(@PathVariable Long id, @RequestBody PolicyRequest request)
//   @DeleteMapping("/api/policies/{id}") void deletePolicy(@PathVariable Long id)
// Convert PolicyRequest to a record with @NotNull and @Positive validation.
// Convert PolicyResponse to a record.
// Add the spring-cloud-starter-openfeign dependency to pom.xml.
```

---

## Verification Checklist

After completing each part, verify with Copilot Chat:

```
Review my modernized PremiumCalculator and check:
1. Are there any remaining null checks that could use Optional?
2. Are there any remaining anonymous classes that could be lambdas?
3. Is the switch expression exhaustive — does it handle all policy types?
4. Are the records properly immutable (no setters)?
List any remaining Java 8 patterns you find.
```
