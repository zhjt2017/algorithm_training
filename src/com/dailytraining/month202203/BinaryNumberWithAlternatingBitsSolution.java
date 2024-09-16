package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-28) 交替位二进制数
 * - https://leetcode-cn.com/problems/binary-number-with-alternating-bits/ (693题)
 * <p>
 * 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 * <p>
 * - 输入：n = 5
 * 输出：true
 * 解释：5 的二进制表示是：101
 * <p>
 * - 输入：n = 7
 * 输出：false
 * 解释：7 的二进制表示是：111.
 * <p>
 * - 输入：n = 11
 * 输出：false
 * 解释：11 的二进制表示是：1011.
 * <p>
 * 1 <= n <= 2^31 - 1
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-28 12:11:05
 */
public class BinaryNumberWithAlternatingBitsSolution {
    public static void main(String[] args) {
        final BinaryNumberWithAlternatingBitsSolution solution = new BinaryNumberWithAlternatingBitsSolution();

        int n = 5;
        System.out.println("Input n : " + n + ", binary : " + Integer.toBinaryString(n));
        System.out.println("Output has alternating bits : " + solution.hasAlternatingBits(n));
        System.out.println();

        n = 7;
        System.out.println("Input n : " + n + ", binary : " + Integer.toBinaryString(n));
        System.out.println("Output has alternating bits : " + solution.hasAlternatingBits(n));
        System.out.println();

        n = 11;
        System.out.println("Input n : " + n + ", binary : " + Integer.toBinaryString(n));
        System.out.println("Output has alternating bits : " + solution.hasAlternatingBits(n));
        System.out.println();
    }

    boolean hasAlternatingBits(final int n) {
        final int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }
}
