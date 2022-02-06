package com.speed.week5;

import java.util.Arrays;

/**
 * 算法实现: 寻找峰值
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
 * @since 2022-01-28 06:05:37
 */
public class FindPeakElementSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output findPeakElement : " + findPeakElement(nums));

        nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output findPeakElement : " + findPeakElement(nums));
    }

    /**
     * 设计思想:
     * - 二分查找, 每次得到新的mid进行比较, mid刚好为峰值时, 直接返回mid, 否则mid要么比mid+1小, 要么比mid-1小, 将下次迭代的范围放到比mid大的那个区域
     * - 另外, 根据题意, 边界外的数可以认为是负无穷, 则边界值如果比其内测的大, 那么边界值也是一个答案, 于是二分查找算法针对除边界外的值, 则不会漏
     * - 单个元素的, 其必然为峰值
     *
     * @param nums
     * @return
     */
    private static int findPeakElement(final int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[n - 1] > nums[n - 2]) {
            return n - 1;
        }

        int left = 1, right = n - 2;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] > nums[mid + 1] && nums[mid] > nums[mid - 1]) {
                return mid;
            }
            // 只跟右侧(找定一个基准点)相比, 如果右侧小, 就不管右侧, 如果右侧大, 就必然右侧有希望, 如果右侧相等, 则看左侧, 如果左侧大, 则考虑左侧有希望, 否则看右侧
            if (nums[mid] > nums[mid + 1] || nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
