package com.dailytraining.month202204;

/**
 * 算法训练(2022-04-07) 旋转字符串
 * - https://leetcode-cn.com/problems/rotate-string/ (796题)
 * <p>
 * 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 * <p>
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。 
 * <p>
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 * <p>
 * - 输入: s = "abcde", goal = "cdeab"
 * 输出: true
 * <p>
 * - 输入: s = "abcde", goal = "abced"
 * 输出: false
 * <p>
 * 1 <= s.length, goal.length <= 100
 * s 和 goal 由小写英文字母组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-07 09:04:54
 */
public class RotateStringSolution {
    public static void main(String[] args) {
        final RotateStringSolution solution = new RotateStringSolution();

        String s = "abcde";
        String goal = "cdeab";
        System.out.println("Input s : " + s + ", goal : " + goal);
        System.out.println("Output rotate string : " + solution.rotateString(s, goal));
        System.out.println("Output rotate string (contains) : " + solution.rotateStringContains(s, goal));
        System.out.println();

        s = "abcde";
        goal = "abced";
        System.out.println("Input s : " + s + ", goal : " + goal);
        System.out.println("Output rotate string : " + solution.rotateString(s, goal));
        System.out.println("Output rotate string (contains) : " + solution.rotateStringContains(s, goal));
        System.out.println();
    }

    /**
     * 我的初始题解
     * <p>
     * - 时间复杂度 O(n^2)
     *
     * @param s
     * @param goal
     * @return
     */
    boolean rotateString(final String s, final String goal) {
        final int n = s.length();
        final StringBuilder sb = new StringBuilder(n << 1);
        sb.append(s).append(s);
        for (int i = 0; i < n; i++) {
            if (goal.equals(sb.substring(i, i + n))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 题解2
     * <p>
     * - 时间复杂度 O(n) (KMP算法contains)
     *
     * @param s
     * @param goal
     * @return
     */
    boolean rotateStringContains(final String s, final String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        return (s + s).contains(goal);
    }
}
