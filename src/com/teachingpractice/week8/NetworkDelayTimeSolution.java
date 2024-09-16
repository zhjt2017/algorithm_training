package com.teachingpractice.week8;

import java.util.Arrays;

/**
 * 算法实现：图论算法 - 最短路径问题 - 网络延迟时间
 * - https://leetcode-cn.com/problems/network-delay-time/ (743题)
 * <p>
 * 有 n 个网络节点，标记为 1 到 n。
 * <p>
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 
 * times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * <p>
 * - 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * <p>
 * - 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * <p>
 * - 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * <p>
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 06:04:20
 */
public class NetworkDelayTimeSolution {
    public static void main(String[] args) {
        final NetworkDelayTimeSolution solution = new NetworkDelayTimeSolution();

        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;
        System.out.println("Input times : " + Arrays.deepToString(times) + ", n : " + n + ", k : " + k);
        System.out.println("Output network delay time : " + solution.networkDelayTime(times, n, k));
        System.out.println();

        times = new int[][]{{1, 2, 1}};
        n = 2;
        k = 1;
        System.out.println("Input times : " + Arrays.deepToString(times) + ", n : " + n + ", k : " + k);
        System.out.println("Output network delay time : " + solution.networkDelayTime(times, n, k));
        System.out.println();

        times = new int[][]{{1, 2, 1}};
        n = 2;
        k = 2;
        System.out.println("Input times : " + Arrays.deepToString(times) + ", n : " + n + ", k : " + k);
        System.out.println("Output network delay time : " + solution.networkDelayTime(times, n, k));
        System.out.println();

    }

    /**
     * 属于经典的单源最短路径，使用Bellman-Ford最短路径算法
     * - 时间复杂度 O(nm)
     * - 空间复杂度 O(n)
     * - 使用[]times对n个网络节点进行一轮一轮(r)的更新，最多n-1轮，最后再校验一轮，发现不用再更新时，完成
     *
     * @param times
     * @param n
     * @param k
     * @return
     */
    int networkDelayTime(final int[][] times, final int n, final int k) {
        final int[] dist = new int[n + 1];
        Arrays.fill(dist, BIG);
        dist[k] = 0;
        for (int r = 1; r < n; r++) {
            boolean immutable = true;
            for (final int[] t : times) {
                int u = dist[t[0]] + t[2];
                if (u < dist[t[1]]) {
                    dist[t[1]] = u;
                    immutable = false;
                }
            }
            if (immutable) {
                break;
            }
        }
        int ans = -1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == BIG) {
                return -1;
            }
            if (dist[i] > ans) {
                ans = dist[i];
            }
        }
        return ans;
    }

    private static final int BIG = (int) 1e9;
}
