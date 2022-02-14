package com.teachingpractice.week5;

/**
 * 算法实现：翻转对
 * - https://leetcode-cn.com/problems/reverse-pairs/ (493题)
 * <p>
 * - 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * - 你需要返回给定数组中的重要翻转对的数量。
 * <p>
 * - 这里说的是“翻转对”的概念
 * <p>
 * - 还有一个更重要的概念，就是“逆序对” (如果 i < j 且 nums[i] > nums[j])
 * - 只有有序序列，没有逆序对 (排序，就是去除逆序对的过程)
 * <p>
 * - 输入: [1,3,2,3,1]
 * 输出: 2
 * <p>
 * - 输入: [2,4,3,5,1]
 * 输出: 3
 * <p>
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-14 01:24:21
 */
public class ReversePairsSolution {
    public static void main(String[] args) {

    }

    int reversePairs(final int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return 0;
    }

    private void mergeSort(final int[] nums, final int left, final int right) {
        if (left >= right) {
            return;
        }
        int mid = ((right - left) >> 1) + left;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(final int[] nums, final int left, final int mid, final int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        for (int k = 0; k < temp.length; k++) {
            if (j > right || (i <= mid && nums[i] <= nums[j])) {
                temp[k] = nums[i++];
            } else {
                temp[k] = nums[j++];
            }
        }
        // copy回原数组
        for (int k = 0; k < temp.length; k++) {
            nums[left + k] = temp[k];
        }
    }
}
