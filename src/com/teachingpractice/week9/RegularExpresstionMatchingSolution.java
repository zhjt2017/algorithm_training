package com.teachingpractice.week9;

/**
 * 算法实现：字符串处理 - 字符串+动态规划 - 正则表达式匹配
 * - https://leetcode-cn.com/problems/regular-expression-matching/ (10题)
 * <p>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * - 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * <p>
 * - 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * <p>
 * - 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * <p>
 * 1 <= s.length <= 20
 * 1 <= p.length <= 30
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 保证每次出现字符 * 时，前面都匹配到有效的字符
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 04:39:43
 */
public class RegularExpresstionMatchingSolution {
    public static void main(String[] args) {
        final RegularExpresstionMatchingSolution solution = new RegularExpresstionMatchingSolution();

        String s = "aa";
        String p = "a";
        System.out.println("Input : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();

        s = "aa";
        p = "a*";
        System.out.println("Input : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();

        s = "ab";
        p = ".*";
        System.out.println("Input : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();

        s = "aab";
        p = "c*a*b";
        System.out.println("Input : " + s + ", p : " + p);
        System.out.println("Output is match : " + solution.isMatch(s, p));
        System.out.println();
    }

    /**
     * dfs(从s的最后一个字符开始扫描，也算是人工数据归纳验证是否匹配)过程中的元素，构成了动态规划的状态
     * - f[i][j]表示s的前i个字符，p的前j个字符，能否匹配
     * - 如果p[j]是小写字母，f[i][j] = f[i-1][j-1] && s[i]==p[j]
     * - 如果p[j]是.，f[i][j] = f[i-1][j-1]
     * - 如果p[j]是*，
     * - (1) 继续配 f[i][j] <- f[i-1][j] && (p[j-1]=='.' || s[i]==p[j-1])
     * - (2) 不配了，*的使命完成了 f[i][j] <- f[i][j-2]
     * - 至于拆分出来的每一个f[i-1][j]或f[i][j-2]能否匹配，怎么匹配，则是子问题了
     * <p>
     * - 初值：f[0][0] = true, f[0][j] = true (j及之前一直是：某*)
     * - 目标：f[n][m]
     * <p>
     * - 时间复杂度 O(nm)
     * - 空间复杂度 O(nm)
     *
     * @param sourceS
     * @param sourceP
     * @return
     */
    boolean isMatch(final String sourceS, final String sourceP) {
        final int n = sourceS.length();
        final int m = sourceP.length();
        final String s = " " + sourceS;
        final String p = " " + sourceP;
        final boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int j = 2; j <= m; j += 2) {
            if (p.charAt(j) == ASTERISK) {
                f[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (p.charAt(j) == DOT) {
                    f[i][j] = f[i - 1][j - 1];
                    continue;
                }
                if (p.charAt(j) != ASTERISK) {
                    f[i][j] = f[i - 1][j - 1] && s.charAt(i) == p.charAt(j);
                    continue;
                }

                // * 匹配0个的情形
                f[i][j] = f[i][j - 2];
                // * 匹配多个的情形 (或)
                if (p.charAt(j - 1) == DOT || p.charAt(j - 1) == s.charAt(i)) {
                    f[i][j] = f[i][j] || f[i - 1][j];
                }
            }
        }

        return f[n][m];
    }

    private static final char ASTERISK = '*';
    private static final char DOT = '.';
}
