package com.homework.week7;

import java.util.Arrays;

/**
 * 算法实现：动态规划 - 买卖股票的最佳时机 IV
 * - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/ (188题)
 * <p>
 * - 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * - 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k笔 交易。
 * - 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * - 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * - 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * <p>
 * <p>
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-20 03:00:19
 */
public class StockBestTimeSolutionIV {
    public static void main(String[] args) {
        final StockBestTimeSolutionIV solution = new StockBestTimeSolutionIV();

        int[] prices = new int[]{2, 4, 1};
        int k = 2;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", k = " + k);
        System.out.println("Output max profit : " + solution.maxProfit(k, prices));
        System.out.println();

        prices = new int[]{3, 2, 6, 5, 0, 3};
        k = 2;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", k = " + k);
        System.out.println("Output max profit : " + solution.maxProfit(k, prices));
    }

    int maxProfit(final int k, final int[] prices) {
        return 0;
    }
}
