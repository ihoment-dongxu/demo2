package com.example.demo.utils;

import org.thymeleaf.util.ArrayUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * BigDecimal工具类
 */
public class BigDecimalUtils {
    private static final int DEFAULT_SCALE = 10;
    public static final BigDecimal HUNDRED = new BigDecimal(100);
    public static final BigDecimal CENTI = new BigDecimal("0.01");

    public BigDecimalUtils() {
    }

    public static boolean isEmpty(BigDecimal bd) {
        return bd == null || bd.doubleValue() == 0.0D;
    }

    public static boolean isZeroOrNull(BigDecimal val) {
        return val == null || equals(val, BigDecimal.ZERO);
    }

    public static boolean isAnyEmpty(BigDecimal... bds) {
        if (ArrayUtils.isEmpty(bds)) {
            return true;
        } else {

            for (BigDecimal bd : bds) {
                if (isEmpty(bd)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static BigDecimal onlyDivide(BigDecimal numerator, BigDecimal denominator) {
        return isAnyEmpty(numerator, denominator) ? BigDecimal.ZERO : numerator.divide(denominator, MathContext.DECIMAL128);
    }

    public static BigDecimal divideOrReturnNull(Number numeratorNumber, Number denominatorNumber) {
        return divideOrReturnNull((Number)numeratorNumber, (Number)denominatorNumber, 10);
    }

    public static BigDecimal divideOrReturnNull(BigDecimal numerator, BigDecimal denominator) {
        return divideOrReturnNull((BigDecimal)numerator, (BigDecimal)denominator, 10);
    }

    public static BigDecimal divideOrReturnNull(Number numeratorNumber, Number denominatorNumber, int scale) {
        BigDecimal numeratorDouble = parse2BigDecimal(numeratorNumber);
        BigDecimal denominatorDouble = parse2BigDecimal(denominatorNumber);
        return divideOrReturnNull(numeratorDouble, denominatorDouble, scale);
    }

    public static BigDecimal divideOrReturnNull(BigDecimal numerator, BigDecimal denominator, int scale) {
        if (numerator != null && numerator.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        } else {
            return numerator != null && denominator != null && denominator.compareTo(BigDecimal.ZERO) != 0 ? numerator.divide(denominator, scale, RoundingMode.HALF_EVEN) : null;
        }
    }

    public static BigDecimal valueOf(String val, BigDecimal elseVal) {
        try {
            return new BigDecimal(val);
        } catch (Exception var3) {
            if (elseVal == null) {
                throw new RuntimeException(var3);
            } else {
                return elseVal;
            }
        }
    }

    public static BigDecimal divideOrReturnZero(Number numeratorNumber, Number denominatorNumber) {
        BigDecimal bigDecimal = divideOrReturnNull((Number)numeratorNumber, (Number)denominatorNumber, 10);
        return bigDecimal == null ? BigDecimal.ZERO : bigDecimal;
    }

    public static BigDecimal parse2BigDecimal(Number numeratorNumber) {
        if (numeratorNumber == null) {
            return null;
        } else if (numeratorNumber instanceof BigDecimal) {
            return (BigDecimal)numeratorNumber;
        } else {
            double doubleValue = numeratorNumber.doubleValue();
            return BigDecimal.valueOf(doubleValue);
        }
    }


    public static boolean equals(BigDecimal v1, BigDecimal v2) {
        if (Objects.equals(v1, v2)) {
            return true;
        } else if (v1 != null && v2 != null) {
            return v1.compareTo(v2) == 0;
        } else {
            return false;
        }
    }

    public static BigDecimal onlyDivideWithPrecision(BigDecimal numerator, BigDecimal denominator, int scale, RoundingMode roundingMode) {
        return isAnyEmpty(numerator, denominator) ? BigDecimal.ZERO : numerator.divide(denominator, scale, roundingMode);
    }

    public static BigDecimal addIgnoreNull(BigDecimal addend, BigDecimal target) {
        if (null == addend && null == target) {
            return BigDecimal.ZERO;
        } else if (null == addend) {
            return target;
        } else {
            return null == target ? addend : addend.add(target);
        }
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal... b) {
        if (null == a) {
            a = BigDecimal.ZERO;
        }

        BigDecimal result = a;

        for(int i = 0; i < b.length; ++i) {
            if (null == b[i]) {
                b[i] = BigDecimal.ZERO;
            }
            result = result.multiply(b[i]);
        }

        return result;
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal... b) {
        if (null == a) {
            a = BigDecimal.ZERO;
        }

        BigDecimal result = a;

        for(int i = 0; i < b.length; ++i) {
            if (null == b[i]) {
                b[i] = BigDecimal.ZERO;
            }
            result = result.subtract(b[i]);
        }

        return result;
    }
}
