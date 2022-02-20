package com.homework.week7;

import java.util.Arrays;

/**
 * 算法实现：贪心 - 跳跃游戏
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

    /**
     * 贪心：不可能越过最大跳跃点，只要有一个跳跃点能达到最后一个下标，则true，否则为false
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param nums
     * @return
     */
    boolean canJump(final int[] nums) {
        final int n = nums.length;
        int farthest = 0;
        for (int i = 0; i < n; i++) {
            if (i > farthest) {
                return false;
            }
            farthest = Math.max(farthest, i + nums[i]);
            if (farthest >= n - 1) {
                return true;
            }
        }
        return false;
    }

    boolean canJumpByDp(final int[] nums) {
        return false;
    }
}
