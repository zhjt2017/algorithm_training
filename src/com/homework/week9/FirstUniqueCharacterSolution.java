package com.homework.week9;

import java.util.Arrays;

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
        System.out.println("Output first unique char (reversed) : " + solution.firstUniqCharReversed(s));
        System.out.println();

        s = "loveleetcode";
        System.out.println("Input s : " + s);
        System.out.println("Output first unique char : " + solution.firstUniqChar(s));
        System.out.println("Output first unique char (reversed) : " + solution.firstUniqCharReversed(s));
        System.out.println();

        s = "aabb";
        System.out.println("Input s : " + s);
        System.out.println("Output first unique char : " + solution.firstUniqChar(s));
        System.out.println("Output first unique char (reversed) : " + solution.firstUniqCharReversed(s));
        System.out.println();
    }

    /**
     * 我的思路1：全部扫一遍后，记录count，然后正向找到第一个count=1的，而由于s只包含小写字母，则HashMap可以优化为int[26]
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(∑) (∑=26)
     *
     * @param s
     * @return
     */
    int firstUniqChar(final String s) {
        final int[] count = new int[LETTER_SIZE];
        final int n = s.length();
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - A]++;
        }
        for (int i = 0; i < n; i++) {
            if (count[s.charAt(i) - A] == 1) {
                return i;
            }
        }
        return -1;
    }

    private static final int LETTER_SIZE = 26;
    private static final int A = 'a';
    private static final int INDEX_NOT_EXISTS = -1;

    /**
     * 我的思路2：无论是正向遍历还是反向遍历，均无法通过维护一个最终的index进行更新(因为一旦被废弃后没有选择替代者)
     * - 优化：int[]数组中存储index而不是count，这样就可以避免第二次遍历，而是直接从int[]中直接选取最小index即可
     *
     * @param s
     * @return
     */
    int firstUniqCharReversed(final String s) {
        final int n = s.length();
        final int[] index = new int[LETTER_SIZE];
        Arrays.fill(index, INDEX_NOT_EXISTS);
        int letterIndex;
        for (int i = s.length() - 1; i >= 0; i--) {
            letterIndex = s.charAt(i) - A;
            if (index[letterIndex] == INDEX_NOT_EXISTS) {
                index[letterIndex] = i;
            } else {
                index[letterIndex] = n;
            }
        }

        int ans = n;
        for (final int i : index) {
            if (i != INDEX_NOT_EXISTS && i < ans) {
                ans = i;
            }
        }
        return ans == n ? INDEX_NOT_EXISTS : ans;
    }
}
