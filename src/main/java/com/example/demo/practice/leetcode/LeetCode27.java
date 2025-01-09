package com.example.demo.practice.leetcode;

/**
 * 27. 移除元素
 * https://leetcode.cn/problems/remove-element/description/
 *
 * @author dongxu
 * @create 2025-01-09 12:09
 */
public class LeetCode27 {

    public static void main(String[] args) {
        int[] nums = {3,2,2,3};
        int val = 3;
        int l = removeElement2(nums, val);
        System.out.println("l = " + l);
    }

    /**
     * 暴力解法
     *
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement1(int[] nums, int val) {
        // 暴力法
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == val) {
                for (int j = i + 1; j < n; j++) {
                    // 前移一位
                    nums[j - 1] = nums[j];
                }
                i--;
                n--;
            }
        }
        return n;
    }

    public static int removeElement2(int[] nums, int val) {
        // 快慢指针
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }
}
