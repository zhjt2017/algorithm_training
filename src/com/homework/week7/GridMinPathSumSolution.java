package com.homework.week7;

import java.util.Arrays;

/**
 * 算法实现：(动态规划之)滚动数组与打印路径问题 - 最小路径和
 * - https://leetcode-cn.com/problems/minimum-path-sum/ (64题)
 * <p>
 * - 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * - 说明：每次只能向下或者向右移动一步。
 * - 要求求得最小路径和，并打印该路径
 * <p>
 * - 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * - 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-20 02:27:47
 */
public class GridMinPathSumSolution {
    public static void main(String[] args) {
        final GridMinPathSumSolution solution = new GridMinPathSumSolution();

        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output min path sum (from top left to bottom right) : " + solution.minPathSum(grid));
        System.out.println();

        grid = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output min path sum (from top left to bottom right) : " + solution.minPathSum(grid));
    }

    int minPathSum(final int[][] grid) {
        return 0;
    }
}
