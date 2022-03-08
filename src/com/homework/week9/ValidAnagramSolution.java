package com.homework.week9;

/**
 * 算法实现：字符串处理 - 同构 / 异位词系列问题 - 有效的字母异位词 (anagram : 相同字母异序词)
 * - https://leetcode-cn.com/problems/valid-anagram/ (242题)
 * <p>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * <p>
 * - 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * - 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 1 <= s.length, t.length <= 5 * 10^4
 * s 和 t 仅包含小写字母
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-08 09:42:37
 */
public class ValidAnagramSolution {
    public static void main(String[] args) {
        final ValidAnagramSolution solution = new ValidAnagramSolution();

        String s = "anagram";
        String t = "nagaram";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is anagram : " + solution.isAnagram(s, t));
        System.out.println();

        s = "rat";
        t = "car";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is anagram : " + solution.isAnagram(s, t));
        System.out.println();
    }

    boolean isAnagram(final String s, final String t) {
        return false;
    }
}
