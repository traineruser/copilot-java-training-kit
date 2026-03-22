package com.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LAB 06 STARTER: ClaimProcessorServiceTest
 *
 * Currently has only 2 basic tests — WELL BELOW acceptable coverage.
 *
 * YOUR TASK (Part A): Use Copilot to generate tests for all 6 methods.
 *
 * COPILOT PROMPT (paste into Chat after opening ClaimProcessorService.java):
 * ─────────────────────────────────────────────────────────────────────────
 * Generate JUnit 5 unit tests for ClaimProcessorService covering ALL public methods:
 * - processClaim(): happy path, null claim, zero amount, negative amount, amount exceeds limit
 * - getClaimLimit(): AUTO/HOME/LIFE types, null type, unknown type
 * - calculateFraudRiskScore(): null claim, low risk, high frequency, large amount, combined risk, score capped at 1.0
 * - requiresManualReview(): null, low risk, high fraud score, large amount ($25000+)
 * - calculateTotalClaimsValue(): null list, empty list, list with nulls, list with valid claims
 * - findClaimById(): null list, blank claimId, found, not found
 *
 * Use @BeforeEach to create a shared ClaimProcessorService instance.
 * Use descriptive test method names: should_[expected]_when_[condition]
 * Target: at least 15 test methods.
 * ─────────────────────────────────────────────────────────────────────────
 *
 * ALSO ADD: @ParameterizedTest for getClaimLimit with different policy types
 * PROMPT:
 *   Generate @ParameterizedTest with @MethodSource for getClaimLimit.
 *   Provide test cases for: AUTO→50000, HOME→200000, LIFE→500000,
 *   null→exception, "UNKNOWN"→exception, empty string→exception
 */
class ClaimProcessorServiceTest {

    private ClaimProcessorService service;

    @BeforeEach
    void setUp() {
        service = new ClaimProcessorService();
    }

    // ─── EXISTING TESTS (only 2 — need many more!) ───────────────────────────

    @Test
    void should_approve_claim_when_amount_is_within_auto_limit() {
        var claim = new ClaimProcessorService.ClaimSubmission(
            "CLM-001", "AUTO", new BigDecimal("10000.00")
        );
        var result = service.processClaim(claim);
        assertNotNull(result);
        assertEquals("CLM-001", result.claimId());
        assertEquals(ClaimProcessorService.ClaimStatus.APPROVED, result.status());
    }

    @Test
    void should_return_zero_when_claims_list_is_empty() {
        var result = service.calculateTotalClaimsValue(List.of());
        assertEquals(BigDecimal.ZERO, result);
    }

    // ─── TODO: Use Copilot to generate tests below ───────────────────────────
    //
    // MINIMUM REQUIRED:
    //
    // processClaim() tests:
    //   - should_throw_when_claim_is_null
    //   - should_throw_when_amount_is_zero
    //   - should_throw_when_amount_is_negative
    //   - should_throw_ClaimLimitExceededException_when_AUTO_claim_exceeds_50000
    //   - should_approve_HOME_claim_within_limit
    //
    // getClaimLimit() tests:
    //   - should_return_50000_for_AUTO
    //   - should_return_200000_for_HOME
    //   - should_return_500000_for_LIFE
    //   - should_throw_when_policyType_is_null
    //   - should_throw_when_policyType_is_unknown
    //
    // calculateFraudRiskScore() tests:
    //   - should_return_zero_when_claim_is_null
    //   - should_add_0_3_when_recent_claims_exceed_3
    //   - should_add_0_4_when_amount_exceeds_80_percent_of_limit
    //   - should_cap_score_at_1_0
    //
    // requiresManualReview() tests:
    //   - should_return_false_for_null_claim
    //   - should_flag_when_fraud_score_is_high
    //   - should_flag_when_amount_is_25000_or_more
    //
    // findClaimById() tests:
    //   - should_return_empty_when_list_is_null
    //   - should_return_empty_when_claimId_is_blank
    //   - should_find_claim_when_it_exists
    //   - should_return_empty_when_claim_not_found
}
