package dev.patika.schoolmanagementsystem.core.utils;

import java.math.BigDecimal;

public class SalaryHelper {

    public static BigDecimal increaseByPercent(BigDecimal number, double percent) {
        return BigDecimals.increaseByPercent(number, percent).max(BigDecimal.ZERO);
    }

}
