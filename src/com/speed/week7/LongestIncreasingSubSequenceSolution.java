package com.speed.week7;

import java.util.Arrays;

/**
 * 算法实现： 最长递增子序列
 * - https://leetcode-cn.com/problems/longest-increasing-subsequence/ (300题)
 * - 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * - 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * - 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * - 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * - 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 要求：将时间复杂度控制在 O(NlogN)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-14 10:56:46
 */
public class LongestIncreasingSubSequenceSolution {
    public static void main(String[] args) {
        final LongestIncreasingSubSequenceSolution solution = new LongestIncreasingSubSequenceSolution();

        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));

        nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));

        nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));
    }

    /**
     * 注意：最长递增序列，不是最长连续递增序列
     *
     * @param nums
     * @return
     */
    int longestLength(final int[] nums) {
        return 0;
    }
}
