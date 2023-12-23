package com.example.demo.decorator;

/**
 * 普通会员
 */
public class NormalMember implements VideoPlay {

    @Override
    public void play() {
        System.out.println("普通会员：你现在可以观看普通视频");
    }

}
