package com.example.demo.utils;

import com.example.demo.pojo.modle.Coupon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONUtil {

    public static <T> T parseObject(String value, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(value, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析JSON
     *
     * @param json
     * @param valueTypeRef 可以解析泛型类
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, TypeReference<T> valueTypeRef) {
        T result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            result = objectMapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 转换JSON字符串
     *
     * @param o
     * @return
     */
    public static String toJSON(Object o) {
        String json = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            json = objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static void main(String[] args) {
        String json = "";
        Coupon coupon = parseObject(json, Coupon.class);
        Coupon coupon1 = parseObject(json, new TypeReference<Coupon>() {
        });
    }
}
