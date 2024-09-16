package com.teachingpractice.weeka;

import java.util.Arrays;

/**
 * 算法实现：期末串讲 - 包含每个查询的最小区间
 * - https://leetcode-cn.com/problems/minimum-interval-to-include-each-query/ (1851题)
 * <p>
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 lefti 、结束于 righti（包含两侧取值，闭区间）。
 * 区间的 长度 定义为区间中包含的整数数目，更正式地表达是 righti - lefti + 1 。
 * <p>
 * 再给你一个整数数组 queries 。
 * 第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小区间 i 的长度 。
 * 如果不存在这样的区间，那么答案是 -1 。
 * <p>
 * 以数组形式返回对应查询的所有答案。
 * <p>
 * - 输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * 输出：[3,3,1,4]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
 * - Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
 * <p>
 * - 输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * 输出：[2,-1,4,6]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
 * - Query = 19：不存在包含 19 的区间，答案为 -1 。
 * - Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
 * - Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
 * <p>
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 10:25:08
 */
public class MinIntervalIncludeEachQuerySolution {
    public static void main(String[] args) {
        final MinIntervalIncludeEachQuerySolution solution = new MinIntervalIncludeEachQuerySolution();

        int[][] intervals = new int[][]{{1, 4}, {2, 4}, {3, 6}, {4, 4}};
        int[] queries = new int[]{2, 3, 4, 5};
        System.out.println("Input intervals : " + Arrays.deepToString(intervals) + ", queries : " + Arrays.toString(queries));
        System.out.println("Output min interval : " + Arrays.toString(solution.minInterval(intervals, queries)));
        System.out.println();

        intervals = new int[][]{{2, 3}, {2, 5}, {1, 8}, {20, 25}};
        queries = new int[]{2, 19, 5, 22};
        System.out.println("Input intervals : " + Arrays.deepToString(intervals) + ", queries : " + Arrays.toString(queries));
        System.out.println("Output min interval : " + Arrays.toString(solution.minInterval(intervals, queries)));
        System.out.println();

    }

    int[] minInterval(final int[][] intervals, final int[] queries) {
        return new int[]{};
    }
}
