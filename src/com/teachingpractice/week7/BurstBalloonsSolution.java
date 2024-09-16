package com.teachingpractice.week7;

import java.util.Arrays;

/**
 * 算法实现：动态规划 - 区间动态规划 - 戳气球
 * - https://leetcode-cn.com/problems/burst-balloons/ (312题)
 * <p>
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 
 * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * 求所能获得硬币的最大数量。
 * <p>
 * - 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * <p>
 * - 输入：nums = [1,5]
 * 输出：10
 * <p>
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 08:39:30
 */
public class BurstBalloonsSolution {
    public static void main(String[] args) {
        final BurstBalloonsSolution solution = new BurstBalloonsSolution();

        int[] nums = new int[]{3, 1, 5, 8};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output max coins : " + solution.maxCoins(nums));
        System.out.println();

        nums = new int[]{1, 5};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output max coins : " + solution.maxCoins(nums));
        System.out.println();
    }

    int maxCoins(final int[] nums) {
        return 0;
    }
}
