package com.example.demo.practice.lambda;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Lambda函数式编程测试
 *
 * @author dongxu
 * @create 2023-05-13 下午6:54
 */
public class LambdaDemo {
    /**
     * Consumer 接受一个输入参数，并且不返回结果
     *
     * @param param
     * @param consumer
     */
    public static void consumerDemo(String param, Consumer<String> consumer) {
        consumer.accept(param);
    }

    /**
     * Function接口：接受一个输入参数，并返回一个结果。
     *
     * @param age
     * @param function
     * @return
     */
    public static String functionDemo(Integer age, Function<Integer, String> function) {
        String apply = function.apply(age);
        System.out.println(apply);
        return apply;
    }

    /**
     * Predicate接口：接受一个输入参数，并返回一个布尔值。
     *
     * @param age
     * @param predicate
     * @return
     */
    public static boolean predicateDemo(Integer age, Predicate<Integer> predicate) {
        boolean test = predicate.test(age);
        System.out.println("张宇是18岁吗：" + test);
        Predicate<Integer> negate = predicate.negate();
        System.out.println("反过来说，张宇不是18岁吗？：" + negate.test(age));
        return test;
    }

    /**
     * Supplier接口：不接受任何输入参数，并返回一个结果。
     *
     * @param supplier
     * @return
     */
    public static String supplierDemo(Supplier<String> supplier) {
        return "今日的幸运数字是：" + supplier.get();
    }

    public static void main(String[] args) {
        System.out.println("--------Consumer--------");
        String consumerParam = "接受一个输入参数，并且不返回结果";
        consumerDemo(consumerParam, System.out::println);

        System.out.println("--------Function--------");
        int age = 18;
        functionDemo(age, x -> "张宇永远" + x + "岁");

        System.out.println("--------Predicate--------");
        boolean b = predicateDemo(24, x -> x == 18);
        System.out.println(b);

        System.out.println("--------Supplier--------");
        Random random = new Random();
        String supplierDemo = supplierDemo(() -> String.valueOf(random.nextInt(10)));
        System.out.println(supplierDemo);
    }
}
