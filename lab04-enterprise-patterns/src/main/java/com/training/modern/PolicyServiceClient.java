package com.training.modern;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * ============================================================
 * LAB 04 PART C (Optional): PolicyServiceClient
 * ============================================================
 *
 * This is a stub for a Feign client that calls a remote PolicyService REST API.
 *
 * YOUR TASK: Use Copilot to convert this stub into a proper
 * Spring Cloud OpenFeign client interface.
 *
 * The remote PolicyService runs at: ${policy.service.url}
 * (configured in application.properties)
 *
 * Endpoints to call:
 *   GET  /api/policies/{id}         → returns PolicyResponse
 *   POST /api/policies              → takes PolicyRequest, returns PolicyResponse
 *   PUT  /api/policies/{id}         → takes PolicyRequest, returns PolicyResponse
 *   DELETE /api/policies/{id}       → no body, returns void
 *
 * Copilot prompt to use (copy into Eclipse above this class):
 *   // Convert this stub into a Spring Cloud OpenFeign @FeignClient interface
 *   // named "policy-service" pointing to ${policy.service.url}
 *   // with methods: getPolicy(Long id), createPolicy(PolicyRequest dto),
 *   // updatePolicy(Long id, PolicyRequest dto), deletePolicy(Long id)
 * ============================================================
 */
// TODO: Use Copilot to add @FeignClient annotation here
public class PolicyServiceClient {

    // TODO: Use Copilot to replace these stub methods with proper Feign interface methods

    public PolicyResponse getPolicy(Long id) {
        // STUB — Copilot will replace with Feign @GetMapping
        throw new UnsupportedOperationException("Replace with Feign client via Copilot");
    }

    public PolicyResponse createPolicy(PolicyRequest request) {
        // STUB — Copilot will replace with Feign @PostMapping
        throw new UnsupportedOperationException("Replace with Feign client via Copilot");
    }

    // ─── DTO STUBS (Copilot will complete) ────────────────────────────────────

    /**
     * Request DTO for creating/updating a policy.
     * TODO: Ask Copilot to add validation annotations and a builder.
     */
    public static class PolicyRequest {
        private String customerId;
        private String policyType;   // AUTO, HOME, LIFE
        private BigDecimal premium;
        private LocalDate startDate;
        private LocalDate endDate;

        // TODO: Copilot will add getters, setters, validation annotations
    }

    /**
     * Response DTO returned from PolicyService.
     * TODO: Ask Copilot to convert this to a Java 17 record.
     */
    public static class PolicyResponse {
        private Long policyId;
        private String customerId;
        private String policyType;
        private BigDecimal premium;
        private String status;
        private LocalDate startDate;
        private LocalDate endDate;

        // TODO: Copilot will convert this to a record in Part B modernization
    }
}
