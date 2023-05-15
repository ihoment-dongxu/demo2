package com.example.demo.pojo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模拟计算器，加减乘除
 *
 * @author dongxu
 * @create 2023-05-14 下午2:41
 */
@AllArgsConstructor
@Getter
public enum Operation {
    PlUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    },
    ;
    /**
     * 运算符
     */
    private final String symbol;

    /**
     * 执行算法
     *
     * @param x
     * @param y
     * @return
     */
    public abstract double apply(double x, double y);

    public static void main(String[] args) {
        double x = 10;
        double y = 8;
        double plusApply = Operation.PlUS.apply(x, y);
        System.out.println("Operation.PlUS = " + plusApply);

        double minusApply = Operation.MINUS.apply(x, y);
        System.out.println("Operation.MINUS = " + minusApply);

        double timesApply = Operation.TIMES.apply(x, y);
        System.out.println("Operation.TIMES = " + timesApply);

        double divideApply = Operation.DIVIDE.apply(x, y);
        System.out.println("Operation.DIVIDE = " + divideApply);
    }
}
