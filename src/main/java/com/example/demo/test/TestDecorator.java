package com.example.demo.test;

import com.example.demo.decorator.DiyOneVideoPlayDecorator;
import com.example.demo.decorator.DiyTwoVideoPlayDecorator;
import com.example.demo.decorator.NormalMember;
import com.example.demo.decorator.VideoPlay;
import com.example.demo.strategy.videoOtherService.MultiDownload;
import com.example.demo.strategy.videoOtherService.OtherPay;
import com.example.demo.strategy.videoOtherService.VipAfterSales;
import com.example.demo.strategy.videoplay.VideoHDAction;
import com.example.demo.strategy.videoplay.VideoNoAdAction;
import com.example.demo.strategy.videoplay.VideoPayAction;
import com.google.common.collect.Lists;

import java.util.List;

public class TestDecorator {
    public static void main(String[] args) {
        List<VideoPayAction> videoPayActions = Lists.newArrayList(new VideoHDAction(), new VideoNoAdAction());
        List<OtherPay> otherPays = Lists.newArrayList(new MultiDownload(), new VipAfterSales());

        VideoPlay memberDecorator = new DiyOneVideoPlayDecorator(new NormalMember(), videoPayActions);
        memberDecorator = new DiyTwoVideoPlayDecorator(memberDecorator, otherPays);
        memberDecorator.play();
        System.out.println();
    }
}
