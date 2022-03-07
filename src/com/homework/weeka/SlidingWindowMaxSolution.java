package com.homework.weeka;

import java.util.Arrays;

/**
 * 算法实现：平衡二叉树 - 滑动窗口最大值问题
 * - https://leetcode-cn.com/problems/sliding-window-maximum/ (239题)
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * <p>
 * - 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * - 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * 要求：尝试使用语言内置的有序集合库，或写一棵平衡树，来实现
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 03:04:24
 */
public class SlidingWindowMaxSolution {
    public static void main(String[] args) {
        final SlidingWindowMaxSolution solution = new SlidingWindowMaxSolution();

        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output max (value of) sliding window : " + Arrays.toString(solution.maxSlidingWindow(nums, k)));
        System.out.println();

        nums = new int[]{1};
        k = 1;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output max (value of) sliding window : " + Arrays.toString(solution.maxSlidingWindow(nums, k)));
        System.out.println();
    }

    int[] maxSlidingWindow(int[] nums, int k) {
        return new int[]{};
    }
}
