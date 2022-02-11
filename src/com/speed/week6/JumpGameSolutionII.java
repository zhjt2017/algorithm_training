package com.speed.week6;

import java.util.Arrays;

/**
 * 算法实现：跳跃游戏 II
 * - https://leetcode-cn.com/problems/jump-game-ii/ (45题)
 * <p>
 * - 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * - 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * - 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * - 假设你总是可以到达数组的最后一个位置。
 * <p>
 * - 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * - 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 1000
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-11 11:41:26
 */
public class JumpGameSolutionII {
    public static void main(String[] args) {
        final JumpGameSolutionII solution = new JumpGameSolutionII();

        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min jump steps : " + solution.jump(nums));
        System.out.println();

        nums = new int[]{2, 3, 0, 1, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min jump steps : " + solution.jump(nums));
    }

    int jump(final int[] nums) {
        return 0;
    }
}
