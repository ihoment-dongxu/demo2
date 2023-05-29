package com.example.demo.practice.aync;

import java.util.concurrent.*;

/**
 * 异步测试 CompletableFuture
 *
 * @author dongxu
 * @create 2023-05-19 下午2:50
 */
public class AsyncDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                10,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(4 * 2),
                new ThreadPoolExecutor.AbortPolicy());

        // runAsync：无返回值
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync：无返回值测试");
        }, threadPoolExecutor);

        // supplyAsync：有返回值
        //  whenComplete能感知异常，能感知结果，但没办法给返回值
        //  exceptionally能感知异常，不能感知结果，能给返回值。相当于，如果出现异常就返回这个值
        CompletableFuture<Long> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            long id = Thread.currentThread().getId();
            System.out.println("supplyAsync：有返回值测试，线程ID=" + id);
            return id;
        }, threadPoolExecutor).whenComplete((a, exception) -> {
            System.out.println("发生异常");
            System.out.println("结果为：" + a);
            System.out.println("模拟发送钉钉消息，错误：" + exception);
        }).exceptionally(exception -> {
            System.out.println("发生异常");
            System.out.println("此时返回默认值");
            return 0L;
        });

        try {
            Long supplyAsyncFutureThreadId = supplyAsyncFuture.get();
            System.out.println("supplyAsyncFutureThreadId = " + supplyAsyncFutureThreadId);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------thenRunAsync----------------------------------------");
        // 多个任务
        // thenRunAsync：不能接收上一次的执行结果，也没返回值
        CompletableFuture<Void> thenRunAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1启动。。。");
            return 10;
        }, threadPoolExecutor).thenRunAsync(() -> System.out.println("thenRunAsync任务2启动。。。"));

        System.out.println("------------------------thenAcceptAsync----------------------------------------");
        // thenAcceptAsync：能接收上一次的执行结果，但没返回值
        CompletableFuture<Void> thenAcceptAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1启动。。。");
            return 10;
        }, threadPoolExecutor).thenAcceptAsync(value -> {
            System.out.println("任务2启动。。。,任务1的值为：" + value);
        });

        System.out.println("------------------------thenApplyAsync----------------------------------------");
        // thenApplyAsync：能接收上一次的执行结果，又可以有返回值
        CompletableFuture<Integer> thenApplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1启动。。。");
            return 10;
        }, threadPoolExecutor).thenApplyAsync(value -> {
            System.out.println("任务2启动。。。任务1的值为" + value);
            return value + 10;
        });
        try {
            Integer thenApplyAsyncFutureValue = thenApplyAsyncFuture.get();
            System.out.println("thenApplyAsyncFutureValue = " + thenApplyAsyncFutureValue);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------三任务编排---------------------------");

        CompletableFuture<Object> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务1开始，线程" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("任务1结束：");
            return i;
        }, threadPoolExecutor);

        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务2开始，线程" + Thread.currentThread().getId());
            try {
                Thread.sleep(3000);
                System.out.println("任务2结束：");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }, threadPoolExecutor);

        System.out.println("******三任务组合，前两个任务都完成，才执行任务3*******");
        System.out.println("---------------------runAfterBothAsync---------------------------");

        // 「runAfterBothAsync」 ：「任务01 任务02都完成了，再开始执行任务3，不感知任务1、2的结果的，也没返回值」
        CompletableFuture<Void> runAfterBothAsyncFuture = future01.runAfterBothAsync(future02, () -> {
            System.out.println("任务3启动");
            System.out.println("任务3结束");
        }, threadPoolExecutor);

        System.out.println("---------------------thenAcceptBothAsyncFuture---------------------------");
        // 「thenAcceptBothAsync：任务01 任务02都完成了，再开始执行任务3，能感知到任务1、2的结果，但没返回值」
        CompletableFuture<Void> thenAcceptBothAsyncFuture = future01.thenAcceptBothAsync(future02, (v1, v2) -> {
            System.out.println("任务3启动");
            System.out.println("v1 = " + v1);
            System.out.println("v2 = " + v2);
            System.out.println("任务3结束");
        }, threadPoolExecutor);

        System.out.println("结束。。。");
        threadPoolExecutor.shutdown();
    }
}
