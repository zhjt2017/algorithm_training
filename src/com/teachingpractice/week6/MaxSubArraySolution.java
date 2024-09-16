package com.teachingpractice.week6;

import java.util.Arrays;

/**
 * 算法实现：动态规划 - 最大子段(串)和
 * - https://leetcode-cn.com/problems/maximum-subarray/ (53题)
 * - 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * - 子数组 是数组中的一个连续部分。
 * <p>
 * - 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * - 输入：nums = [1]
 * 输出：1
 * <p>
 * - 输入：nums = [5,4,-1,7,8]
 * 输出：23
 * <p>
 * - 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 12:30:38
 */
public class MaxSubArraySolution {
    public static void main(String[] args) {
        final MaxSubArraySolution solution = new MaxSubArraySolution();

        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output maximum sub array sum : " + solution.maxSubArray(nums));

        nums = new int[]{1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output maximum sub array sum : " + solution.maxSubArray(nums));

        nums = new int[]{5, 4, -1, 7, 8};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output maximum sub array sum : " + solution.maxSubArray(nums));
    }

    /**
     * 针对本题的基本方法：动态规划
     * - 前序和如果<=0，丢弃
     * <p>
     * f[i]表示以i结尾的最大子段和
     * f[i]=max(f[i-1]+nums[i],nums[i])
     * 边界：f[0]=nums[0]
     * 目标：max{f[i]} (0<=i<n)
     *
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     *
     * @param nums
     * @return
     */
    private int maxSubArray(final int[] nums) {
        int pre = 0;
        int ans = nums[0];
        for (final int value : nums) {
            pre = Math.max(pre + value, value);
            ans = Math.max(ans, pre);
        }
        return ans;
    }
}
