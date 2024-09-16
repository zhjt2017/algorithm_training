package com.mistakescollection;

import java.util.Arrays;

/**
 * 寻找2个正序数组的中位数
 * - https://leetcode-cn.com/problems/median-of-two-sorted-arrays/ (4题)
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * - 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * <p>
 * - 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * - nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 10^6
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-09 12:28:40
 */
public class MedianOfTwoSortedArraySolution {
    public static void main(String[] args) {
        final MedianOfTwoSortedArraySolution solution = new MedianOfTwoSortedArraySolution();

        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        System.out.println("Input nums1 : " + Arrays.toString(nums1) + ", nums2 : " + Arrays.toString(nums2));
        System.out.println("Output find median of two sorted arrays : " + solution.findMedianSortedArrays(nums1, nums2));
        System.out.println();

        nums1 = new int[]{1, 2};
        nums2 = new int[]{3, 4};
        System.out.println("Input nums1 : " + Arrays.toString(nums1) + ", nums2 : " + Arrays.toString(nums2));
        System.out.println("Output find median of two sorted arrays : " + solution.findMedianSortedArrays(nums1, nums2));
        System.out.println();
    }

    /**
     * 基本解法：二分查找 (最小的第k个的思想)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    double findMedianSortedArrays(final int[] nums1, final int[] nums2) {
        final int n1 = nums1.length;
        final int n2 = nums2.length;
        end1 = n1 - 1;
        end2 = n2 - 1;
        final int total = n1 + n2;
        // 将total奇数、偶数的情况统一进行处理：取得第kLeft个、第kRight个，求其平均数，作为最终的中位数
        final int kLeft = (total + 1) >> 1;
        final int kRight = (total + 2) >> 1;
        return (getKthMin(nums1, nums2, 0, 0, kLeft) + getKthMin(nums1, nums2, 0, 0, kRight)) * 0.5;
    }

    private int end1;
    private int end2;

    private int getKthMin(final int[] nums1, final int[] nums2, final int start1, final int start2, final int k) {
        if (start1 > end1) {
            return nums2[start2 - 1 + k];
        }
        if (start2 > end2) {
            return nums1[start1 - 1 + k];
        }

        // k == 1的时候无法再拆分，直接给出结果 (如果只在一个数组中进行去除，那么最终k=1而不是直接k=0，这样的写法更简洁)
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 二分：去除掉一定在前k-1个范围内的数
        final int half = k >> 1;
        final int mid1 = Math.min(end1, start1 - 1 + half);
        final int mid2 = Math.min(end2, start2 - 1 + half);

        return nums1[mid1] <= nums2[mid2] ? getKthMin(nums1, nums2, mid1 + 1, start2, k - mid1 - 1 + start1) : getKthMin(nums1, nums2, start1, mid2 + 1, k - mid2 - 1 + start2);
    }

    /**
     * 摘录方法：划分数组
     *
     * @param nums1
     * @param nums2
     * @return
     */
    double findMedianSortedArraysPartition(final int[] nums1, final int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}
