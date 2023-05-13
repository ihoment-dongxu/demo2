package com.example.demo.config;

import com.example.demo.pojo.common.DingMessage;
import com.example.demo.utils.DingTalkUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池配置
 *
 * @author dongxu
 * @create 2023-05-13 下午4:28
 */
@EnableAsync
@Configuration
@Slf4j
public class ThreadPoolExecutorConfig {

    @Bean(name = "couponThreadPoolExecutor")
    public ThreadPoolExecutor couponThreadPoolExecutor() {
        // 自定义线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("dx-demo")
                .setUncaughtExceptionHandler((thread, throwable) -> {
                    String format = String.format("线程池执行异常：thread=%s", thread.getName());
                    log.info(format, throwable);
                    DingTalkUtil.sendDingAlarm(new DingMessage("couponThreadPoolExecutor,线程池执行异常:", format, false));
                }).build();
        int cpu = 2;
        log.info("cpu线程数：{}", cpu);
        // 核心线程数
        int corePoolSize = (int) (cpu / (1 - 0.8));
        return new ThreadPoolExecutor(corePoolSize,
                corePoolSize * 2,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(corePoolSize * 2),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
