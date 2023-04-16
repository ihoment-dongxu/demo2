package com.example.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author dongxu
 * @create 2023-04-05 下午4:24
 */
public class Interceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器拦截成功，执行了Interceptor的preHandle方法。");
        System.out.println("模拟拦截器逻辑。。。。。");
        String requestURI = request.getRequestURI();
        String ip = request.getRemoteAddr();
        int port = request.getRemotePort();
        System.out.println("请求路径："+requestURI);
        System.out.println("请求ip：" + ip + ":" + port);
        // 设置为false，拦截器拦截不向下执行，true，继续执行
        return true;
    }
}
