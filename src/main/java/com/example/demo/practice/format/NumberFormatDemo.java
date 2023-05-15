package com.example.demo.practice.format;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author dongxu
 * @create 2023-05-15 下午9:48
 */
public class NumberFormatDemo {
    public static void main(String[] args) {
        int price = 18;
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println("nf.format(price) = " + nf.format(price));

        NumberFormat nf1 = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println("nf1.format(price) = " + nf1.format(price));

        NumberFormat nf2 = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println("nf2.format(price) = " + nf2.format(price));

        NumberFormat nf3 = NumberFormat.getCurrencyInstance(Locale.GERMAN);
        System.out.println("nf3.format(price) = " + nf3.format(price));

        System.out.println("---------------Percent-----------------");

        double d = 0.5;
        nf = NumberFormat.getPercentInstance(Locale.CHINA);
        System.out.println("nf = " + nf.format(d));

        nf = NumberFormat.getPercentInstance(Locale.GERMAN);
        System.out.println("nf = " + nf.format(d));
    }
}
