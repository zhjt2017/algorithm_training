package com.speed.week9;

/**
 * 算法实现：验证回文串
 * - https://leetcode-cn.com/problems/valid-palindrome-ii/ (680题)
 * <p>
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * <p>
 * - 输入: s = "aba"
 * 输出: true
 * <p>
 * - 输入: s = "abca"
 * 输出: true
 * 解释: 你可以删除c字符。
 * <p>
 * - 输入: s = "abc"
 * 输出: false
 * <p>
 * 1 <= s.length <= 2 * 10^5
 * 字符串 s 由 小写英文字母组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-04 11:55:47
 */
public class ValidPalindromeSolutionII {
    public static void main(String[] args) {
        final ValidPalindromeSolutionII solution = new ValidPalindromeSolutionII();

        String s = "aba";
        System.out.println("Input s : " + s);
        System.out.println("Output isPalindrome : " + solution.isPalindrome(s));
        System.out.println();

        s = "abca";
        System.out.println("Input s : " + s);
        System.out.println("Output isPalindrome : " + solution.isPalindrome(s));
        System.out.println();

        s = "abc";
        System.out.println("Input s : " + s);
        System.out.println("Output isPalindrome : " + solution.isPalindrome(s));
        System.out.println();
    }

    /**
     * 贪心
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param s
     * @return
     */
    boolean isPalindrome(final String s) {
        int low = 0, high = s.length() - 1;
        while (low < high) {
            char c1 = s.charAt(low), c2 = s.charAt(high);
            if (c1 == c2) {
                ++low;
                --high;
            } else {
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }

    private boolean validPalindrome(String s, int low, int high) {
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;
    }
}
