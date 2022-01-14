package com.speed.week3;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法实现：括号生成
 * - leetcode-cn 22题
 * <p>
 * - 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * - 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * - 输入：n = 1
 * 输出：["()"]
 * <p>
 * 1 <= n <= 8
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-14 11:56:07
 */
public class GenerateParenthesisSolution {
    public static void main(String[] args) {
        int n = 1;
        System.out.println("n = " + n);
        System.out.println("result : " + generateParenthesis(n));
    }

    private static List<String> generateParenthesis(final int n) {
        final List<String> result = new ArrayList<>();
        return result;
    }
}
