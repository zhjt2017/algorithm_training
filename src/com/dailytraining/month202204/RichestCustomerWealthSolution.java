package com.dailytraining.month202204;

import java.util.Arrays;

/**
 * 算法训练(2022-04-14) 最富有客户的资产总量
 * - https://leetcode-cn.com/problems/richest-customer-wealth/ (1672题)
 * <p>
 * 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i​​​​​​​​​​​​ 位客户在第 j 家银行托管的资产数量。
 * 返回最富有客户所拥有的 资产总量 。
 * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。
 * 最富有客户就是 资产总量 最大的客户。
 * <p>
 * - 输入：accounts = [[1,2,3],[3,2,1]]
 * 输出：6
 * 解释：
 * 第 1 位客户的资产总量 = 1 + 2 + 3 = 6
 * 第 2 位客户的资产总量 = 3 + 2 + 1 = 6
 * 两位客户都是最富有的，资产总量都是 6 ，所以返回 6 。
 * <p>
 * - 输入：accounts = [[1,5],[7,3],[3,5]]
 * 输出：10
 * 解释：
 * 第 1 位客户的资产总量 = 6
 * 第 2 位客户的资产总量 = 10
 * 第 3 位客户的资产总量 = 8
 * 第 2 位客户是最富有的，资产总量是 10
 * <p>
 * - 输入：accounts = [[2,8,7],[7,1,3],[1,9,5]]
 * 输出：17
 * <p>
 * m == accounts.length
 * n == accounts[i].length
 * 1 <= m, n <= 50
 * 1 <= accounts[i][j] <= 100
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-14 11:02:16
 */
public class RichestCustomerWealthSolution {
    public static void main(String[] args) {
        final RichestCustomerWealthSolution solution = new RichestCustomerWealthSolution();

        int[][] accounts = new int[][]{{1, 2, 3}, {3, 2, 1}};
        System.out.println("Input accounts : " + Arrays.deepToString(accounts));
        System.out.println("Output maximum wealth : " + solution.maximumWealth(accounts));
        System.out.println();
        accounts = new int[][]{{1, 5}, {7, 3}, {3, 5}};
        System.out.println("Input accounts : " + Arrays.deepToString(accounts));
        System.out.println("Output maximum wealth : " + solution.maximumWealth(accounts));
        System.out.println();
        accounts = new int[][]{{2, 8, 7}, {7, 1, 3}, {1, 9, 5}};
        System.out.println("Input accounts : " + Arrays.deepToString(accounts));
        System.out.println("Output maximum wealth : " + solution.maximumWealth(accounts));
        System.out.println();
    }

    /**
     * 时间复杂度 O(mn)
     * 空间复杂度 O(1)
     *
     * @param accounts
     * @return
     */
    int maximumWealth(final int[][] accounts) {
        int ans = 0;
        int sum;
        for (final int[] acc : accounts) {
            sum = 0;
            for (final int amount : acc) {
                sum += amount;
            }
            if (sum > ans) {
                ans = sum;
            }
        }
        return ans;
    }
}
