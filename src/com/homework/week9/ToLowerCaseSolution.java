package com.homework.week9;

/**
 * 算法实现：字符串处理 - 基础问题 - 转换成小写字母
 * - https://leetcode-cn.com/problems/to-lower-case/ (709题)
 * <p>
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 * <p>
 * - 输入：s = "Hello"
 * 输出："hello"
 * <p>
 * - 输入：s = "here"
 * 输出："here"
 * <p>
 * - 输入：s = "LOVELY"
 * 输出："lovely"
 * <p>
 * 1 <= s.length <= 100
 * s 由 ASCII 字符集中的可打印字符组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 03:54:43
 */
public class ToLowerCaseSolution {
    public static void main(String[] args) {
        final ToLowerCaseSolution solution = new ToLowerCaseSolution();

        String s = "Hello";
        System.out.println("Input s : " + s);
        System.out.println("Output to lower case : " + solution.toLowerCase(s));
        System.out.println("Output to upper case : " + solution.toUpperCase(s));
        System.out.println("Output to lower case (jdk) : " + solution.toLowerCaseJdk(s));
        System.out.println();

        s = "here";
        System.out.println("Input s : " + s);
        System.out.println("Output to lower case : " + solution.toLowerCase(s));
        System.out.println("Output to upper case : " + solution.toUpperCase(s));
        System.out.println("Output to lower case (jdk) : " + solution.toLowerCaseJdk(s));
        System.out.println();

        s = "LOVELY";
        System.out.println("Input s : " + s);
        System.out.println("Output to lower case : " + solution.toLowerCase(s));
        System.out.println("Output to upper case : " + solution.toUpperCase(s));
        System.out.println("Output to lower case (jdk) : " + solution.toLowerCaseJdk(s));
        System.out.println();
    }

    /**
     * 单独实现lower case功能
     * - 'A' + 32 = 'a'  -  32 : 00100000 (ASCII码将英文字母设置为这样的值是有好处的，便于二进制大小写转换, 26 < 32)
     * - 'A' - 'Z' : [65, 90]  -  [01000001, 01011010]
     * - 'a' - 'z' : [97, 122] - [01100001, 01111010]
     * - lower = upper | 32
     * - upper = lower & (~32)
     * <p>
     * - 时间复杂度 O(n) + 位运算优化
     * - 空间复杂度 O(1)
     *
     * @param s
     * @return
     */
    String toLowerCase(final String s) {
        final int n = s.length();
        final StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= A && c <= Z) {
                c |= UPPER_2_LOWER;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    String toUpperCase(final String s) {
        final int n = s.length();
        final StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= A_LOWER && c <= Z_LOWER) {
                c &= LOWER_2_UPPER;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static final char A = 'A';
    private static final char Z = 'Z';
    private static final char A_LOWER = 'a';
    private static final char Z_LOWER = 'z';
    private static final int UPPER_2_LOWER = 1 << 5;
    private static final int LOWER_2_UPPER = ~(1 << 5);

    /**
     * 找到UpperCase，然后调用Character.toLowerCase()
     *
     * @param s
     * @return
     */
    String toLowerCaseJdk(final String s) {
        return s.toLowerCase();
    }
}
