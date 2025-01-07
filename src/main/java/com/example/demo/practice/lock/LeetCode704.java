package com.example.demo.practice.lock;

/**
 * 704. 二分查找
 * https://leetcode.cn/problems/binary-search/description/
 *
 * @author dongxu
 * @create 2025-01-07 18:52
 */
public class LeetCode704 {

    public static void main(String[] args) {
        int[] arr = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 3;
        int index = search(arr, target);
        System.out.println(index);
    }

    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
