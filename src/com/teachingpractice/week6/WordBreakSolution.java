package com.teachingpractice.week6;

import java.util.List;

/**
 * 算法实现：动态规划 - 单词拆分
 * - https://leetcode-cn.com/problems/word-break/ (139题)
 * <p>
 * - 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * - 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * <p>
 * - 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 * <p>
 * - 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 * <p>
 * - 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 04:09:47
 */
public class WordBreakSolution {
    public static void main(String[] args) {

    }

    /**
     * 记忆化搜索
     *
     * @param s
     * @param wordDict
     * @return
     */
    boolean wordBreakMemorySearch(final String s, final List<String> wordDict) {
        return false;
    }

    /**
     * 动态规划 DP
     * - 时间复杂度 O(N^2)
     * - 空间复杂度 O(N)
     *
     * @param s
     * @param wordDict
     * @return
     */
    boolean wordBreakDynamicPlan(final String s, final List<String> wordDict) {
        return false;
    }
}
