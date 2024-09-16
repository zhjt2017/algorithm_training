package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-05) 最长特殊序列 I
 * - https://leetcode-cn.com/problems/longest-uncommon-subsequence-i/ (521题)
 * <p>
 * 给你两个字符串 a 和 b，请返回 这两个字符串中 最长的特殊序列  的长度。如果不存在，则返回 -1 。
 * <p>
 * 「最长特殊序列」 定义如下：该序列为 某字符串独有的最长子序列（即不能是其他字符串的子序列） 。
 * <p>
 * 字符串 s 的子序列是在从 s 中删除任意数量的字符后可以获得的字符串。
 * <p>
 * 例如，"abc" 是 "aebdc" 的子序列，因为删除 "aebdc" 中斜体加粗的字符可以得到 "abc" 。 "aebdc" 的子序列还包括 "aebdc" 、 "aeb" 和 "" (空字符串)。
 * <p>
 * - 输入: a = "aba", b = "cdc"
 * 输出: 3
 * 解释: 最长特殊序列可为 "aba" (或 "cdc")，两者均为自身的子序列且不是对方的子序列。
 * <p>
 * - 输入：a = "aaa", b = "aaa"
 * 输出：-1
 * 解释: 字符串 a 的每个子序列也是字符串 b 的每个子序列。同样，字符串 b 的每个子序列也是字符串 a 的子序列。
 * <p>
 * - 输入: a = "aaa", b = "bbb"
 * 输出: 3
 * 解释: 最长特殊序列可为 "aaa" (或 "bbb")，两者均为自身的子序列且不是对方的子序列。
 * <p>
 * 1 <= a.length, b.length <= 100
 * a 和 b 由小写英文字母组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-05 04:34:26
 */
public class LongestUncommonSubsequenceSolution {
    public static void main(String[] args) {
        final LongestUncommonSubsequenceSolution solution = new LongestUncommonSubsequenceSolution();

        String a = "aba";
        String b = "cdc";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output longest uncommon subsequence length : " + solution.findLUSlength(a, b));
        System.out.println();

        a = "aaa";
        b = "aaa";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output longest uncommon subsequence length : " + solution.findLUSlength(a, b));
        System.out.println();

        a = "aaa";
        b = "bbb";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output longest uncommon subsequence length : " + solution.findLUSlength(a, b));
        System.out.println();

        a = "";
        b = "";
        System.out.println("Input a : " + a + ", b : " + b);
        System.out.println("Output longest uncommon subsequence length : " + solution.findLUSlength(a, b));
        System.out.println();
    }

    /**
     * (脑筋急转弯)
     *
     * @param a
     * @param b
     * @return
     */
    int findLUSlength(final String a, final String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
