package com.teachingpractice.week2;

import java.util.Arrays;

/**
 * TwoSumSolution的特例，给定的整数数组是已经按大小排好序的 (这里不妨设为递增排序)
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-06 08:01:28
 */
public class TwoSumOrderedSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println("nums = " + Arrays.toString(nums) + ",target = " + target);
        System.out.println("result : " + Arrays.toString(twoSum(nums, target)));

        nums = new int[]{10, 26, 30, 31, 47, 60};
        target = 40;
        System.out.println("nums = " + Arrays.toString(nums) + ",target = " + target);
        System.out.println("result : " + Arrays.toString(twoSum(nums, target)));
    }

    /**
     * 设计思想：Hash的思想可以优化为双指针，向内测靠拢，称为“对撞双指针”
     * - 索引i从左侧开始，索引j从右侧开始，基于每个被放弃掉的i或者j都是相当于已经遍历过另一半即可保证该索引的值一定不是一个答案，从而保证正确性
     * 总体时间复杂度：O(n)
     * 总体空间复杂度：O(1)
     *
     * @param nums   输入方自己保证2个已上的元素
     * @param target
     * @return
     */
    private static int[] twoSum(final int[] nums, final int target) {
        int i = 0;
        int j = nums.length - 1;
        int sum;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{nums[i], nums[j]};
            }
            if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{};
    }
}
