package com.teachingpractice.week5;

/**
 * 算法实现：二分查找方式实现-算术平方根 (结果只取整数部分)
 * <p>
 * - 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * - 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * - 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5
 * <p>
 * - 输入：x = 4
 * 输出：2
 * <p>
 * - 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去
 * <p>
 * 0 <= x <= 2^31 - 1
 * <p>
 * 设计思想：
 * 1、牛顿迭代法 (数形结合，得出公式后，迭代次数更少，但是涉及到小数)
 * 2、二分查找
 * 3、jdk的pow(x,0.5)是native方法
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-27 11:32:10
 */
public class BinarySearchSquareRootSolution {
    public static void main(String[] args) {
        final BinarySearchSquareRootSolution solution = new BinarySearchSquareRootSolution();

        int x = 4;
        System.out.println("Input x = " + x);
        System.out.println("Output mySquareRoot = " + solution.mySquareRoot(x));
        System.out.println("Output doubleSquareRoot = " + solution.doubleSquareRoot(x));
        System.out.println();

        x = 8;
        System.out.println("Input x = " + x);
        System.out.println("Output mySquareRoot = " + solution.mySquareRoot(x));
        System.out.println("Output doubleSquareRoot = " + solution.doubleSquareRoot(x));
        System.out.println();

        x = 9;
        System.out.println("Input x = " + x);
        System.out.println("Output mySquareRoot = " + solution.mySquareRoot(x));
        System.out.println("Output doubleSquareRoot = " + solution.doubleSquareRoot(x));
    }

    /**
     * 二分查找, base case: 从左到右找到满足mid*mid<=x的最大值
     *
     * @param x
     * @return
     */
    private int mySquareRoot(final int x) {
        int left = 0;
        int right = x;
        int mid;
        while (left < right) {
            mid = ((right - left - 1) >> 1) + left + 1;
            if (mid <= x / mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    /**
     * 实数二分, 仍然是求满足mid*mid<=x的最大值
     *
     * @param x
     * @return
     */
    private double doubleSquareRoot(final double x) {
        final double minDiff = 1e-7;
        System.out.println("Termination condition : " + minDiff + ", decimal output : " + String.format("%.9f", minDiff));
        double left = 0;
        double right = Math.max(x, 1.0);
        double mid;
        while (right - left > minDiff) {
            mid = (left + right) / 2.0;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }

        // 这里特别处理一个问题, 就是平方根刚好是整数, 但是返回的结果不够精准的问题 (这是因为终止条件的问题)
        System.out.println("double left : " + left);
        int intLeft = (int) left;
        if ((intLeft + 1) * (intLeft + 1) == x) {
            intLeft = intLeft + 1;
            System.out.println("int left : " + intLeft);
            return intLeft;
        } else {
            System.out.println("int left : " + intLeft);
            return left;
        }
    }
}
