package com.homework.week9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 算法实现：字符串处理 - 基本操作问题 - 翻转字符串里的单词
 * - https://leetcode-cn.com/problems/reverse-words-in-a-string/ (151题)
 * <p>
 * - 给你一个字符串 s ，逐个翻转字符串中的所有 单词 。
 * - 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * - 请你返回一个翻转 s 中单词顺序并用单个空格相连的字符串。
 * - 说明：
 * 输入字符串 s 可以在前面、后面或者单词间包含多余的空格。
 * 翻转后单词间应当仅用一个空格分隔。
 * 翻转后的字符串中不应包含额外的空格。
 * <p>
 * - 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * <p>
 * - 输入：s = "  hello world  "
 * 输出："world hello"
 * <p>
 * - 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * <p>
 * - 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * <p>
 * 1 <= s.length <= 10^4
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * <p>
 * 要求：尝试使用O(1) 额外空间复杂度的原地解法
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 10:52:52
 */
public class ReverseWordsSolution {
    public static void main(String[] args) {
        final ReverseWordsSolution solution = new ReverseWordsSolution();

        String s = "the sky is blue";
        System.out.println("Input s : " + s);
        System.out.println("Output reversed words (SDK) : " + solution.reverseWordsSDK(s));
        System.out.println("Output reversed words : " + solution.reverseWords(s));
        System.out.println();

        s = "  hello world  ";
        System.out.println("Input s : " + s);
        System.out.println("Output reversed words (SDK) : " + solution.reverseWordsSDK(s));
        System.out.println("Output reversed words : " + solution.reverseWords(s));
        System.out.println();

        s = "  Bob    Loves  Alice   ";
        System.out.println("Input s : " + s);
        System.out.println("Output reversed words (SDK) : " + solution.reverseWordsSDK(s));
        System.out.println("Output reversed words : " + solution.reverseWords(s));
        System.out.println();

        s = "Alice does not even like bob";
        System.out.println("Input s : " + s);
        System.out.println("Output reversed words (SDK) : " + solution.reverseWordsSDK(s));
        System.out.println("Output reversed words : " + solution.reverseWords(s));
    }

    /**
     * SDK自带工具方法的复合调用
     * - 时间复杂度 O(NlogN)
     * - 空间复杂度 O(N)
     * - leetcode run, 8ms, 41.1MB
     *
     * @param s
     * @return
     */
    String reverseWordsSDK(final String s) {
        // regex也可以写成 \\s+
        List<String> list = Arrays.asList(s.trim().split(" +"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    /**
     * 一次遍历(根据题意从右向左模拟)完成 (从右向左遍历，StringBuilder不用移动已经放好的元素，仅使用append方法即可)
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     * - leetcode run, 2ms, 40.9MB
     *
     * @param s
     * @return
     */
    String reverseWords(final String s) {
        final StringBuilder sb = new StringBuilder(s.length());
        int right = s.length() - 1;
        // 1、到达第一个非空格字符(结果中第一个单词的最后一个字符) (根据题意，至少存在一个单词，故不用加范围判断)
        while (s.charAt(right) == BLANK) {
            right--;
        }

        int end;
        while (right >= 0) {
            // 2、使用end记录下该单词的最后一个字符位置，然后到达该单词的第一个字符的前面一个位置 (空格或者right=-1)
            end = right;
            while (right >= 0 && s.charAt(right) != BLANK) {
                right--;
            }
            // 3、向结果中写入该单词，并在单词结尾固定加入单个空格作为分隔符
            sb.append(s, right + 1, end + 1);
            sb.append(BLANK);
            // 4、循环：到达下一个单词的最后一个字符(或者right=-1), 转到2
            while (right >= 0 && s.charAt(right) == BLANK) {
                right--;
            }
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static final char BLANK = ' ';
}
