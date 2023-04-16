package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dongxu
 * @create 2023-04-16 下午1:00
 */
@AllArgsConstructor
@Getter
public enum BusinessExceptionEnum {
    SYSTEM_ERROR(1000, "System error"),
    ;
    private int code;

    private String description;
}
