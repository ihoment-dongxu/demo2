package com.example.demo.decorator;

import com.example.demo.strategy.videoplay.VideoPayAction;

import java.util.List;

/**
 * 自选套餐1
 * @author DX
 */
public class DiyOneVideoPlayDecorator extends VideoPlayDecorator {

    private final List<VideoPayAction> videoPayActions;

    public DiyOneVideoPlayDecorator(VideoPlay videoPlay, List<VideoPayAction> videoPayActions) {
        super(videoPlay);
        this.videoPayActions = videoPayActions;
    }

    @Override
    public void play() {
        super.play();
        System.out.println("自选会员1套餐：");
        // 会员自选服务
        videoPayActions.forEach(VideoPayAction::action);
    }
}
