package com.example.demo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentageUtil {
    
    public static BigDecimal calculatePercentage(BigDecimal part, BigDecimal total) {
        if (total.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return part.divide(total, 4, RoundingMode.HALF_UP)
                   .multiply(new BigDecimal("100"))
                   .setScale(2, RoundingMode.HALF_UP);
    }
}