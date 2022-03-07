package com.teachingpractice.week7;

import java.util.Arrays;

/**
 * 算法实现：动态规划 - 区间动态规划 - 合并石头的最低成本
 * - https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/ (1000题)
 * <p>
 * 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
 * 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 * <p>
 * - 输入：stones = [3,2,4,1], K = 2
 * 输出：20
 * 解释：
 * 从 [3, 2, 4, 1] 开始。
 * 合并 [3, 2]，成本为 5，剩下 [5, 4, 1]。
 * 合并 [4, 1]，成本为 5，剩下 [5, 5]。
 * 合并 [5, 5]，成本为 10，剩下 [10]。
 * 总成本 20，这是可能的最小值。
 * <p>
 * - 输入：stones = [3,2,4,1], K = 3
 * 输出：-1
 * 解释：任何合并操作后，都会剩下 2 堆，我们无法再进行合并。所以这项任务是不可能完成的。
 * <p>
 * - 输入：stones = [3,5,1,2,6], K = 3
 * 输出：25
 * 解释：
 * 从 [3, 5, 1, 2, 6] 开始。
 * 合并 [5, 1, 2]，成本为 8，剩下 [3, 8, 6]。
 * 合并 [3, 8, 6]，成本为 17，剩下 [17]。
 * 总成本 25，这是可能的最小值。
 * <p>
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 08:44:27
 */
public class MinCostToMergeStonesSolution {
    public static void main(String[] args) {
        final MinCostToMergeStonesSolution solution = new MinCostToMergeStonesSolution();

        int[] stones = new int[]{3, 2, 4, 1};
        int k = 2;
        System.out.println("Input stones : " + Arrays.toString(stones) + ", k : " + k);
        System.out.println("Output min cost to merge stones : " + solution.mergeStones(stones, k));
        System.out.println();

        k = 3;
        System.out.println("Input stones : " + Arrays.toString(stones) + ", k : " + k);
        System.out.println("Output min cost to merge stones : " + solution.mergeStones(stones, k));
        System.out.println();

        stones = new int[]{3, 5, 1, 2, 6};
        System.out.println("Input stones : " + Arrays.toString(stones) + ", k : " + k);
        System.out.println("Output min cost to merge stones : " + solution.mergeStones(stones, k));
    }

    int mergeStones(final int[] stones, final int k) {
        return 0;
    }
}
