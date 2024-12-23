package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RpcAspect {
    private static final Logger logger = LoggerFactory.getLogger(RpcAspect.class);

    @Around("execution(* com.example.demo.aop.RpcCaller(..))")
    public Object aroundRpcCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String serviceName = (String) joinPoint.getArgs()[0];
        String methodName = (String) joinPoint.getArgs()[1];
        Object[] args = (Object[]) joinPoint.getArgs()[2];
        try {
            logger.info("RPC  request: service={}, method={}, args={}", serviceName, methodName, args);
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("RPC  response: result={}, time cost={}ms", result, endTime - startTime);
            return result;
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            logger.error("RPC  call failed: service={}, method={}, args={}, time cost={}ms", serviceName, methodName, args, endTime - startTime, e);
            throw e;
        }
    }
}