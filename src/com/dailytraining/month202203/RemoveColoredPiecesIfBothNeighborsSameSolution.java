package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-22) 如果相邻两个颜色均相同则删除当前颜色
 * - https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color/ (2038题)
 * <p>
 * 总共有 n 个颜色片段排成一列，每个颜色片段要么是 'A' 要么是 'B' 。给你一个长度为 n 的字符串 colors ，其中 colors[i] 表示第 i 个颜色片段的颜色。
 * <p>
 * Alice 和 Bob 在玩一个游戏，他们 轮流 从这个字符串中删除颜色。Alice 先手 。
 * <p>
 * 如果一个颜色片段为 'A' 且 相邻两个颜色 都是颜色 'A' ，那么 Alice 可以删除该颜色片段。Alice 不可以 删除任何颜色 'B' 片段。
 * 如果一个颜色片段为 'B' 且 相邻两个颜色 都是颜色 'B' ，那么 Bob 可以删除该颜色片段。Bob 不可以 删除任何颜色 'A' 片段。
 * Alice 和 Bob 不能 从字符串两端删除颜色片段。
 * 如果其中一人无法继续操作，则该玩家 输 掉游戏且另一玩家 获胜 。
 * 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回 true，否则 Bob 获胜，返回 false。
 * <p>
 * - 输入：colors = "AAABABB"
 * 输出：true
 * 解释：
 * AAABABB -> AABABB
 * Alice 先操作。
 * 她删除从左数第二个 'A' ，这也是唯一一个相邻颜色片段都是 'A' 的 'A' 。
 * 现在轮到 Bob 操作。
 * Bob 无法执行任何操作，因为没有相邻位置都是 'B' 的颜色片段 'B' 。
 * 因此，Alice 获胜，返回 true 。
 * <p>
 * - 输入：colors = "AA"
 * 输出：false
 * 解释：
 * Alice 先操作。
 * 只有 2 个 'A' 且它们都在字符串的两端，所以她无法执行任何操作。
 * 因此，Bob 获胜，返回 false 。
 * <p>
 * - 输入：colors = "ABBBBBBBAAA"
 * 输出：false
 * 解释：
 * ABBBBBBBAAA -> ABBBBBBBAA
 * Alice 先操作。
 * 她唯一的选择是删除从右数起第二个 'A' 。
 * <p>
 * ABBBBBBBAA -> ABBBBBBAA
 * 接下来轮到 Bob 操作。
 * 他有许多选择，他可以选择任何一个 'B' 删除。
 * <p>
 * 然后轮到 Alice 操作，她无法删除任何片段。
 * 所以 Bob 获胜，返回 false 。
 * <p>
 * 1 <= colors.length <= 10^5
 * colors 只包含字母 'A' 和 'B'
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-22 09:20:48
 */
public class RemoveColoredPiecesIfBothNeighborsSameSolution {
    public static void main(String[] args) {
        final RemoveColoredPiecesIfBothNeighborsSameSolution solution = new RemoveColoredPiecesIfBothNeighborsSameSolution();

        String colors = "AAABABB";
        System.out.println("Input colors : " + colors);
        System.out.println("Output winner of game : " + solution.winnerOfGame(colors));
        System.out.println();

        colors = "AA";
        System.out.println("Input colors : " + colors);
        System.out.println("Output winner of game : " + solution.winnerOfGame(colors));
        System.out.println();

        colors = "ABBBBBBBAAA";
        System.out.println("Input colors : " + colors);
        System.out.println("Output winner of game : " + solution.winnerOfGame(colors));
        System.out.println();
    }

    /**
     * 规则唯一：
     * 1、至少连续3个相同，在中间删除一个，也就是删完后剩下2个
     * 2、A和B的删除相互独立，互不影响
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     *
     * @param colors
     * @return
     */
    boolean winnerOfGame(final String colors) {
        int[] count = new int[2];
        int color = colors.charAt(0);
        int neighbor = 1;
        final int n = colors.length();
        for (int i = 1; i < n; i++) {
            if (color == colors.charAt(i)) {
                if (neighbor == 2) {
                    count[color - A]++;
                } else {
                    neighbor++;
                }
            } else {
                neighbor = 1;
                color = colors.charAt(i);
            }
        }
        // A优先操作，故至少多一个才表示赢
        return count[0] > count[1];
    }

    private static final char A = 'A';
    private static final char B = 'B';
}
