package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法训练(2022-03-20) 网络空闲的时刻
 * - https://leetcode-cn.com/problems/the-time-when-the-network-becomes-idle/ (2039题)
 * <p>
 * 给你一个有 n 个服务器的计算机网络，服务器编号为 0 到 n - 1 。
 * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示服务器 ui 和 vi 之间有一条信息线路，在 一秒 内它们之间可以传输 任意 数目的信息。
 * 再给你一个长度为 n 且下标从 0 开始的整数数组 patience 。
 * <p>
 * 题目保证所有服务器都是 相通 的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。
 * <p>
 * 编号为 0 的服务器是 主 服务器，其他服务器为 数据 服务器。
 * 每个数据服务器都要向主服务器发送信息，并等待回复。
 * 信息在服务器之间按 最优 线路传输，也就是说每个信息都会以 最少时间 到达主服务器。
 * 主服务器会处理 所有 新到达的信息并 立即 按照每条信息来时的路线 反方向 发送回复信息。
 * <p>
 * 在 0 秒的开始，所有数据服务器都会发送各自需要处理的信息。
 * 从第 1 秒开始，每 一秒最 开始 时，每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：
 * <p>
 * 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息。
 * 数据服务器 i 每 patience[i] 秒都会重发一条信息，也就是说，数据服务器 i 在上一次发送信息给主服务器后的 patience[i] 秒 后 会重发一条信息给主服务器。
 * 否则，该数据服务器 不会重发 信息。
 * 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 空闲 状态。
 * <p>
 * 请返回计算机网络变为 空闲 状态的 最早秒数 。
 * <p>
 * - 输入：edges = [[0,1],[1,2]], patience = [0,2,1]
 * 输出：8
 * 解释：
 * 0 秒最开始时，
 * - 数据服务器 1 给主服务器发出信息（用 1A 表示）。
 * - 数据服务器 2 给主服务器发出信息（用 2A 表示）。
 * <p>
 * 1 秒时，
 * - 信息 1A 到达主服务器，主服务器立刻处理信息 1A 并发出 1A 的回复信息。
 * - 数据服务器 1 还没收到任何回复。距离上次发出信息过去了 1 秒（1 < patience[1] = 2），所以不会重发信息。
 * - 数据服务器 2 还没收到任何回复。距离上次发出信息过去了 1 秒（1 == patience[2] = 1），所以它重发一条信息（用 2B 表示）。
 * <p>
 * 2 秒时，
 * - 回复信息 1A 到达服务器 1 ，服务器 1 不会再重发信息。
 * - 信息 2A 到达主服务器，主服务器立刻处理信息 2A 并发出 2A 的回复信息。
 * - 服务器 2 重发一条信息（用 2C 表示）。
 * ...
 * 4 秒时，
 * - 回复信息 2A 到达服务器 2 ，服务器 2 不会再重发信息。
 * ...
 * 7 秒时，回复信息 2D 到达服务器 2 。
 * <p>
 * 从第 8 秒开始，不再有任何信息在服务器之间传输，也不再有信息到达服务器。
 * 所以第 8 秒是网络变空闲的最早时刻。
 * <p>
 * - 输入：edges = [[0,1],[0,2],[1,2]], patience = [0,10,10]
 * 输出：3
 * 解释：数据服务器 1 和 2 第 2 秒初收到回复信息。
 * 从第 3 秒开始，网络变空闲。
 * <p>
 * n == patience.length
 * 2 <= n <= 10^5
 * patience[0] == 0
 * 对于 1 <= i < n ，满足 1 <= patience[i] <= 10^5
 * 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
 * edges[i].length == 2
 * 0 <= ui, vi < n
 * ui != vi
 * 不会有重边。
 * 每个服务器都直接或间接与别的服务器相连。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-20 12:13:26
 */
public class TimeNetworkBecomesIdleSolution {
    public static void main(String[] args) {
        final TimeNetworkBecomesIdleSolution solution = new TimeNetworkBecomesIdleSolution();

        int[][] edges = new int[][]{{0, 1}, {1, 2}};
        int[] patience = new int[]{0, 2, 1};
        System.out.println("Input edges : " + Arrays.deepToString(edges) + ", patience : " + Arrays.toString(patience));
        System.out.println("Output the time network becomes idle : " + solution.networkBecomesIdle(edges, patience));
        System.out.println();

        edges = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        patience = new int[]{0, 10, 10};
        System.out.println("Input edges : " + Arrays.deepToString(edges) + ", patience : " + Arrays.toString(patience));
        System.out.println("Output the time network becomes idle : " + solution.networkBecomesIdle(edges, patience));
        System.out.println();
    }

    /**
     * 典型的最短路径问题
     * - 使用Dijkstra求出每个服务器到达主服务器的最短路径 path
     * - 第一条消息发出后到接收到的时间 path * 2
     * - 如果path * 2 <= patience[i], 则该服务器不再涉及消息的时间就是 path * 2
     * - 如果path * 2 > patience[i], 则最后一次发出消息去往主服务器的时刻是 path * 2 - 1, 该服务器不再涉及消息的时间就是 2 * (path * 2 - 1)
     *
     * @param edges
     * @param patience
     * @return
     */
    int networkBecomesIdle(final int[][] edges, final int[] patience) {
        // TODO
        return 0;
    }
}
