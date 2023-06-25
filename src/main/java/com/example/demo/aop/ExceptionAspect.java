package com.example.demo.aop;

import com.example.demo.exception.BusinessException;
import com.example.demo.utils.DingTalkUtil;
import com.example.demo.utils.JSONUtil;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * controller 异常告警
 *
 * @author dongxu
 * @create 2023-06-25 上午11:49
 */
@Slf4j
@Component
@Aspect
public class ExceptionAspect {

    private String profiles = "loc";

    @Pointcut("execution(* com.example.demo.controller..*.*(..))")
    public void allController() {
    }

    @Around("allController()")
    public Object alertEmail(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        long start = System.currentTimeMillis();
        Object o;
        Throwable throwable = null;
        try {
            o = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        } finally {
            // 告警
            this.errorAlarm(joinPoint, throwable);
        }
        assert response != null;
        // 向Header中设置接口实际执行时间
        response.addHeader("Response-Time-Mills", String.valueOf(System.currentTimeMillis() - start));
        return o;
    }

    private void errorAlarm(ProceedingJoinPoint joinPoint, Throwable throwable) {
        if (Objects.nonNull(throwable) && checkBizException(throwable)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes == null) {
                return;
            }
            HttpServletRequest request = attributes.getRequest();
            // 请求url
            String requestUrl = request.getRequestURL().toString();
            log.info("request url: {}", requestUrl);
            String requestHeader = this.getRequestHeader(request);
            log.info("request header: {}", requestHeader);
            String requestParam = this.getRequestParam(joinPoint);
            log.info("request params: {}", requestParam);
            String stackTrace = throwable.getMessage();
            stackTrace = StringUtils.hasText(stackTrace) ? stackTrace : this.getBriefStackTrace(throwable);
            DingTalkUtil.sendDingAlarm(profiles, requestUrl, requestHeader, requestParam, stackTrace);
        }
    }

    public static final int BRIEF_STACK_TRACE = 500;

    private String getBriefStackTrace(Throwable throwable) {
        String stack = Throwables.getStackTraceAsString(throwable);
        return stack.length() > BRIEF_STACK_TRACE ? stack.substring(0, BRIEF_STACK_TRACE) : stack;
    }

    private String getRequestParam(ProceedingJoinPoint joinPoint) {
        // 构造参数组集合
        List<Object> argList = new ArrayList<>();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            // request/response 无法使用toJSON
            if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse || arg instanceof MultipartFile) {
                continue;
            }
            argList.add(JSONUtil.toJSON(arg));
        }
        return JSONUtil.toJSON(argList);
    }

    private String getRequestHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, Object> headers = new HashMap<>(16);
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, request.getHeader(name));
        }
        return JSONUtil.toJSON(headers);
    }

    /**
     * Determine whether it is a business exception
     *
     * @param throwable 异常
     * @return true is business exception
     */
    private boolean checkBizException(Throwable throwable) {
        return throwable instanceof BusinessException;
    }

}
