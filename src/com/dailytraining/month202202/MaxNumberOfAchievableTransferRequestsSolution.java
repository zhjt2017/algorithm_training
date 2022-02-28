package com.dailytraining.month202202;

import java.util.Arrays;

/**
 * 算法训练(2022-02-28) 最多可达成的换楼请求数目
 * <p>
 * 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 * <p>
 * 给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
 * <p>
 * 一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。意思是每栋楼 离开 的员工数目 等于 该楼 搬入 的员工数数目。比方说 n = 3 且两个员工要离开楼 0 ，一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
 * <p>
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 * <p>
 * 输入：n = 5, requests = [[0,1],[1,0],[0,1],[1,2],[2,0],[3,4]]
 * 输出：5
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
 * 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
 * 从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
 * 没有员工从楼 4 离开。
 * 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
 * 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
 * 所以最多可以满足 5 个请求。
 * <p>
 * 输入：n = 3, requests = [[0,0],[1,2],[2,1]]
 * 输出：3
 * 解释：请求列表如下：
 * 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
 * 从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
 * 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
 * 我们可以满足所有的请求。
 * <p>
 * 输入：n = 4, requests = [[0,3],[3,1],[1,2],[2,0]]
 * 输出：4
 * <p>
 * 1 <= n <= 20
 * 1 <= requests.length <= 16
 * requests[i].length == 2
 * 0 <= fromi, toi < n
 * 通过次数7,142提交次数11,768
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-28 10:49:46
 */
public class MaxNumberOfAchievableTransferRequestsSolution {
    public static void main(String[] args) {
        final MaxNumberOfAchievableTransferRequestsSolution solution = new MaxNumberOfAchievableTransferRequestsSolution();

        int n = 5;
        int[][] requests = new int[][]{{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}};
        System.out.println("Input requests : " + Arrays.deepToString(requests));
        System.out.println("Output maximum number of achievable transfer requests : " + solution.maxRequests(n, requests));
        System.out.println();

        n = 3;
        requests = new int[][]{{0, 0}, {1, 2}, {2, 1}};
        System.out.println("Input requests : " + Arrays.deepToString(requests));
        System.out.println("Output maximum number of achievable transfer requests : " + solution.maxRequests(n, requests));
        System.out.println();

        n = 4;
        requests = new int[][]{{0, 3}, {3, 1}, {1, 2}, {2, 0}};
        System.out.println("Input requests : " + Arrays.deepToString(requests));
        System.out.println("Output maximum number of achievable transfer requests : " + solution.maxRequests(n, requests));
        System.out.println();
    }

    /**
     * 想来想去，都必须要遍历所有情况，故时间复杂度 O(2^M) (其中判断一组选择是否符合从O(N)优化为O(1))
     * 空间复杂度 O(M+N)
     *
     * @param n
     * @param requests
     * @return
     */
    int maxRequests(final int n, final int[][] requests) {
        this.requests = requests;
        delta = new int[n];
        ans = 0;
        selectCount = 0;
        zeroChanges = 0;
        dfs(0);
        return ans;
    }

    private void dfs(final int index) {
        if (index == requests.length) {
            if (zeroChanges == 0) {
                ans = Math.max(ans, selectCount);
            }
            return;
        }

        // 不选
        dfs(index + 1);

        // 选 (需要还原现场)
        int lastZeroChanges = zeroChanges;
        selectCount++;
        int source = requests[index][0];
        int target = requests[index][1];
        if (delta[source] == 0) {
            zeroChanges++;
        }
        delta[source]--;
        delta[target]++;
        if (delta[target] == 0) {
            zeroChanges--;
        }
        dfs(index + 1);
        // 还原现场
        selectCount--;
        zeroChanges = lastZeroChanges;
        delta[source]++;
        delta[target]--;
    }

    private int[][] requests;
    private int[] delta;
    private int ans;
    private int selectCount;
    private int zeroChanges;
}
