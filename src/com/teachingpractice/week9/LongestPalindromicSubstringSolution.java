package com.teachingpractice.week9;

/**
 * 算法实现：字符串处理 - 回文系列问题 - 最长回文子串
 * - https://leetcode-cn.com/problems/longest-palindromic-substring/ (5题)
 * <p>
 * - 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * - 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * - 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * - 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 * <p>
 * 提示：
 * - 中间向两边扩张 O(n2)
 * - 加入二分 + Rabin-Karp 优化，O(nlogn)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 04:20:08
 */
public class LongestPalindromicSubstringSolution {
    public static void main(String[] args) {
        final LongestPalindromicSubstringSolution solution = new LongestPalindromicSubstringSolution();

        String s = "babad";
        System.out.println("Input : " + s);
        System.out.println("Output longest palindrome substring : " + solution.longestPalindrome(s));
        System.out.println();

        s = "cbbd";
        System.out.println("Input : " + s);
        System.out.println("Output longest palindrome substring : " + solution.longestPalindrome(s));
        System.out.println();
    }

    /**
     * DP[i]的物理意义：
     * - 状态转移方程中，我们是从较短的字符串向较长的字符串进行转移
     *
     * @param s
     * @return
     */
    String longestPalindrome(String s) {
        return "";
    }
}
