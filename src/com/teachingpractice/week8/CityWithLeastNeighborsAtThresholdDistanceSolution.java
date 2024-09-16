package com.teachingpractice.week8;

import java.util.Arrays;

/**
 * 算法实现：图论算法 - 最短路径问题 - 阈值距离内邻居最少的城市
 * - https://leetcode-cn.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/ (1334题)
 * <p>
 * 有 n 个城市，按从 0 到 n-1 编号。给你一个边数组 edges，
 * 其中 edges[i] = [fromi, toi, weighti] 代表 fromi 和 toi 两个城市之间的双向加权边，距离阈值是一个整数 distanceThreshold。
 * <p>
 * 返回能通过某些路径到达其他城市数目最少、且路径距离 最大 为 distanceThreshold 的城市。如果有多个这样的城市，则返回编号最大的城市。
 * <p>
 * 注意，连接城市 i 和 j 的路径的距离等于沿该路径的所有边的权重之和。
 * <p>
 * - 输入：n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * 输出：3
 * 解释：城市分布图如上。
 * 每个城市阈值距离 distanceThreshold = 4 内的邻居城市分别是：
 * 城市 0 -> [城市 1, 城市 2] 
 * 城市 1 -> [城市 0, 城市 2, 城市 3] 
 * 城市 2 -> [城市 0, 城市 1, 城市 3] 
 * 城市 3 -> [城市 1, 城市 2] 
 * 城市 0 和 3 在阈值距离 4 以内都有 2 个邻居城市，但是我们必须返回城市 3，因为它的编号最大。
 * <p>
 * - 输入：n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 * 输出：0
 * 解释：城市分布图如上。 
 * 每个城市阈值距离 distanceThreshold = 2 内的邻居城市分别是：
 * 城市 0 -> [城市 1] 
 * 城市 1 -> [城市 0, 城市 4] 
 * 城市 2 -> [城市 3, 城市 4] 
 * 城市 3 -> [城市 2, 城市 4]
 * 城市 4 -> [城市 1, 城市 2, 城市 3] 
 * 城市 0 在阈值距离 2 以内只有 1 个邻居城市。
 * <p>
 * 2 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= fromi < toi < n
 * 1 <= weighti, distanceThreshold <= 10^4
 * 所有 (fromi, toi) 都是不同的。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 06:26:58
 */
public class CityWithLeastNeighborsAtThresholdDistanceSolution {
    public static void main(String[] args) {
        final CityWithLeastNeighborsAtThresholdDistanceSolution solution = new CityWithLeastNeighborsAtThresholdDistanceSolution();

        int n = 4;
        int[][] edges = new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;
        System.out.println("Input n : " + n + ", edges : " + Arrays.deepToString(edges) + ", distance threshold : " + distanceThreshold);
        System.out.println("Output the city : " + solution.findTheCity(n, edges, distanceThreshold));
        System.out.println();

        n = 5;
        edges = new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}};
        distanceThreshold = 2;
        System.out.println("Input n : " + n + ", edges : " + Arrays.deepToString(edges) + ", distance threshold : " + distanceThreshold);
        System.out.println("Output the city : " + solution.findTheCity(n, edges, distanceThreshold));
        System.out.println();
    }

    int findTheCity(final int n, final int[][] edges, final int distanceThreshold) {
        return 0;
    }
}
