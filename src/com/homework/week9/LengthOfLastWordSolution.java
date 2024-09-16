package com.homework.week9;

/**
 * 算法实现：字符串处理 - 基础问题 - 最后一个单词的长度
 * - https://leetcode-cn.com/problems/length-of-last-word/ (58题)
 * <p>
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * - 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是“World”，长度为5。
 * <p>
 * - 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是“moon”，长度为4。
 * <p>
 * - 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是长度为6的“joyboy”。
 * <p>
 * 1 <= s.length <= 10^4
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 05:06:58
 */
public class LengthOfLastWordSolution {
    public static void main(String[] args) {
        final LengthOfLastWordSolution solution = new LengthOfLastWordSolution();

        String s = "Hello World";
        System.out.println("Input s : " + s);
        System.out.println("Output length of last word : " + solution.lengthOfLastWord(s));
        System.out.println();

        s = "   fly me   to   the moon  ";
        System.out.println("Input s : " + s);
        System.out.println("Output length of last word : " + solution.lengthOfLastWord(s));
        System.out.println();

        s = "luffy is still joyboy";
        System.out.println("Input s : " + s);
        System.out.println("Output length of last word : " + solution.lengthOfLastWord(s));
        System.out.println();
    }

    /**
     * 从后向前遍历，直接模拟
     *
     * @param s
     * @return
     */
    int lengthOfLastWord(final String s) {
        int index = s.length() - 1;
        // 找到最右边第一个不是空格的字符
        while (s.charAt(index) == BLANK) {
            index--;
        }
        // 根据题意，至少存在一个单词，则last必然>=0
        final int last = index;
        // 找到最后一个单词的前面一个字符
        while (s.charAt(index) != BLANK) {
            index--;
            if (index == -1) {
                return last + 1;
            }
        }
        return last - index;
    }

    private static final char BLANK = ' ';
}
