package com.teachingpractice.week9;

/**
 * 算法实现：字符串处理 - 基础知识 - 字符串转整数 (atoi : ascii to integer)
 * - https://leetcode-cn.com/problems/string-to-integer-atoi/ (8题)
 * <p>
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 * <p>
 * - 输入：s = "42"
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："42"（读入 "42"）
 * ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
 * <p>
 * - 输入：s = "   -42"
 * 输出：-42
 * 解释：
 * 第 1 步："   -42"（读入前导空格，但忽视掉）
 * ^
 * 第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
 * ^
 * 第 3 步："   -42"（读入 "42"）
 * ^
 * 解析得到整数 -42 。
 * 由于 "-42" 在范围 [-231, 231 - 1] 内，最终结果为 -42 。
 * <p>
 * - 输入：s = "4193 with words"
 * 输出：4193
 * 解释：
 * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 * ^
 * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * ^
 * 第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 * ^
 * 解析得到整数 4193 。
 * 由于 "4193" 在范围 [-231, 231 - 1] 内，最终结果为 4193 。
 * <p>
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-01 03:31:53
 */
public class AtoiSolution {
    public static void main(String[] args) {
        final AtoiSolution solution = new AtoiSolution();

        String s = "42";
        System.out.println("Input s : " + s);
        System.out.println("Output my atoi : " + solution.myAtoi(s));
        System.out.println("Output my atoi (original) : " + solution.myAtoiOriginal(s));
        System.out.println();

        s = "     -42";
        System.out.println("Input s : " + s);
        System.out.println("Output my atoi : " + solution.myAtoi(s));
        System.out.println("Output my atoi (original) : " + solution.myAtoiOriginal(s));
        System.out.println();

        s = "4193 with words";
        System.out.println("Input s : " + s);
        System.out.println("Output my atoi : " + solution.myAtoi(s));
        System.out.println("Output my atoi (original) : " + solution.myAtoiOriginal(s));
    }

    int myAtoiOriginal(final String s) {
        int i = 0;
        int len = s.length();
        int sign = 1;
        while (i < len && s.charAt(i) == BLANK) {
            i++;
        }
        if (i >= len) {
            return 0;
        }
        if (s.charAt(i) == PLUS || s.charAt(i) == MINUS) {
            if (s.charAt(i) == MINUS) {
                sign = -1;
            }
            i++;
        }

        int res = 0;
        for (; i < len; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                break;
            }
            char c = s.charAt(i);
            int num = c - '0';
            if (res > MAX_QUOTIENT || (res == MAX_QUOTIENT && num > MAX_REMAINDER)) {
                return Integer.MAX_VALUE;
            }
            if (res < MIN_QUOTIENT || (res == MIN_QUOTIENT && -num < MIN_REMAINDER)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * num;
        }
        return res;
    }

    private static final char BLANK = ' ';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final int MAX_REMAINDER = Integer.MAX_VALUE % 10;
    private static final int MIN_REMAINDER = Integer.MIN_VALUE % 10;
    private static final int MAX_QUOTIENT = Integer.MAX_VALUE / 10;
    private static final int MIN_QUOTIENT = Integer.MIN_VALUE / 10;

    /**
     * 根据题意完全模拟：逐个字符检查 (与myAtoiOriginal其实是一样的)
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     *
     * @param s
     * @return
     */
    int myAtoi(final String s) {
        final int n = s.length();
        int index = 0;
        // 1、忽略前导空格
        while (index < n && s.charAt(index) == BLANK) {
            index++;
        }
        // 2、下一个字符：处理正负号 (正号可能被忽略)
        int sign = 1;
        if (index < n && (s.charAt(index) == PLUS || s.charAt(index) == MINUS)) {
            if (s.charAt(index) == MINUS) {
                sign = -1;
            }
            index++;
        }
        // 3、处理数字，直到非数字或结尾
        int value = 0;
        char c;
        while (index < n && Character.isDigit((c = s.charAt(index)))) {
            // 4、绝对值如果>INT_MAX, 直接取int极值, 否则取极值：value * 10 + (c - '0') > INT_MAX, 即value > (INT_MAX - (c - '0')) / 10
            if (value > (Integer.MAX_VALUE - c + '0') / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            value = value * 10 - '0' + c;
            index++;
        }
        // 5、返回最终结果
        return sign * value;
    }
}
