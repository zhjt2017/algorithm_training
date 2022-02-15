package com.exam;

import java.util.Arrays;

/**
 * 期中考试：迷宫问题 进一步的问题
 * - 我自己提出的问题：如果能够在目的地停下，求最短路径长度，否则返回-1
 * - 修改的条件：停下才能选择下一个方向 -> 随时可以调整下一个方向
 * <p>
 * 由空地（用 0 表示）和墙（用 1 表示）组成的迷宫 maze 中有一个球。球可以途经空地向 上、下、左、右 四个方向滚动，
 * 且在遇到墙壁前不会停止滚动。当球停下时，可以选择向下一个方向滚动。
 * 给你一个大小为 m x n 的迷宫 maze ，以及球的初始位置 start 和目的地 destination ，
 * 其中 start = [startrow, startcol] 且 destination = [destinationrow, destinationcol] 。
 * <p>
 * 你可以 假定迷宫的边缘都是墙壁（参考示例）。
 * <p>
 * - 输入：maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * 输出：8
 * 解释：一种可能的路径是 : 左 -> 下 -> 左 -> 下 -> 右 -> 下 -> 右。
 * <p>
 * - 输入：maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * 输出：-1
 * 解释：不存在能够使球停在目的地的路径。注意，球可以经过目的地，但无法在那里停驻。
 * <p>
 * - 输入：maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * 输出：-1
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
public class MazeFurtherSolution {
    public static void main(String[] args) {
        final MazeFurtherSolution solution = new MazeFurtherSolution();

        int[][] maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        int[] start = new int[]{0, 4};
        int[] destination = new int[]{4, 4};
        System.out.println(String.format("Input maze : %s, start : %s, destination : %s",
                Arrays.deepToString(maze), Arrays.toString(start), Arrays.toString(destination)));
        System.out.println("Output min path : " + solution.minPath(maze, start, destination));

        maze = new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
        start = new int[]{0, 4};
        destination = new int[]{3, 2};
        System.out.println(String.format("\nInput maze : %s, start : %s, destination : %s",
                Arrays.deepToString(maze), Arrays.toString(start), Arrays.toString(destination)));
        System.out.println("Output min path : " + solution.minPath(maze, start, destination));

        maze = new int[][]{{0, 0, 0, 0, 0}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 0, 0}};
        start = new int[]{4, 3};
        destination = new int[]{0, 1};
        System.out.println(String.format("\nInput maze : %s, start : %s, destination : %s",
                Arrays.deepToString(maze), Arrays.toString(start), Arrays.toString(destination)));
        System.out.println("Output min path : " + solution.minPath(maze, start, destination));
    }

    /**
     * 我的初始题解
     *
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    int minPath(final int[][] maze, final int[] start, final int[] destination) {
        return -1;
    }
}
