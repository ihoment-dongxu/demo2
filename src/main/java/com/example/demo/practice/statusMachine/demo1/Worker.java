package com.example.demo.practice.statusMachine.demo1;

import com.example.demo.practice.statusMachine.demo2.IStatus;
import lombok.Data;

/**
 * @author dongxu
 * @create 2023-04-08 下午8:38
 */
@Data
public class Worker {

    private String name;

    private Status status;

    private IStatus iStatus;

    /**
     * 打工人的一天
     */
    public void oneDay() {
        System.out.println("9点，开始起床。");
        switch (status) {
            case SUN:
                System.out.println("今天晴天，骑车上班");
                break;
            case THURSDAY:
                System.out.println("今天疯狂星期四，矿工一天吃肯德基");
                break;
            case RAIN:
                System.out.println("今天雨天，坐公交车上班");
                break;
            default:
                System.out.println("状态错误");
                break;
        }
        System.out.println("晚上6点，下班");
    }

    public void statusMachine() {
        System.out.println("9点，开始起床。");
        iStatus.handle();
        System.out.println("晚上6点，下班");
    }
}
