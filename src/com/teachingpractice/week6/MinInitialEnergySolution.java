package com.teachingpractice.week6;

import java.util.Arrays;

/**
 * 算法实现：贪心 - 完成所有任务的最少初始能量 (minimum initial energy to finish tasks)
 * - https://leetcode-cn.com/problems/minimum-initial-energy-to-finish-tasks/ (1665题)
 * <p>
 * - 给你一个任务数组 tasks ，其中 tasks[i] = [actuali, minimumi] ：
 * - actuali 是完成第 i 个任务 需要耗费 的实际能量。
 * - minimumi 是开始第 i 个任务前需要达到的最低能量。
 * - 比方说，如果任务为 [10, 12] 且你当前的能量为 11 ，那么你不能开始这个任务。如果你当前的能量为 13 ，你可以完成这个任务，且完成它后剩余能量为 3 。
 * - 你可以按照 任意顺序 完成任务。
 * - 请你返回完成所有任务的 最少 初始能量。
 * <p>
 * - 输入：tasks = [[1,2],[2,4],[4,8]]
 * 输出：8
 * 解释：
 * 一开始有 8 能量，我们按照如下顺序完成任务：
 * - 完成第 3 个任务，剩余能量为 8 - 4 = 4 。
 * - 完成第 2 个任务，剩余能量为 4 - 2 = 2 。
 * - 完成第 1 个任务，剩余能量为 2 - 1 = 1 。
 * 注意到尽管我们有能量剩余，但是如果一开始只有 7 能量是不能完成所有任务的，因为我们无法开始第 3 个任务。
 * <p>
 * - 输入：tasks = [[1,3],[2,4],[10,11],[10,12],[8,9]]
 * 输出：32
 * 解释：
 * 一开始有 32 能量，我们按照如下顺序完成任务：
 * - 完成第 1 个任务，剩余能量为 32 - 1 = 31 。
 * - 完成第 2 个任务，剩余能量为 31 - 2 = 29 。
 * - 完成第 3 个任务，剩余能量为 29 - 10 = 19 。
 * - 完成第 4 个任务，剩余能量为 19 - 10 = 9 。
 * - 完成第 5 个任务，剩余能量为 9 - 8 = 1 。
 * <p>
 * - 输入：tasks = [[1,7],[2,8],[3,9],[4,10],[5,11],[6,12]]
 * 输出：27
 * 解释：
 * 一开始有 27 能量，我们按照如下顺序完成任务：
 * - 完成第 5 个任务，剩余能量为 27 - 5 = 22 。
 * - 完成第 2 个任务，剩余能量为 22 - 2 = 20 。
 * - 完成第 3 个任务，剩余能量为 20 - 3 = 17 。
 * - 完成第 1 个任务，剩余能量为 17 - 1 = 16 。
 * - 完成第 4 个任务，剩余能量为 16 - 4 = 12 。
 * - 完成第 6 个任务，剩余能量为 12 - 6 = 6 。
 * <p>
 * - 1 <= tasks.length <= 10^5
 * 1 <= actual​i <= minimumi <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-19 09:40:49
 */
public class MinInitialEnergySolution {
    public static void main(String[] args) {
        final MinInitialEnergySolution solution = new MinInitialEnergySolution();

        int[][] tasks = new int[][]{{1, 2}, {2, 4}, {4, 8}};
        System.out.println("Input tasks : " + Arrays.deepToString(tasks));
        System.out.println("Output minimum initial energy : " + solution.minEffort(tasks));
        System.out.println();

        tasks = new int[][]{{1, 3}, {2, 4}, {10, 11}, {10, 12}, {8, 9}};
        System.out.println("Input tasks : " + Arrays.deepToString(tasks));
        System.out.println("Output minimum initial energy : " + solution.minEffort(tasks));
        System.out.println();

        tasks = new int[][]{{1, 7}, {2, 8}, {3, 9}, {4, 10}, {5, 11}, {6, 12}};
        System.out.println("Input tasks : " + Arrays.deepToString(tasks));
        System.out.println("Output minimum initial energy : " + solution.minEffort(tasks));
        System.out.println();

        tasks = new int[][]{{1, 1}, {1, 4}, {1, 4}};
        System.out.println("Input tasks : " + Arrays.deepToString(tasks));
        System.out.println("Output minimum initial energy : " + solution.minEffort(tasks));
        System.out.println();

        tasks = new int[][]{{1, 2}, {2, 6}, {4, 8}};
        System.out.println("Input tasks : " + Arrays.deepToString(tasks));
        System.out.println("Output minimum initial energy : " + solution.minEffort(tasks));
    }

    /**
     * 我意：实际耗费能量必须是累加的，然后就是让任务开始的最低能量，取最大的额外能量，也就是整个桶最短的那块板子的性质，成为了整个任务列表全部任务开始的额外能量
     * - 自我定义：一个任务的额外能量 = 开始一个任务的最低能量 - 该任务实际耗费能量
     * - 但实际上，这是在需要最大的额外能量的任务最后一个执行时，我们的答案
     * - 如果先执行额外能量大的那些，那么就可以将额外能量在任务完成后用于其他任务：所以这个题本质还是：求一个顺序 (到底按什么顺序可以使得更多的额外能量可以更多地分摊到小的任务上)
     * - 显然，按额外能量降序执行 (贪心策略)，即可实现总需要的初始能量最少 (此处我们是按照额外能量的这个含义来进行说明的，当然，这个还可以使用严谨的数学归纳法/邻项交换进行证明，这里略过，见文档笔记)
     * - 至于这个初始能量到底是多少，我们反过来进行计算
     * <p>
     * - 时间复杂度 O(NlogN)
     * - 空间复杂度 O(logN)
     *
     * @param tasks
     * @return
     */
    int minEffort(final int[][] tasks) {
        // 按额外能量升序 (如果额外相同的，先计算任务最低开始能量小的) (这里我们只是写 (a, b) -> a[1] - a[0] - b[1] + b[0] 执行效果是一致的)
        // (这一点是可以证明的：max(max(a[1], s + a[0]) + b[0], b[1]) 与 max(max(b[1], s + b[0]) + a[0], a[1]) 是相等的 (在a[1]=a[0]+k, b[1]=b[0]+k的条件下)
        // max(max(a[0]+k, s + a[0]) + b[0], b[0]+k) == max(max(b[0]+k, s + b[0]) + a[0], a[0]+k)
        Arrays.sort(tasks, (a, b) -> (a[1] - a[0] == b[1] - b[0] ? a[1] - b[1] : a[1] - a[0] - b[1] + b[0]));
        int ans = 0;
        for (final int[] task : tasks) {
            ans = Math.max(task[1], ans + task[0]);
        }
        return ans;
    }
}
