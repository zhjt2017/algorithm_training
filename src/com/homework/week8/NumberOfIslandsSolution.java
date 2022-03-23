package com.homework.week8;

import java.util.Arrays;

/**
 * 算法实现：并查集 - 岛屿数量
 * - https://leetcode-cn.com/problems/number-of-islands/ (200题)
 *
 * <p>
 * - 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * - 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * - 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * - 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * <p>
 * - 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1''
 * <p>
 * <p>
 * - (之前的做法：连通块系列问题-图中可以用BFS或者DFS划分连通块 - numIslandsDfs 方法)
 * - 这里，我们使用并查集来解决连通性问题 - numIslands 方法
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 08:12:32
 */
public class NumberOfIslandsSolution {
    public static void main(String[] args) {
        NumberOfIslandsSolution solution = new NumberOfIslandsSolution();

        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Number of islands with BFS : " + solution.numIslands(grid));

        grid = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Number of islands with BFS: " + solution.numIslands(grid));
    }

    /**
     * Disjoint Sets (比直接DFS的记忆化搜索必然要慢一点，但是大O一致)
     * 时间复杂度 期待O(nm)
     * 空间复杂度 O(nm)
     *
     * @param grid
     * @return
     */
    int numIslands(final char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        upperLimit = m * n;
        makeSets();

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == WATER) {
                    continue;
                }
                int index1 = find(num(x, y));
                for (int k = 0; k < DX.length; k++) {
                    int nx = x + DX[k];
                    int ny = y + DY[k];
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == WATER) {
                        continue;
                    }
                    unionSets(index1, num(nx, ny));
                }
            }
        }

        int ans = 0;
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == LAND && find(num(x, y)) == num(x, y)) {
                    ans++;
                }
            }
        }
        return ans;
    }


    private int[] fa;
    private int upperLimit;

    private void makeSets() {
        fa = new int[upperLimit];
        for (int i = 0; i < upperLimit; i++) {
            fa[i] = i;
        }
    }

    private void unionSets(final int index1, final int y) {
        final int index2 = find(y);
        if (index1 != index2) {
            // fa[index2] = index1 (2ms) ,  fa[index1] = index2 (2500ms)
            fa[index2] = index1;
        }
    }

    private int find(final int x) {
        if (fa[x] == x) {
            return x;
        }
        return find(fa[x]);
    }

    private int num(final int x, final int y) {
        return x * n + y;
    }

    int numIslandsDfs(final char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int sum = 0;
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == WATER || grid[i][j] == USED) {
                    continue;
                }
                dfs(i, j);
                sum++;
            }
        }

        return sum;
    }

    private void dfs(final int x, final int y) {
        int nx, ny;
        for (int i = 0; i < DX.length; i++) {
            nx = x + DX[i];
            ny = y + DY[i];
            // base case 一个方向上到了边界或者已经重合，则该分支深度见底
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                continue;
            }
            if (grid[nx][ny] == WATER || grid[nx][ny] == USED) {
                continue;
            }
            grid[nx][ny] = USED;
            dfs(nx, ny);
        }
    }

    private static final int[] DX = new int[]{0, 1, 0, -1};
    private static final int[] DY = new int[]{1, 0, -1, 0};
    private static final char LAND = '1';
    private static final char WATER = '0';
    private static final char USED = '2';
    private int m;
    private int n;
    private char[][] grid;

}
