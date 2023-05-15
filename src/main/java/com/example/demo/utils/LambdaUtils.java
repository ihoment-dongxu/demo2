package com.example.demo.utils;

import com.example.demo.exception.BusinessException;

import java.util.function.Consumer;

/**
 * @author dongxu
 * @create 2023-05-15 下午10:20
 */
public class LambdaUtils {

    public static ThrowExceptionFunction isTrue(boolean b) {
        return message -> {
            if (!b) {
                throw new BusinessException(message);
            }
        };
    }

    public static BranchHandle isTrueOrFalse(boolean b) {
        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    public static PresentOrElseHandle<?> isPresent(String obj) {
        return (action, falseHandle) -> {
            if (obj != null) {
                action.accept(obj);
            } else {
                falseHandle.run();
            }
        };
    }

    @FunctionalInterface
    public interface ThrowExceptionFunction {
        /**
         * 抛出异常
         *
         * @param message 异常信息
         */
        void throwMessage(String message);
    }

    @FunctionalInterface
    public interface BranchHandle {
        /**
         * 替代if,else逻辑
         *
         * @param trueHandle  true 执行
         * @param falseHandle false 执行
         */
        void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);
    }

    @FunctionalInterface
    public interface PresentOrElseHandle<T extends Object> {
        /**
         * 符合条件执行action逻辑
         *
         * @param action      动作
         * @param falseHandle 错误时处理
         */
        void presentOrElseHandle(Consumer<? super T> action, Runnable falseHandle);
    }

    public static void main(String[] args) {

        LambdaUtils.isTrueOrFalse(true).trueOrFalseHandle(
                () -> System.out.println("执行true逻辑"),
                () -> System.out.println("执行false逻辑"));

        LambdaUtils.isPresent("这是啥啊").presentOrElseHandle(System.out::println, () -> {
            System.out.println("什么也没有");
        });

        LambdaUtils.isTrue(true).throwMessage("Hello,true");
        LambdaUtils.isTrue(false).throwMessage("Hello,false");

    }
}
