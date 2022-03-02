package com.teachingpractice.week7;

import java.util.Arrays;

/**
 * 线性动态规划 - 买卖股票系列问题 - 买卖股票的最佳时机含冷冻期
 * - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/ (309题)
 * <p>
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * - 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * - 输入: prices = [1]
 * 输出: 0
 * <p>
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-01 08:29:37
 */
public class StockBestTimeWithCooldownSolution {
    public static void main(String[] args) {
        final StockBestTimeWithCooldownSolution solution = new StockBestTimeWithCooldownSolution();

        int[] prices = new int[]{1, 2, 3, 0, 2};
        int cooldown = 1;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", cooldown : " + cooldown);
        System.out.println("Output max profit : " + solution.maxProfit(prices, cooldown));
        System.out.println();

        prices = new int[]{1};
        System.out.println("Input prices : " + Arrays.toString(prices) + ", cooldown : " + cooldown);
        System.out.println("Output max profit : " + solution.maxProfit(prices, cooldown));
        System.out.println();
    }

    int maxProfit(final int[] prices, final int cooldown) {
        return 0;
    }
}
