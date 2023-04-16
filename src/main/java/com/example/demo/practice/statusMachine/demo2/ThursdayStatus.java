package com.example.demo.practice.statusMachine.demo2;

/**
 * @author dongxu
 * @create 2023-04-08 下午8:58
 */
public class ThursdayStatus implements IStatus {
    @Override
    public void handle() {
        System.out.println("今天疯狂星期四，矿工一天吃肯德基");
    }
}
