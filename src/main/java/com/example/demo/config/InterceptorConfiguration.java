package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * 配置所有拦截器
 *
 * @author dongxu
 * @create 2023-04-05 下午3:21
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    private CollectorInterceptor collectorInterceptor;

    /**
     * 解决跨域请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 支持跨域的接口url
        registry.addMapping("/**")
                .allowedOriginPatterns("http://localhost:8080")
                // 前端哪些域名允许跨域
//                .allowedOrigins("*")
                // 需要带cookie时，设置为true，就会把cookie带上
                .allowCredentials(true)
                // 指的是允许哪些方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // cookie失效时间
                .maxAge(1000)
        ;
    }

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将自己写的拦截器注入Spring容器
        InterceptorRegistration registration = registry.addInterceptor(new Interceptor());
        // 设置要拦截的路径
        registration.addPathPatterns("/portal/ping")
                // 设置放行的路径 （不走拦截器）
                .excludePathPatterns("/portal/pong");

        registry.addInterceptor(collectorInterceptor)
                .addPathPatterns("/**");
    }
}
