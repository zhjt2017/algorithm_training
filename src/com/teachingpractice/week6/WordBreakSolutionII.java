package com.teachingpractice.week6;

import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：单词拆分 II
 * - https://leetcode-cn.com/problems/word-break-ii/ (140题)
 * <p>
 * - 给定一个字符串 s 和一个字符串字典 wordDict ，
 * - 在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
 * - 以任意顺序 返回所有这些可能的句子。
 * - 注意：词典中的同一个单词可能在分段中被重复使用多次。
 * <p>
 * - 输入:s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * 输出:["cats and dog","cat sand dog"]
 * <p>
 * - 输入:s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
 * 输出:["pine apple pen apple","pineapple pen apple","pine applepen apple"]
 * 解释: 注意你可以重复使用字典中的单词。
 * <p>
 * - 输入:s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * 输出:[]
 * <p>
 * 1 <= s.length <= 20
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 10
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中所有字符串都 不同
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 04:09:47
 */
public class WordBreakSolutionII {
    public static void main(String[] args) {

    }

    /**
     * 记忆化搜索
     * - 参考：https://leetcode-cn.com/problems/word-break-ii/solution/140dan-ci-chai-fen-iijavazhi-jie-jian-da-m2l8/
     * @param s
     * @param wordDict
     * @return
     */
    List<String> wordBreak(final String s, final List<String> wordDict) {
        final List<String> ans = new LinkedList<>();
        return ans;
    }
}
