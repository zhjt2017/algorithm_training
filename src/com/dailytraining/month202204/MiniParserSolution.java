package com.dailytraining.month202204;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法训练(2022-04-15) 迷你语法分析器
 * - https://leetcode-cn.com/problems/mini-parser (385题)
 * <p>
 * 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
 * <p>
 * 列表中的每个元素只可能是整数或整数嵌套列表
 * <p>
 * - 输入：s = "324",
 * 输出：324
 * 解释：你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
 * <p>
 * - 输入：s = "[123,[456,[789]]]",
 * 输出：[123,[456,[789]]]
 * 解释：返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
 * 1. 一个 integer 包含值 123
 * 2. 一个包含两个元素的嵌套列表：
 * i.  一个 integer 包含值 456
 * ii. 一个包含一个元素的嵌套列表
 * a. 一个 integer 包含值 789
 * <p>
 * 1 <= s.length <= 5 * 104
 * s 由数字、方括号 "[]"、负号 '-' 、逗号 ','组成
 * 用例保证 s 是可解析的 NestedInteger
 * 输入中的所有值的范围是 [-106, 106]
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-15 09:47:40
 */
public class MiniParserSolution {
    public static void main(String[] args) {

    }

    private static final char NEST_LEFT = '[';
    private static final char NEST_RIGHT = ']';

    NestedInteger deserialize(final String s) {
        if (s.charAt(0) != NEST_LEFT) {
            return new NestedInteger(Integer.parseInt(s));
        }
        final int n = s.length();
        final Deque<NestedInteger> stack = new LinkedList<>();
        int num = 0;
        boolean negative = false;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '-') {
                negative = true;
                continue;
            }
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
                continue;
            }
            if (c == NEST_LEFT) {
                stack.push(new NestedInteger());
                continue;
            }

            if (Character.isDigit(s.charAt(i - 1))) {
                stack.peek().add(new NestedInteger(negative ? -num : num));
            }
            num = 0;
            negative = false;
            if (c == ',') {
                continue;
            }
            if (i == n - 1) {
                return stack.pop();
            }
            NestedInteger curr = stack.pop();
            stack.peek().add(curr);
        }
        return stack.pop();
    }

    static class NestedInteger {

        private Integer value;

        // Constructor initializes an empty nested list.
        public NestedInteger() {

        }

        // Constructor initializes a single integer.
        public NestedInteger(int value) {
            this.value = value;
        }

        public boolean isInteger() {
            return false;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return this.value;
        }

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value) {
        }

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni) {

        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return new ArrayList<>();
        }
    }
}
