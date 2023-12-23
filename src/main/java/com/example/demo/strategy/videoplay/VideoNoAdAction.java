package com.example.demo.strategy.videoplay;

/**
 * 视频免广告服务
 *
 * @author DX
 */
public class VideoNoAdAction implements VideoPayAction {
    @Override
    public void action() {
        System.out.println("您已开通视频免广告服务");
    }
}
