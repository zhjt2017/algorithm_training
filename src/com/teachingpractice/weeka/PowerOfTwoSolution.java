package com.teachingpractice.weeka;

/**
 * 算法实现：位运算 - 2的幂
 * - https://leetcode-cn.com/problems/power-of-two/ (231题)
 * <p>
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 * <p>
 * - 输入：n = 1
 * 输出：true
 * 解释：20 = 1
 * <p>
 * - 输入：n = 16
 * 输出：true
 * 解释：24 = 16
 * <p>
 * - 输入：n = 3
 * 输出：false
 * <p>
 * - 输入：n = 4
 * 输出：true
 * <p>
 * - 输入：n = 5
 * 输出：false
 * <p>
 * -2^31 <= n <= 2^31 - 1
 *
 * - 时间复杂度 O(1)
 * - 空间复杂度 O(1)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 11:04:27
 */
public class PowerOfTwoSolution {
    public static void main(String[] args) {
        final PowerOfTwoSolution solution = new PowerOfTwoSolution();

        int n = 1;
        System.out.println("Input n : " + n);
        System.out.println("Output is power of 2 : " + solution.isPowerOfTwo(n));
        System.out.println("Output is power of 2 (another) : " + solution.isPowerOfTwoAnother(n));
        System.out.println();

        n = 16;
        System.out.println("Input n : " + n);
        System.out.println("Output is power of 2 : " + solution.isPowerOfTwo(n));
        System.out.println("Output is power of 2 (another) : " + solution.isPowerOfTwoAnother(n));
        System.out.println();

        n = 3;
        System.out.println("Input n : " + n);
        System.out.println("Output is power of 2 : " + solution.isPowerOfTwo(n));
        System.out.println("Output is power of 2 (another) : " + solution.isPowerOfTwoAnother(n));
        System.out.println();

        n = 4;
        System.out.println("Input n : " + n);
        System.out.println("Output is power of 2 : " + solution.isPowerOfTwo(n));
        System.out.println("Output is power of 2 (another) : " + solution.isPowerOfTwoAnother(n));
        System.out.println();

        n = 5;
        System.out.println("Input n : " + n);
        System.out.println("Output is power of 2 : " + solution.isPowerOfTwo(n));
        System.out.println("Output is power of 2 (another) : " + solution.isPowerOfTwoAnother(n));
        System.out.println();
    }

    /**
     * 最高位是1，其余位均为0 (不可能没有1)
     * - 得到最低位的1，与n本身相同
     *
     * @param n
     * @return
     */
    boolean isPowerOfTwo(final int n) {
        if (n <= 0) {
            return false;
        }
        return (n & -n) == n;
    }

    /**
     * 最高位是1，其余位均为0 (不可能没有1，也不可能将1放在低位上，高位上都是0，因为高位上的0没有实际意义)
     * - 清零最低位的1后，为0
     *
     * @param n
     * @return
     */
    boolean isPowerOfTwoAnother(final int n) {
        if (n <= 0) {
            return false;
        }
        return (n & (n - 1)) == 0;
    }
}
