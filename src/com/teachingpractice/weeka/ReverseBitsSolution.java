package com.teachingpractice.weeka;

/**
 * 算法实现：位运算 - 颠倒二进制位
 * - https://leetcode-cn.com/problems/reverse-bits/ (190题)
 * <p>
 * 颠倒给定的 32 位无符号整数的二进制位。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 2 中，输入表示有符号整数 -3，输出表示有符号整数 -1073741825。
 * <p>
 * - 输入：n = 00000010100101000001111010011100
 * 输出：964176192 (00111001011110000010100101000000)
 * 解释：输入的二进制串 00000010100101000001111010011100 表示无符号整数 43261596，
 * 因此返回 964176192，其二进制表示形式为 00111001011110000010100101000000。
 * <p>
 * - 输入：n = 11111111111111111111111111111101
 * 输出：3221225471 (10111111111111111111111111111111)
 * 解释：输入的二进制串 11111111111111111111111111111101 表示无符号整数 4294967293，
 *   因此返回 3221225471 其二进制表示形式为 10111111111111111111111111111111 。
 * <p>
 * 输入是一个长度为 32 的二进制字符串
 * <p>
 * 进阶: 如果多次调用这个函数，你将如何优化你的算法？
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 11:05:26
 */
public class ReverseBitsSolution {
    public static void main(String[] args) {
        final ReverseBitsSolution solution = new ReverseBitsSolution();

        String binary = "00000010100101000001111010011100";
        System.out.println("Input binary : " + binary + ", n : " + Integer.parseInt(binary, 2));
        System.out.println("Output reverse bits result : " + solution.reverseBits(Integer.parseInt(binary, 2)));
        System.out.println();

        binary = "11111111111111111111111111111101";
        System.out.println("Input binary : " + binary + ", n : " + (int) Long.parseLong(binary, 2));
        System.out.println("Output reverse bits result : " + solution.reverseBits((int) Long.parseLong(binary, 2)));
        System.out.println();
    }

    /**
     * 1 - 逐位赋值
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    int reverseBits(final int n) {
        int ans = 0;
        for (int i = 0; i < INT_32; i++) {
            // 新的数字ans从高位开始赋值，采用n的低位顺序
            ans = (ans << 1) | (n >> i & 1);
        }
        return ans;
    }

    private static final int INT_32 = 32;

    /**
     * 2 - 位运算分治 (太过复杂，就不考虑了)
     * - 时间复杂度 O(1)
     * - 空间复杂度 O(1)
     *
     * @param value
     * @return
     */
    int reverseBitsFurther(final int value) {
        int n = value;
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    private static final int M1 = 0x55555555;
    private static final int M2 = 0x33333333;
    private static final int M4 = 0x0f0f0f0f;
    private static final int M8 = 0x00ff00ff;
}
