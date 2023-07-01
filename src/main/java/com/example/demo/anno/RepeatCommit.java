package com.example.demo.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 重复提交验证注解
 *
 * @author dongxu
 * @create 2023-07-01 下午4:01
 */
@Inherited // 指示注解是否可以被继承
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatCommit {

    /**
     * 间隔时间（ms）默认5000ms
     */
    int interval() default 5000;

    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 提示信息
     */
    String message() default "请不要重复提交";

}
