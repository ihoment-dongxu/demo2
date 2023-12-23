package com.example.demo.strategy.videoOtherService;

/**
 * vip客服一对一服务
 */
public class VipAfterSales implements OtherPay {
    @Override
    public void service() {
        System.out.println("您已开通专享vip客服一对一服务");
    }
}
