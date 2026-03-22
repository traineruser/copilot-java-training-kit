# Lab 01 — Prompt Reference

Copy these prompts directly into Eclipse or Copilot Chat.

---

## calculateTotal()

### Strategy 1 — Comment-Driven (Vague)
```java
// calculate total
```

### Strategy 1 — Comment-Driven (Better)
```java
// Calculate the total price for all items in this order, applying a percentage discount
```

### Strategy 2 — Intent Block
```java
/*
 * Calculate order total.
 * Input: List<OrderItem> items — each item has unitPrice (BigDecimal) and quantity (int)
 * Input: Double discountPercentage — 0.0 to 1.0 (e.g. 0.10 = 10% off). Null = no discount.
 * Output: BigDecimal — final total after discount, or ZERO if items is null/empty
 * Logic: sum(item.unitPrice * item.quantity) * (1 - discountPercentage)
 */
```

### Strategy 3 — Chat Prompt
```
Generate a Java method called calculateTotal that:
- Takes a List<OrderItem> (each has BigDecimal unitPrice and int quantity) and a Double discountPercentage
- Sums all item prices (unitPrice * quantity)
- Applies the discount percentage (0.0 to 1.0)
- Returns BigDecimal.ZERO if items is null or empty
- Treats null discountPercentage as 0
- Returns the final total as BigDecimal
```

---

## applyDiscount()

### Strategy 1
```java
// apply discount to total
```

### Strategy 2
```java
/*
 * Apply a percentage discount to a BigDecimal total.
 * Input: BigDecimal total, double discountPercentage (must be 0.0 to 1.0)
 * Output: BigDecimal — discounted amount rounded to 2dp (HALF_UP)
 * Validation: throw IllegalArgumentException if discountPercentage < 0 or > 1
 * Edge case: return ZERO if total is null
 */
```

### Strategy 3 — Chat Prompt
```
Write a Java method applyDiscount(BigDecimal total, double discountPercentage) that:
- Validates discountPercentage is between 0.0 and 1.0, throws IllegalArgumentException if not
- Multiplies total by (1 - discountPercentage)
- Rounds to 2 decimal places using RoundingMode.HALF_UP
- Returns BigDecimal.ZERO if total is null
```

---

## processOrder()

### Strategy 2
```java
/*
 * Process an Order: validate, calculate total, update status.
 * Input: Order order — must not be null, must have at least 1 item
 * Steps:
 *   1. Validate: throw IllegalArgumentException if order or items null/empty
 *   2. Calculate total using calculateTotal (no discount)
 *   3. Set order.totalAmount to calculated total
 *   4. Set order.status to "PROCESSING"
 *   5. Set order.createdAt to LocalDateTime.now()
 * Output: the updated Order object
 */
```

### Strategy 3 — Chat Prompt
```
Implement processOrder(Order order) in OrderService that:
1. Throws IllegalArgumentException if order is null or has no items
2. Calls calculateTotal(order.getItems(), null) to get the total
3. Sets the total on the order with order.setTotalAmount(total)
4. Sets order status to "PROCESSING"
5. Sets createdAt to LocalDateTime.now()
6. Returns the modified order
```

---

## PromptStrategies.md Template

Use this to document your results:

| Method | Strategy | Prompt Used (brief) | Result Quality (1-5) | Notes |
|--------|----------|---------------------|---------------------|-------|
| calculateTotal | 1 — Comment-Driven | // calculate total | ? | |
| calculateTotal | 2 — Intent Block | /* Calculate order total... */ | ? | |
| calculateTotal | 3 — Chat-Driven | Generate a Java method... | ? | |
| applyDiscount | 1 | | | |
| applyDiscount | 2 | | | |
| applyDiscount | 3 | | | |
| processOrder | 1 | | | |
| processOrder | 2 | | | |
| processOrder | 3 | | | |

**Team Recommendation:** Which strategy works best for which type of method?
