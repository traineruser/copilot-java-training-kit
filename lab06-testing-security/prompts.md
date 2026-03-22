# Lab 06 — Testing, Security & SonarQube Prompts

## Part A: Generate Full Test Suite

### Prompt A1 — Full Test Suite (Copilot Chat)
Paste this after showing ClaimProcessorService.java in Chat:

```
Generate a complete JUnit 5 test class for ClaimProcessorService.

Class under test: ClaimProcessorService (no dependencies to mock — pure logic).
Test class name: ClaimProcessorServiceTest (add to existing file below the existing 2 tests)

Cover ALL 6 public methods with these test cases:

processClaim():
  - Happy path: valid AUTO claim → status APPROVED, amount set
  - Null claim → throws IllegalArgumentException
  - Zero amount → throws IllegalArgumentException
  - Negative amount → throws IllegalArgumentException
  - AUTO claim amount > 50000 → throws ClaimLimitExceededException
  - HOME claim within limit → approved
  - Amount is rounded to 2 decimal places

getClaimLimit():
  - "AUTO" → 50000
  - "HOME" → 200000
  - "LIFE" → 500000
  - null → throws IllegalArgumentException
  - "UNKNOWN" → throws IllegalArgumentException
  - Case insensitive: "auto" → 50000

calculateFraudRiskScore():
  - null claim → returns 0.0
  - 0 recent claims, small amount → returns 0.0
  - 4+ recent claims → score includes 0.3
  - Amount > 80% of limit → score includes 0.4
  - Both conditions → score is 0.7
  - Score never exceeds 1.0

requiresManualReview():
  - null claim → false
  - Low risk, small amount → false
  - Fraud score >= 0.75 → true
  - Amount exactly $25000 → true

calculateTotalClaimsValue():
  - null list → BigDecimal.ZERO
  - Empty list → BigDecimal.ZERO
  - List with null entries → sums non-null correctly
  - List with valid claims → correct sum

findClaimById():
  - null list → empty Optional
  - null claimId → empty Optional
  - Blank claimId → empty Optional
  - Claim found → Optional containing claim
  - Claim not found → empty Optional

Use descriptive names: should_[expected]_when_[condition]
```

### Prompt A2 — Parameterized Tests
```
Add @ParameterizedTest tests to ClaimProcessorServiceTest for:

1. getClaimLimit with @MethodSource:
   Provide a static method returning Stream<Arguments> with cases:
   - ("AUTO", 50000), ("HOME", 200000), ("LIFE", 500000)
   - ("auto", 50000), ("Auto", 200000) — case-insensitive

2. calculateFraudRiskScore with @CsvSource:
   Cases: claimId, policyType, amount, recentCount, expectedScore
   Include at least 5 cases covering different score combinations.
```

---

## Part B: OWASP Security Review

### Prompt B1 — Find Vulnerabilities (Copilot Chat)
Paste UserQueryService.java into Chat, then send:

```
Analyze this Java class for OWASP Top 10 (2021) security vulnerabilities.

For each vulnerability found, provide:
1. Line number(s) where the vulnerability exists
2. OWASP category code and name (e.g., A03:2021 - Injection)
3. Severity: Critical / High / Medium / Low
4. Brief explanation of why this is vulnerable
5. Recommended fix (code snippet if applicable)

Format as a numbered list. Be thorough — this class has at least 5 vulnerabilities.
```

### Prompt B2 — Generate Fixed Version
After reviewing the vulnerability list:

```
Generate a complete fixed version of UserQueryService that remediates ALL vulnerabilities identified.

Specific fixes required:
1. SQL injection: Use JdbcTemplate with parameterized queries (? placeholders)
2. Hardcoded credentials: Replace with @Value("${db.password}") injection or environment variables
3. Insecure deserialization: Replace ObjectInputStream with Jackson JSON parsing (use ObjectMapper)
4. Path traversal: Validate and sanitize file paths — reject paths with ".." or absolute paths
5. Sensitive data logging: Replace System.out.println with SLF4J Logger, never log passwords or secrets
6. Access control: Add @PreAuthorize("hasRole('ADMIN')") on getAdminData()

Show the complete fixed class with all imports and annotations.
```

---

## Part C: SonarQube Report Analysis

### Prompt C1 — Interpret the Report
Open sonar-report.json, paste into Copilot Chat:

```
This is a SonarQube scan report in JSON format for a Java Spring Boot project.

Please:
1. Summarize the quality gate result in 2 sentences
2. List the top 3 most critical issues with: rule, file, line, and plain-English explanation
3. For each of the top 3 issues, generate a Java code fix
4. Estimate the total remediation effort
5. Suggest the priority order for fixing all 8 issues (most impactful first)

SonarQube report:
[PASTE contents of sonar-report.json here]
```

### Prompt C2 — Auto-Fix Critical Issues
```
Based on the SonarQube report, generate specific code fixes for the 3 CRITICAL issues:

Issue 1 (ISSUE-001, line 57): SQL injection in UserQueryService.findUserByName()
Issue 2 (ISSUE-002, line 34): Hardcoded password in UserQueryService
Issue 3 (ISSUE-003, line 93): Insecure deserialization in UserQueryService.deserializeUserProfile()

For each fix:
- Show the vulnerable code (before)
- Show the fixed code (after)
- Add a comment explaining what the fix does
```
