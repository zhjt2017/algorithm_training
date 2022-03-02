package com.teachingpractice.week7;

import java.util.Arrays;

/**
 * 算法实现：动态规划 - 买卖股票的最佳时机 III
 * - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/ (123题)
 * <p>
 * - 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * - 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * - 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * - 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * - 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * - 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * - 输入：prices = [1]
 * 输出：0
 * <p>
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see StockBestTimeSolution
 * @since 2022-02-28 11:07:42
 */
public class StockBestTimeSolutionIII {
    public static void main(String[] args) {
        final StockBestTimeSolutionIII solution = new StockBestTimeSolutionIII();

        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println();

        prices = new int[]{1, 2, 3, 4, 5};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println();

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println();

        prices = new int[]{1};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
    }

    int maxProfit(final int[] prices) {
        return new StockBestTimeSolutionIV().maxProfit(2, prices);
    }

    int maxProfitSpecific(final int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
