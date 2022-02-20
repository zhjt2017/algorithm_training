package com.teachingpractice.week6;

import java.util.Arrays;

/**
 * 算法实现: 动态规划 - 零钱兑换问题 (这其实是一个生活日常问题, 彻底拿下它)
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
 * @see com.speed.week6.CoinChangeSolution
 * @since 2022-02-17 09:55:11
 */
public class CoinChangeSolution {
    public static void main(String[] args) {
        final CoinChangeSolution solution = new CoinChangeSolution();

        int[] coins = new int[]{1, 2, 5};
        int amount = 11;
        System.out.println("Input coins : " + Arrays.toString(coins) + ", amount : " + amount);
        System.out.println("Output min count : " + solution.coinChange(coins, amount));
        System.out.println();

        coins = new int[]{2};
        amount = 3;
        System.out.println("Input coins : " + Arrays.toString(coins) + ", amount : " + amount);
        System.out.println("Output min count : " + solution.coinChange(coins, amount));
        System.out.println();

        coins = new int[]{1};
        amount = 0;
        System.out.println("Input coins : " + Arrays.toString(coins) + ", amount : " + amount);
        System.out.println("Output min count : " + solution.coinChange(coins, amount));
    }

    /**
     * 自我解法1：贪心 + 回溯 + 剪枝 (当给定的硬币面额不是成倍数时，贪心可能没有动态规划性能好)
     * - 放弃
     * - 理由1：时间复杂度：O(N!) 太大
     * - 理由2：当给定的硬币面额不是成倍数时，贪心无法证明，并且得到的解可能是错误的：比如amount=18, coins=[4,9,10], 贪心得到结果[10, 4, 4]=3, 实际的解应该是[9, 9]=2
     *
     * @param coins
     * @param amount
     * @return
     */
    int coinChangeGreedy(final int[] coins, final int amount) {
        if (amount == 0) {
            return 0;
        }
        Arrays.sort(coins);
        System.out.println("Greedy is not competent...");
        return -1;
    }

    /**
     * 动态规划 - 递推实现
     * - 原始状态：剩余金额、已用硬币枚数，目标：到达终点 (0元)
     * - 新状态：剩余金额，最优化目标：硬币枚数最少 (通过最优化目标剪除不是最优解的分支) (这里的二元问题也变成了一元的问题)
     * - 推导关系：coins = [1, 9, 10], opt(n) = min(opt(n-1), opt(n-9), opt(n-10)) + 1 (opt : optimization)
     * <p>
     * - 时间复杂度 O(NK)
     * - 空间复杂度 O(N)
     *
     * @param coins
     * @param amount
     * @return
     */
    int coinChange(final int[] coins, final int amount) {
        final int[] opt = new int[amount + 1];
        opt[0] = 0;
        // 统一设置上限值，待后面刷新为最优化目标的硬币数
        for (int i = 1; i <= amount; i++) {
            opt[i] = amount + 1;
        }
        for (int remaining = 1; remaining <= amount; remaining++) {
            for (final int denomination : coins) {
                if (remaining >= denomination) {
                    opt[remaining] = Math.min(opt[remaining], opt[remaining - denomination] + 1);
                }
            }
        }
        return opt[amount] > amount ? -1 : opt[amount];
    }
}
