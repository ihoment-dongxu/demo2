package com.example.demo.practice.log;

import lombok.extern.slf4j.Slf4j;

/**
 * @author dongxu
 * @create 2023-04-10 下午9:52
 */
@Slf4j
public class LogDemoController {
    public static void logLevel(){
        log.error("这个错误很严重，阻碍程序运行，需要告警！");
        log.warn("这里不应该发生错误，但是程序可以向下继续运行");
        log.info("没啥事，只是想打日志");
        log.debug("debug模式，打日志");
        log.trace("追踪使用");

        // 正确的使用debug日志
        if (log.isDebugEnabled()) {
            log.debug("正确的使用debug日志");
        }
        try {
            System.out.println(1/0);
        }catch (Exception e){
            log.error("这里发生了错误");
        }

    }

    public static void main(String[] args) {
        logLevel();
    }
}
