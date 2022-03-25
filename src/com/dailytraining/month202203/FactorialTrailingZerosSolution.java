package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-25) 阶乘后的零
 * - https://leetcode-cn.com/problems/factorial-trailing-zeroes/ (172题)
 * <p>
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * <p>
 * - 输入：n = 3
 * 输出：0
 * 解释：3! = 6 ，不含尾随 0
 * <p>
 * - 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 * <p>
 * - 输入：n = 0
 * 输出：0
 * <p>
 * 0 <= n <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-25 09:25:22
 */
public class FactorialTrailingZerosSolution {
    public static void main(String[] args) {
        final FactorialTrailingZerosSolution solution = new FactorialTrailingZerosSolution();

        int n = 3;
        System.out.println("Input n : " + n);
        System.out.println("Output trailing zeros : " + solution.trailingZeros(n));
        System.out.println("Output trailing zeros (logn) : " + solution.trailingZerosOpti(n));
        System.out.println();

        n = 5;
        System.out.println("Input n : " + n);
        System.out.println("Output trailing zeros : " + solution.trailingZeros(n));
        System.out.println("Output trailing zeros (logn) : " + solution.trailingZerosOpti(n));
        System.out.println();

        n = 0;
        System.out.println("Input n : " + n);
        System.out.println("Output trailing zeros : " + solution.trailingZeros(n));
        System.out.println("Output trailing zeros (logn) : " + solution.trailingZerosOpti(n));
        System.out.println();
    }

    /**
     * 10 = 2 * 5
     * 而1-n中质因子2的个数一定大于质因子5的个数
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    int trailingZeros(final int n) {
        int ans = 0;
        for (int i = FIVE; i <= n; i++) {
            ans += primeFactorCount(i);
        }
        return ans;
    }

    private int primeFactorCount(final int n) {
        int count = 0;
        int value = n;
        while (value % FIVE == 0) {
            count++;
            value = value / FIVE;
        }
        return count;
    }

    private static final int FIVE = 5;

    /**
     * 我们对更大的n在计算质因子5的个数时，对前面的质因子5的个数重复进行了计算
     * (每隔5个数就会增加一个质因子，这是一重质因子，然后再计算二重质因子，即每隔25个数... 这也可以反过来使用/5只有的值再/5实现)
     * <p>
     * 时间复杂度 O(logn)
     * 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    int trailingZerosOpti(final int n) {
        int ans = 0;
        int value = n;
        while (value > 0) {
            value = value / FIVE;
            ans += value;
        }
        return ans;
    }
}
