package com.example.demo.utils;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

import java.util.Random;

/**
 * ID生成工具
 *
 * @author dongxu
 * @create 2022-10-21 上午10:16
 */
public class IdUtil {

    /**
     * 参与生成的字符集 除去I O 大小写
     */
    private static final char[] STR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static final char[] STR_2 = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static final int SIZE = 12;
    private static final int REQUEST_ID_SIZE = 20;

    private static final Random RANDOM = new Random();

    /**
     * 生成优惠券码
     *
     * @return
     */
    public static String generateCouponCode() {
        return NanoIdUtils.randomNanoId(RANDOM, STR, SIZE);
    }

    /**
     * 生成指定位数的优惠券码
     *
     * @return
     */
    public static String generateCouponCode(int size) {
        return com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId(RANDOM, STR, size);
    }

    /**
     * 生成指定位数,指定字符集的优惠券码
     *
     * @return
     */
    public static String generateCouponCode(int size, char[] str) {
        return NanoIdUtils.randomNanoId(RANDOM, str, size);
    }

    public static String generateRequestId() {
        return NanoIdUtils.randomNanoId(RANDOM, STR_2, REQUEST_ID_SIZE);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generateCouponCode());
        }
        System.out.println("--------------------------------");
        for (int i = 0; i < 10; i++) {
            System.out.println(generateRequestId());
        }
    }

}
