package com.example.demo.practice.statusMachine.demo2;

/**
 * @author dongxu
 * @create 2023-04-08 下午8:57
 */
public class SunStatus implements IStatus{
    @Override
    public void handle() {
        System.out.println("今天晴天，骑车上班");
    }
}
