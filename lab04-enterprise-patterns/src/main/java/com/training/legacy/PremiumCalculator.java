package com.training.legacy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * LAB 04 STARTER: PremiumCalculator (Java 8 Legacy Code)
 * ============================================================
 *
 * This class is INTENTIONALLY written in Java 8 style with known issues:
 * - Verbose null checks
 * - Anonymous inner classes
 * - Raw types
 * - Long if-else chains
 * - No use of Optional
 * - Hard-coded "magic" multipliers
 * - All calculation rules in one class (violates single responsibility)
 *
 * YOUR TASK:
 *   Part A: Apply the Strategy pattern using Copilot prompts
 *   Part B: Modernize to Java 17 (lambdas, Optional, records, switch expressions)
 *   Part C (optional): Add a Feign client stub for PolicyServiceClient
 *
 * See prompts.md for the exact Copilot comments/prompts to use.
 * ============================================================
 */
public class PremiumCalculator {

    // Raw type - Java 8 style
    @SuppressWarnings("rawtypes")
    private List discountRules = new ArrayList();

    // Magic number constants — hard to maintain
    private static final double AUTO_BASE_MULTIPLIER = 1.20;
    private static final double HOME_BASE_MULTIPLIER = 1.10;
    private static final double LIFE_BASE_MULTIPLIER = 1.35;
    private static final double YOUNG_DRIVER_SURCHARGE = 0.25;
    private static final double HIGH_RISK_SURCHARGE = 0.40;
    private static final double LOYALTY_DISCOUNT = 0.05;
    private static final double MULTI_POLICY_DISCOUNT = 0.08;

    /**
     * Calculates the premium for a given customer and policy.
     * Verbose, hard-to-read Java 8 style with many problems.
     */
    public BigDecimal calculate(Customer customer, Policy policy) {
        // Null check Java 8 style
        if (customer == null) {
            return BigDecimal.ZERO;
        }
        if (policy == null) {
            return BigDecimal.ZERO;
        }
        if (policy.getBasePremium() == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal premium = policy.getBasePremium();

        // Long if-else chain — hard to extend
        if (policy.getPolicyType() != null) {
            if (policy.getPolicyType().equals("AUTO")) {
                premium = premium.multiply(new BigDecimal(String.valueOf(AUTO_BASE_MULTIPLIER)));

                // Nested if for young driver
                if (customer.getAge() != null) {
                    if (customer.getAge() < 25) {
                        premium = premium.multiply(new BigDecimal(String.valueOf(1 + YOUNG_DRIVER_SURCHARGE)));
                    }
                }

            } else if (policy.getPolicyType().equals("HOME")) {
                premium = premium.multiply(new BigDecimal(String.valueOf(HOME_BASE_MULTIPLIER)));

                // Risk zone check
                if (customer.getRiskZone() != null) {
                    if (customer.getRiskZone().equals("HIGH")) {
                        premium = premium.multiply(new BigDecimal(String.valueOf(1 + HIGH_RISK_SURCHARGE)));
                    }
                }

            } else if (policy.getPolicyType().equals("LIFE")) {
                premium = premium.multiply(new BigDecimal(String.valueOf(LIFE_BASE_MULTIPLIER)));
            }
        }

        // Apply discounts — anonymous inner class Java 8 style
        Discountable loyaltyDiscount = new Discountable() {
            @Override
            public BigDecimal applyDiscount(BigDecimal amount, Customer c) {
                if (c.getYearsAsCustomer() != null && c.getYearsAsCustomer() >= 3) {
                    return amount.multiply(new BigDecimal(String.valueOf(1 - LOYALTY_DISCOUNT)));
                }
                return amount;
            }
        };

        Discountable multiPolicyDiscount = new Discountable() {
            @Override
            public BigDecimal applyDiscount(BigDecimal amount, Customer c) {
                if (c.getNumberOfPolicies() != null && c.getNumberOfPolicies() > 1) {
                    return amount.multiply(new BigDecimal(String.valueOf(1 - MULTI_POLICY_DISCOUNT)));
                }
                return amount;
            }
        };

        premium = loyaltyDiscount.applyDiscount(premium, customer);
        premium = multiPolicyDiscount.applyDiscount(premium, customer);

        return premium;
    }

    /**
     * Returns a summary map with raw types — Java 8 style.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getPremiumBreakdown(Customer customer, Policy policy) {
        Map breakdown = new HashMap();
        if (customer != null && policy != null) {
            breakdown.put("basePremium", policy.getBasePremium());
            breakdown.put("policyType", policy.getPolicyType());
            breakdown.put("customerId", customer.getCustomerId());
            breakdown.put("finalPremium", calculate(customer, policy));
        }
        return breakdown;
    }

    /**
     * Interface for discount rules (used via anonymous inner classes above).
     */
    interface Discountable {
        BigDecimal applyDiscount(BigDecimal amount, Customer customer);
    }

    // ─── NESTED DATA CLASSES (verbose Java 8 style) ──────────────────────────

    public static class Customer {
        private String customerId;
        private Integer age;
        private String riskZone;  // "LOW", "MEDIUM", "HIGH"
        private Integer yearsAsCustomer;
        private Integer numberOfPolicies;

        public Customer() {}

        public Customer(String customerId, Integer age, String riskZone,
                        Integer yearsAsCustomer, Integer numberOfPolicies) {
            this.customerId = customerId;
            this.age = age;
            this.riskZone = riskZone;
            this.yearsAsCustomer = yearsAsCustomer;
            this.numberOfPolicies = numberOfPolicies;
        }

        public String getCustomerId() { return customerId; }
        public void setCustomerId(String customerId) { this.customerId = customerId; }
        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
        public String getRiskZone() { return riskZone; }
        public void setRiskZone(String riskZone) { this.riskZone = riskZone; }
        public Integer getYearsAsCustomer() { return yearsAsCustomer; }
        public void setYearsAsCustomer(Integer yearsAsCustomer) { this.yearsAsCustomer = yearsAsCustomer; }
        public Integer getNumberOfPolicies() { return numberOfPolicies; }
        public void setNumberOfPolicies(Integer numberOfPolicies) { this.numberOfPolicies = numberOfPolicies; }
    }

    public static class Policy {
        private String policyId;
        private String policyType;  // "AUTO", "HOME", "LIFE"
        private BigDecimal basePremium;

        public Policy() {}

        public Policy(String policyId, String policyType, BigDecimal basePremium) {
            this.policyId = policyId;
            this.policyType = policyType;
            this.basePremium = basePremium;
        }

        public String getPolicyId() { return policyId; }
        public void setPolicyId(String policyId) { this.policyId = policyId; }
        public String getPolicyType() { return policyType; }
        public void setPolicyType(String policyType) { this.policyType = policyType; }
        public BigDecimal getBasePremium() { return basePremium; }
        public void setBasePremium(BigDecimal basePremium) { this.basePremium = basePremium; }
    }
}
