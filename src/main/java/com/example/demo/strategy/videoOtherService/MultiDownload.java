package com.example.demo.strategy.videoOtherService;

/**
 * 并行下载服务
 */
public class MultiDownload implements OtherPay{
    @Override
    public void service() {
        System.out.println("您已开通并行下载服务");
    }
}
