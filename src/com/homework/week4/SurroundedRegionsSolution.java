package com.homework.week4;

/**
 * 算法实现：被围绕的区域
 * - https://leetcode-cn.com/problems/surrounded-regions/ (130题)
 *
 * - 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * - 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * - 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * 设计思想：区分边边角角的O与中间被包围的O
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-23 01:10:28
 */
public class SurroundedRegionsSolution {
    public static void main(String[] args) {

    }

    private static void solve(char[][] board) {

    }
}
