package com.teachingpractice.week5;

import java.util.Arrays;

/**
 * 算法实现: 二分答案 - 分割数组, 使得能够获得最小的 子数组元素和的最大值
 * - https://leetcode-cn.com/problems/split-array-largest-sum/ (410题)
 * <p>
 * - 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 * - 设计一个算法使得这 m 个子数组各自和的最大值最小。
 * - 返回子数组元素和的最大值
 * <p>
 * - 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。
 * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * <p>
 * - 输入：nums = [1,2,3,4,5], m = 2
 * 输出：9
 * <p>
 * - 输入：nums = [1,4,4], m = 3
 * 输出：4
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= m <= min(50, nums.length)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-06 06:22:58
 */
public class SplitArrayMinLargestSumSolution {
    public static void main(String[] args) {
        final SplitArrayMinLargestSumSolution solution = new SplitArrayMinLargestSumSolution();

        int[] nums = new int[]{7, 2, 5, 10, 8};
        int m = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", m : " + m);
        System.out.println("Output min of largest subArray sum : " + solution.splitArray(nums, m));

        nums = new int[]{1, 2, 3, 4, 5};
        m = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", m : " + m);
        System.out.println("Output min of largest subArray sum : " + solution.splitArray(nums, m));

        nums = new int[]{1, 4, 4};
        m = 3;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", m : " + m);
        System.out.println("Output min of largest subArray sum : " + solution.splitArray(nums, m));
    }

    /**
     * 二分答案 + 判定 (总的时间复杂度: O(N*logN), 空间复杂度: O(1))
     *
     * @param nums
     * @param m
     * @return
     */
    private int splitArray(final int[] nums, final int m) {
        // 初始left边界为单个最大元素的值, right边界为所有元素之和 (如果m=1, 则直接返回该值), 然后在[left, right]范围内作为盒子的大小, 求第一个(最小值)validate成功的盒子大小
        int left = 0;
        int right = 0;
        for (final int value : nums) {
            left = max(left, value);
            right += value;
        }
        if (m == 1 || nums.length <= 1) {
            return right;
        }

        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (validate(nums, m, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 判定: 猜测盒子的大小为boxSize, 判定m个那么大的盒子, 是否能够装下整个nums的元素
     * - 时间复杂度: O(n)
     *
     * @param nums
     * @param m
     * @param boxSize 确保此值至少能够单独放下nums中最大元素
     * @return 满足true的最小的boxSize值即为整个题的最终题解
     */
    private boolean validate(final int[] nums, final int m, final int boxSize) {
        int boxSum = 0;
        int count = 1;
        for (final int value : nums) {
            boxSum += value;
            if (boxSum > boxSize) {
                count++;
                boxSum = value;
            }
            if (count > m) {
                return false;
            }
        }
        return true;
    }

    private int max(final int a, final int b) {
        return a >= b ? a : b;
    }
}
