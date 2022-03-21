package com.teachingpractice.week9;

/**
 * 算法实现：字符串处理 - 字符串+动态规划 - 不同的子序列
 * - https://leetcode-cn.com/problems/distinct-subsequences/ (115题)
 * <p>
 * 给定一个字符串 s 和一个字符串 t ，计算s有多少个子序列中出现t。
 * <p>
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * <p>
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * - 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * rabbbit
 * rabbbit
 * rabbbit
 * <p>
 * - 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * babgbag
 * <p>
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 04:47:31
 */
public class DistinctSubsequencesSolution {
    public static void main(String[] args) {
        final DistinctSubsequencesSolution solution = new DistinctSubsequencesSolution();

        String s = "rabbbit";
        String t = "rabbit";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output distinct number of subsequences : " + solution.numDistinct(s, t));
        System.out.println("Output distinct number of subsequences (partial omission) : " + solution.numDistinctPartialOmission(s, t));
        System.out.println();

        s = "babgbag";
        t = "bag";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output distinct number of subsequences : " + solution.numDistinct(s, t));
        System.out.println("Output distinct number of subsequences (partial omission) : " + solution.numDistinctPartialOmission(s, t));
        System.out.println();
    }

    /**
     * f[i][j]表示s[1~i]选子序列得到t[1~j]的方案数
     * f[i][j] = f[i-1][j] (情况1：s中第i个字符没有继续匹配)
     * 如果s[i] == t[j], 有 f[i][j] += f[i-1][j-1] (情况2：s中第i个字符可以继续匹配的情况)
     * 注意：情况1+情况2就是所有的情况了，然后如果要细节到多个重复的或者连续重复的，已经包含在了情况2中
     * <p>
     * 初值：f[i][0] = 1
     * <p>
     * - 时间复杂度 O(nm)
     * - 空间复杂度 O(nm)
     * <p>
     * - 这是基本写法，必须将最优子结构与状态转移方程写出来
     *
     * @param s
     * @param t
     * @return
     */
    int numDistinct(final String s, final String t) {
        final int n = s.length();
        final int m = t.length();
        if (n < m) {
            return 0;
        }

        final int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            f[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = f[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[n][m];
    }

    /**
     * 在基础的动态规划解答上对空间复杂度进行优化
     * - 时间复杂度 O(nm)
     * - 空间复杂度 O(m)
     *
     * @param s
     * @param t
     * @return
     */
    int numDistinctPartialOmission(final String s, final String t) {
        final int n = s.length();
        final int m = t.length();
        if (n < m) {
            return 0;
        }

        final int[] f = new int[m + 1];
        final int[] lastF = new int[m + 1];
        f[0] = 1;
        lastF[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[j] += lastF[j - 1];
                }
            }
            for (int j = 1; j <= m; j++) {
                lastF[j] = f[j];
            }
        }
        return f[m];
    }


}
