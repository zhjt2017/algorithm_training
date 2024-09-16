package com.homework.week5;

import com.teachingpractice.week5.RotatedSortedArrayFindMinSolution;

import java.util.Arrays;

/**
 * 算法实现: 二分查找拓展- 寻找旋转排序数组中的最小值II
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
 * <p>
 * - 输入：nums = [2,2,2,0,1]
 * 输出：0
 * <p>
 * - n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see RotatedSortedArrayFindMinSolution
 * - 本题是该题的延伸, 数组中的元素允许重复, 会影响算法的时间复杂度吗? 如何影响? 为什么?
 * @since 2022-02-05 10:42:59
 */
public class RotatedSortedArrayFindMinSolutionII {
    public static void main(String[] args) {
        final RotatedSortedArrayFindMinSolutionII solution = new RotatedSortedArrayFindMinSolutionII();

        int[] nums = new int[]{1, 3, 5};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));

        nums = new int[]{2, 2, 2, 0, 1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));

        nums = new int[]{2, 2, 2, 0, 2};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));

        nums = new int[]{1, 3, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));

        nums = new int[]{3, 1, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));
    }

    /**
     * 我的想法: 当0-k个位置上的数与last相等时, 我们的答案是无效的
     * - 稍作变更, 不再求第一个<=last的位置, 而是求第一个<last的位置, 如果找到, 该位置上的值就是答案, 如果找不到, last就是答案
     * - 我们让初始的right=last的index, 则答案必然为nums[right]
     * - 满足条件, 向左收缩, 没问题, 不满足条件, 理论上向右收缩, 但是如果mid值=last, 那么就不知道向右收缩还是向左收缩了
     * <p>
     * - 稍作优化, 我们将nums[mid]不与last比对, 而是以最新的nums[right]比对, 满足条件nums[mid] < nums[right], 并且nums[right]会更新,
     * - 当nums[mid] == nums[right]时, right左移一位(nums[right]可能会刷新), 于是 nums[mid] > nums[right]时向右收缩的判断也不按last判断, 这可以兼容last为最大值, 并等于first的情形
     * <p>
     * - 当然了, 当nums中没有重复元素时, 我们也可以以nums[right]来替代last
     * --- 1. 当nums[right]变小时, 继续向左收缩
     * --- 2. 由于没有重复元素, 大于最小的nums[right], 又在right左边, 必然要向右收缩, 则完全符合
     * <p>
     * - 空间复杂度O(1)
     * - 时间复杂度O(logN), 最坏情况下, 都是last, O(N)
     *
     * @param nums
     * @return
     */
    private int findMin(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }
        return nums[right];
    }
}
