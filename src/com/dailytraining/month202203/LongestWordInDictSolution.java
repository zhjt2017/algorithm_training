package com.dailytraining.month202203;

import java.util.*;

/**
 * 算法训练(2022-03-17) 词典中最长的单词
 * - https://leetcode-cn.com/problems/longest-word-in-dictionary/ (720题)
 * <p>
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 * <p>
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * <p>
 * - 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * <p>
 * - 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串 words[i] 都只包含小写字母。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 08:41:18
 */
public class LongestWordInDictSolution {
    public static void main(String[] args) {
        final LongestWordInDictSolution solution = new LongestWordInDictSolution();

        String[] words = new String[]{"w", "wo", "wor", "worl", "world"};
        System.out.println("Input words : " + Arrays.toString(words));
        System.out.println("Output longest word : " + solution.longestWord(words));
//        words = new String[]{"w", "wo", "wor", "worl", "world"};
//        System.out.println("Output longest word (ordered) : " + solution.longestWordOrdered(words));
//        System.out.println("Output longest word (trie) : " + solution.longestWordTrie(words));
//        System.out.println();
//
//        words = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
//        System.out.println("Input words : " + Arrays.toString(words));
//        System.out.println("Output longest word : " + solution.longestWord(words));
//        words = new String[]{"a", "banana", "app", "appl", "ap", "apply", "apple"};
//        System.out.println("Output longest word (ordered) : " + solution.longestWordOrdered(words));
//        System.out.println("Output longest word (trie) : " + solution.longestWordTrie(words));
//        System.out.println();
//
//        words = new String[]{"m", "mo", "moc", "moch", "mocha", "l", "la", "lat", "latt", "latte", "c", "ca", "cat"};
//        System.out.println("Input words : " + Arrays.toString(words));
//        System.out.println("Output longest word : " + solution.longestWord(words));
//        words = new String[]{"m", "mo", "moc", "moch", "mocha", "l", "la", "lat", "latt", "latte", "c", "ca", "cat"};
//        System.out.println("Output longest word (ordered) : " + solution.longestWordOrdered(words));
//        System.out.println("Output longest word (trie) : " + solution.longestWordTrie(words));
//        System.out.println();

        words = new String[]{"ogz", "eyj", "e", "ey", "hmn", "v", "hm", "ogznkb", "ogzn", "hmnm", "eyjuo", "vuq", "ogznk", "og", "eyjuoi", "d"};
        System.out.println("Input words : " + Arrays.toString(words));
        System.out.println("Output longest word : " + solution.longestWord(words));
        words = new String[]{"ogz", "eyj", "e", "ey", "hmn", "v", "hm", "ogznkb", "ogzn", "hmnm", "eyjuo", "vuq", "ogznk", "og", "eyjuoi", "d"};
        System.out.println("Output longest word (ordered) : " + solution.longestWordOrdered(words));
        System.out.println("Output longest word (trie) : " + solution.longestWordTrie(words));
        System.out.println();
    }

    /**
     * 解法1：Set (排序，消耗的性能过于多，所以相对于longestWordOrdered，还是推荐使用本方法)
     *
     * @param words
     * @return
     */
    String longestWord(final String[] words) {
        final Set<String> dict = new HashSet<>();
        for (final String s : words) {
            dict.add(s);
        }
        String ans = "";
        for (final String s : words) {
            if (s.length() < ans.length()) {
                continue;
            }
            if (s.length() == ans.length() && s.compareTo(ans) >= 0) {
                continue;
            }
            if (contains(s, dict)) {
                ans = s;
            }
        }
        return ans;
    }

    private boolean contains(final String s, final Set<String> dict) {
        // i倒序迭代，能屏蔽一些重复的项，效果更好一点
        for (int i = s.length() - 1; i > 0; i--) {
            if (!dict.contains(s.substring(0, i))) {
//                // 小优化：如果有中断的部分，后续拼接出来的单词(包括s本身)全部作废，减少二次查询次数 (但这样的优化有限，甚至remove操作还会占据过多的性能)
//                for (int j = i; j <= s.length(); j++) {
//                    dict.remove(s.substring(0, j));
//                }
                return false;
            }
        }
        return true;
    }

    /**
     * 针对解法1的另一种思路：先排序，这样在迭代判定时只要保证比自己少最后一位的字符串存在(一边判定一边添加，既优化了空间，又可以保证不会中断，则可以只判断一位)，
     * 就是答案(并且，如果长度相等，那么只取前面的)
     *
     * @param words
     * @return
     */
    String longestWordOrdered(final String[] words) {
        Arrays.sort(words, (a, b) -> (a.length() == b.length() ? a.compareTo(b) : a.length() - b.length()));
        final Set<String> dict = new HashSet<>();
        String ans = "";
        dict.add(ans);
        for (final String s : words) {
            if (!dict.contains(s.substring(0, s.length() - 1))) {
                continue;
            }
            dict.add(s);
            if (s.length() > ans.length()) {
                ans = s;
            }
        }
        return ans;
    }

    String longestWordOrderedAnother(final String[] words) {
        // 当这里的排序，在长度相等时，让字母顺序大的在前面，小的在后面，则后面再更新ans时可以减少一次s.length() > ans.length()的判断 (从本质上来说，这里的优化有限，但是写法更值得推荐)
        Arrays.sort(words, (a, b) -> (a.length() == b.length() ? b.compareTo(a) : a.length() - b.length()));
        final Set<String> dict = new HashSet<>();
        String ans = "";
        dict.add(ans);
        for (final String s : words) {
            if (dict.contains(s.substring(0, s.length() - 1))) {
                dict.add(s);
                ans = s;
            }
        }
        return ans;
    }

    /**
     * 解法2：字典树
     *
     * @param words
     * @return
     */
    String longestWordTrie(final String[] words) {
        Trie trie = new Trie();
        trie.s = "";
        for (final String s : words) {
            trie.insert(s);
        }
        ans = "";
        dfs(trie);
        return ans;
    }

    private String ans;

    /**
     * dfs
     * - 可能会成为ans的数据：1、所有被不中断遍历到的叶子结点 2、所有被中断前的最长单词
     *
     * @param node
     */
    private void dfs(final Trie node) {
        if (node.s == null) {
            return;
        }
        if (node.s.length() > ans.length() || (node.s.length() == ans.length() && node.s.compareTo(ans) < 0)) {
            ans = node.s;
        }
        for (Trie t : node.children.values()) {
            dfs(t);
        }
    }

    static class Trie {
        /**
         * 如果该前缀刚好是一个单词，就引用该单词的值，如果只是前缀，就为null
         */
        private String s;
        private Map<Character, Trie> children;

        Trie() {
            this.children = new HashMap<>();
        }

        void insert(final String word) {
            final int n = word.length();
            Trie node = this;
            Character c;
            for (int i = 0; i < n; i++) {
                c = word.charAt(i);
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new Trie());
                }
                node = node.children.get(c);
                if (i == n - 1) {
                    node.s = word;
                }
            }
        }
    }
}
