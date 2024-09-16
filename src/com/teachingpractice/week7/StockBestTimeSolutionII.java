package com.teachingpractice.week7;

import java.util.Arrays;

/**
 * 算法实现: 贪心 / 动态规划 - 买卖股票的最佳时机 II
 * - https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/ (122题)
 * <p>
 * - 给定一个数组 prices ，其中 prices[i] 表示股票第 i 天的价格。
 * - 在每一天，你可能会决定购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以购买它，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 * <p>
 * - 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
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
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see StockBestTimeSolution
 * @since 2022-02-18 11:06:46
 */
public class StockBestTimeSolutionII {
    public static void main(String[] args) {
        final StockBestTimeSolutionII solution = new StockBestTimeSolutionII();

        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println("Output max profit (further) : " + solution.maxProfitFurther(prices));
        System.out.println("Output max profit (dp protection) : " + solution.maxProfitDp(prices));
        System.out.println("Output max profit (dp) : " + solution.maxProfitDpAgain(prices));
        System.out.println();

        prices = new int[]{1, 2, 3, 4, 5};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println("Output max profit (further) : " + solution.maxProfitFurther(prices));
        System.out.println("Output max profit (dp protection) : " + solution.maxProfitDp(prices));
        System.out.println("Output max profit (dp) : " + solution.maxProfitDpAgain(prices));
        System.out.println();

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println("Input prices : " + Arrays.toString(prices));
        System.out.println("Output max profit : " + solution.maxProfit(prices));
        System.out.println("Output max profit (further) : " + solution.maxProfitFurther(prices));
        System.out.println("Output max profit (dp protection) : " + solution.maxProfitDp(prices));
        System.out.println("Output max profit (dp) : " + solution.maxProfitDpAgain(prices));
    }

    /**
     * 贪心 - 往后看一天
     * 每天都有3种选择：买、卖、什么都不做(相当于当天买与卖的次数持平)
     * 本题是一个理想预言家式的炒股状态，知道每天的股价，并且每天的任何时候股价都是一样的
     * - 当天持有股票，卖不卖？往后看一天，明天还涨就不卖，明天跌就卖
     * - 当天没有股票，买不买？往后看一天，明天还跌就不买，明天涨就买
     * - 最后就是完美结果 - 获得所有prices[i] - prices[i-1] > 0区间的收益
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param prices
     * @return
     */
    int maxProfit(final int[] prices) {
        int ans = 0;
        // 当前是否持有股票
        boolean positionStatus = false;
        int purchasePrice = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] == prices[i]) {
                continue;
            }

            if (prices[i + 1] > prices[i]) {
                if (!positionStatus) {
                    purchasePrice = prices[i];
                    positionStatus = true;
                }
                continue;
            }

            if (positionStatus) {
                ans += (prices[i] - purchasePrice);
                positionStatus = false;
            }
        }
        if (positionStatus) {
            ans += (prices[prices.length - 1] - purchasePrice);
        }
        return ans;
    }

    /**
     * 直接输出完美结果 (由于可以无限次交易，那么结果其实就是所有价格差全部变为利润)
     *
     * @param prices
     * @return
     */
    int maxProfitFurther(final int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                ans += (prices[i] - prices[i - 1]);
            }
        }
        return ans;
    }

    /**
     * - 最优子结构：f[i,j]表示第i天结束时，持有j股股票的最大收益 (相对应的，prices[i]也表示第i天的价格，而不是第i+1天的价格)
     * - 决策 (以天数为阶段的持仓状态机)
     * --- 买：f[i,1] = max(f[i-1,1], f[i-1,0] - prices[i])
     * --- 卖：f[i,0] = max(f[i-1,0], f[i-1,1] + prices[i])
     * --- 啥也不干：f[i,j] = f[i-1,j] (上面的买、卖已经包含)
     * - 于是 (j=0,1 共2n个点)
     * - 边界：f[0,0]=0, 其余负无穷
     * - 最优性动态规划问题，数组从1开始算(1-n天)，0作为边界，没算过/不合法的状态一律赋值为正/负无穷，有助于省去各种复杂的边界判断
     * --- 求max, 赋值负无穷，求min，赋值正无穷
     * - 目标：f[n,0]
     * - 时间复杂度：O(N)
     * - 空间复杂度：O(N)
     *
     * @param prices
     * @return
     */
    int maxProfitDp(final int[] prices) {
        final int n = prices.length;
        final int[][] f = new int[n + 1][2];
        Arrays.fill(f, new int[]{(int) -1e5, (int) -1e5});
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i - 1]);
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i - 1]);
        }
        return f[n][0];
    }

    /**
     * 将f设置为n+1的长度，最方便，求max设置为-oo (有保护节点，无需额外设置初始值)。那么实际上，也可以将f就设置为n的长度，就没有保护节点，需要额外设置初始值
     *
     * @param prices
     * @return
     */
    int maxProfitDpAgain(final int[] prices) {
        final int n = prices.length;
        final int[][] f = new int[n][2];
        f[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            f[i][1] = Math.max(f[i - 1][1], f[i - 1][0] - prices[i]);
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][1] + prices[i]);
        }
        return f[n - 1][0];
    }
}
