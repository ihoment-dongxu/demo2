package com.example.demo.practice.statusMachine.demo2;

/**
 * @author dongxu
 * @create 2023-04-08 下午8:57
 */
public class RainStatus implements IStatus{
    @Override
    public void handle() {
        System.out.println("今天雨天，坐公交车上班");
    }
}
