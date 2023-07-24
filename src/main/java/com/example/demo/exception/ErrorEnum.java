package com.example.demo.exception;

/**
 * @author dongxu
 * @create 2023-07-05 下午9:20
 */
public interface ErrorEnum {
    /**
     * 获取异常码
     */
    int getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
