package com.speed.week6;

import java.util.Arrays;

/**
 * 算法实现：跳跃游戏
 * - https://leetcode-cn.com/problems/jump-game/ (55题)
 * <p>
 * - 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * - 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * - 判断你是否能够到达最后一个下标。
 * <p>
 * - 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * <p>
 * - 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-11 11:32:33
 */
public class JumpGameSolution {
    public static void main(String[] args) {
        final JumpGameSolution solution = new JumpGameSolution();

        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output can jump : " + solution.canJump(nums));
        System.out.println();

        nums = new int[]{3, 2, 1, 0, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output can jump : " + solution.canJump(nums));
    }

    boolean canJump(final int[] nums) {
        return false;
    }
}
