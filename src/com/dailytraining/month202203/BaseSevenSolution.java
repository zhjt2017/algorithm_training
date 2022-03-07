package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-07) 七进制数
 * - https://leetcode-cn.com/problems/base-7/ (504题)
 * <p>
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * - 输入: num = 100
 * 输出: "202"
 * <p>
 * - 输入: num = -7
 * 输出: "-10"
 * <p>
 * -10^7 <= num <= 10^7
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 01:27:05
 */
public class BaseSevenSolution {
    public static void main(String[] args) {
        final BaseSevenSolution solution = new BaseSevenSolution();

        int num = 100;
        System.out.println("Input num : " + num);
        System.out.println("Output base 7 value : " + solution.convertToBase7(num));
        System.out.println();

        num = -7;
        System.out.println("Input num : " + num);
        System.out.println("Output base 7 value : " + solution.convertToBase7(num));
        System.out.println();
    }

    /**
     * 短除法
     *
     * @param num
     * @return
     */
    String convertToBase7(final int num) {
        if (num == 0) {
            return "0";
        }
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }

        final StringBuilder sb = new StringBuilder();
        int value = num;
        while (value > 0) {
            sb.append(value % 7);
            value = value / 7;
        }
        return sb.reverse().toString();
    }
}
