package com.example.demo.practice.statusMachine.demo1;

import com.example.demo.practice.statusMachine.demo2.SunStatus;

/**
 * @author dongxu
 * @create 2023-04-08 下午8:39
 */
public class WorkerTest {
    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.setName("董旭");
        worker.setStatus(Status.SUN);
        worker.oneDay();

        System.out.println("--------------------------------");

        worker.setIStatus(new SunStatus());
        worker.statusMachine();
    }
}
