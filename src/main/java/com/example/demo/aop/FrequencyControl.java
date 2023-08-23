package com.example.demo.aop;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 频控注解
 *
 * @author dongxu
 * @create 2023-08-22 下午9:13
 */
@Repeatable(FrequencyControlContainer.class) // 可重复
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrequencyControl {

    /**
     * key的前缀,默认取方法全限定名
     *
     * @return
     */
    String prefixKey() default "";

    /**
     * 频控对象，默认el表达指定具体的频控对象
     * 对于ip 和uid模式，需要是http入口的对象，保证RequestHolder里有值
     *
     * @return 对象
     */
    Target target() default Target.EL;

    /**
     * springEl 表达式，target=EL必填
     *
     * @return 表达式
     */
    String spEl() default "";


    /**
     * 频控时间范围，默认单位秒
     *
     * @return 时间范围
     */
    int time();

    /**
     * 频控时间单位，默认秒
     *
     * @return 单位
     */
    TimeUnit unit() default TimeUnit.SECONDS;

    /**
     * 单位时间内最大访问次数
     *
     * @return 次数
     */
    int count();

    enum Target {
        UID, IP, EL
    }
}
