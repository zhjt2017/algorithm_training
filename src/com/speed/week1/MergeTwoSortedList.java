package com.speed.week1;

import java.util.Arrays;

/**
 * 合并2个有序List (不妨设2者都是从小到大排序的)
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2021-12-27 08:46:34
 */
public class MergeTwoSortedList {
    public static void main(String[] args) {
        int[] nums1 = {4, 5, 6, 0, 0, 0};
        int[] nums2 = {1, 2, 3};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        nums1 = new int[]{4, 5, 6};
        nums2 = new int[]{1, 2, 3, 5, 7, 8};
        final int[] result = mergeDirectly(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }

    private static void merge(final int[] nums1, final int m, final int[] nums2, final int n) {
        /*
         * 1、反向, 无须右移复制, 空间O(1)
         * 2、直接取2个来源处的较大值写入, 而不是跟上一个写入值比对, 节省比较次数, 构成比较一次可以写入一个, 时间O(m+n)
         * 3、迭代时, nums1如果先结束, 剩下的nums2写入nums1, 如果nums2先结束, 则nums1浑然天成, 直接结束
         */
        int index = m + n - 1;
        int i = m - 1;
        for (int j = n - 1; j >= 0; ) {
            if (i < 0) {
                for (; j >= 0; ) {
                    nums1[index--] = nums2[j--];
                }
                break;
            }
            if (nums1[i] <= nums2[j]) {
                nums1[index--] = nums2[j--];
            } else {
                nums1[index--] = nums1[i--];
            }
        }
    }

    private static int[] mergeDirectly(final int[] nums1, final int[] nums2) {
        /*
         * 1、直接取2个来源处的较大值写入, 而不是跟上一个写入值比对, 节省比较次数, 构成比较一次可以写入一个, 时间O(m+n)
         * 2、迭代时, nums1如果先结束, 剩下的nums2直接写入result, 如果nums2先结束, 剩下的nums2直接写入result
         */
        final int len1 = nums1.length;
        final int len2 = nums2.length;
        final int[] result = new int[len1 + len2];
        int index = 0;
        int i = 0;
        for (int j = 0; j < len2; ) {
            if (i >= len1) {
                for (; j < len2; j++) {
                    result[index++] = nums2[j];
                }
                break;
            }
            if (nums1[i] <= nums2[j]) {
                result[index++] = nums1[i++];
            } else {
                result[index++] = nums2[j++];
            }
        }
        for (; i < len1; i++) {
            result[index++] = nums1[i];
        }

        return result;
    }
}
