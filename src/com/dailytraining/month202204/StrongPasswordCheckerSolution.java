package com.dailytraining.month202204;

/**
 * 算法训练(2022-04-02) 强密码校验器
 * - https://leetcode-cn.com/problems/strong-password-checker/ (420题)
 * <p>
 * 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * 由至少 6 个，至多 20 个字符组成。
 * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。
 * <p>
 * 在一步修改操作中，你可以：
 * <p>
 * 插入一个字符到 password ，
 * 从 password 中删除一个字符，或
 * 用另一个字符来替换 password 中的某个字符。
 * <p>
 * - 输入：password = "a"
 * 输出：5
 * <p>
 * - 输入：password = "aA1"
 * 输出：3
 * <p>
 * - 输入：password = "1337C0d3"
 * 输出：0
 * <p>
 * 1 <= password.length <= 50
 * password 由字母、数字、点 '.' 或者感叹号 '!'
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-02 04:29:29
 */
public class StrongPasswordCheckerSolution {
    public static void main(String[] args) {
        final StrongPasswordCheckerSolution solution = new StrongPasswordCheckerSolution();

        String password = "a";
        System.out.println("Input password : " + password);
        System.out.println("Output strong password checker : " + solution.strongPasswordChecker(password));
        System.out.println();

        password = "aA1";
        System.out.println("Input password : " + password);
        System.out.println("Output strong password checker : " + solution.strongPasswordChecker(password));
        System.out.println();

        password = "1337C0d3";
        System.out.println("Input password : " + password);
        System.out.println("Output strong password checker : " + solution.strongPasswordChecker(password));
        System.out.println();
    }

    /**
     * 这其实不算是一条训练题，只能O(n)扫描进行判断，分类讨论
     * @param password
     * @return
     */
    int strongPasswordChecker(final String password) {
        final int n = password.length();
        int hasLower = 0, hasUpper = 0, hasDigit = 0;
        for (int i = 0; i < n; ++i) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = 1;
                continue;
            }
            if (Character.isUpperCase(ch)) {
                hasUpper = 1;
                continue;
            }
            if (Character.isDigit(ch)) {
                hasDigit = 1;
            }
        }
        // 情况1：length < 6 (有效操作：插入字符、替换字符)


        // 情况2：6 <= length <= 20 (有效操作：替换字符)


        // 情况3：length > 20

        return 0;
    }
}
