package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务异常枚举
 *
 * @author dongxu
 * @create 2023-04-16 下午1:00
 */
@AllArgsConstructor
@Getter
public enum BusinessExceptionEnum implements ErrorEnum {
    SYSTEM_ERROR(1000, "System error"),
    ;
    private int code;

    private String message;
}
