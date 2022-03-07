package com.homework.week9;

/**
 * 算法实现：字符串处理 - 基础问题 - 字符串中的第一个唯一字符
 * - https://leetcode-cn.com/problems/first-unique-character-in-a-string/ (387题)
 * <p>
 * 给定一个字符串 s ，找到 它的第一个不重复的字符，并返回它的索引 。如果不存在，则返回 -1 。
 * <p>
 * - 输入: s = "leetcode"
 * 输出: 0
 * <p>
 * - 输入: s = "loveleetcode"
 * 输出: 2
 * <p>
 * - 输入: s = "aabb"
 * 输出: -1
 * <p>
 * 1 <= s.length <= 10^5
 * s 只包含小写字母
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 10:29:20
 */
public class FirstUniqueCharacterSolution {
    public static void main(String[] args) {
        final FirstUniqueCharacterSolution solution = new FirstUniqueCharacterSolution();

        String s = "leetcode";
        System.out.println("Input s : " + s);
        System.out.println("Output first unique char : " + solution.firstUniqChar(s));
        System.out.println();

        s = "loveleetcode";
        System.out.println("Input s : " + s);
        System.out.println("Output first unique char : " + solution.firstUniqChar(s));
        System.out.println();

        s = "aabb";
        System.out.println("Input s : " + s);
        System.out.println("Output first unique char : " + solution.firstUniqChar(s));
        System.out.println();
    }

    int firstUniqChar(final String s) {
        return 0;
    }
}
