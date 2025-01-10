package com.example.demo.practice.leetcode;

import java.util.Arrays;

/**
 * 0977.有序数组的平方
 * https://leetcode.cn/problems/squares-of-a-sorted-array/description/
 * @author dongxu
 * @create 2025-01-10 17:51
 */
public class LeetCode977 {

    public static void main(String[] args) {
        int[] nums = new int[]{-4, -1, 0, 3, 10};
        int[] results = sortedSquares(nums);
        System.out.println(Arrays.toString(results));
    }

    public static int[] sortedSquares(int[] nums) {
        // 数组原来是有序的，平方后，负数会变成正数，需要重新排序
        // 双指针法
        // 定义一个新数组，用于存放结果
        // 定义两个指针，一个指向数组的头部，一个指向数组的尾部
        // 比较两个指针所指的元素的绝对值大小，将较大的元素的平方放入新数组的尾部
        // 然后将指针向中间移动
        // 直到两个指针相遇
        int[] result = new int[nums.length];
        int i = 0;
        int j = nums.length - 1;
        // 填充的位置：从最大的开始填充，依次减少
        int index = result.length - 1;
        while (i <= j) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                result[index--] = nums[i] * nums[i];
                i++;
            } else {
                result[index--] = nums[j] * nums[j];
                j--;
            }
        }
        return result;
    }
}
