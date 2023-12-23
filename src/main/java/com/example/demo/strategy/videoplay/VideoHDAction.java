package com.example.demo.strategy.videoplay;

/**
 * 超清HD画质
 */
public class VideoHDAction implements VideoPayAction {
    @Override
    public void action() {
        System.out.println("您已开通超清HD画质服务");
    }
}
