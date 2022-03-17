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
        System.out.println();

        s = "babgbag";
        t = "bag";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output distinct number of subsequences : " + solution.numDistinct(s, t));
        System.out.println();
    }

    int numDistinct(final String s, final String t) {
        return 0;
    }
}
