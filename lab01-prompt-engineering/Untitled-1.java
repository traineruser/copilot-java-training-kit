// calculate total order price by summing item prices and applying discount percentage, return BigDecimal rounded to 2 decimal places
import java.math.BigDecimal;
import java.math.RoundingMode;import java.util.List; 
public class OrderCalculator {
    public static BigDecimal calculateTotalPrice(List<BigDecimal> itemPrices, BigDecimal discountPercentage) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        // Sum up item prices
        for (BigDecimal price : itemPrices) {
            totalPrice = totalPrice.add(price);
        }

        // Apply discount
        BigDecimal discountAmount = totalPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100));
        totalPrice = totalPrice.subtract(discountAmount);

        // Round to 2 decimal places
        return totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
public class BigDecimal calculateTotalPrice(List<BigDecimal> itemPrices, BigDecimal discountPercentage) {
    BigDecimal totalPrice = BigDecimal.ZERO;

    // Sum up item prices
    for (BigDecimal price : itemPrices) {
        totalPrice = totalPrice.add(price);
    }

    // Apply discount
    BigDecimal discountAmount = totalPrice.multiply(discountPercentage).divide(BigDecimal.valueOf(100));
    totalPrice = totalPrice.subtract(discountAmount);

    // Round to 2 decimal places
    return totalPrice.setScale(2, RoundingMode.HALF_UP);
}