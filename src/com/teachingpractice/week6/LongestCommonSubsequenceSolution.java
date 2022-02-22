package com.teachingpractice.week6;

/**
 * 算法实现：动态规划 - 最长公共子序列
 * - https://leetcode-cn.com/problems/longest-common-subsequence/ (1143题)
 * <p>
 * - 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * - 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * - 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * - 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 * <p>
 * - 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 * <p>
 * - 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 * <p>
 * - 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 * <p>
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-22 03:42:36
 */
public class LongestCommonSubsequenceSolution {
    public static void main(String[] args) {
        final LongestCommonSubsequenceSolution solution = new LongestCommonSubsequenceSolution();

        String text1 = "abcde";
        String text2 = "ace";
        System.out.println("Input text1 : " + text1 + ", text2 : " + text2);
        System.out.println("Output longest common subsequence (two dimensions) (length) : " + solution.longestCommonSubsequenceDirectlyTwoDimension(text1, text2));
        System.out.println("Output longest common subsequence (length) : " + solution.longestCommonSubsequence(text1, text2));
        System.out.println("Output longest common subsequence (directly) (length) : " + solution.longestCommonSubsequenceDirectly(text1, text2));
        System.out.println();

        text1 = "abc";
        text2 = "abc";
        System.out.println("Input text1 : " + text1 + ", text2 : " + text2);
        System.out.println("Output longest common subsequence (two dimensions) (length) : " + solution.longestCommonSubsequenceDirectlyTwoDimension(text1, text2));
        System.out.println("Output longest common subsequence (length) : " + solution.longestCommonSubsequence(text1, text2));
        System.out.println("Output longest common subsequence (directly) (length) : " + solution.longestCommonSubsequenceDirectly(text1, text2));
        System.out.println();

        text1 = "abc";
        text2 = "def";
        System.out.println("Input text1 : " + text1 + ", text2 : " + text2);
        System.out.println("Output longest common subsequence (two dimensions) (length) : " + solution.longestCommonSubsequenceDirectlyTwoDimension(text1, text2));
        System.out.println("Output longest common subsequence (length) : " + solution.longestCommonSubsequence(text1, text2));
        System.out.println("Output longest common subsequence (directly) (length) : " + solution.longestCommonSubsequenceDirectly(text1, text2));
    }

    /**
     * - f[i, j]表示text1的前i个字符和text2的前j个字符能组成的LCS的长度
     * - 如果text1[i] == text2[j], f[i, j] = f[i - 1, j - 1] + 1
     * - 如果text1[i] != text2[j], f[i, j] = max(f[i - 1, j], f[i, j - 1])
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(min(M, N))
     *
     * @param text1
     * @param text2
     * @return
     */
    int longestCommonSubsequence(final String text1, final String text2) {
        final int m = text1.length();
        final int n = text2.length();
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }

        final int[] f = new int[n];
        int preValue, temp;
        for (int i = 0; i < m; i++) {
            preValue = f[0];
            if (f[0] == 0 && text1.charAt(i) == text2.charAt(0)) {
                f[0] = 1;
            }
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    temp = f[j];
                    f[j] = preValue + 1;
                    preValue = temp;
                } else {
                    preValue = f[j];
                    f[j] = Math.max(f[j], f[j - 1]);
                }
            }
        }
        return f[n - 1];
    }

    int longestCommonSubsequenceDirectly(final String text1, final String text2) {
        final int m = text1.length();
        final int n = text2.length();
        if (m < n) {
            return longestCommonSubsequenceDirectly(text2, text1);
        }

        final String s1 = " " + text1;
        final String s2 = " " + text2;
        final int[] f = new int[n + 1];
        int preValue, temp;
        for (int i = 1; i <= m; i++) {
            preValue = 0;
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    temp = f[j];
                    f[j] = preValue + 1;
                    preValue = temp;
                } else {
                    preValue = f[j];
                    f[j] = Math.max(f[j], f[j - 1]);
                }
            }
        }
        return f[n];
    }

    /**
     * 直接使用二维数组进行“状态”的表示
     * 时间复杂度 O(MN)
     * 空间复杂度 O(MN)
     *
     * @param text1
     * @param text2
     * @return
     */
    int longestCommonSubsequenceDirectlyTwoDimension(final String text1, final String text2) {
        final int m = text1.length();
        final int n = text2.length();
        if (m < n) {
            return longestCommonSubsequenceDirectly(text2, text1);
        }

        final int[][] f = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m][n];
    }
}
