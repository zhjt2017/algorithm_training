package com.teachingpractice.week4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 算法实现：搜索-岛屿数量 (连通块系列问题-图中可以用BFS或者DFS划分连通块)
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
 * 题意关键认知：每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成
 * <p>
 * 设计思想：
 * 本题，我们分别使用bfs与dfs来实现
 * <p>
 * - 辅助常量：方向数组dx, dy
 * - 状态数据：
 * --- 遍历出发点坐标
 * --- 针对每个出发点，进行搜索算法：当前坐标、每个坐标是否选中的状态(我们可以使用'2'来标识已选中的岛屿)
 * - 遍历矩阵每个点，未选中的陆地(即为新的一块岛屿)，满足搜索条件，该点作为起始点向前后左右4个方向进行搜索，搜索的目的是对这块新的岛屿所在的陆地进行标记
 * <p>
 * - BFS实现
 * - 时间复杂度：剪枝之后 O(m*n)
 * - 空间复杂度：O(min(m,n))
 * - DFS实现
 * - 时间复杂度：剪枝之后 O(m*n)
 * - 空间复杂度：最坏O(m*n)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-20 02:13:34
 */
public class NumberOfIslandsSolution {
    public static void main(String[] args) {
        NumberOfIslandsSolution solution = new NumberOfIslandsSolution();

        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Number of islands with BFS : " + solution.numIslandsWithBFS(grid));
        solution.resetGrid();
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Number of islands with DFS: " + solution.numIslandsWithDFS(grid));

        grid = new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Number of islands with BFS: " + solution.numIslandsWithBFS(grid));
        solution.resetGrid();
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Number of islands with DFS: " + solution.numIslandsWithDFS(grid));
    }

    private int numIslandsWithBFS(final char[][] grid) {
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
                bfs(i, j);
                sum++;
            }
        }

        return sum;
    }

    private int numIslandsWithDFS(final char[][] grid) {
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

    /**
     * 广搜：第一步 (取队头)，第二步 (扩展队头)
     * 遍历搜索的目的：对这个新的岛屿所在的陆地进行标记
     */
    private void bfs(final int sx, final int sy) {
        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy});
        grid[sx][sy] = USED;
        int x, y, nx, ny;
        while (!queue.isEmpty()) {
            // step1: peek the head
            x = queue.peek()[0];
            y = queue.peek()[1];
            queue.poll();
            // step2: extend
            for (int i = 0; i < DX.length; i++) {
                nx = x + DX[i];
                ny = y + DY[i];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                // 四个方向上不断辐射可能会重合，故需要判重
                if (grid[nx][ny] == WATER || grid[nx][ny] == USED) {
                    continue;
                }
                queue.offer(new int[]{nx, ny});
                grid[nx][ny] = USED;
            }
        }
    }

    /**
     * 深搜，注意base case与还原现场
     * 遍历搜索的目的：对这个新的岛屿所在的陆地进行标记 (除标记外，由于没有更改数据，无须还原现场)
     *
     * @param x
     * @param y
     */
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
    private static final char WATER = '0';
    private static final char USED = '2';
    private int m;
    private int n;
    private char[][] grid;

    private void resetGrid() {
        for (final char[] column : grid) {
            for (int j = 0; j < n; j++) {
                if (column[j] == USED) {
                    column[j] = '1';
                }
            }
        }
    }
}
