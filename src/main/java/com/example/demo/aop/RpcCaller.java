package com.example.demo.aop;

/**
 * @author dongxu
 * @create 2024-12-23 12:16
 */
public interface RpcCaller {
    <T> T call(String serviceName, String methodName, Object[] args, Class<T> returnType);
}
