package com.homework.week9;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 算法实现：字符串处理 - 同构 / 异位词系列问题 - 同构字符串
 * - https://leetcode-cn.com/problems/isomorphic-strings/ (205题)
 * <p>
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * <p>
 * - 输入：s = "egg", t = "add"
 * 输出：true
 * <p>
 * - 输入：s = "foo", t = "bar"
 * 输出：false
 * <p>
 * - 输入：s = "paper", t = "title"
 * 输出：true
 * <p>
 * 1 <= s.length <= 5 * 10^4
 * t.length == s.length
 * s 和 t 由任意有效的 ASCII 字符组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-08 06:02:31
 */
public class IsomorphicStringsSolution {
    public static void main(String[] args) {
        final IsomorphicStringsSolution solution = new IsomorphicStringsSolution();

        String s = "egg";
        String t = "add";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is isomorphic (common-startIndex) : " + solution.isIsomorphic(s, t));
        System.out.println("Output is isomorphic (Character hash) : " + solution.isIsomorphicCharacter(s, t));
        System.out.println();

        s = "foo";
        t = "bar";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is isomorphic (common-startIndex) : " + solution.isIsomorphic(s, t));
        System.out.println("Output is isomorphic (Character hash) : " + solution.isIsomorphicCharacter(s, t));
        System.out.println();

        s = "paper";
        t = "title";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is isomorphic (common-startIndex) : " + solution.isIsomorphic(s, t));
        System.out.println("Output is isomorphic (Character hash) : " + solution.isIsomorphicCharacter(s, t));
        System.out.println();

        s = "badc";
        t = "baba";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is isomorphic (common-startIndex) : " + solution.isIsomorphic(s, t));
        System.out.println("Output is isomorphic (Character hash) : " + solution.isIsomorphicCharacter(s, t));
        System.out.println();
    }

    /**
     * 我的思路：在s与t长度相等的基础上，根据同构的性质，比较遍历每个位置上的值映射的start index是否一致
     * (使用startIndex的思想更通用些(通过startIndex内聚了性质))
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(n)
     *
     * @param s
     * @param t
     * @return
     */
    boolean isIsomorphic(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }

        final int n = s.length();
        final HashMap<Character, Integer> sIndexHash = new HashMap<>(n);
        final HashMap<Character, Integer> tIndexHash = new HashMap<>(n);
        Character c;
        for (int i = 0; i < n; i++) {
            c = s.charAt(i);
            Integer start1 = sIndexHash.get(c);
            if (start1 == null) {
                start1 = i;
                sIndexHash.put(c, i);
            }

            c = t.charAt(i);
            Integer start2 = tIndexHash.get(c);
            if (start2 == null) {
                start2 = i;
                tIndexHash.put(c, i);
            }

            if (start1 != start2) {
                return false;
            }
        }
        return true;
    }

    /**
     * 次解：根据题意中的字符映射
     *
     * @param s
     * @param t
     * @return
     */
    boolean isIsomorphicCharacter(final String s, final String t) {
        if (s.length() != t.length()) {
            return false;
        }

        final int n = s.length();
        final HashMap<Character, Character> hash = new HashMap<>(n);
        final HashSet<Character> tSet = new HashSet<>();
        Character to, tValue;
        for (int i = 0; i < n; i++) {
            to = hash.get(s.charAt(i));
            tValue = t.charAt(i);
            if (to == null) {
                if (tSet.contains(tValue)) {
                    return false;
                }
                tSet.add(tValue);
                hash.put(s.charAt(i), tValue);
                continue;
            }
            if (!to.equals(tValue)) {
                return false;
            }
        }
        return true;
    }
}
