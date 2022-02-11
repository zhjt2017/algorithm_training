package com.teachingpractice.week5;

import java.util.Arrays;

/**
 * 算法实现: 对数组进行排序
 * - https://leetcode-cn.com/problems/sort-an-array/ (912题)
 * <p>
 * - 给你一个整数数组 nums，请你将该数组升序排列。
 * <p>
 * - 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * <p>
 * - 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * <p>
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-09 04:18:21
 */
public class SortAnArraySolution {
    public static void main(String[] args) {
        final SortAnArraySolution solution = new SortAnArraySolution();

        int[] nums = new int[]{5, 2, 3, 1};
        System.out.println("Input array : " + Arrays.toString(nums));
        System.out.println("Output sorted : " + Arrays.toString(solution.sortArray(nums)));

        nums = new int[]{5, 1, 1, 2, 0, 0};
        System.out.println("Input array : " + Arrays.toString(nums));
        System.out.println("Output sorted : " + Arrays.toString(solution.sortArray(nums)));
    }

    int[] sortArray(final int[] nums) {
        return nums;
    }
}
