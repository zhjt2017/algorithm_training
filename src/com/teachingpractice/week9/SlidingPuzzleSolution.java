package com.teachingpractice.week9;

import java.util.Arrays;

/**
 * 算法实现：高级搜索 - 启发式搜索(A*) - 滑动谜题
 * - https://leetcode-cn.com/problems/sliding-puzzle/ (773题)
 * <p>
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
 * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换
 * (只有与空缺进行交换才能被定义为移动，实际上就是空缺旁的一块砖瓦移动到空缺位置，移动后原先砖瓦的位置成为0新的空缺位置)
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * <p>
 * - 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * <p>
 * - 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * <p>
 * - 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * <p>
 * - 输入：board = [[3,2,4],[1,5,0]]
 * 输出：14
 * <p>
 * board 是一个如上所述的 2 x 3 的数组.
 * board[i][j] 是一个 [0, 1, 2, 3, 4, 5] 的排列.
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 05:39:28
 */
public class SlidingPuzzleSolution {
    public static void main(String[] args) {
        final SlidingPuzzleSolution solution = new SlidingPuzzleSolution();

        int[][] board = new int[][]{{1, 2, 3}, {4, 0, 5}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        System.out.println("Output sliding puzzle minimum steps to complete : " + solution.slidingPuzzle(board));
        System.out.println();

        board = new int[][]{{1, 2, 3}, {5, 4, 0}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        System.out.println("Output sliding puzzle minimum steps to complete : " + solution.slidingPuzzle(board));
        System.out.println();

        board = new int[][]{{4, 1, 2}, {5, 0, 3}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        System.out.println("Output sliding puzzle minimum steps to complete : " + solution.slidingPuzzle(board));
        System.out.println();

        board = new int[][]{{3, 2, 4}, {1, 5, 0}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        System.out.println("Output sliding puzzle minimum steps to complete : " + solution.slidingPuzzle(board));
        System.out.println();
    }

    int slidingPuzzle(final int[][] board) {
        return -1;
    }
}
