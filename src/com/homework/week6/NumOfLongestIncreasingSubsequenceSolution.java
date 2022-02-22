package com.homework.week6;

import com.teachingpractice.week6.LongestIncreasingSubSequenceSolution;

import java.util.Arrays;

/**
 * 算法实现：最长递增子序列的个数
 * - https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/ (673题)
 * <p>
 * - 给定一个未排序的整数数组nums, 返回最长严格递增子序列的个数
 * - 最长严格递增子序列的最小长度可以是1
 * <p>
 * - 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * <p>
 * 1 <= nums.length <= 2000
 * -10^6 <= nums[i] <= 10^6
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see LongestIncreasingSubSequenceSolution (本题以此题为基础)
 * @since 2022-02-15 06:42:52
 */
public class NumOfLongestIncreasingSubsequenceSolution {
    public static void main(String[] args) {
        final NumOfLongestIncreasingSubsequenceSolution solution = new NumOfLongestIncreasingSubsequenceSolution();

        int[] nums = new int[]{1, 3, 5, 4, 7};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println();
        nums = new int[]{2, 2, 2, 2, 2};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
    }

    int findCount(final int[] nums) {
        return 1;
    }
}
