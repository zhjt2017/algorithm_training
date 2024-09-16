package com.homework.week9;

/**
 * 算法实现：字符串处理 - 字符串+动态规划 - 通配符匹配
 * - https://leetcode-cn.com/problems/wildcard-matching/ (44题)
 * <p>
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串, 0或多个任意字符）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *
 * <p>
 * - 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * <p>
 * - 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * <p>
 * - 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * <p>
 * - 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * <p>
 * - 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-08 10:21:59
 */
public class WildcardMatchingSolution {
    public static void main(String[] args) {
        final WildcardMatchingSolution solution = new WildcardMatchingSolution();

        String s = "aa";
        String p = "a";
        System.out.println("Input s : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();

        s = "aa";
        p = "*";
        System.out.println("Input s : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();

        s = "cb";
        p = "?a";
        System.out.println("Input s : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();

        s = "adceb";
        p = "*a*b";
        System.out.println("Input s : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();

        s = "acdcb";
        p = "a*c?b";
        System.out.println("Input s : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();
    }

    /**
     * dfs(从s的最后一个字符开始扫描，也算是人工数据归纳验证是否匹配)过程中的元素，构成了动态规划的状态
     * - f[i][j]表示s的前i个字符，p的前j个字符，能否匹配
     * - 注意：这里没有考虑p中的开头的*，其还可以匹配空字符串或任意多个任意字符
     * - 如果p[j]是小写字母，f[i][j] = f[i-1][j-1] && s[i]==p[j]
     * - 如果p[j]是?，f[i][j] = f[i-1][j-1]
     * - 如果p[j]是*，(1) || (2)
     * - (1) 匹配空格 f[i][j] = f[i][j-1]
     * - (2) 匹配>=1个任意字符 f[i][j] = f[i-1][j]
     * <p>
     * - 初值：f[0][0] = true, f[0][j] = true (j及之前一直是：*)
     * - 目标：f[n][m]
     * <p>
     * - 时间复杂度 O(nm)
     * - 空间复杂度 O(nm)
     *
     * @param sourceS
     * @param sourceP
     * @return
     */
    boolean isMatch(final String s, final String p) {
        final int n = s.length();
        final int m = p.length();

        final boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int j = 1; j <= m; j++) {
            if (p.charAt(j - 1) == ASTERISK) {
                f[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j - 1) == ANY) {
                    f[i][j] = f[i - 1][j - 1];
                    continue;
                }
                if (p.charAt(j - 1) == ASTERISK) {
                    f[i][j] = f[i - 1][j] || f[i][j - 1];
                    continue;
                }
                f[i][j] = f[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1);
            }
        }

        return f[n][m];
    }

    private static final char ASTERISK = '*';
    private static final char ANY = '?';
}
