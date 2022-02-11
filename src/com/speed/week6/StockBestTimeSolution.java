package com.speed.week6;

import java.util.Arrays;

/**
 * 算法实现: 买卖股票的最佳时机 I
 * - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/ (121题)
 * <p>
 * - 给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
 * - 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * - 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * - 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * - 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-10 11:15:20
 */
public class StockBestTimeSolution {
    public static void main(String[] args) {
        final StockBestTimeSolution solution = new StockBestTimeSolution();

        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println("Output max profit (Second) : " + solution.maxProfitSecond(prices));
        System.out.println("Output max profit (Third) : " + solution.maxProfitThird(prices));

        prices = new int[]{1, 2, 3, 4, 5};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println("Output max profit (Second) : " + solution.maxProfitSecond(prices));
        System.out.println("Output max profit (Third) : " + solution.maxProfitThird(prices));

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println("Output max profit (Second) : " + solution.maxProfitSecond(prices));
        System.out.println("Output max profit (Third) : " + solution.maxProfitThird(prices));
    }

    /**
     * 这个题要看的是单笔出售股票的最大利润
     * - 在某一天的交易中, 要说谁可以获得最大利润, 那就是他是在历史上的最低点买入至今未卖出
     * - 股票价格不断有波峰波谷出现, 要想刚好是整个时间段内的最大利润, 还得将上述的最大利润进行比对
     * - 做法1(最偷懒): 每天都计算最大利润
     * - 做法2: 波谷不计算最大利润 (在maxProfitSecond方法中实现)
     * - 做法3(更像是人工统计): 只在波峰计算最大利润 (在maxProfitAnother方法中实现)
     * --- 人工可以通过图形直接找到波峰波谷, 然而程序就得一个个比较了, 反而慢了, 因此在程序实现上, 做法2最佳
     * - 无论哪种, 时间复杂度 O(N), 空间复杂度 O(1)
     *
     * @param prices
     * @return
     */
    int maxProfit(final int[] prices) {
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return profit;
    }

    int maxProfitSecond(final int[] prices) {
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] <= min) {
                min = prices[i];
                continue;
            }
            if (prices[i] - min > profit) {
                profit = prices[i] - min;
            }
        }
        return profit;
    }

    int maxProfitThird(final int[] prices) {
        int min = prices[0];
        int profit = 0;
        final int maxIndex = prices.length - 1;
        for (int i = 1; i < maxIndex; i++) {
            if (prices[i] > prices[i - 1] && prices[i] >= prices[i + 1]) {
                profit = Math.max(profit, prices[i] - min);
                continue;
            }
            if (prices[i] < min) {
                min = prices[i];
            }
        }
        return Math.max(profit, prices[maxIndex] - min);
    }
}
