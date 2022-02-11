package com.speed.week6;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现: 零钱兑换问题 (这其实是一个生活日常问题, 彻底拿下它)
 * - https://leetcode-cn.com/problems/coin-change/ (322题)
 * <p>
 * - 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * - 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * - 你可以认为每种硬币的数量是无限的。
 * <p>
 * - 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * <p>
 * - 输入：coins = [2], amount = 3
 * 输出：-1
 * <p>
 * - 输入：coins = [1], amount = 0
 * 输出：0
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-09 04:29:29
 */
public class CoinChangeSolution {
    public static void main(String[] args) {
        final CoinChangeSolution solution = new CoinChangeSolution();

        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println("Input coins : " + Arrays.toString(coins) + ", amount : " + amount);
        System.out.println("Output min count : " + solution.gainCount(solution.coinChange(coins, amount), amount));
        System.out.println();

        coins = new int[]{2};
        amount = -1;
        System.out.println("Input coins : " + Arrays.toString(coins) + ", amount : " + amount);
        System.out.println("Output min count : " + solution.gainCount(solution.coinChange(coins, amount), amount));
        System.out.println();

        coins = new int[]{1};
        amount = 0;
        System.out.println("Input coins : " + Arrays.toString(coins) + ", amount : " + amount);
        System.out.println("Output min count : " + solution.gainCount(solution.coinChange(coins, amount), amount));
    }

    /**
     * coins这些面额, 每种面额的硬币其数量都是充足的, 给定总金额amount, 希望获得可出手的硬币组合
     * (如果找不到任何一种可用的组合, 返回空数组, 如果有, 返回所需硬币个数最小的那种组合, 如果有多种组合并列硬币个数最少, 都返回)
     *
     * @param coins
     * @param amount
     * @return
     */
    List<int[]> coinChange(final int[] coins, final int amount) {
        final List<int[]> ans = new LinkedList<>();

        System.out.println("ans : " + ans);
        return ans;
    }

    private int gainCount(final List<int[]> change, final int amount) {
        if (amount == 0) {
            return 0;
        }
        if (change.isEmpty()) {
            return -1;
        }
        return change.get(0).length;
    }
}
