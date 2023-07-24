package com.example.demo.practice.asp;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 声明引介增强
 *
 * @author dongxu
 * @create 2023-07-24 下午4:52
 */
@Aspect
public class IntroductionAspect {

    /**
     * 引介增强，让目标类默认实现某个接口功能
     */
    @DeclareParents(value = "com.example.demo.practice.asp.TargetImpl", defaultImpl = Logger.class)
    private Loggable loggable;

}
