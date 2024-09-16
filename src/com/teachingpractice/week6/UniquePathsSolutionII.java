package com.teachingpractice.week6;

import java.util.Arrays;

/**
 * 算法实现：动态规划 - 不同路径 II
 * - https://leetcode-cn.com/problems/unique-paths-ii/ (63题)
 * <p>
 * - 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * - 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * - 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * - 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * <p>
 * - 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * <p>
 * - 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 * <p>
 * - m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-21 10:43:20
 */
public class UniquePathsSolutionII {
    public static void main(String[] args) {
        final UniquePathsSolutionII solution = new UniquePathsSolutionII();

        int[][] obstacleGrid = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println("Input obstacleGrid = " + Arrays.deepToString(obstacleGrid));
        System.out.println("Output unique paths with obstacles : " + solution.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Output unique paths with obstacles (memory search) : " + solution.uniquePathsWithObstaclesDfs(obstacleGrid));
        System.out.println();

        obstacleGrid = new int[][]{{0, 1}, {0, 0}};
        System.out.println("Input obstacleGrid = " + Arrays.deepToString(obstacleGrid));
        System.out.println("Output unique paths with obstacles : " + solution.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Output unique paths with obstacles (memory search) : " + solution.uniquePathsWithObstaclesDfs(obstacleGrid));
        System.out.println();

        obstacleGrid = new int[][]{{0, 0}, {0, 1}};
        System.out.println("Input obstacleGrid = " + Arrays.deepToString(obstacleGrid));
        System.out.println("Output unique paths with obstacles : " + solution.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Output unique paths with obstacles (memory search) : " + solution.uniquePathsWithObstaclesDfs(obstacleGrid));
        System.out.println();

        obstacleGrid = new int[][]{{1}};
        System.out.println("Input obstacleGrid = " + Arrays.deepToString(obstacleGrid));
        System.out.println("Output unique paths with obstacles : " + solution.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Output unique paths with obstacles (memory search) : " + solution.uniquePathsWithObstaclesDfs(obstacleGrid));
        System.out.println();

        obstacleGrid = new int[][]{{0, 0}};
        System.out.println("Input obstacleGrid = " + Arrays.deepToString(obstacleGrid));
        System.out.println("Output unique paths with obstacles : " + solution.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Output unique paths with obstacles (memory search) : " + solution.uniquePathsWithObstaclesDfs(obstacleGrid));
        System.out.println();

        obstacleGrid = new int[][]{{0}, {0}};
        System.out.println("Input obstacleGrid = " + Arrays.deepToString(obstacleGrid));
        System.out.println("Output unique paths with obstacles : " + solution.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Output unique paths with obstacles (memory search) : " + solution.uniquePathsWithObstaclesDfs(obstacleGrid));

        obstacleGrid = new int[][]{{1}, {0}};
        System.out.println("Input obstacleGrid = " + Arrays.deepToString(obstacleGrid));
        System.out.println("Output unique paths with obstacles : " + solution.uniquePathsWithObstacles(obstacleGrid));
        System.out.println("Output unique paths with obstacles (memory search) : " + solution.uniquePathsWithObstaclesDfs(obstacleGrid));
    }

    /**
     * (bottom - up) (递归、分治思想 - 记忆化搜索)
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(MN)
     *
     * @param obstacleGrid
     * @return
     */
    int uniquePathsWithObstaclesDfs(final int[][] obstacleGrid) {
        this.m = obstacleGrid.length;
        this.n = obstacleGrid[0].length;
        this.obstacleGrid = obstacleGrid;
        this.paths = new int[m][n];
        for (final int[] arr : this.paths) {
            Arrays.fill(arr, -1);
        }
        return dfs(0, 0);
    }

    private int m;
    private int n;
    private int[][] paths;
    private int[][] obstacleGrid;

    private int dfs(final int i, final int j) {
        if (i == m - 1) {
            if (j == n - 1) {
                paths[i][j] = obstacleGrid[i][j] == 1 ? 0 : 1;
                return paths[i][j];
            }
            if (paths[i][j] == -1) {
                paths[i][j] = obstacleGrid[i][j] == 1 ? 0 : dfs(i, j + 1);
            }
            return paths[i][j];
        }
        if (j == n - 1) {
            if (paths[i][j] == -1) {
                paths[i][j] = obstacleGrid[i][j] == 1 ? 0 : dfs(i + 1, j);
            }
            return paths[i][j];
        }

        if (paths[i][j] > -1) {
            return paths[i][j];
        }
        paths[i][j] = obstacleGrid[i][j] == 1 ? 0 : dfs(i + 1, j) + dfs(i, j + 1);
        return paths[i][j];
    }

    /**
     * (bottom - up) (递归、分治思想 - 记忆化搜索)
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(MN)
     *
     * @param obstacleGrid
     * @return
     */
    int uniquePathsWithObstacles(final int[][] obstacleGrid) {
        final int m = obstacleGrid.length;
        final int n = obstacleGrid[0].length;
        int[] dp = new int[m];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < m; i++) {
            dp[i] = obstacleGrid[i][0] == 1 ? 0 : dp[i - 1];
        }
        for (int j = 1; j < n; j++) {
            dp[0] = obstacleGrid[0][j] == 1 ? 0 : dp[0];
            for (int i = 1; i < m; i++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i] = 0;
                    continue;
                }
                dp[i] = dp[i] + dp[i - 1];
            }
        }
        return dp[m - 1];
    }
}
