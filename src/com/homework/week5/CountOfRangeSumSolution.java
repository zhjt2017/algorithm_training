package com.homework.week5;

/**
 * 算法实现：区间和的个数
 * - https://leetcode-cn.com/problems/count-of-range-sum/ (327题)
 * <p>
 * - 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * - 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * - 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * <p>
 * - 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * <p>
 * - 输入：nums = [0], lower = 0, upper = 0
 * 输出：1
 * <p>
 * 1 <= nums.length <= 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 * -10^5 <= lower <= upper <= 10^5
 * 题目数据保证答案是一个 32 位 的整数
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 10:27:11
 */
public class CountOfRangeSumSolution {
    public static void main(String[] args) {
        final CountOfRangeSumSolution solution = new CountOfRangeSumSolution();

        int[] nums = new int[]{-2, 5, -1};
        int lower = -2;
        int upper = 2;
        System.out.println("Input nums : " + nums + ", lower : " + lower + ", upper : " + upper);
        System.out.println("Output count of range sum : " + solution.countRangeSum(nums, lower, upper));
        System.out.println();

        nums = new int[]{0};
        lower = 0;
        upper = 0;
        System.out.println("Input nums : " + nums + ", lower : " + lower + ", upper : " + upper);
        System.out.println("Output count of range sum : " + solution.countRangeSum(nums, lower, upper));
    }

    int countRangeSum(final int[] nums, final int lower, final int upper) {
        return 0;
    }
}
