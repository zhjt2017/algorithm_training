package com.homework.week9;

import java.util.Arrays;

/**
 * 算法实现：字符串处理 - 基础问题 - 最长公共前缀
 * - https://leetcode-cn.com/problems/longest-common-prefix/description/ (14题)
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * - 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * - 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * 
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 10:33:45
 */
public class LongestCommonPrefixSolution {
    public static void main(String[] args) {
        final LongestCommonPrefixSolution solution = new LongestCommonPrefixSolution();

        String[] strs = new String[] {"flower","flow","flight"};
        System.out.println("Input strs : " + Arrays.toString(strs));
        System.out.println("Output longest common prefix : " + solution.longestCommonPrefix(strs));
        System.out.println();

        strs = new String[] {"flower","flow","flight"};
        System.out.println("Input strs : " + Arrays.toString(strs));
        System.out.println("Output longest common prefix : " + solution.longestCommonPrefix(strs));
        System.out.println();
    }

    String longestCommonPrefix(final String[] strs) {
        return "";
    }
}
