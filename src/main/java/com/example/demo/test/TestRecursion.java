package com.example.demo.test;

import java.util.Stack;

/**
 * @author dongxu
 * @create 2024-10-25 17:17
 */
public class TestRecursion {

//    public static void main(String[] args) {
//        // 1...n 的和
//        int n = 100;
//        int sum = calcCount(n);
//        System.out.println(sum);
//    }
//
//    private static int calcCount(int n) {
//        // 终止条件
//        if (n == 1) {
//            return 1;
//        }
//        // 递：改变参数自己调自己
//        int res = calcCount(n - 1);
//        // 归，返回结果
//        return res + n;
//    }

    public static void main(String[] args) {
        int n = 10;
//        for (int i = 1; i < n; i++) {
//            int nNumber = findN(i);
//            System.out.print(nNumber);
//            System.out.print(",");
//        }

        int i = forLoopRecur(10);
        System.out.println(i);
    }

    private static int findN(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return findN(n - 1) + findN(n - 2);
    }

    private static int forLoopRecur(int n) {
        // 使用一个显式的栈来模拟系统调用栈
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        // 递：递归调用
        for (int i = n; i > 0; i--) {
            // 通过“入栈操作”模拟“递”
            stack.push(i);
        }
        // 归：返回结果
        while (!stack.isEmpty()) {
            // 通过“出栈操作”模拟“归”
            res += stack.pop();
        }
        // res = 1+2+3+...+n
        return res;
    }
}
