package com.teachingpractice.week9;

/**
 * 算法实现 - 字符串处理 - Rabin-Karp字符串哈希算法 - 重复叠加字符串匹配
 * - https://leetcode-cn.com/problems/repeated-string-match/ (686题)
 * <p>
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 * <p>
 * - 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 * <p>
 * - 输入：a = "a", b = "aa"
 * 输出：2
 * <p>
 * - 输入：a = "a", b = "a"
 * 输出：1
 * <p>
 * - 输入：a = "abc", b = "wxyz"
 * 输出：-1
 * <p>
 * 1 <= a.length <= 10^4
 * 1 <= b.length <= 10^4
 * a 和 b 由小写英文字母组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 04:01:03
 */
public class RepeatedStringMatchSolution {
    public static void main(String[] args) {
        final RepeatedStringMatchSolution solution = new RepeatedStringMatchSolution();

        String a = "abcd";
        String b = "cdabcdab";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output repeated string match (times) : " + solution.repeatedStringMatch(a, b));
        System.out.println();

        a = "a";
        b = "aa";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output repeated string match (times) : " + solution.repeatedStringMatch(a, b));
        System.out.println();

        a = "a";
        b = "a";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output repeated string match (times) : " + solution.repeatedStringMatch(a, b));
        System.out.println();

        a = "abc";
        b = "wxyz";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output repeated string match (times) : " + solution.repeatedStringMatch(a, b));
        System.out.println();
    }

    int repeatedStringMatch(final String a, final String b) {
        return -1;
    }
}
