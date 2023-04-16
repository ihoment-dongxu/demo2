package com.example.demo.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * AES加解密
 */
public class AesUtil {

    /**
     * mysql 加密一直的SecretKeySpec
     *
     * @param key 密钥
     * @return
     */
    public static SecretKeySpec general(String key) {

        try {
            byte[] finalKey = Hex.decodeHex(key);
            return new SecretKeySpec(finalKey, "AES");
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] aesEncrypt(String password, String strKey) {
        if (password == null)
            return null;
        try {
            //生成和mysql一直的加密数据
            SecretKeySpec key = general(strKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] cleartext = password.getBytes(StandardCharsets.UTF_8);
            return cipher.doFinal(cleartext);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] aesEncrypt(byte[] password, String strKey) {
        try {
            //生成和mysql一直的加密数据
            SecretKeySpec key = general(strKey);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return cipher.doFinal(password);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static SecretKeySpec generateMySQLAESKey(final String key, final String encoding) {
        try {
            byte[] finalKey = Hex.decodeHex(key);
            return new SecretKeySpec(finalKey, "AES");
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    public static String aesDecrypt(String content, String aesKey) {
        try {
            SecretKey key = generateMySQLAESKey(aesKey, "ASCII");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cleartext = Hex.decodeHex(content.toCharArray());
            byte[] ciphertextBytes = cipher.doFinal(cleartext);

            return new String(ciphertextBytes, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeyException | IllegalBlockSizeException |
                BadPaddingException | DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesDecrypt(byte[] content, String aesKey) {
        if (content == null)
            return null;
        try {
            SecretKey key = generateMySQLAESKey(aesKey, "ASCII");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] ciphertextBytes = cipher.doFinal(content);
            return new String(ciphertextBytes, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        //手机号 71D93226E78C57803A415851C5179256
        //key : HW6VgzxwqhITadOhwzG7K6KSW7gxDs1I
        //加密
        byte[] bytes = aesEncrypt("dongxu@gmail.com", "235EA258FC6F836723508B390843C72F");
        System.out.println(Arrays.toString(bytes));
        byte[] bytes1 = {-54, 115, -26, 87, 126, 72, -75, 32, -74, 111, -70, 122, -101, 68, 69, -92, 57, -6, -99, -115, 18, -17, 77, -126, 50, 72, 121, -4, 75, -34, -89, -113};
        //解密
        String s = aesDecrypt(bytes, "235EA258FC6F836723508B390843C72F");
        String s1 = aesDecrypt(bytes1, "235EA258FC6F836723508B390843C72F");
        System.out.println(s);
        System.out.println(s1);
    }

}