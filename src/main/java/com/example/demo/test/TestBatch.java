package com.example.demo.test;

import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author dongxu
 * @create 2023-04-15 下午3:41
 */
public class TestBatch {

    private static int threadCount = 30;

    /**
     * 为保证30个线程同时并发运行
     */
    private final static CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(threadCount);

    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        //循环开30个线程
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //每次减一
                    COUNT_DOWN_LATCH.countDown();
                    //此处等待状态，为了让30个线程同时进行
                    try {
                        COUNT_DOWN_LATCH.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 1; j <= 3; j++) {
                        int param = new Random().nextInt(4);
                        if (param <=0){
                            param++;
                        }
                        String responseBody = restTemplate.getForObject("http://localhost:8080/user/merge?userId=" + param, String.class);
                        System.out.println(Thread.currentThread().getName() + "参数 " + param + " 返回值 " + responseBody);
                    }
                }
            }).start();
        }
    }
}
