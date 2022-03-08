package com.homework.week9;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法实现：字符串处理 - 同构 / 异位词系列问题 - 找到字符串中所有字母异位词
 * - https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/ (438题)
 * <p>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * <p>
 * - 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * <p>
 * - 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * 1 <= s.length, p.length <= 3 * 10^4
 * s 和 p 仅包含小写字母
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-08 10:04:05
 */
public class FindAllAnagramsInStringSolution {
    public static void main(String[] args) {
        final FindAllAnagramsInStringSolution solution = new FindAllAnagramsInStringSolution();

        String s = "cbaebabacd";
        String p = "abc";
        System.out.println("Input s : " + s + ", p : " + p);
        System.out.println("Output find all anagrams : " + solution.findAnagrams(s, p));
        System.out.println();

        s = "abab";
        p = "ab";
        System.out.println("Input s : " + s + ", p : " + p);
        System.out.println("Output find all anagrams : " + solution.findAnagrams(s, p));
        System.out.println();
    }

    List<Integer> findAnagrams(final String s, final String p) {
        final List<Integer> ans = new ArrayList<>();

        return ans;
    }
}
