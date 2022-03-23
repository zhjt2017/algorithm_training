package com.homework.week8;

import java.util.Arrays;

/**
 * 算法实现：并查集 - 冗余连接
 * - https://leetcode-cn.com/problems/redundant-connection/ (684题)
 *
 * <p>
 * - 树可以看成是一个连通且 无环 的 无向图。
 * <p>
 * - 给定往一棵 n 个节点 (节点值 1～n) 的树中添加一条边后的图。添加的边的两个顶点包含在 1 到 n 中间，且这条附加的边不属于树中已存在的边。
 * - 图的信息记录于长度为 n 的二维数组 edges ，edges[i] = [ai, bi] 表示图中在 ai 和 bi 之间存在一条边。
 * <p>
 * 请找出一条可以删去的边，删除后可使得剩余部分是一个有着 n 个节点的树。如果有多个答案，则返回数组 edges 中最后出现的边。
 * <p>
 * - 输入: edges = [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * <p>
 * - 输入: edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * <p>
 * - n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * edges 中无重复元素
 * 给定的图是连通的
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-23 07:23:58
 */
public class RedundantConnectionSolution {
    public static void main(String[] args) {
        final RedundantConnectionSolution solution = new RedundantConnectionSolution();
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        System.out.println("Input edges : " + Arrays.deepToString(edges));
        System.out.println("Output redundant (last) edge : " + Arrays.toString(solution.findRedundantConnection(edges)));

        edges = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        System.out.println("Input edges : " + Arrays.deepToString(edges));
        System.out.println("Output redundant (last) edge : " + Arrays.toString(solution.findRedundantConnection(edges)));
    }

    /**
     * Disjoint Sets解决连通性问题
     * - 树是无向无环图(n个节点，n-1条边)，多了一条附加的边之后(而没有新的节点)，就会出现环
     * 时间复杂度 O(nlogn)
     * 空间复杂度 O(n)
     *
     * @param edges
     * @return
     */
    private int[] findRedundantConnection(final int[][] edges) {
        final int n = edges.length;
        makeSets(n);
        for (final int[] edge : edges) {
            int f1 = find(edge[0]);
            int f2 = find(edge[1]);
            if (f1 == f2) {
                return edge;
            }
            fa[f1] = f2;
        }
        return new int[]{};
    }

    private int[] fa;

    private void makeSets(final int n) {
        fa = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            fa[i] = i;
        }
    }

    private int find(final int x) {
        if (fa[x] == x) {
            return x;
        }
        return find(fa[x]);
    }
}
