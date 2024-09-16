package com.speed.week5;

/**
 * 算法实现：算术平方根 (结果只取整数部分)
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
public class SquareRootSolution {
    public static void main(String[] args) {
        final SquareRootSolution solution = new SquareRootSolution();

        int x = 4;
        System.out.println("Input x = " + x);
        System.out.println("Output mySquareRoot = " + solution.mySquareRoot(x));
        System.out.println("Output mySquareRootNewtonIteration = " + solution.mySquareRootNewtonIteration(x));
        System.out.println("Output mySquareRootReverse = " + solution.mySquareRootReverse(x));
        System.out.println("Output mySquareRootAnother = " + solution.mySquareRootAnother(x));

        x = 8;
        System.out.println("Input x = " + x);
        System.out.println("Output mySquareRoot = " + solution.mySquareRoot(x));
        System.out.println("Output mySquareRootNewtonIteration = " + solution.mySquareRootNewtonIteration(x));
        System.out.println("Output mySquareRootReverse = " + solution.mySquareRootReverse(x));
        System.out.println("Output mySquareRootAnother = " + solution.mySquareRootAnother(x));
    }

    /**
     * 二分查找, base case: 从左到右找到满足mid*mid<=x的最大值
     *
     * @param x
     * @return
     */
    private int mySquareRoot(final int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1;
        int right = x >> 1;
        int ans = 1;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (mid <= x / mid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    /**
     * 二分查找, base case: 从左到右找到满足mid*mid>x的最小值-1
     *
     * @param x
     * @return
     */
    private int mySquareRootReverse(final int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1;
        int right = (x >> 1) + 1;
        int ans = right;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (mid <= x / mid) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }

        return ans - 1;
    }

    /**
     * 牛顿迭代法 (来自于LeetCode官方，这里就不展开说明了)
     *
     * @param x
     * @return
     */
    public int mySquareRootNewtonIteration(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }

    /**
     * 二分查找
     * (这是参考他人的另一种实现)
     * (我们之前是mid取的都是偏向left的, left<=right, left=mid+1)
     * (这里给我们提供了一种额外的思路: mid取偏向right的, left<right, left=mid, 于是最后一次的right就是取得<=的最后一个值)
     *
     * @param x
     * @return
     */
    private int mySquareRootAnother(final int x) {
        if (x <= 1) {
            return x;
        }

        int left = 1;
        int right = x >> 1;
        int mid;
        while (left < right) {
            mid = ((right - left + 1) >> 1) + left;
            if (mid <= x / mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}
