package com.dailytraining.month202204;

import java.util.Arrays;

/**
 * 算法训练(2022-04-22) 旋转函数
 * - https://leetcode-cn.com/problems/rotate-function/ (396题)
 * <p>
 * 给定一个长度为 n 的整数数组 nums 。
 * <p>
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 * <p>
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 * <p>
 * 生成的测试用例让答案符合 32 位 整数。
 * <p>
 * - 输入: nums = [4,3,2,6]
 * 输出: 26
 * 解释:
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 * <p>
 * - 输入: nums = [100]
 * 输出: 0
 * <p>
 * n == nums.length
 * 1 <= n <= 10^5
 * -100 <= nums[i] <= 100
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-22 11:31:49
 */
public class RotateFunctionSolution {
    public static void main(String[] args) {
        final RotateFunctionSolution solution = new RotateFunctionSolution();

        int[] nums = new int[]{4, 3, 2, 6};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output max rotate function result : " + solution.maxRotateFunction(nums));
        System.out.println();

        nums = new int[]{100};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output max rotate function result : " + solution.maxRotateFunction(nums));
        System.out.println();
    }

    int maxRotateFunction(final int[] nums) {
        /**
         * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
         * F(0)=0×nums[0]+1×nums[1]+…+(n−1)×nums[n−1]
         * F(1)=1×nums[0]+2×nums[1]+…+0×nums[n−1]=F(0)+numSum−n×nums[n−1]
         * 1 ≤ k < n 时，F(k) =F(k−1)+numSum−n×nums[n−k]
         */
        return 0;
    }
}
