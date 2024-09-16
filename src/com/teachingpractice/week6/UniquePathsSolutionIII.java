package com.teachingpractice.week6;

import java.util.Arrays;

/**
 * 算法实现：不同路径III (这可以归属于迷宫一类的问题 Maze)
 * - https://leetcode-cn.com/problems/unique-paths-iii/ (980题)
 * <p>
 * - 在二维网格 grid 上，有 4 种类型的方格：
 * - 1 表示起始方格。且只有一个起始方格。
 * - 2 表示结束方格，且只有一个结束方格。
 * - 0 表示我们可以走过的空方格。
 * - -1 表示我们无法跨越的障碍。
 * - 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
 * - 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
 * <p>
 * - 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * 输出：2
 * 解释：我们有以下两条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * <p>
 * - 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * 输出：4
 * 解释：我们有以下四条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * <p>
 * - 输入：[[0,1],[2,0]]
 * 输出：0
 * 解释：
 * 没有一条路能完全穿过每一个空的方格一次。
 * 请注意，起始和结束方格可以位于网格中的任意位置。
 * <p>
 * 1 <= grid.length * grid[0].length <= 20
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-22 10:58:25
 */
public class UniquePathsSolutionIII {
    public static void main(String[] args) {
        final UniquePathsSolutionIII solution = new UniquePathsSolutionIII();

        int[][] grid = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output unique paths (dfs) : " + solution.uniquePathsIII(grid));
        System.out.println();

        grid = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output unique paths (dfs) : " + solution.uniquePathsIII(grid));
        System.out.println();

        grid = new int[][]{{0, 1}, {2, 0}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output unique paths (dfs) : " + solution.uniquePathsIII(grid));
        System.out.println();
    }

    /**
     * dfs记忆化搜索实现
     * <p>
     * - 时间复杂度 O(MN) - O(4^(MN)) (更精确的时间复杂度, 我目前还不会)
     * - 空间复杂度 O(MN)
     *
     * @param grid
     * @return
     */
    int uniquePathsIII(final int[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        ans = 0;
        currentStep = 0;

        // 在到达end之前需要走过的方格数，包括起始点
        totalOver = 1;
        int startX = -1;
        int startY = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == START) {
                    startX = i;
                    startY = j;
                    continue;
                }
                if (grid[i][j] == BLANK) {
                    totalOver++;
                }
            }
        }

        dfs(startX, startY);

        // 起始点还原现场
        grid[startX][startY] = START;

        return ans;
    }

    private static final int START = 1;
    private static final int END = 2;
    private static final int OBSTACLE = -1;
    private static final int BLANK = 0;

    private int[][] grid;
    private int m;
    private int n;
    private int ans;
    private int currentStep;
    private int[] dx = new int[]{1, 0, -1, 0};
    private int[] dy = new int[]{0, -1, 0, 1};
    private int totalOver;

    private void dfs(final int x, final int y) {
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == OBSTACLE) {
            return;
        }

        if (grid[x][y] == END) {
            if (currentStep >= totalOver) {
                ans++;
            }
            return;
        }

        currentStep++;
        grid[x][y] = OBSTACLE;

        for (int k = 0; k < dx.length; k++) {
            dfs(x + dx[k], y + dy[k]);
        }

        // 还原现场
        currentStep--;
        grid[x][y] = BLANK;
    }
}
