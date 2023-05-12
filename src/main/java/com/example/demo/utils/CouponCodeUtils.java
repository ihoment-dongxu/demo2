package com.example.demo.utils;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.util.Random;

/**
 * @author dongxu
 * @create 2022-10-21 上午10:16
 */
public class CouponCodeUtils {

    /**
     * 参与生成的字符集 除去I O 大小写
     */
    private static final char[] STR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static final int SIZE = 12;

    private static final Random random = new Random();

    /**
     * 生成优惠券码
     *
     * @return
     */
    public static String generateCouponCode() {
        return NanoIdUtils.randomNanoId(random, STR, SIZE);
    }

    /**
     * 生成指定位数的优惠券码
     *
     * @return
     */
    public static String generateCouponCode(int size) {
        return NanoIdUtils.randomNanoId(random, STR, size);
    }

    /**
     * 生成指定位数,指定字符集的优惠券码
     *
     * @return
     */
    public static String generateCouponCode(int size, char[] str) {
        return NanoIdUtils.randomNanoId(random, str, size);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generateCouponCode());
        }
    }

}
