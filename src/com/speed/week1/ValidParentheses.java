package com.speed.week1;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有效的括号
 * -判断括号是否成对闭合
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2021-12-30 06:37:01
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("{()[}]"));
        System.out.println(isValid("{(([{()}]))}"));
    }

    private static boolean isValid(final String s) {
        int n = s.length();
        if ((n & 1) == 1) {
            return false;
        }

        /* 彻底理解成对闭合是什么意思：允许添加多重左括号, 但是使用右括号进行闭合时又必须是左括号刚好相反的顺序, 故使用数据结构：栈 */
        Map<Character, Character> pairs = new HashMap<Character, Character>(3) {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                stack.push(ch);
                continue;
            }
            if (stack.isEmpty() || !pairs.get(stack.peek()).equals(ch)) {
                return false;
            }
            stack.pop();
        }
        return stack.isEmpty();
    }
}
