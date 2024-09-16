package com.speed.week7;

import java.util.Arrays;

/**
 * 算法实现：动态规划 - 乘积最大子数组
 * - https://leetcode-cn.com/problems/maximum-product-subarray/ (152题)
 * <p>
 * - 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），
 * - 并返回该子数组所对应的乘积。子数组是数组的连续子序列。
 * - 测试用例的答案是一个 32位 整数。
 * <p>
 * - 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * - 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums 的任何子序乘积都在int范围 (我意：可能大多数是1、-1，或者中间被0隔成好多段，每段最大乘积都是int范围，包括负数)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-15 02:28:24
 */
public class MaxProductSubarraySolution {
    public static void main(String[] args) {
        final MaxProductSubarraySolution solution = new MaxProductSubarraySolution();

        int[] nums = new int[]{2, 3, -2, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output max product of subarray : " + solution.maxProduct(nums));
        System.out.println("Output max product of subarray (me) : " + solution.maxProductMe(nums));
        System.out.println();
        nums = new int[]{-2, 0, -1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output max product of subarray : " + solution.maxProduct(nums));
        System.out.println("Output max product of subarray (me) : " + solution.maxProductMe(nums));
        System.out.println();
        nums = new int[]{-2, -3, -1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output max product of subarray : " + solution.maxProduct(nums));
        System.out.println("Output max product of subarray (me) : " + solution.maxProductMe(nums));
    }

    /**
     * 动态规划
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     * <p>
     * - 每个数均为整数
     * --- 遇到新值为负数时，最大值与新值product最小值，最小值与新值product最大值
     * --- 全局维护加上当前位置数值的最大值与最小值、历史最大值
     *
     * @param nums
     * @return
     */
    int maxProduct(final int[] nums) {
        int ans = Integer.MIN_VALUE, imax = 1, imin = 1;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            ans = Math.max(ans, imax);
        }
        return ans;
    }

    /**
     * 1. 按0分隔
     * 2. 负数个数为偶数个：段内所有数的乘积即为最大值
     * 3. 负数个数为奇数个：则去除段内第一个负数及其左边所有正数，去除段内最后一个负数及其右边所有正数，二者乘积中的较大者即为最大值
     * -- 3的处理略有复杂，这在人工处理时是非常优越的，但是计算机处理的话，还是持续维护ans, imax, imin为好，性能稳定
     *
     * @param nums
     * @return
     */
    int maxProductMe(final int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int ans = 0, imax = 1, imin = 1;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                imax = 1;
                imin = 1;
                continue;
            }
            if (nums[i] < 0) {
                temp = imax;
                imax = imin;
                imin = temp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            ans = Math.max(ans, imax);
        }
        return ans;
    }
}
