package com.teachingpractice.week8;

import java.util.HashMap;

/**
 * 算法实现：字典树 - 实现Trie (前缀树)
 * - https://leetcode-cn.com/problems/implement-trie-prefix-tree/ (208题)
 * <p>
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类 (单词查找树)：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-09 10:22:10
 */
public class TrieSolution {
    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        System.out.println("trie insert (apple) success...");
        System.out.println("trie search result (apple) : " + obj.search("apple"));
        System.out.println("trie search result (app) : " + obj.search("app"));
        System.out.println("trie startsWith result (app) : " + obj.startsWith("app"));
        obj.insert("app");
        System.out.println("trie insert (app) success...");
        System.out.println("trie search result (app) : " + obj.search("app"));
    }

    static class Trie {

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(final String word) {
            find(word, true, false);
        }

        public boolean search(final String word) {
            return find(word, false, false);
        }

        public boolean startsWith(final String prefix) {
            return find(prefix, false, true);
        }

        private boolean find(final String s, final boolean isInsert, final boolean isPrefixSearch) {
            final int n = s.length();
            Node node = root;
            int index;
            for (int i = 0; i < n; i++) {
                index = s.charAt(i) - LOWER_A;
                if (node.children[index] == null) {
                    if (isInsert) {
                        node.children[index] = new Node();
                    } else {
                        return false;
                    }
                }
                node = node.children[index];
            }
            if (isInsert) {
                node.count = 1;
            }
            return isPrefixSearch ? true : node.count >= 1;

        }

        static class Node {
            private int count;
            private Node[] children;
            // 本题数据量比较小，在数据量比较大的情况下，我们还是推荐使用HashMap，因为单词没有那么密
            private HashMap<Character, Node> childrenMap;

            private Node() {
                this.children = new Node[LETTER_RANGE];
            }
        }

        private static final int LETTER_RANGE = 26;
        private static final char LOWER_A = 'a';
    }
}
