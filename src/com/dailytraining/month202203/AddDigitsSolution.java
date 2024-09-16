package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-03) 各位相加 - 自然数的数根
 * - https://leetcode-cn.com/problems/add-digits/ (258题)
 * <p>
 * - 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * <p>
 * - 输入: num = 38
 * 输出: 2
 * 解释: 各位相加的过程为：
 * 38 --> 3 + 8 --> 11
 * 11 --> 1 + 1 --> 2
 * 由于 2 是一位数，所以返回 2。
 * <p>
 * - 输入: num = 0
 * 输出: 0
 * <p>
 * 0 <= num <= 2^31 - 1
 * <p>
 * 你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-03 08:47:53
 */
public class AddDigitsSolution {
    public static void main(String[] args) {
        final AddDigitsSolution solution = new AddDigitsSolution();

        int num = 38;
        System.out.println("Input num : " + num);
        System.out.println("Output add digits final result : " + solution.addDigits(num));
        System.out.println("Output add digits final result (specific) : " + solution.addDigitsSpecific(num));
        System.out.println();

        num = 0;
        System.out.println("Input num : " + num);
        System.out.println("Output add digits final result : " + solution.addDigits(num));
        System.out.println("Output add digits final result (specific) : " + solution.addDigitsSpecific(num));
        System.out.println();
    }

    /**
     * 最直观的模拟
     * - 由于num在int范围，故计算一次各位相加的最大可能结果为82，故最多再计算2次即可得到一位数
     * <p>
     * - 时间复杂度 O(log(num))
     *
     * @param num
     * @return
     */
    int addDigits(final int num) {
        if (num <= NINE) {
            return num;
        }
        int sum = 0;
        int remaining = num;
        while (remaining >= NINE) {
            sum += remaining % TEN;
            remaining = remaining / TEN;
        }
        return addDigits(sum + remaining);
    }

    /**
     * 数学：同余定理 及 ”同余式相加“性质
     * (10^i)-1 是9的倍数
     *
     * @param num
     * @return
     */
    int addDigitsSpecific(final int num) {
        return (num - 1) % NINE + 1;
    }

    private static final int NINE = 9;
    private static final int TEN = 10;
}
