package com.teachingpractice.week7;

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
        System.out.println("Output max profit (protection) : " + solution.maxProfit(k, prices));
        System.out.println("Output max profit : " + solution.maxProfitAgain(k, prices));
        System.out.println();

        prices = new int[]{3, 2, 6, 5, 0, 3};
        k = 2;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", k = " + k);
        System.out.println("Output max profit (protection) : " + solution.maxProfit(k, prices));
        System.out.println("Output max profit : " + solution.maxProfitAgain(k, prices));

        prices = new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        k = 4;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", k = " + k);
        System.out.println("Output max profit (protection) : " + solution.maxProfit(k, prices));
        System.out.println("Output max profit : " + solution.maxProfitAgain(k, prices));

        prices = new int[]{1, 2, 4, 7};
        k = 2;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", k = " + k);
        System.out.println("Output max profit (protection) : " + solution.maxProfit(k, prices));
        System.out.println("Output max profit : " + solution.maxProfitAgain(k, prices));

        prices = new int[]{1, 2, 4};
        k = 2;
        System.out.println("Input prices : " + Arrays.toString(prices) + ", k = " + k);
        System.out.println("Output max profit (protection) : " + solution.maxProfit(k, prices));
        System.out.println("Output max profit : " + solution.maxProfitAgain(k, prices));
    }

    /**
     * - 题意：只能持有一股、最多交易c次 (每次交易，买入+卖出)
     * - 最优子结构：f[i,j,k]表示第i天结束时，持有j股股票，已经交易了k次的最大收益
     * - 决策 (以天数为阶段的持仓状态机)
     * - 买：f[i,1,k] = f[i-1,0,k-1] - prices[i]
     * - 卖：f[i,0,k] = f[i-1,1,k] + prices[i]
     * - 啥也不干：f[i,j,k] = f[i-1,j,k]
     * - f[i,1,k] = max(f[i-1,1,k], f[i-1,0,k-1] - prices[i])
     * - f[i,0,k] = max(f[i-1,0,k], f[i-1,1,k] + prices[i])
     * - 边界：f[0,0,0]=0, 其余负无穷
     * - 最优性动态规划问题，数组从1开始算(1-n天)，0作为边界，没算过/不合法的状态一律赋值为正/负无穷，有助于省去各种复杂的边界判断
     * - 求max, 赋值负无穷，求min，赋值正无穷
     * - 目标：max{f[n,0,c]}
     * - 时间复杂度：O(NC)
     *
     * @param c
     * @param prices
     * @return
     */
    int maxProfit(final int c, final int[] prices) {
        final int n = prices.length;
        final int[][][] f = new int[n + 1][2][c + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= 1; j++) {
                Arrays.fill(f[i][j], (int) -1e9);
            }
        }
        f[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= Math.min(c, n >> 1); k++) {
                    if (k > 0) {
                        f[i][1][k] = Math.max(f[i - 1][1][k], f[i - 1][0][k - 1] - prices[i - 1]);
                    }
                    f[i][0][k] = Math.max(f[i - 1][0][k], f[i - 1][1][k] + prices[i - 1]);
                }
            }
        }
        // 使用动态规划而不是贪心，并没有选择在低价的时候买入再高价的时候卖出，而是随便先买入再卖出，然后递推所有情况，于是本题结果需要再聚合各种k的f[n][0]来取得最大值
        int ans = 0;
        for (int k = 0; k <= c; k++) {
            ans = Math.max(ans, f[n][0][k]);
        }
        return ans;
    }

    /**
     * 注意：计算范围为k <= Math.min(c, (i >> 1) + 1)
     * - 这就属于边界判断比较复杂，并且还要为无效的k去赋初始值
     * for (int k = (i >> 1) + 2; k <= c; k++) {
     * f[i][1][k] = -prices[i];
     * }
     * 而既然[][][]的f已经创建，空间已经建立，因此还是全部fill来得好，即maxProfit方法，这样不用考虑边界问题，只要对最开始的位置赋值一个初始值即可
     *
     * @param c
     * @param prices
     * @return
     */
    int maxProfitAgain(final int c, final int[] prices) {
        final int n = prices.length;
        if (n == 0 || c <= 0) {
            return 0;
        }
        final int[][][] f = new int[n][2][c + 1];
        f[0][1][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= Math.min(c, (i >> 1) + 1); k++) {
                    f[i][1][k] = Math.max(f[i - 1][1][k], f[i - 1][0][k - 1] - prices[i]);
                    f[i][0][k] = Math.max(f[i - 1][0][k], f[i - 1][1][k] + prices[i]);
                }
                for (int k = (i >> 1) + 2; k <= c; k++) {
                    f[i][1][k] = -prices[i];
                }
            }
        }

        System.out.println("f : " + Arrays.deepToString(f));

        // 使用动态规划而不是贪心，并没有选择在低价的时候买入再高价的时候卖出，而是随便先买入再卖出，然后递推所有情况，于是本题结果需要再聚合各种k的f[n][0]来取得最大值 (k=0时max=0刚好为默认值)
        int ans = 0;
        for (int k = 1; k <= c; k++) {
            ans = Math.max(ans, f[n - 1][0][k]);
        }
        return ans;
    }
}
