package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 公共异常枚举
 *
 * @author dongxu
 * @create 2023-07-05 下午9:21
 */
@Getter
@AllArgsConstructor
public enum CommonExceptionEnum implements ErrorEnum {

    SYSTEM_ERROR(-1, "系统出小差儿了～请稍后再试！"),
    PARAM_VALID(-2, "参数校验失败"),
    FREQUENCY_LIMIT(-3, "请求太频繁了，请稍后再试哦~~"),
    LOCK_LIMIT(-4, "请求太频繁了，请稍后再试哦~~"),
    ;
    private int code;

    private String message;

}
