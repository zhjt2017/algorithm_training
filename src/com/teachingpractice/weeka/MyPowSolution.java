package com.teachingpractice.weeka;

/**
 * 算法实现：增加位运算优化 - 实现 pow(x, n)
 * - https://leetcode-cn.com/problems/powx-n/ (50题)
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 * <p>
 * - 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * <p>
 * - 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * <p>
 * - 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= xn <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 09:36:54
 */
public class MyPowSolution {
    public static void main(String[] args) {
        final MyPowSolution solution = new MyPowSolution();

        double x = 2.0;
        int n = 10;
        System.out.println("Input x : " + x + ", n : " + n);
        System.out.println("Output pow result : " + solution.myPow(x, n));
        System.out.println();

        x = 2.1;
        n = 3;
        System.out.println("Input x : " + x + ", n : " + n);
        System.out.println("Output pow result : " + solution.myPow(x, n));
        System.out.println();

        x = 2.0;
        n = -2;
        System.out.println("Input x : " + x + ", n : " + n);
        System.out.println("Output pow result : " + solution.myPow(x, n));
        System.out.println();
    }

    double myPow(final double x, final int n) {
        return 1.0;
    }
}
