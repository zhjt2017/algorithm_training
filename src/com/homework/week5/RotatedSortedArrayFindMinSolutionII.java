package com.homework.week5;

import com.teachingpractice.week5.RotatedSortedArrayFindMinSolution;

import java.util.Arrays;

/**
 * 算法实现: 寻找旋转排序数组中的最小值II
 * - https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/ (154题)
 * <p>
 * - 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 可能存在重复元素 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * - 输入：nums = [1,3,5]
 * 输出：1
 *
 * - 输入：nums = [2,2,2,0,1]
 * 输出：0
 * <p>
 * - n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * @see RotatedSortedArrayFindMinSolution
 * - 本题是该题的延伸, 数组中的元素允许重复, 会影响算法的时间复杂度吗? 如何影响? 为什么?
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-05 10:42:59
 */
public class RotatedSortedArrayFindMinSolutionII {
    public static void main(String[] args) {
        final RotatedSortedArrayFindMinSolutionII solution = new RotatedSortedArrayFindMinSolutionII();

        int[] nums = new int[]{1,3,5};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));

        nums = new int[]{2,2,2,0,1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));
    }

    private int findMin(final int[] nums) {
        return -1;
    }

    private int max(final int a, final int b) {
        return a >= b ? a : b;
    }
}
