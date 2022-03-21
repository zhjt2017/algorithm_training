package com.homework.week9;

import java.util.Arrays;

/**
 * 算法实现：字符串处理 - 基本操作问题 - 反转字符串
 * - https://leetcode-cn.com/problems/reverse-string/ (344题)
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * - 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * <p>
 * - 输入：s = ["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 1 <= s.length <= 10^5
 * s[i] 都是 ASCII 码表中的可打印字符
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 10:36:50
 */
public class ReverseStringSolution {
    public static void main(String[] args) {
        final ReverseStringSolution solution = new ReverseStringSolution();

        char[] s = new char[]{'h', 'e', 'l', 'l', 'o'};
        System.out.println("Input s : " + Arrays.toString(s));
        solution.reverseString(s);
        System.out.println("Output s : " + Arrays.toString(s));
        System.out.println();

        s = new char[]{'H', 'a', 'n', 'n', 'a', 'H'};
        System.out.println("Input s : " + Arrays.toString(s));
        solution.reverseString(s);
        System.out.println("Output s : " + Arrays.toString(s));
        System.out.println();
    }

    /**
     * 双指针夹逼
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     * @param s
     */
    void reverseString(final char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            swap(s, left, right);
            left++;
            right--;
        }
    }

    private void swap(final char[] s, final int left, final int right) {
        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;
    }
}
