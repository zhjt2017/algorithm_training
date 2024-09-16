package com.homework.week4;

import java.util.Arrays;

/**
 * 算法实现：被围绕的区域 (有点儿像围棋吃子)
 * - https://leetcode-cn.com/problems/surrounded-regions/ (130题)
 * <p>
 * - 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * - 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * - 输入：board = [["X"]]
 * 输出：[["X"]]
 * <p>
 * - 输入: [["X","O","X","X"],["O","X","O","X"],["X","O","X","O"],["O","X","O","X"],["X","O","X","O"],["O","X","O","X"]]
 * 输出: [["X","O","X","X"],["O","X","X","X"],["X","X","X","O"],["O","X","X","X"],["X","X","X","O"],["O","X","O","X"]]
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 * <p>
 * 设计思想：区分边边角角的O与中间被包围的O
 * - 没有被包围的充分必要条件: O直接或间接与边界相邻
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-23 01:10:28
 */
public class SurroundedRegionsSolution {
    public static void main(String[] args) {
        final SurroundedRegionsSolution solution = new SurroundedRegionsSolution();

        char[][] board = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board : " + Arrays.deepToString(board));

        board = new char[][]{{'X'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board : " + Arrays.deepToString(board));

        board = new char[][]{{'X', 'O', 'X', 'X'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board : " + Arrays.deepToString(board));
    }

    private void solve(final char[][] board) {
        this.m = board.length;
        if (m == 0) {
            return;
        }
        this.n = board[0].length;
        this.board = board;

        // 上下边界起始点遍历, 设置未包围标记
        for (int j = 0; j < n; j++) {
            dfs(0, j);
            dfs(m - 1, j);
        }
        // 左右边界起始点遍历, 设置未包围标记
        for (int i = 1; i < m - 1; i++) {
            dfs(i, 0);
            dfs(i, n - 1);
        }
        // 遍历矩阵, 没有未包围标记的表示被包围, 根据题意设置为X (被X收降), 有标记的恢复为O
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == O) {
                    board[i][j] = X;
                    continue;
                }
                if (board[i][j] == A) {
                    board[i][j] = O;
                }
            }
        }
    }

    /**
     * 四周边界的点作为起点, 进行4个方向的不断延伸, 经过的点都使用A标记为没有被包围 (由于连通的O很可能延伸到多个边界点, 故使用记忆, 已经被标记过的, 直接终止即可)
     *
     * @param i
     * @param j
     */
    private void dfs(final int i, final int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] == O) {
            board[i][j] = A;
            for (int k = 0; k < DX.length; k++) {
                dfs(i + DX[k], j + DY[k]);
            }
        }
    }

    private static final char X = 'X';
    private static final char O = 'O';
    private static final char A = 'A';
    private static final int[] DX = new int[]{1, 0, -1, 0};
    private static final int[] DY = new int[]{0, -1, 0, 1};
    private int m;
    private int n;
    private char[][] board;
}
