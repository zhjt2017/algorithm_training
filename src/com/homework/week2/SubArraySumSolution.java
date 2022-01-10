package com.homework.week2;

import java.util.Arrays;

/**
 * 算法实现：和为K的子数组
 * - 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * <p>
 * - 输入：nums = [1,1,1], k = 2
 * - 输出：2
 * <p>
 * - 输入：nums = [1,2,3], k = 3
 * - 输出：2
 *
 * 设计思想：
 * 时间复杂度：O()
 * 空间复杂度：O()
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-09 10:26:40
 */
public class SubArraySumSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("num of sub arrays : " + subArraySum(nums, k));

        nums = new int[]{1, 1, 1};
        k = 3;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("num of sub arrays : " + subArraySum(nums, k));

        nums = new int[]{1, 2, 3};
        k = 3;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("num of sub arrays : " + subArraySum(nums, k));
    }

    private static int subArraySum(final int[] nums, final int k) {
        return 0;
    }
}
