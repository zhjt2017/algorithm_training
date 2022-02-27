package com.dailytraining.month202202;

/**
 * 算法训练(2022-02-23)：仅仅反转字母 (复习双指针)
 * - https://leetcode-cn.com/problems/reverse-only-letters/ (917题)
 * <p>
 * - 给你一个字符串 s ，根据下述规则反转字符串：
 * - 所有非英文字母保留在原有位置。
 * - 所有英文字母（小写或大写）位置反转。
 * - 返回反转后的 s 。
 * <p>
 * - 输入：s = "ab-cd"
 * 输出："dc-ba"
 * <p>
 * - 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * <p>
 * - 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 * <p>
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-23 05:02:38
 */
public class ReverseOnlyLettersSolution {
    public static void main(String[] args) {
        final ReverseOnlyLettersSolution solution = new ReverseOnlyLettersSolution();

        String s = "ab-cd";
        System.out.println("Input s : " + s);
        System.out.println("Output string after reversing only letters : " + solution.reverseOnlyLetters(s));
        System.out.println();

        s = "a-bC-dEf-ghIj";
        System.out.println("Input s : " + s);
        System.out.println("Output string after reversing only letters : " + solution.reverseOnlyLetters(s));
        System.out.println();

        s = "Test1ng-Leet=code-Q!";
        System.out.println("Input s : " + s);
        System.out.println("Output string after reversing only letters : " + solution.reverseOnlyLetters(s));
        System.out.println();
    }

    /**
     * 双指针夹逼
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(N) (Java语言String类型为不可变类型)
     *
     * @param s
     * @return
     */
    String reverseOnlyLetters(final String s) {
        // 根据题意，无论左指针还是右指针，遇到非英文字母跳过，遇到都是英文进行交换(若只有一侧是英文字母，则无须反转，保持原位置即可，换句话说，其反转之前与反转之后的效果应该是一样的)
        final char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        char temp;
        while (left < right) {
            if (!Character.isLetter(chars[left])) {
                left++;
                continue;
            }
            if (!Character.isLetter(chars[right])) {
                right--;
                continue;
            }
            temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

}
