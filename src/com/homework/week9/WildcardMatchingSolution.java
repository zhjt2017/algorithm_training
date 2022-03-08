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

    boolean isMatch(final String s, final String p) {
        return false;
    }
}
