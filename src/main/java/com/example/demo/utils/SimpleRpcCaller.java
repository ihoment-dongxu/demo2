package com.example.demo.utils;


import com.example.demo.aop.RpcCaller;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author dongxu
 * @create 2024-12-23 12:16
 */
@Component
public class SimpleRpcCaller implements RpcCaller {

    @Override
    public <T> T call(String serviceName, String methodName, Object[] args, Class<T> returnType) {
        // 这里只是模拟RPC调用，实际应该使用RPC框架的客户端调用逻辑
        try {
            // 假设这里有一个模拟的服务对象
            Object service = getService(serviceName);
            Method method = service.getClass().getMethod(methodName,  getArgTypes(args));
            return (T) method.invoke(service,  args);
        } catch (Exception e) {
            throw new RuntimeException("RPC call failed", e);
        }
    }

    private Class[] getArgTypes(Object[] args) {
        if (args == null) {
            return new Class[0];
        }
        Class[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length;  i++) {
            argTypes[i]=args[i].getClass();
        }
        return argTypes;
    }

    private Object getService(String serviceName) {
        // 这里只是模拟获取服务对象，实际可能从服务注册中心获取
        return new Object();
    }
}