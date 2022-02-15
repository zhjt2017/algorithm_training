package com.exam;

import java.util.Arrays;

/**
 * 期中考试：迷宫问题
 * https://leetcode-cn.com/problems/the-maze/ (490题) (Plus)
 * <p>
 * 由空地（用 0 表示）和墙（用 1 表示）组成的迷宫 maze 中有一个球。球可以途经空地向 上、下、左、右 四个方向滚动，
 * 且在遇到墙壁前不会停止滚动。当球停下时，可以选择向下一个方向滚动。
 * 给你一个大小为 m x n 的迷宫 maze ，以及球的初始位置 start 和目的地 destination ，
 * 其中 start = [startrow, startcol] 且 destination = [destinationrow, destinationcol] 。
 * 请你判断球能否在目的地停下：如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 你可以 假定迷宫的边缘都是墙壁（参考示例）。
 * <p>
 * - 输入：maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * 输出：true
 * 解释：一种可能的路径是 : 左 -> 下 -> 左 -> 下 -> 右 -> 下 -> 右。
 * <p>
 * - 输入：maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * 输出：false
 * 解释：不存在能够使球停在目的地的路径。注意，球可以经过目的地，但无法在那里停驻。
 * <p>
 * - 输入：maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * 输出：false
 * <p>
 * - m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= startrow, destinationrow <= m
 * 0 <= startcol, destinationcol <= n
 * 球和目的地都在空地上，且初始时它们不在同一位置
 * 迷宫 至少包括 2 块空地
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-14 06:18:37
 */
public class MazeSolution {
    public static void main(String[] args) {
        final MazeSolution solution = new MazeSolution();

        int[][] maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        System.out.println(String.format("Input maze : %s, start : %s, destination : %s",
                Arrays.deepToString(maze), Arrays.toString(start), Arrays.toString(destination)));
        System.out.println("Output has path : " + solution.hasPath(maze, start, destination));
        System.out.println("Output has path optimization : " + solution.hasPathOptimization(maze, start, destination));

        maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        start = new int[]{0, 4};
        destination = new int[]{3, 2};
        System.out.println(String.format("\nInput maze : %s, start : %s, destination : %s",
                Arrays.deepToString(maze), Arrays.toString(start), Arrays.toString(destination)));
        System.out.println("Output has path : " + solution.hasPath(maze, start, destination));
        System.out.println("Output has path optimization : " + solution.hasPathOptimization(maze, start, destination));

        maze = new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}};
        start = new int[]{4, 3};
        destination = new int[]{0, 1};
        System.out.println(String.format("\nInput maze : %s, start : %s, destination : %s",
                Arrays.deepToString(maze), Arrays.toString(start), Arrays.toString(destination)));
        System.out.println("Output has path : " + solution.hasPath(maze, start, destination));
        System.out.println("Output has path optimization : " + solution.hasPathOptimization(maze, start, destination));
    }

    /**
     * 我的初始题解
     * - leetcode执行 13ms 42M
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    boolean hasPath(final int[][] maze, final int[] start, final int[] destination) {
        this.destination = destination;
        this.maze = maze;
        m = maze.length;
        n = maze[0].length;
        access = new boolean[m][n][DIRECTION_SIZE];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWall(i, j)) {
                    continue;
                }
                for (int k = 0; k < DIRECTION_SIZE; k++) {
                    if (!isWall(i + dx[k], j + dy[k])) {
                        access[i][j][k] = true;
                    }
                }
            }
        }

        boolean[] startDirection = access[start[0]][start[1]];
        for (int k = 0; k < DIRECTION_SIZE; k++) {
            if (startDirection[k]) {
                startDirection[k] = false;
                if (dfs(start[0] + dx[k], start[1] + dy[k], k)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 我意：
     * 1、dfs遍历，不走同样的路 (即以每个不是墙的位置为出发点时，每个方向只能走一次)
     * 2、dfs终止条件:
     * - 刚好在destination停下：该点是destination，并且前面是墙，答案为true
     * - 无路可走：任意点作为出发点，每个方向都已经走完，答案为false
     * - 以任意点为出发点，只要一个方向返回true，就返回true
     * <p>
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(M+N)
     * <p>
     *
     * @param x
     * @param y
     */
    private boolean dfs(final int x, final int y, final int direction) {
        if (x == destination[0] && y == destination[1] && isWall(x + dx[direction], y + dy[direction])) {
            return true;
        }
        if (access[x][y][direction]) {
            access[x][y][direction] = false;
            return dfs(x + dx[direction], y + dy[direction], direction);
        }
        for (int k = 0; k < DIRECTION_SIZE; k++) {
            if (access[x][y][k]) {
                access[x][y][k] = false;
                if (dfs(x + dx[k], y + dy[k], k)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final int DIRECTION_SIZE = 4;
    private int[] dx = new int[]{1, 0, -1, 0};
    private int[] dy = new int[]{0, -1, 0, 1};
    private int[] destination;
    private int[][] maze;
    private int m;
    private int n;
    private boolean[][][] access;
    private int nx;
    private int ny;

    private boolean isWall(int x, int y) {
        return x < 0 || y < 0 || x >= m || y >= n || maze[x][y] == 1;
    }

    /**
     * 优化：将同一个方向走的一整段路作为一个原子操作(迭代完成，不作递归)，那么每一步都是能够停下来的，于是就转化为求能走到destination
     * - 这样再对下一步进行判断的时候，只要判断有没有走到过，而不需要所有方向都判断一次，也少开了一个boolean[][][]
     * - 此时的递归，还有一点可以优化，就是不走回头路，可以省掉约N/2
     * - 结束的条件 (到达destination，返回true，到达曾经到过的点(利用maze[i][j]=-1来判断)，返回false)
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(MN) (仅在于递归)
     * <p>
     * - leetcode执行 1ms 42M
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    boolean hasPathOptimization(final int[][] maze, final int[] start, final int[] destination) {
        this.destination = destination;
        this.maze = maze;
        m = maze.length;
        n = maze[0].length;
        for (int k = 0; k < DIRECTION_SIZE; k++) {
            nx = start[0] + dx[k];
            ny = start[1] + dy[k];
            if (isWall(nx, ny)) {
                continue;
            }
            while (!isWall(nx + dx[k], ny + dy[k])) {
                nx += dx[k];
                ny += dy[k];
            }
            if (nx == destination[0] && ny == destination[1]) {
                return true;
            }
            if (optimizedDfs(nx, ny, k)) {
                return true;
            }
        }
        return false;
    }

    private boolean optimizedDfs(final int x, final int y, final int direction) {
        for (int k = 0; k < DIRECTION_SIZE; k++) {
            nx = x + dx[k];
            ny = y + dy[k];
            if (isWall(nx, ny)) {
                continue;
            }
            if (dx[k] + dx[direction] == 0 && dy[k] + dy[direction] == 0) {
                continue;
            }
            while (!isWall(nx + dx[k], ny + dy[k])) {
                nx += dx[k];
                ny += dy[k];
            }
            if (nx == destination[0] && ny == destination[1]) {
                return true;
            }
            if (maze[nx][ny] == -1) {
                continue;
            }
            maze[nx][ny] = -1;
            if (optimizedDfs(nx, ny, k)) {
                return true;
            }
        }
        return false;
    }
}
