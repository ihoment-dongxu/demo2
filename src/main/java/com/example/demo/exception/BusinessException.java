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

    private ErrorEnum errorEnum;

    public BusinessException(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public BusinessException(ErrorEnum errorEnum, String msg) {
        super(msg);
        this.errorEnum = errorEnum;
        this.msg = msg;
    }

    public BusinessException(String msg){
        this.msg = msg;
    }

}
