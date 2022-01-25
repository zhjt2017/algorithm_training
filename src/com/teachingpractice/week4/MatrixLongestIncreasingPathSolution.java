package com.teachingpractice.week4;

import java.util.Arrays;

/**
 * 算法实现: 矩阵中的最长递增路径
 * - https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/ (329题)
 * <p>
 * - 给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
 * <p>
 * - 输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * 输出：4
 * 解释：最长递增路径为 [1, 2, 6, 9]。
 * <p>
 * - 输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * 输出：4
 * 解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * <p>
 * - 输入：matrix = [[1]]
 * 输出：1
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 * <p>
 * 设计思想:
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-25 03:05:41
 */
public class MatrixLongestIncreasingPathSolution {
    public static void main(String[] args) {
        final MatrixLongestIncreasingPathSolution solution = new MatrixLongestIncreasingPathSolution();
        int[][] matrix = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println("Input matrix : " + Arrays.deepToString(matrix));
        System.out.println("Output longest increasing path : " + solution.longestIncreasingPath(matrix));

        matrix = new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println("Input matrix : " + Arrays.deepToString(matrix));
        System.out.println("Output longest increasing path : " + solution.longestIncreasingPath(matrix));

        matrix = new int[][]{{1}};
        System.out.println("Input matrix : " + Arrays.deepToString(matrix));
        System.out.println("Output longest increasing path : " + solution.longestIncreasingPath(matrix));
    }

    private int longestIncreasingPath(final int[][] matrix) {
        return 0;
    }
}
