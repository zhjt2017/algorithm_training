package com.speed.week7;

import java.util.Arrays;

/**
 * 算法实现：有序数组中的单一元素
 * - https://leetcode-cn.com/problems/single-element-in-a-sorted-array/ (540题)
 * <p>
 * - 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * - 请你找出并返回只出现一次的那个数。
 * - 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * <p>
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-14 02:52:43
 */
public class SingleElementInSortedArraySolution {
    public static void main(String[] args) {
        final SingleElementInSortedArraySolution solution = new SingleElementInSortedArraySolution();

        int[] nums = new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output single non-duplicate element (binary search) : " + solution.singleNonDuplicate(nums));
        System.out.println("Output single non-duplicate element (bit operation 1) : " + solution.singleNonDuplicateBitOperation1(nums));
        System.out.println("Output single non-duplicate element (bit operation 2) : " + solution.singleNonDuplicateBitOperation2(nums));
        System.out.println();
        nums = new int[]{3, 3, 7, 7, 10, 11, 11};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output single non-duplicate element (binary search) : " + solution.singleNonDuplicate(nums));
        System.out.println("Output single non-duplicate element (bit operation 1) : " + solution.singleNonDuplicateBitOperation1(nums));
        System.out.println("Output single non-duplicate element (bit operation 2) : " + solution.singleNonDuplicateBitOperation2(nums));
    }

    /**
     * 数组中的元素不会出现超过2次，并且只有一个元素出现1次，其余都是2次
     * - 当数组有序时，所求元素的下标是相同元素的开始下标由偶数转为奇数的分界
     * - 时间复杂度 O(logN)
     * - 空间复杂度 O(1)
     *
     * @param nums
     * @return
     */
    int singleNonDuplicate(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            // 当mid为奇数时，mid^1=mid-1 (结果元素左边的场景-mid为相同元素的第2个)，当mid为偶数时，mid^1=mid+1 (结果元素左边的场景-mid为相同元素的第1个)
            if (nums[mid] == nums[mid ^ 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[right];
    }

    /**
     * 根据位运算异或运算的特点，2个相同元素异或运算的结果为0，则所有元素位运算后的结果即为答案元素 (或者说是第一个让一个连续2个元素异或结果不为0，其中第1个元素即为结果元素)
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param nums
     * @return
     */
    int singleNonDuplicateBitOperation1(final int[] nums) {
        int ans = 0;
        for (int value : nums) {
            ans ^= value;
        }
        return ans;
    }

    int singleNonDuplicateBitOperation2(final int[] nums) {
        for (int i = 0; i < nums.length - 1; i += 2) {
            if ((nums[i] ^ nums[i + 1]) != 0) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }
}
