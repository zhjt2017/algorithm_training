package com.teachingpractice.week7;

/**
 * 动态规划 (重点题) - 编辑距离
 * - https://leetcode-cn.com/problems/edit-distance/ (72题)
 * <p>
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * - 插入一个字符
 * - 删除一个字符
 * - 替换一个字符
 * <p>
 * - 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * <p>
 * - 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 08:02:02
 */
public class EditDistanceSolution {
    public static void main(String[] args) {
        final EditDistanceSolution solution = new EditDistanceSolution();

        String word1 = "horse";
        String word2 = "ros";
        System.out.println("Input word1 : " + word1 + ", word2 : " + word2);
        System.out.println("Output minimum edit distance : " + solution.minDistance(word1, word2));

        word1 = "intention";
        word2 = "execution";
        System.out.println("Input word1 : " + word1 + ", word2 : " + word2);
        System.out.println("Output minimum edit distance : " + solution.minDistance(word1, word2));
    }

    int minDistance(final String word1, final String word2) {
        return 0;
    }
}
