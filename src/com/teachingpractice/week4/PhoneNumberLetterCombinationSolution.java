package com.teachingpractice.week4;

import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：搜索-电话号码的字母组合
 * - https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/ (17)
 * <p>
 * - 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * - 2-9这8个数字，共映射26个字母，具体映射如下
 * - 2 a,b,c
 * - 3 d,e,f
 * - 4 g,h,i
 * - 5 j,k,l
 * - 6 m,n,o
 * - 7 p,q,r,s
 * - 8 t,u,v
 * - 9 w,x,y,z
 * <p>
 * - 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * - 输入：digits = ""
 * 输出：[]
 * <p>
 * - 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 搜索实现：
 * 1、DFS：时间复杂度O(3^n), 空间复杂度O(n)
 * 2、BFS：时间复杂度与空间复杂度是一样的，还要维护队列，及StringBuilder组装的中间字符串，不推荐
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-18 03:55:29
 */
public class PhoneNumberLetterCombinationSolution {
    public static void main(String[] args) {
        String digits = "23";
        System.out.println("Input digits : " + digits);
        System.out.println("Output letter combinations : " + letterCombinations(digits));

        digits = "";
        System.out.println("Input digits : " + digits);
        System.out.println("Output letter combinations : " + letterCombinations(digits));

        digits = "2";
        System.out.println("Input digits : " + digits);
        System.out.println("Output letter combinations : " + letterCombinations(digits));
    }

    private static List<String> letterCombinations(final String digits) {
        if (digits == null || digits.isEmpty()) {
            return new LinkedList<>();
        }

        final PhoneNumberLetterCombinationSolution solution = new PhoneNumberLetterCombinationSolution();
        solution.digits = digits;
        solution.sb = new StringBuilder();
        solution.results = new LinkedList<>();
        solution.dfs(0);
        return solution.results;
    }

    private void dfs(final int index) {
        if (index == digits.length()) {
            results.add(sb.toString());
            return;
        }

        final String letters = DICT[digits.charAt(index) - '2'];
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            dfs(index + 1);
            // 还原现场
            sb.deleteCharAt(index);
        }
    }

    private static final String[] DICT = new String[8];

    static {
        DICT['2' - '2'] = "abc";
        DICT['3' - '2'] = "def";
        DICT['4' - '2'] = "ghi";
        DICT['5' - '2'] = "jkl";
        DICT['6' - '2'] = "mno";
        DICT['7' - '2'] = "pqrs";
        DICT['8' - '2'] = "tuv";
        DICT['9' - '2'] = "wxyz";
    }

    private String digits;
    private StringBuilder sb;
    private List<String> results;
}
