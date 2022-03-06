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
        System.out.println("Output max profit (space optimization) : " + solution.maxProfitSpaceOptimization(prices, cooldown));
        System.out.println();

        prices = new int[]{1};
        System.out.println("Input prices : " + Arrays.toString(prices) + ", cooldown : " + cooldown);
        System.out.println("Output max profit : " + solution.maxProfit(prices, cooldown));
        System.out.println("Output max profit (space optimization) : " + solution.maxProfitSpaceOptimization(prices, cooldown));
        System.out.println();
    }

    /**
     * 动态规划 - 列表法 (看出边而不是入边)
     * f[i][j][l]表示第i天完成后持股j冷冻期l时的代价/收益
     * | 当前状态 | 下一天干啥 | 条件 | 代价/收益 | 完成之后的状态 |
     * | :-: | :-: | :-: | :-: | :-: |
     * | f[i][j][l] | buy | j=0, l=0 | -prices[i+1] - fee | f[i+1][1][0] |
     * | ~ | sell | j=1, l=0 | prices[i+1] | f[i+1][0][1] |
     * | ~ | rest | Nothing | 0 | f[i+1][j][0] |
     *
     * @param prices
     * @param cooldown
     * @return
     */
    int maxProfit(final int[] prices, final int cooldown) {
        final int n = prices.length;
        final int[][][] f = new int[n + 1][2][cooldown + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i][0], (int) -1e5);
            Arrays.fill(f[i][1], (int) -1e5);
        }
        f[0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int l = 0; l < 2; l++) {
                    if (f[i][j][l] == (int) -1e5) {
                        continue;
                    }
                    if (j == 0 && l == 0) {
                        f[i + 1][1][0] = f[i][j][l] - prices[i];
                    } else if (j == 1 && l == 0) {
                        f[i + 1][0][1] = f[i][j][l] + prices[i];
                    }
                    f[i + 1][j][0] = Math.max(f[i + 1][j][0], f[i][j][l]);
                }
            }
        }
        return Math.max(f[n][0][0], f[n][0][1]);
    }

    /**
     * 动态规划
     * - maxProfit 写完后，对其空间进行优化
     * - 算完后，重新初始化 (“滚动数组思想”) (而且，使用&1之后是来回滚动，不用复制数据，最佳)
     * <p>
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     *
     * @param prices
     * @param cooldown
     * @return
     */
    int maxProfitSpaceOptimization(final int[] prices, final int cooldown) {
        final int n = prices.length;
        final int[][][] f = new int[2][2][cooldown + 1];
        for (int i = 0; i < f.length; i++) {
            Arrays.fill(f[i][0], (int) -1e5);
            Arrays.fill(f[i][1], (int) -1e5);
        }
        f[0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int l = 0; l < 2; l++) {
                    if (f[i & 1][j][l] == (int) -1e5) {
                        continue;
                    }
                    if (j == 0 && l == 0) {
                        f[(i + 1) & 1][1][0] = f[i & 1][j][l] - prices[i];
                    } else if (j == 1 && l == 0) {
                        f[(i + 1) & 1][0][1] = f[i & 1][j][l] + prices[i];
                    }
                    f[(i + 1) & 1][j][0] = Math.max(f[(i + 1) & 1][j][0], f[i & 1][j][l]);
                    // 重新初始化 (复用的空间)
                    f[i & 1][j][l] = (int) -1e5;
                }
            }
        }
        return Math.max(f[n & 1][0][0], f[n & 1][0][1]);
    }
}
