package com.training;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * LAB 06 STARTER: ClaimProcessorService
 *
 * This service has 6 public methods but only 2 basic tests in ClaimProcessorServiceTest.java.
 *
 * YOUR TASK (Part A):
 *   Use Copilot to generate a full JUnit 5 test suite covering:
 *   - All 6 methods
 *   - Happy path, null inputs, empty lists, boundary values, exception paths
 *   - Target: 15+ test methods
 */
@Service
public class ClaimProcessorService {

    private static final BigDecimal MAX_AUTO_CLAIM = new BigDecimal("50000.00");
    private static final BigDecimal MAX_HOME_CLAIM = new BigDecimal("200000.00");
    private static final BigDecimal MAX_LIFE_CLAIM = new BigDecimal("500000.00");
    private static final double FRAUD_RISK_THRESHOLD = 0.75;

    /**
     * Validates and processes a claim submission.
     * @throws IllegalArgumentException if claim is null or amount is not positive
     * @throws ClaimLimitExceededException if amount exceeds the policy type limit
     */
    public ProcessedClaim processClaim(ClaimSubmission claim) {
        if (claim == null) {
            throw new IllegalArgumentException("Claim cannot be null");
        }
        if (claim.amount() == null || claim.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Claim amount must be positive");
        }
        BigDecimal limit = getClaimLimit(claim.policyType());
        if (claim.amount().compareTo(limit) > 0) {
            throw new ClaimLimitExceededException(
                "Claim amount " + claim.amount() + " exceeds limit " + limit + " for type " + claim.policyType()
            );
        }
        BigDecimal adjustedAmount = claim.amount().setScale(2, RoundingMode.HALF_UP);
        return new ProcessedClaim(claim.claimId(), adjustedAmount, ClaimStatus.APPROVED, LocalDate.now());
    }

    /**
     * Returns the maximum claim amount allowed for a given policy type.
     * @throws IllegalArgumentException if policyType is null or unrecognized
     */
    public BigDecimal getClaimLimit(String policyType) {
        if (policyType == null) {
            throw new IllegalArgumentException("Policy type cannot be null");
        }
        return switch (policyType.toUpperCase()) {
            case "AUTO"  -> MAX_AUTO_CLAIM;
            case "HOME"  -> MAX_HOME_CLAIM;
            case "LIFE"  -> MAX_LIFE_CLAIM;
            default -> throw new IllegalArgumentException("Unknown policy type: " + policyType);
        };
    }

    /**
     * Calculates a fraud risk score (0.0 to 1.0) based on claim characteristics.
     * Returns 0.0 for null input.
     * High frequency (> 3 recent claims) adds 0.3 to score.
     * Large amount (> 80% of policy limit) adds 0.4 to score.
     * Score is capped at 1.0.
     */
    public double calculateFraudRiskScore(ClaimSubmission claim, int recentClaimsCount) {
        if (claim == null) return 0.0;
        double score = 0.0;
        if (recentClaimsCount > 3) {
            score += 0.3;
        }
        try {
            BigDecimal limit = getClaimLimit(claim.policyType());
            BigDecimal eightyPercent = limit.multiply(new BigDecimal("0.8"));
            if (claim.amount().compareTo(eightyPercent) > 0) {
                score += 0.4;
            }
        } catch (IllegalArgumentException ignored) {
            score += 0.2; // Unknown policy type adds some risk
        }
        return Math.min(score, 1.0);
    }

    /**
     * Returns true if the claim should be flagged for manual review.
     * Flagged if: fraud score >= FRAUD_RISK_THRESHOLD OR amount >= $25,000
     */
    public boolean requiresManualReview(ClaimSubmission claim, int recentClaimsCount) {
        if (claim == null) return false;
        double fraudScore = calculateFraudRiskScore(claim, recentClaimsCount);
        boolean highFraudRisk = fraudScore >= FRAUD_RISK_THRESHOLD;
        boolean largeAmount = claim.amount() != null &&
                              claim.amount().compareTo(new BigDecimal("25000.00")) >= 0;
        return highFraudRisk || largeAmount;
    }

    /**
     * Calculates total value of a list of processed claims.
     * Returns BigDecimal.ZERO for null or empty list.
     * Ignores null entries in the list.
     */
    public BigDecimal calculateTotalClaimsValue(List<ProcessedClaim> claims) {
        if (claims == null || claims.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return claims.stream()
                     .filter(c -> c != null && c.approvedAmount() != null)
                     .map(ProcessedClaim::approvedAmount)
                     .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Finds a claim by ID from a list of processed claims.
     * Returns empty Optional if list is null, empty, or claim not found.
     */
    public Optional<ProcessedClaim> findClaimById(List<ProcessedClaim> claims, String claimId) {
        if (claims == null || claimId == null || claimId.isBlank()) {
            return Optional.empty();
        }
        return claims.stream()
                     .filter(c -> c != null && claimId.equals(c.claimId()))
                     .findFirst();
    }

    // ─── DOMAIN RECORDS AND ENUMS ─────────────────────────────────────────────

    public record ClaimSubmission(String claimId, String policyType, BigDecimal amount) {}

    public record ProcessedClaim(String claimId, BigDecimal approvedAmount,
                                 ClaimStatus status, LocalDate processedDate) {}

    public enum ClaimStatus { APPROVED, REJECTED, PENDING_REVIEW }

    public static class ClaimLimitExceededException extends RuntimeException {
        public ClaimLimitExceededException(String message) { super(message); }
    }
}
