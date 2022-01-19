package com.teachingpractice.week3.divide_and_conquer;

import java.math.BigDecimal;

/**
 * 算法实现：分治-计算x的n次方 (x为double类型)
 * - 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn ）。
 * - 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * - 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * - 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * <p>
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-13 11:57:20
 */
public class MyPowerSolution {
    public static void main(String[] args) {
        double x = 2.0;
        int n = 10;
        System.out.println("x = " + x + ", n = " + n);
        System.out.println("result : " + myPow(x, n));

        x = 2.1;
        n = 3;
        System.out.println("x = " + x + ", n = " + n);
        System.out.println("result : " + myPow(x, n));

        x = 2;
        n = -2;
        System.out.println("x = " + x + ", n = " + n);
        System.out.println("result : " + myPow(x, n));

        x = 0.345;
        n = 0;
        System.out.println("x = " + x + ", n = " + n);
        System.out.println("result : " + myPow(x, n));

        x = 2;
        n = Integer.MIN_VALUE;
        System.out.println("x = " + x + ", n = " + n);
        System.out.println("result : " + myPow(x, n));
    }

    private static double myPow(final double x, final int n) {
        if (n == 0) {
            return 1.0;
        }
        if (n > 0) {
            return quickMulti(x, n).doubleValue();
        }

        // 注意考虑边界值：最小的负数，其理想的相反数不在int范围 (这会资源耗尽，故直接采用double计算)
        final BigDecimal odx = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(x));
        if (n == Integer.MIN_VALUE) {
            return quickMultiDouble(odx.doubleValue(), Integer.MAX_VALUE) * odx.doubleValue();
        }

        return quickMulti(odx.doubleValue(), -n).doubleValue();
    }

    private static BigDecimal quickMulti(final double x, final int n) {
        // 考虑整数n的二进制表示，n=每位相加，x^n=x的每位方相乘，从低位到高位，除最低位外(可作为初始值)，每位方的值是其紧邻右边低位的平方
        BigDecimal result = BigDecimal.valueOf(1);
        BigDecimal indexResult = BigDecimal.valueOf(x);
        for (int indexValue = n; indexValue >= 1; indexValue = indexValue >> 1, indexResult = indexResult.multiply(indexResult)) {
            if ((indexValue & 1) == 1) {
                result = result.multiply(indexResult);
            }
        }
        return result;
    }

    private static double quickMultiDouble(final double x, final int n) {
        double result = 1.0;
        double indexResult = x;
        for (int indexValue = n; indexValue >= 1; indexValue = indexValue >> 1, indexResult *= indexResult) {
            if ((indexValue & 1) == 1) {
                result = result * indexResult;
            }
        }
        return result;
    }
}
