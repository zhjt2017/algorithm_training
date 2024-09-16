package com.speed.weeka;

import java.util.Arrays;

/**
 * 算法实现：接雨水
 * - https://leetcode-cn.com/problems/trapping-rain-water/ (42题)
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * - 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * <p>
 * - 输入：height = [4,2,0,3,2,5]
 * 输出：9
 * <p>
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-13 09:40:29
 */
public class TrapRainWaterSolution {
    public static void main(String[] args) {
        final TrapRainWaterSolution solution = new TrapRainWaterSolution();

        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Input height : " + Arrays.toString(height));
        System.out.println("Output trap rain water amount : " + solution.trap(height));
        System.out.println();

        height = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println("Input height : " + Arrays.toString(height));
        System.out.println("Output trap rain water amount : " + solution.trap(height));
        System.out.println();
    }

    int trap(final int[] height) {
        return 0;
    }
}
