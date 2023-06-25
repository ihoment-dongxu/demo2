package com.example.demo.pojo.constant;

import org.springframework.util.StringUtils;

/**
 * 正则检查
 *
 * @author dongxu
 * @create 2023-06-25 下午6:10
 */
public class RegularConstant {
    public static final String MOBILE_NUMBER = "^(?:\\+?86)?1(?:3\\d{3}|5[^4\\D]\\d{2}|8\\d{3}|7(?:[01356789]\\d{2}|4(?:0\\d|1[0-2]|9\\d))|9[189]\\d{2}|6[567]\\d{2}|4(?:[14]0\\d{3}|[68]\\d{4}|[579]\\d{2}))\\d{6}$";

    public static boolean isMobileNumber(String str) {
        if (!StringUtils.hasText(str)) {
            return false;
        }
        return str.matches(MOBILE_NUMBER);
    }
}
