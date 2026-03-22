@claims @submission
Feature: Insurance Claim Submission
  As a customer service agent
  I want to submit and manage insurance claims
  So that customers can receive compensation for covered incidents

  Background:
    Given the Claims API is running
    And a valid policy number "POL-2024-001" exists in the system

  # ─── HAPPY PATH SCENARIOS ────────────────────────────────────────────────────

  @smoke
  Scenario: Successfully submit an auto collision claim
    Given I have a valid claim request with the following details:
      | policyNumber  | POL-2024-001     |
      | claimType     | AUTO_COLLISION   |
      | description   | Vehicle rear-ended at traffic light causing bumper damage |
      | incidentDate  | 2024-01-15       |
      | claimAmount   | 3500.00          |
    When I submit the claim to POST /api/claims
    Then the response status code should be 201
    And the response body should contain a non-null claimId
    And the claim status should be "SUBMITTED"
    And the submittedAt timestamp should be set

  Scenario: Retrieve an existing claim by ID
    Given a claim with ID "CLM-001" exists in the system
    When I send a GET request to /api/claims/CLM-001
    Then the response status code should be 200
    And the response body should contain claimId "CLM-001"
    And the response body should contain a valid policyNumber

  Scenario: List all claims returns correct count
    Given the following claims exist in the system:
      | claimId | policyNumber  | claimType      | status        |
      | CLM-001 | POL-2024-001  | AUTO_COLLISION | SUBMITTED     |
      | CLM-002 | POL-2024-001  | HOME_FIRE      | UNDER_REVIEW  |
      | CLM-003 | POL-2024-002  | MEDICAL        | APPROVED      |
    When I send a GET request to /api/claims
    Then the response status code should be 200
    And the response should contain 3 claims

  Scenario: Update an existing claim description
    Given a claim with ID "CLM-001" exists with status "SUBMITTED"
    When I send a PUT request to /api/claims/CLM-001 with updated description "Updated damage description after further inspection"
    Then the response status code should be 200
    And the response body description should be "Updated damage description after further inspection"

  Scenario: Cancel an existing claim
    Given a claim with ID "CLM-001" exists with status "SUBMITTED"
    When I send a DELETE request to /api/claims/CLM-001
    Then the response status code should be 204

  # ─── VALIDATION ERROR SCENARIOS ──────────────────────────────────────────────

  @validation
  Scenario: Reject claim submission with missing policy number
    Given I have a claim request with a blank policyNumber
    When I submit the claim to POST /api/claims
    Then the response status code should be 400
    And the error response should contain field error for "policyNumber"

  Scenario: Reject claim with description too short
    Given I have a claim request with description "short"
    When I submit the claim to POST /api/claims
    Then the response status code should be 400
    And the error response should contain field error for "description"

  Scenario: Reject claim with negative claim amount
    Given I have a claim request with claimAmount -100.00
    When I submit the claim to POST /api/claims
    Then the response status code should be 400
    And the error response should contain field error for "claimAmount"

  Scenario: Reject claim with zero claim amount
    Given I have a claim request with claimAmount 0.00
    When I submit the claim to POST /api/claims
    Then the response status code should be 400
    And the error response should contain field error for "claimAmount"

  # ─── NOT FOUND SCENARIOS ─────────────────────────────────────────────────────

  @error-handling
  Scenario: Return 404 when claim does not exist
    Given no claim with ID "CLM-NONEXISTENT" exists
    When I send a GET request to /api/claims/CLM-NONEXISTENT
    Then the response status code should be 404
    And the error response message should contain "not found"

  Scenario: Return 404 when updating non-existent claim
    Given no claim with ID "CLM-NONEXISTENT" exists
    When I send a PUT request to /api/claims/CLM-NONEXISTENT
    Then the response status code should be 404

  # ─── FILTERING SCENARIOS ─────────────────────────────────────────────────────

  @filtering
  Scenario: Filter claims by status
    Given claims with statuses SUBMITTED, APPROVED, REJECTED exist
    When I send a GET request to /api/claims?status=APPROVED
    Then the response status code should be 200
    And all returned claims should have status "APPROVED"
