package com.speed.week8;

/**
 * 算法实现：实现Trie (单词查找树) (前缀树)
 * - https://leetcode-cn.com/problems/implement-trie-prefix-tree/ (208题)
 * <p>
 * - Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * - 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * - 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * <p>
 * - 输入
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
 * @since 2022-02-22 03:51:03
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

    /**
     * 使用字典树实现
     * 时间复杂度：初始化为 O(1)，其余操作为 O(|S|)，其中 |S| 是每次插入或查询的字符串的长度。
     * 空间复杂度：O(∣T∣Σ)，其中 ∣T∣ 为所有插入字符串的长度之和，Σ 为字符集的大小，本题Σ=26。
     */
    static class Trie {
        Trie() {

        }

        public void insert(final String word) {

        }

        public boolean search(final String word) {
            return false;
        }

        public boolean startsWith(final String prefix) {
            return false;
        }
    }
}
