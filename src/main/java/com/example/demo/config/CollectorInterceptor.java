package com.example.demo.config;

import cn.hutool.extra.servlet.ServletUtil;
import com.example.demo.pojo.common.RequestInfo;
import com.example.demo.utils.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 信息收集拦截器
 *
 * @author dongxu
 * @create 2023-08-22 下午10:43
 */
@Order(1)
@Slf4j
@Component
public class CollectorInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestInfo info = new RequestInfo();
        // todo 获取uid
        info.setUid(1L);
        info.setIp(ServletUtil.getClientIP(request));
        // 放入上下文
        RequestHolder.set(info);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
    }
}
