package com.example.demo.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dongxu
 * @create 2023-04-16 下午12:58
 */
@Getter
@Setter
public class BusinessException extends RuntimeException{
    private String msg;

    private BusinessExceptionEnum exceptionEnum;

    public BusinessException(BusinessExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public BusinessException(BusinessExceptionEnum exceptionEnum, String msg) {
        super(msg);
        this.exceptionEnum = exceptionEnum;
        this.msg = msg;
    }

    public BusinessException(String msg){
        this.msg = msg;
    }

}
