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
 * <p>
 * 有一个算法，叫做Manacher，其可以在O(n)线性时间内，求出来一个字符串的最长回文子串是什么 (专用：其只能用于求一个字符串的最长回文子串问题)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 04:20:08
 */
public class LongestPalindromicSubstringSolution {
    public static void main(String[] args) {
        final LongestPalindromicSubstringSolution solution = new LongestPalindromicSubstringSolution();

        String s = "babad";
//        System.out.println("Input : " + s);
//        System.out.println("Output longest palindrome substring (base expands) : " + solution.longestPalindromeCenterExpandsToBothSides(s));
//        System.out.println("Output longest palindrome substring (binary answer + Rabin-Karp) : " + solution.longestPalindromeRabinKarp(s));
//        System.out.println();
//
//        s = "cbbd";
//        System.out.println("Input : " + s);
//        System.out.println("Output longest palindrome substring (base expands) : " + solution.longestPalindromeCenterExpandsToBothSides(s));
//        System.out.println("Output longest palindrome substring (binary answer + Rabin-Karp) : " + solution.longestPalindromeRabinKarp(s));
//        System.out.println();

        s = "bb";
        System.out.println("Input : " + s);
        System.out.println("Output longest palindrome substring (base expands) : " + solution.longestPalindromeCenterExpandsToBothSides(s));
        System.out.println("Output longest palindrome substring (binary answer + Rabin-Karp) : " + solution.longestPalindromeRabinKarp(s));
        System.out.println();
    }

    /**
     * 基本思路1：朴素的“中间向两边扩张”(回文串的基本性质)
     * - 时间复杂度 O(n²)
     * - 空间复杂度 O(1)
     * - leetcode运行：
     * --- 执行用时：20ms
     * --- 内存消耗：41MB
     *
     * @param s
     * @return
     */
    String longestPalindromeCenterExpandsToBothSides(final String s) {
        n = s.length();
        ansLength = 1;
        ansStart = 0;
        for (int i = 1; i < n; i++) {
            // 奇回文串 - 以单个字符为中心
            expandsToBothSides(s, i - 1, i + 1);
            // 偶回文串 - 以两个字符中间(的空挡)为中心 (不妨以后面那个字符的index作为center所在的index)
            expandsToBothSides(s, i - 1, i);
        }
        return s.substring(ansStart, ansStart + ansLength);
    }

    private void expandsToBothSides(final String s, final int leftStart, final int rightStart) {
        int left = leftStart, right = rightStart;
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        int length = right - left - 1;
        // 这里取length > ansLength，即最终获取的答案为index最小的那个最长回文子串
        if (length > ansLength) {
            ansLength = length;
            ansStart = left + 1;
        }
    }

    private int n;
    private int ansLength;
    private int ansStart;

    /**
     * 基本思路2：Rabin-Karp字符串哈希算法通过比较h与reverseH是否一致来验证回文字符串，从中心扩展，利用二分答案优化验证的调用
     * - 时间复杂度 O(nlogn)
     * - 空间复杂度 O(n)
     * - 由于本题的数据量范围仅为1000，看不出太大的效果
     * - leetcode运行：
     * --- 执行用时：17ms
     * --- 内存消耗：41.6MB
     *
     * @param s
     * @return
     */
    String longestPalindromeRabinKarp(final String s) {
        n = s.length();
        ansLength = 1;
        ansStart = 0;
        h = new long[n + 1];
        reverseH = new long[n + 2];
        powB = new long[n + 1];
        powB[0] = 1;
        for (int i = 1; i <= n; i++) {
            h[i] = (h[i - 1] * B + s.charAt(i - 1) - CHAR_A + 1) % P;
            powB[i] = powB[i - 1] * B % P;
        }
        for (int i = n; i >= 1; i--) {
            reverseH[i] = (reverseH[i + 1] * B + s.charAt(i - 1) - CHAR_A + 1) % P;
        }

        for (int i = 1; i < n; i++) {
            // 二分答案，找到仍然满足回文串的最大interval (奇回文串)
            int interval = binaryAnswer(true, i, 0, Math.min(i - 1, n - i));
            int length = (interval << 1) + 1;
            if (length > ansLength) {
                ansLength = length;
                ansStart = i - 1 - interval;
            }
            // 偶回文串 (borderInterval++)
            interval = binaryAnswer(false, i, 0, Math.min(i - 1, n - i + 1));
            // 特别注意：对于偶回文串来说，当interval为0时，length可能为0或者2
            length = interval << 1;
            if (length == 0 && s.length() >= i && s.charAt(i - 1) == s.charAt(i)) {
                length = 2;
            }
            if (length > ansLength) {
                ansLength = length;
                ansStart = i - 1 - interval;
            }
        }
        return s.substring(ansStart, ansStart + ansLength);
    }

    private int binaryAnswer(final boolean odd, final int center, final int start, final int end) {
        int left = start, right = end;
        while (left < right) {
            int mid = ((right - left - 1) >> 1) + left + 1;
            if (validate(odd, center, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * Rabin-Karp字符串哈希算法判定一段字符串是否是回文串
     *
     * @param odd      是否是奇回文串
     * @param center
     * @param interval
     * @return
     */
    private boolean validate(final boolean odd, final int center, final int interval) {
        // 奇回文串：(center - interval) - (center + interval)
        if (odd) {
            return (h[center + interval] - h[center - interval - 1] * powB[(interval << 1) + 1] - reverseH[center - interval] + reverseH[center + interval + 1] * powB[(interval << 1) + 1]) % P == 0;
        }
        // 偶回文串：(center - interval) - (center + interval - 1)
        return (h[center + interval - 1] - h[center - interval - 1] * powB[interval << 1] - reverseH[center - interval] + reverseH[center + interval] * powB[interval << 1]) % P == 0;
    }

    private int B = 131;
    private int P = (int) 1e9 + 7;
    private char CHAR_A = 'a';
    private long[] h;
    private long[] reverseH;
    private long[] powB;
}
