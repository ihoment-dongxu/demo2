package com.example.demo.decorator;

public class VipMember implements VideoPlay{

    @Override
    public void play() {
        System.out.println("vip会员:你现在可以查询VIP内容");
    }
}
