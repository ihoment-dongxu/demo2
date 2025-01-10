package com.example.demo.practice.leetcode;

import java.util.Arrays;

/**
 * 167. 两数之和 II - 输入有序数组
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/
 * @author dongxu
 * @create 2025-01-10 18:14
 */
public class LeetCode167 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = twoSumIndexArr(nums, target);
        System.out.println(Arrays.toString(res));
    }

    private static int[] twoSumIndexArr(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int[] index = new int[2];
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                index[0] = l + 1;
                index[1] = r + 1;
                return index;
            }
            if (nums[l] + nums[r] > target) {
                // 大于则右指针回退
                r--;
            }

            if (nums[l] + nums[r] < target) {
                // 小于则左指针前进
                l++;
            }
        }
        return index;
    }


}
