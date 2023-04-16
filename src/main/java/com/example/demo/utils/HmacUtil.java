package com.example.demo.utils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Hmac加密工具
 * @author dongxu
 * @create 2023-04-04 上午10:27
 */
public class HmacUtil {

    /**
     * 实现Hmac系列的加密算法HmacSHA1、HmacMD5等
     *
     * @param input     需要加密的输入参数
     * @param key       密钥
     * @param algorithm 选择加密算法
     * @return 加密后的值
     **/
    public static String encrypt(String input, String key, String algorithm) {
        String cipher = "";
        try {
            byte[] data = key.getBytes(StandardCharsets.UTF_8);
            // 根据给定的字节数组构建一个密钥，第二个参数指定一个密钥的算法名称，生成指定算法的专属密钥
            SecretKey secretKey = new SecretKeySpec(data, algorithm);
            // 生成一个指定Mac算法的Mac对象
            Mac mac = Mac.getInstance(algorithm);
            // 用给定密钥初始化Mac对象
            mac.init(secretKey);
            byte[] text = input.getBytes(StandardCharsets.UTF_8);
            byte[] encryptByte = mac.doFinal(text);
            cipher = byte2HexStr(encryptByte);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return cipher;
    }


    /**
     * byte数组转16进制字符串
     *
     * @param bytes byte数组
     * @return hex字符串
     */
    private static String byte2HexStr(byte[] bytes) {
        StringBuilder hexStr = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(b & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexStr.append(hex);
        }
        return hexStr.toString();
    }

    /**
     * 验证密钥
     *
     * @param input      需要加密的输入参数
     * @param key        密钥
     * @param algorithm  选择加密算法
     * @param encryptStr 加密后的值
     * @return true 验证成功
     */
    private static boolean verify(String input, String key, String algorithm, String encryptStr) {
        return encryptStr.equals(encrypt(input, key, algorithm));
    }

    public static void main(String[] args) {
        String input = "dongxu";
        String key = "test";
        String algorithm = HmacEnum.HmacSHA1.name();

        String encrypt = encrypt(input, key, algorithm);
        System.out.println("输入：" + input);
        System.out.println("密钥：" + key);
        System.out.println("加密算法：" + algorithm);
        System.out.println("加密后：" + encrypt);

        boolean verify = verify(input, key, algorithm, encrypt);
        System.out.println("验证结果：" + verify);

    }

    /**
     * 加密算法
     */
    public enum HmacEnum {
        HmacSHA1,
        HmacMD5,
        HmacSHA256,
        HmacSHA512
    }
}
