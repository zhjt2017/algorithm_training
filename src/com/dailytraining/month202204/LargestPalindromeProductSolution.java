package com.dailytraining.month202204;

/**
 * 算法训练(2022-04-16) 最大回文数乘积
 * - https://leetcode-cn.com/problems/largest-palindrome-product/ (479题)
 * <p>
 * - 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 * <p>
 * - 输入： n = 1
 * 输出： 9
 * <p>
 * 1 <= n <= 8
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-16 02:15:47
 */
public class LargestPalindromeProductSolution {
    public static void main(String[] args) {
        final LargestPalindromeProductSolution solution = new LargestPalindromeProductSolution();

        for (int n = 1; n <= 8; n++) {
            System.out.println("Input n : " + n);
            System.out.println("Output largest palindrome (modular 1337) : " + solution.largestPalindrome(n));
        }
    }

    private static final int MODULAR_BASE = 1337;
    private static final int NINE = 9;

    int largestPalindrome(final int n) {
        if (n == 1) {
            return NINE;
        }
        final int upper = (int) Math.pow(10, n) - 1;
        for (int left = upper; left > 0; left--) {
            long p = left;
            for (int x = left; x > 0; x /= 10) {
                p = p * 10 + (x % 10);
            }
            for (long factor = upper; factor * factor >= p; factor--) {
                if (p % factor == 0) {
                    System.out.println("p : " + p);
                    return (int) (p % MODULAR_BASE);
                }
            }
        }
        return NINE;
    }
}
