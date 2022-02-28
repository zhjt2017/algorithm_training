package com.speed.week9;

import java.util.Arrays;

/**
 * 算法实现：；连接所有点的最小费用
 * - https://leetcode-cn.com/problems/min-cost-to-connect-all-points/ (1584题)
 * <p>
 * - 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * <p>
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * <p>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * <p>
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * <p>
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 * <p>
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 * <p>
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 * <p>
 * 输入：points = [[0,0]]
 * 输出：0
 *  
 * 提示：
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * 所有点 (xi, yi) 两两不同。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-28 02:29:21
 */
public class MinCostToConnectAllPointsSolution {
    public static void main(String[] args) {
        final MinCostToConnectAllPointsSolution solution = new MinCostToConnectAllPointsSolution();

        int[][] points = new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}};
        System.out.println("Input points : " + Arrays.deepToString(points));
        System.out.println("Output minimum cost to connect all points : " + solution.minCostConnectPoints(points));
        System.out.println();

        points = new int[][]{{3, 12}, {-2, 5}, {-4, 1}};
        System.out.println("Input points : " + Arrays.deepToString(points));
        System.out.println("Output minimum cost to connect all points : " + solution.minCostConnectPoints(points));
        System.out.println();

        points = new int[][]{{0, 0}, {1, 1}, {1, 0}, {-1, 1}};
        System.out.println("Input points : " + Arrays.deepToString(points));
        System.out.println("Output minimum cost to connect all points : " + solution.minCostConnectPoints(points));
        System.out.println();

        points = new int[][]{{-1000000, -1000000}, {1000000, 1000000}};
        System.out.println("Input points : " + Arrays.deepToString(points));
        System.out.println("Output minimum cost to connect all points : " + solution.minCostConnectPoints(points));
        System.out.println();
    }

    int minCostConnectPoints(final int[][] points) {
        return 0;
    }


}
