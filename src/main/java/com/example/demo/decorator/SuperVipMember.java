package com.example.demo.decorator;

public class SuperVipMember implements VideoPlay {
    @Override
    public void play() {
        System.out.println("超级vip会员：你现在不仅可以观看vip内容，还可以观看超前点播内容");
    }
}
