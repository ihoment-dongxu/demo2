package com.example.demo.decorator;

import com.example.demo.strategy.videoOtherService.OtherPay;
import com.example.demo.strategy.videoplay.VideoPayAction;

import java.util.List;

public class DiyTwoVideoPlayDecorator extends VideoPlayDecorator {
    private List<OtherPay> otherPays;

    public DiyTwoVideoPlayDecorator(VideoPlay videoPlay, List<OtherPay> otherPays) {
        super(videoPlay);
        this.otherPays = otherPays;
    }

    @Override
    public void play(){
        super.play();
        System.out.println("自选会员2套餐：");
        // 会员自选服务
        otherPays.forEach(OtherPay::service);
    }
}
