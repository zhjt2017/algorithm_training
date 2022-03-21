package com.homework.week9;

import java.util.*;

/**
 * 算法实现：A*算法 - 二进制矩阵中的最短路径
 * - https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/ (1091题)
 * <p>
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 * <p>
 * - 输入：grid = [[0,1],[1,0]]
 * 输出：2
 * <p>
 * - 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 * <p>
 * - 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 * <p>
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 为 0 或 1
 * <p>
 * 出题说明：(普通 BFS？双向 BFS？A*？)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-08 10:30:32
 */
public class ShortestPathInBinaryMatrixSolution {
    public static void main(String[] args) {
        final ShortestPathInBinaryMatrixSolution solution = new ShortestPathInBinaryMatrixSolution();

        int[][] grid = new int[][]{{0, 1}, {1, 0}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output shortest path in binary matrix : " + solution.shortestPathBinaryMatrix(grid));
        System.out.println("Output shortest path in binary matrix (A*) : " + solution.shortestPathBinaryMatrixAsterisk(grid));
        System.out.println();

        grid = new int[][]{{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output shortest path in binary matrix : " + solution.shortestPathBinaryMatrix(grid));
        System.out.println("Output shortest path in binary matrix (A*) : " + solution.shortestPathBinaryMatrixAsterisk(grid));
        System.out.println();

        grid = new int[][]{{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output shortest path in binary matrix : " + solution.shortestPathBinaryMatrix(grid));
        System.out.println("Output shortest path in binary matrix (A*) : " + solution.shortestPathBinaryMatrixAsterisk(grid));
        System.out.println();

        grid = new int[][]{{0}};
        System.out.println("Input grid : " + Arrays.deepToString(grid));
        System.out.println("Output shortest path in binary matrix : " + solution.shortestPathBinaryMatrix(grid));
        System.out.println("Output shortest path in binary matrix (A*) : " + solution.shortestPathBinaryMatrixAsterisk(grid));
        System.out.println();
    }

    /**
     * 我的基本思想方法：bfs寻找最短路径(可以维护全局boolean[][] visited)
     * - 双向BFS，扩散地更慢一点，效率更高一点
     * - from : [0][0]
     * - target : [n-1][n-1]
     * - 时间复杂度：O(n)
     * - 空间复杂度：O(n)
     *
     * @param grid
     * @return
     */
    int shortestPathBinaryMatrix(final int[][] grid) {
        n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }

        final boolean[][] visited = new boolean[n][n];
        final boolean[][] targetVisited = new boolean[n][n];
        final Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        final Queue<int[]> targetQ = new LinkedList<>();
        targetQ.offer(new int[]{n - 1, n - 1});
        targetVisited[n - 1][n - 1] = true;

        int side = 0;
        while (!q.isEmpty()) {
            final List<int[]> rowData = new ArrayList<>(q.size());
            while (!q.isEmpty()) {
                rowData.add(q.poll());
            }
            side++;
            for (final int[] value : rowData) {
                for (int k = 0; k < DX.length; k++) {
                    int nx = value[0] + DX[k];
                    int ny = value[1] + DY[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || grid[nx][ny] != 0) {
                        continue;
                    }
                    if (targetVisited[nx][ny]) {
                        return side + 1;
                    }
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }

            final List<int[]> targetRowData = new ArrayList<>(targetQ.size());
            while (!targetQ.isEmpty()) {
                targetRowData.add(targetQ.poll());
            }
            side++;
            for (final int[] value : targetRowData) {
                for (int k = 0; k < DX.length; k++) {
                    int nx = value[0] + DX[k];
                    int ny = value[1] + DY[k];
                    if (nx < 0 || ny < 0 || nx >= n || ny >= n || targetVisited[nx][ny] || grid[nx][ny] != 0) {
                        continue;
                    }
                    if (visited[nx][ny]) {
                        return side + 1;
                    }
                    targetVisited[nx][ny] = true;
                    targetQ.offer(new int[]{nx, ny});
                }
            }
        }
        return -1;
    }

    private int n;
    private static final int[] DX = {0, 0, -1, 1, -1, 1, -1, 1};
    private static final int[] DY = {-1, 1, 0, 0, -1, -1, 1, 1};


    /**
     * A* - 切比雪夫距离（Chebyshev Distance）
     *
     * @param grid
     * @return
     */
    int shortestPathBinaryMatrixAsterisk(final int[][] grid) {
        n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }

        final PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(v -> v.f));
        grid[0][0] = 1;
        pq.offer(new Node(0, 0, 1));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int x = curr.x;
            int y = curr.y;
            if (x == n - 1 && y == n - 1) {
                return grid[x][y];
            }

            for (int j = 0; j < DX.length; j++) {
                int newX = x + DX[j];
                int newY = y + DY[j];

                if (newX < 0 || newX > n - 1 || newY < 0 || newY > n - 1) {
                    continue;
                }
                // 注意判断 grid[newX][newY] > grid[x][y] + 1
                if (grid[newX][newY] == 0 || grid[newX][newY] > grid[x][y] + 1) {
                    grid[newX][newY] = grid[x][y] + 1;
                    pq.offer(new Node(newX, newY, grid[newX][newY]));
                }
            }
        }
        return -1;
    }

    class Node {
        private int x;
        private int y;
        private int f;

        Node(final int x, final int y, final int step) {
            this.x = x;
            this.y = y;
            this.f = step + Math.max(n - x - 1, n - y - 1);
        }
    }
}
