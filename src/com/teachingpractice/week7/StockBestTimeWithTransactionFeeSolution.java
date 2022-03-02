package com.teachingpractice.week7;

import java.util.Arrays;

/**
 * 线性动态规划 - 买卖股票系列问题 - 买卖股票的最佳时机含手续费
 * - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/ (714题)
 * <p>
 * 给定一个整数数组 prices，其中 prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * - 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * <p>
 * - 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 * <p>
 * 1 <= prices.length <= 5 * 10^4
 * 1 <= prices[i] < 5 * 10^4
 * 0 <= fee < 5 * 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-01 08:17:29
 */
public class StockBestTimeWithTransactionFeeSolution {
    public static void main(String[] args) {
        final StockBestTimeWithTransactionFeeSolution solution = new StockBestTimeWithTransactionFeeSolution();


        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", fee : " + fee);
        System.out.println("Output max profit : " + solution.maxProfit(prices, fee));
        System.out.println();

        prices = new int[]{1, 3, 7, 5, 10, 3};
        fee = 3;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", fee : " + fee);
        System.out.println("Output max profit : " + solution.maxProfit(prices, fee));
        System.out.println();
    }

    int maxProfit(final int[] prices, final int fee) {
        return 0;
    }
}
