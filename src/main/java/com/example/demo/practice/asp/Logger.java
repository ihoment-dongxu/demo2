package com.example.demo.practice.asp;

/**
 * 日志引介实现类
 *
 * @author dongxu
 * @create 2023-07-24 下午4:51
 */
public class Logger implements Loggable {
    @Override
    public void log() {
        System.out.println("Logging method execution...");
    }
}
