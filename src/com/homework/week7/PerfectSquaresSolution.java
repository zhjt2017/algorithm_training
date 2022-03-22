package com.homework.week7;

/**
 * 算法实现：背包DP - 完全平方数
 * <p>
 * - 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * - 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * - 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * - 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * <p>
 * - 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * <p>
 * 1 <= n <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-20 01:14:01
 */
public class PerfectSquaresSolution {
    public static void main(String[] args) {
        final PerfectSquaresSolution solution = new PerfectSquaresSolution();

        int n = 12;
        System.out.println("Input n : " + n);
        System.out.println("Output num of perfect squares : " + solution.numSquares(n));
        System.out.println();

        n = 13;
        System.out.println("Input n : " + n);
        System.out.println("Output num of perfect squares : " + solution.numSquares(n));
    }

    /**
     * 看作物品，体积为 n，价值为 1，用背包 DP 的思想解题
     * @param n
     * @return
     */
    int numSquares(final int n) {
        return 0;
    }
}
