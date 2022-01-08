package com.teachingpractice.week2;

import java.util.*;

/**
 * 串联所有单词的子串
 * - 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * <p>
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * <p>
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * <p>
 * 输入：s = "aaaaaaaaaaaaaa", words = ["aa","aa"]
 * 输出：[0,2,4,6,8,10]
 *
 * <p>
 * 1 <= s.length <= 10^4
 * s 由小写英文字母组成
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 由小写英文字母组成
 * <p>
 * 题意分析：字串刚好覆盖所有单词，哪怕给定的单词组words中有重复的单词，他重复几个，我也重复几个。
 * 单词长度相同，那么字串长度固定
 * 2点优化：
 * 1、匹配时，给定的words中同一个单词可能出现多次，故使用Map，key为word，value为个数，给定标准Map，
 * 当子字符串中的某一个单词，在Map中不存在或者存在次数过多时，都属于不符合要求，当子字符串中的每个单词都在Map中符合要求时，该子字符串本身就是符合的
 * 2、分别将index由0开始到由k-1开始，使用步数为k的滑动窗口，则时间复杂度由O(n*k)优化为n
 * <p>
 * 总体时间复杂度：O(n) k为单词长度
 * 总体空间复杂度：O(n) 由于字串字符串所占的空间，故空间复杂度也是O(n)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-08 01:06:41
 */
public class FindSubStringSolution {
    public static void main(String[] args) {

        String s = "barfoofoobarthefoobarman";
        String[] words = new String[]{"bar", "foo", "the"};
        System.out.println("s = " + s + ", words = " + Arrays.toString(words));
        System.out.println("indexResults = " + findSubString(s, words));

        s = "barfoothefoobarman";
        words = new String[]{"foo", "bar"};
        System.out.println("s = " + s + ", words = " + Arrays.toString(words));
        System.out.println("indexResults = " + findSubString(s, words));

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word", "good", "best", "word"};
        System.out.println("s = " + s + ", words = " + Arrays.toString(words));
        System.out.println("indexResults = " + findSubString(s, words));

        s = "foo";
        words = new String[]{"foo"};
        System.out.println("s = " + s + ", words = " + Arrays.toString(words));
        System.out.println("indexResults = " + findSubString(s, words));

        s = "aaaaaaaaaaaaaa";
        words = new String[]{"aa", "aa"};
        System.out.println("s = " + s + ", words = " + Arrays.toString(words));
        System.out.println("indexResults = " + findSubString(s, words));
    }

    private static List<Integer> findSubString(final String s, final String[] words) {
        final List<Integer> indexResults = new ArrayList<>();
        if (words == null || words.length == 0) {
            return indexResults;
        }

        final int oneWordLength = words[0].length();
        final int subLength = oneWordLength * words.length;
        System.out.println("...oneWordLength = " + oneWordLength + ", subLength = " + subLength);

        final Map<String, Integer> standardMap = new HashMap<>(words.length);
        for (final String w : words) {
            standardMap.put(w, standardMap.getOrDefault(w, 0) + 1);
        }

        final Map<String, Integer> subMap = new HashMap<>(words.length);

        for (int i = 0; i < oneWordLength; i++) {
            if (s.length() - i < subLength) {
                return indexResults;
            }
            singleSlidingWindowFind(s, i, oneWordLength, subLength, standardMap, subMap, indexResults);
            // 每次执行完后，清空subMap，以备复用
            subMap.clear();
        }
        return indexResults;
    }

    private static void singleSlidingWindowFind(final String s, final int startIndex, final int oneWordLength, final int subLength,
                                                final Map<String, Integer> standardMap, final Map<String, Integer> subMap, final List<Integer> indexResults) {
        // substring(left, right) 即为最终的子串
        int left = startIndex;
        int right = startIndex;
        String stepWord;
        String leftWord;
        while (right + oneWordLength <= s.length()) {
            // 滑动窗口，left去除出界元素 (right每次只走一步，left只去除一个元素)
            if (left + subLength == right) {
                leftWord = s.substring(left, left + oneWordLength);
                left += oneWordLength;
                subMap.put(leftWord, subMap.get(leftWord) - 1);
            }

            stepWord = s.substring(right, right + oneWordLength);
            right += oneWordLength;

            // 新的那个词如果不存在，则整个子串需要从下一个词开始
            if (!standardMap.containsKey(stepWord)) {
                left = right;
                subMap.clear();
                continue;
            }

            // 新的那个词如果超过了个数，则left需要去除第一个同样的词
            if (standardMap.get(stepWord).equals(subMap.get(stepWord))) {
                leftWord = s.substring(left, left + oneWordLength);
                while (!leftWord.equals(stepWord)) {
                    subMap.put(leftWord, subMap.get(leftWord) - 1);
                    left += oneWordLength;
                    leftWord = s.substring(left, left + oneWordLength);
                }
                left += oneWordLength;
                continue;
            }

            // 余下的情况，即新的那个词如果个数还不满足，正常设置，此时只要子串长度符合，即符合条件
            subMap.put(stepWord, subMap.getOrDefault(stepWord, 0) + 1);
            if (left + subLength == right) {
                indexResults.add(left);
            }
        }
    }

}
