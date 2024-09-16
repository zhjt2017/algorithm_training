package com.teachingpractice.week5;

import java.util.Arrays;

/**
 * 算法实现: 二分拓展 - 寻找峰值
 * - https://leetcode-cn.com/problems/find-peak-element/ (162题)
 * <p>
 * - 峰值元素是指其值严格大于左右相邻值的元素。
 * - 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * - 你可以假设 nums[-1] = nums[n] = -∞ 。
 * - 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 * <p>
 * - 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 * <p>
 * - 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * <p>
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-06 04:51:54
 */
public class FindPeakElementSolutionReview {
    public static void main(String[] args) {
        final FindPeakElementSolutionReview solution = new FindPeakElementSolutionReview();

        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output findPeakElement : " + solution.findPeakElement(nums));

        nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output findPeakElement : " + solution.findPeakElement(nums));
    }

    /**
     * 三分查找 - 求峰值问题 - 求极大值
     * - 在定义域[left, right]上取任意两点lmid, rmid
     * - 若 f(lmid) 等于 f(rmid) , 则函数必然在lmid处单调递增, 在rmid处单调递减, 极大值在(lmid, rmid)上
     * - 若 f(lmid) 小于 f(rmid) , 则函数必然在lmid处单调递增, 极大值在(lmid, right]上
     * - 若 f(lmid) 大于 f(rmid) , 则函数必然在rmid处单调递减, 极大值在[left, rmid)上
     * <p>
     * - 另一方面, 我们也可以认为是求满足单调递减的第一个位置 (终止于left == right)
     *
     * @param nums
     * @return
     */
    private int findPeakElement(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int lmid, rmid;
        while (left < right) {
            lmid = ((right - left) >> 1) + left;
            rmid = lmid + 1;

//            // 对于rmid = lmid + 1, 不会出现nums[lmid] == nums[rmid]
//            if (nums[lmid] == nums[rmid]) {
//                left = lmid + 1;
//                right = rmid - 1;
//                continue;
//            }
            if (nums[lmid] < nums[rmid]) {
                left = rmid;
                continue;
            }
            right = lmid;
        }
        return right;
    }
}
