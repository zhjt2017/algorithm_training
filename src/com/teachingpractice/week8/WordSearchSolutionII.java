package com.teachingpractice.week8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 算法实现：字典树 - 单词搜索II
 * - https://leetcode-cn.com/problems/word-search-ii/ (212题)
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * - 输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]],
 * words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 * <p>
 * - 输入：board = [["a","b"],["c","d"]], words = ["abcb"]
 * 输出：[]
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-09 10:48:49
 */
public class WordSearchSolutionII {
    public static void main(String[] args) {
        final WordSearchSolutionII solution = new WordSearchSolutionII();

        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        System.out.println("Input board : " + Arrays.deepToString(board) + ", words : " + Arrays.toString(words));
        System.out.println("Output find words result : " + solution.findWords(board, words));
        System.out.println();

        board = new char[][]{{'a', 'b'}, {'c', 'd'}};
        words = new String[]{"abcb"};
        System.out.println("Input board : " + Arrays.deepToString(board) + ", words : " + Arrays.toString(words));
        System.out.println("Output find words result : " + solution.findWords(board, words));
        System.out.println();
    }

    /**
     * 采用Trie来搜索单词 (DFS记忆化搜索)
     * - 额外(剪枝-时间复杂度)优化点：一旦找到了一个符合的单词，就从Trie中删除，不再搜索重复的单词 (这个优化就非常有效果)
     * - 额外(空间复杂度)优化点：使用将board单元格设置为特殊的其他符号来替代boolean[][] visited (当然这个优化可以忽略)
     * - 由于Java的String是不可变类，并且StringBuilder在还原现场时也需要copyArray不好处理，故直接在Trie中就使用String初始化所有单词
     * (注意：HashMap默认capacity为1<<4, 以<<1方式进行自动扩容)
     * <p>
     * - 时间复杂度：O(m*n*3^l) (l为单词长度)
     * - 空间复杂度：O(k*l) (k为words数量，l为单词长度)
     *
     * @param board
     * @param words
     * @return
     */
    List<String> findWords(final char[][] board, final String[] words) {
        ans = new ArrayList<>(words.length);
        this.board = board;
        m = board.length;
        n = board[0].length;
        final Node root = new Node();
        root.children = new HashMap<>();
        for (final String w : words) {
            insert(w, root);
        }

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                dfs(x, y, root);
            }
        }
        return ans;
    }

    private void dfs(final int x, final int y, final Node node) {
        if (x < 0 || y < 0 || x >= m || y >= n) {
            return;
        }
        char c = board[x][y];
        if (c == WELL_NUMBER) {
            return;
        }
        if (!node.children.containsKey(c)) {
            return;
        }

        Node next = node.children.get(c);
        if (next.word != null) {
            ans.add(next.word);
            next.word = null;
            if (next.children.isEmpty()) {
                node.children.remove(c);
            }
        }

        if (next.children.isEmpty()) {
            return;
        }

        board[x][y] = WELL_NUMBER;
        for (int k = 0; k < dx.length; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            dfs(nx, ny, next);
        }
        board[x][y] = c;
    }


    private void insert(final String s, final Node root) {
        final int n = s.length();
        Node node = root;
        char c;
        for (int i = 0; i < n; i++) {
            c = s.charAt(i);
            if (!node.children.containsKey(c)) {
                node.children.put(c, new Node());
            }
            node = node.children.get(c);
        }
        node.word = s;
    }

    private List<String> ans;
    private char[][] board;
    private int m;
    private int n;
    private int[] dx = new int[]{1, 0, -1, 0};
    private int[] dy = new int[]{0, -1, 0, 1};

    private static final char WELL_NUMBER = '#';

    static class Node {
        /**
         * 用来记录单词，并同时可以以null值来标识不是一个单词
         */
        private String word;
        private HashMap<Character, Node> children;

        Node() {
            this.children = new HashMap<>();
        }
    }
}
