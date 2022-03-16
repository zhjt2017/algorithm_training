package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法训练(2022-03-15) 统计 按位或 能得到最大值的子集数目
 * - https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets/ (2044)
 * <p>
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * <p>
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * <p>
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 * <p>
 * - 输入：nums = [3,1]
 * 输出：2
 * 解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
 * - [3]
 * - [3,1]
 * <p>
 * - 输入：nums = [2,2,2]
 * 输出：7
 * 解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 23 - 1 = 7 个子集。
 * <p>
 * - 输入：nums = [3,2,1,5]
 * 输出：6
 * 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 * <p>
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-15 03:14:52
 */
public class CountOfMaxBitwiseOrSubsetsSolution {
    public static void main(String[] args) {
        final CountOfMaxBitwiseOrSubsetsSolution solution = new CountOfMaxBitwiseOrSubsetsSolution();

        int[] nums = new int[]{3, 1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output count of maximum bitwise or subsets : " + solution.countMaxOrSubsets(nums));
        System.out.println();

        nums = new int[]{2, 2, 2};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output count of maximum bitwise or subsets : " + solution.countMaxOrSubsets(nums));
        System.out.println();

        nums = new int[]{3, 2, 1, 5};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output count of maximum bitwise or subsets : " + solution.countMaxOrSubsets(nums));
        System.out.println();
    }

    /**
     * 我的初始题解: dfs + 剪枝
     * - 时间复杂度 O(2^n)
     * - 空间复杂度 O(n)
     *
     * @param nums
     * @return
     */
    int countMaxOrSubsets(final int[] nums) {
        this.nums = nums;
        n = nums.length;
        currentValue = 0;
        count = 0;
        maxValue = 0;
        for (final int value : nums) {
            maxValue |= value;
        }

        dfs(0);
        return count;
    }

    void dfs(final int index) {
        if (index >= n) {
            return;
        }
        // 当前位置上的值不参与 按位或
        dfs(index + 1);
        // 当前位置上的值参与 按位或
        int temp = currentValue;
        currentValue |= nums[index];
        if (currentValue == maxValue) {
            count += (1 << (n - 1 - index));
        } else {
            dfs(index + 1);
        }
        currentValue = temp;
    }

    private int[] nums;
    private int n;
    private int count;
    private int maxValue;
    private int currentValue;
}
