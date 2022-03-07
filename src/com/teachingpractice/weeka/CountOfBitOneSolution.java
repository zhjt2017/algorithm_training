package com.teachingpractice.weeka;

/**
 * 算法实现：位运算 - 位1的个数
 * - https://leetcode-cn.com/problems/number-of-1-bits/ (191题)
 * <p>
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 * <p>
 * - 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * <p>
 * - 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * <p>
 * - 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 11:03:00
 */
public class CountOfBitOneSolution {
    public static void main(String[] args) {
        final CountOfBitOneSolution solution = new CountOfBitOneSolution();

        String binary = "00000000000000000000000000001011";
        System.out.println("Input n : " + Integer.parseInt(binary, 2) + ", binary string : " + binary);
        System.out.println("Output hamming weight (count of bit value - 1) : " + solution.hammingWeight(Integer.parseInt(binary, 2)));
        System.out.println();

        binary = "00000000000000000000000010000000";
        System.out.println("Input n : " + Integer.parseInt(binary, 2) + ", binary string : " + binary);
        System.out.println("Output hamming weight (count of bit value - 1) : " + solution.hammingWeight(Integer.parseInt(binary, 2)));
        System.out.println();

        binary = "-0000000000000000000000000000011";
        System.out.println("Input n : " + Integer.parseInt(binary, 2) + ", binary string : " + binary);
        System.out.println("Output hamming weight (count of bit value - 1) : " + solution.hammingWeight(Integer.parseInt(binary, 2)));
        System.out.println();

        binary = "11111111111111111111111111111101";
        System.out.println("Input n : " + (int) Long.parseLong(binary, 2) + ", binary string : " + binary);
        System.out.println("Output hamming weight (count of bit value - 1) : " + solution.hammingWeight((int) Long.parseLong(binary, 2)));
        System.out.println();
    }

    /**
     * 使用lowbit
     * - 得到最低位的1
     * - x & -x
     * - x & (~x + 1)
     * - 清零最低位的1
     * - x = x & (x - 1)
     * <p>
     * - 时间复杂度 O(logN) - O(N)
     * - 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    int hammingWeight(final int n) {
        int ans = 0;
        int value = n;
        while (value != 0) {
            ans++;
            value = value & (value - 1);
        }
        return ans;
    }
}
