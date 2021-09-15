package dev.patika.schoolmanagementsystem.core.utils;

import java.math.BigDecimal;

public class BigDecimals {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal getPercent(BigDecimal number, double percent) {
        return number.multiply(BigDecimal.valueOf(percent)).divide(ONE_HUNDRED);
    }

    public static BigDecimal increaseByPercent(BigDecimal number, double percent) {
        return number.add(getPercent(number, percent));
    }

}
