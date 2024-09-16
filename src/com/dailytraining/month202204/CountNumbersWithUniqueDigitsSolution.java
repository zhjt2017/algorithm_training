package com.dailytraining.month202204;

/**
 * 算法训练(2022-04-11) 统计各位数字都不同的数字个数
 * - https://leetcode-cn.com/problems/count-numbers-with-unique-digits/ (357题)
 * <p>
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10^n 。
 * <p>
 * - 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * <p>
 * - 输入：n = 0
 * 输出：1
 * <p>
 * 0 <= n <= 8
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-11 01:23:58
 */
public class CountNumbersWithUniqueDigitsSolution {
    public static void main(String[] args) {
        final CountNumbersWithUniqueDigitsSolution solution = new CountNumbersWithUniqueDigitsSolution();

        int n = 2;
        System.out.println("Input n : " + n);
        System.out.println("Output count numbers with unique digits : " + solution.countNumbersWithUniqueDigits(n));
        System.out.println();

        n = 0;
        System.out.println("Input n : " + n);
        System.out.println("Output count numbers with unique digits : " + solution.countNumbersWithUniqueDigits(n));
        System.out.println();
    }

    /**
     * 每次新增一位时，最高位总是1-9共9共情况，剩下的位数，就是(阶乘)排列，次高位为0-9减去1=9位
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1) - 由于n比较小，所以我们完全值得使用O(n)的空间复杂度来实现O(n)时间复杂度初始化后调用时O(1)的时间复杂度
     *
     * @param n
     * @return
     */
    int countNumbersWithUniqueDigits(final int n) {
        return COUNT_NUMBERS[n];
    }

    private static final int NINE = 9;
    private static final int[] COUNT_NUMBERS = new int[NINE];

    static {
        COUNT_NUMBERS[0] = 1;
        COUNT_NUMBERS[1] = NINE + 1;
        int value = NINE;
        for (int i = 2; i < NINE; i++) {
            value *= NINE - i + 2;
            COUNT_NUMBERS[i] = COUNT_NUMBERS[i - 1] + value;
        }
    }
}
