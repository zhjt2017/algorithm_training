package com.homework.week9;

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
        System.out.println("Output is isomorphic : " + solution.isIsomorphic(s, t));
        System.out.println();

        s = "foo";
        t = "bar";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is isomorphic : " + solution.isIsomorphic(s, t));
        System.out.println();

        s = "paper";
        t = "title";
        System.out.println("Input s : " + s + ", t : " + t);
        System.out.println("Output is isomorphic : " + solution.isIsomorphic(s, t));
        System.out.println();
    }

    boolean isIsomorphic(final String s, final String t) {
        return false;
    }
}
