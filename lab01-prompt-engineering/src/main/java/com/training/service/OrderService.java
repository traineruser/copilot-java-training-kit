package com.training.service;

import com.training.model.Order;
import com.training.model.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * ============================================================
 * LAB 01 STARTER: OrderService
 * ============================================================
 *
 * This class has 3 methods with TODO placeholders.
 *
 * YOUR TASK: Use GitHub Copilot to implement each method using
 * 3 different prompt strategies. For each method, try all 3:
 *
 *   Strategy 1 - Comment-Driven:
 *     Write a single // comment above the method
 *
 *   Strategy 2 - Intent Block:
 *     Write a multi-line comment describing inputs/outputs/constraints
 *
 *   Strategy 3 - Chat-Driven:
 *     Open Copilot Chat and describe the requirement in plain English
 *
 * IMPORTANT: Delete Copilot's suggestion between each strategy attempt
 * so you get a fresh result. Document everything in PromptStrategies.md.
 * ============================================================
 */
@Service
public class OrderService {

    /**
     * METHOD 1: calculateTotal
     *
     * Requirements:
     * - Sum up: each item's (unitPrice * quantity)
     * - Apply discountPercentage (0.0 to 1.0 — e.g. 0.10 = 10% off)
     * - Return the final total as BigDecimal
     * - Return BigDecimal.ZERO if items is null or empty
     * - Treat null discountPercentage as zero (no discount)
     *
     * TRY STRATEGY 1: Type a vague comment just above this method
     * TRY STRATEGY 2: Replace with a detailed intent block
     * TRY STRATEGY 3: Open Chat and ask for this implementation
     */
    // PROMPT STRATEGY 1: // calculate total
    // PROMPT STRATEGY 2: (replace below with full intent block)
    // PROMPT STRATEGY 3: (use Copilot Chat)
    public BigDecimal calculateTotal(List<OrderItem> items, Double discountPercentage) {
        // TODO: Replace with Copilot-generated implementation
        return null;
    }

    /**
     * METHOD 2: applyDiscount
     *
     * Requirements:
     * - Apply the given percentage discount to the total
     * - discountPercentage must be between 0.0 and 1.0 (inclusive)
     * - Throw IllegalArgumentException if out of range
     * - Round result to 2 decimal places (RoundingMode.HALF_UP)
     * - Return BigDecimal.ZERO if total is null
     */
    public BigDecimal applyDiscount(BigDecimal total, double discountPercentage) {
        // TODO: Replace with Copilot-generated implementation
        return null;
    }

    /**
     * METHOD 3: processOrder
     *
     * Requirements:
     * - Validate order is not null
     * - Validate it has at least 1 item
     * - Calculate total using calculateTotal (no discount)
     * - Set the total on the order object
     * - Set status to "PROCESSING"
     * - Set createdAt to LocalDateTime.now()
     * - Return the updated order
     * - Throw IllegalArgumentException if order or items are null/empty
     */
    public Order processOrder(Order order) {
        // TODO: Replace with Copilot-generated implementation
        return null;
    }
}
