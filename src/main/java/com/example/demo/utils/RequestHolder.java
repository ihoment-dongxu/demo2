package com.example.demo.utils;

import com.example.demo.pojo.common.RequestInfo;

/**
 * 请求上下文
 *
 * @author dongxu
 * @create 2023-08-22 下午9:44
 */
public class RequestHolder {

    private static final ThreadLocal<RequestInfo> threadLocal = new ThreadLocal<>();

    public static void set(RequestInfo requestInfo) {
        threadLocal.set(requestInfo);
    }

    public static RequestInfo get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
