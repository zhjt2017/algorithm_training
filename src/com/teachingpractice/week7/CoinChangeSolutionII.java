package com.teachingpractice.week7;

/**
 * 算法实现：动态规划 - 背包问题 - 零钱兑换II
 * - https://leetcode-cn.com/problems/coin-change-2/ (518题)
 * <p>
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。 
 * 题目数据保证结果符合 32 位带符号整数。
 * <p>
 * - 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * <p>
 * - 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 * <p>
 * - 输入：amount = 10, coins = [10]
 * 输出：1
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 08:13:46
 */
public class CoinChangeSolutionII {
    public static void main(String[] args) {
        final CoinChangeSolutionII solution = new CoinChangeSolutionII();

        int amount = 5;
        int[] coins = new int[]{1, 2, 5};
        System.out.println("Input amount : " + amount + ", coins : " + solution.change(amount, coins));
        System.out.println("Output change ways : " + solution.change(amount, coins));
        System.out.println();

        amount = 3;
        coins = new int[]{2};
        System.out.println("Input amount : " + amount + ", coins : " + solution.change(amount, coins));
        System.out.println("Output change ways : " + solution.change(amount, coins));
        System.out.println();

        amount = 10;
        coins = new int[]{10};
        System.out.println("Input amount : " + amount + ", coins : " + solution.change(amount, coins));
        System.out.println("Output change ways : " + solution.change(amount, coins));
        System.out.println();
    }

    int change(final int amount, final int[] coins) {
        return 0;
    }
}
