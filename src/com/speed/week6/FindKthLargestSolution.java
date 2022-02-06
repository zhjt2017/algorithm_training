package com.speed.week6;

import java.util.Arrays;

/**
 * 算法实现: 找出数组中第K个最大元素
 * <p>
 * - 给定整数数组 nums (可能包含重复元素) 和整数 k，请返回数组中第 k 个最大的元素。
 * - 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * - 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * - 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 1 <= k <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-06 11:43:16
 */
public class FindKthLargestSolution {
    public static void main(String[] args) {
        final FindKthLargestSolution solution = new FindKthLargestSolution();

        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output kth largest num : " + solution.findKthLargest(nums, k));

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = 4;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output kth largest num : " + solution.findKthLargest(nums, k));
    }

    private int findKthLargest(final int[] nums, final int k) {
        return 0;
    }
}
