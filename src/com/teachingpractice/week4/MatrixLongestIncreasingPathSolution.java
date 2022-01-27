package com.teachingpractice.week4;

import java.util.*;

/**
 * 算法实现: DFS+BFS 矩阵中的最长递增路径
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
 * - 输入：matrix = [[0,1,2,3,4,5,6,7,8,9],
 * [19,18,17,16,15,14,13,12,11,10],
 * [20,21,22,23,24,25,26,27,28,29],
 * [39,38,37,36,35,34,33,32,31,30],
 * [40,41,42,43,44,45,46,47,48,49],
 * [59,58,57,56,55,54,53,52,51,50],
 * [60,61,62,63,64,65,66,67,68,69],
 * [79,78,77,76,75,74,73,72,71,70],
 * [80,81,82,83,84,85,86,87,88,89],
 * [99,98,97,96,95,94,93,92,91,90],
 * [100,101,102,103,104,105,106,107,108,109],
 * [119,118,117,116,115,114,113,112,111,110],
 * [120,121,122,123,124,125,126,127,128,129],
 * [139,138,137,136,135,134,133,132,131,130],
 * [0,0,0,0,0,0,0,0,0,0]]
 * 输出：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 2^31 - 1
 * <p>
 * 设计思想: 状态空间为有向无环图, 可以使用DFS记忆化搜索, 也可以使用BFS拓扑排序。我们都实现一下。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-25 03:05:41
 */
public class MatrixLongestIncreasingPathSolution {
    public static void main(String[] args) {
        final MatrixLongestIncreasingPathSolution solution = new MatrixLongestIncreasingPathSolution();
        int[][] matrix = new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        System.out.println("Input matrix : " + Arrays.deepToString(matrix));
        System.out.println("Output longest increasing path (dfs) : " + solution.longestIncreasingPathDFS(matrix));
        System.out.println("Output longest increasing path (bfs) : " + solution.longestIncreasingPathBFS(matrix));

        matrix = new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
        System.out.println("Input matrix : " + Arrays.deepToString(matrix));
        System.out.println("Output longest increasing path (dfs) : " + solution.longestIncreasingPathDFS(matrix));
        System.out.println("Output longest increasing path (bfs) : " + solution.longestIncreasingPathBFS(matrix));

        matrix = new int[][]{{1}};
        System.out.println("Input matrix : " + Arrays.deepToString(matrix));
        System.out.println("Output longest increasing path (dfs) : " + solution.longestIncreasingPathDFS(matrix));
        System.out.println("Output longest increasing path (bfs) : " + solution.longestIncreasingPathBFS(matrix));

        matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
                {39, 38, 37, 36, 35, 34, 33, 32, 31, 30},
                {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
                {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
                {60, 61, 62, 63, 64, 65, 66, 67, 68, 69},
                {79, 78, 77, 76, 75, 74, 73, 72, 71, 70},
                {80, 81, 82, 83, 84, 85, 86, 87, 88, 89},
                {99, 98, 97, 96, 95, 94, 93, 92, 91, 90},
                {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
                {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
                {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
                {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println("Input matrix : " + Arrays.deepToString(matrix));
        System.out.println("Output longest increasing path (dfs) : " + solution.longestIncreasingPathDFS(matrix));
        System.out.println("Output longest increasing path (bfs) : " + solution.longestIncreasingPathBFS(matrix));
        System.out.println("Output longest increasing path (bfs) : " + solution.longestIncreasingPathBFSAnother(matrix));
    }

    /**
     * BFS拓扑排序
     * - BFS与DFS遍历的方向是相反的，后者为回溯
     * - 在本题的场景中，DFS每层计算出的是从该层开始能够走的最远的距离。故在每一层可以使用记忆化进行剪枝 (每层计算的depth都可以回溯给上一层)
     * - BFS每层计算出的是，从起始点(第一层)到该层的距离。作为一层，因为起始点不固定，则没有准确的值可以进行记忆化剪枝。只有遍历完所有的起始点，这就太多重复计算了。
     * - 先使用一次m*n全遍历，找到所有入度为0(或者出度为0)的点，作为有效起始点，然后BFS全遍历一遍，得到其最大depth。
     * --- (每层计算的depth只能存储在该层每个到达点上，我们也可以不存这个值，那么当完全计算完一层后，depth+1即可，这样更简单)
     * - 总体时间复杂度也是O(m*n)，但由于无法对其中的一部分进行记忆化复用，故略微差于DFS
     * - 空间复杂度O(m*n) (除bfs的队列外，我们在确认有效起始点(入度为0)的时候，也存储每个点的到达点列表，则对于整个一层，我们将所有的到达点列表全部遍历一遍后，就可以正式进入到下一层，直到最后一层)
     *
     * @param matrix
     * @return
     */
    private int longestIncreasingPathBFS(final int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        final List<int[]>[][] arrivals = new List[m][n];
        final int[][] inDegree = new int[m][n];

        int ans = 0;
        final Queue<int[]> q = new LinkedList<>();

        List<int[]> arrival;
        int ni, nj;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arrival = new ArrayList<>(DX.length);
                for (int k = 0; k < DX.length; k++) {
                    ni = i + DX[k];
                    nj = j + DY[k];
                    if (isInvalid(ni, nj) || matrix[ni][nj] == matrix[i][j]) {
                        continue;
                    }
                    // 要么[ni,nj]是[i,j]的到达点，要么[ni,nj]是[i,j]的入度
                    if (matrix[ni][nj] < matrix[i][j]) {
                        inDegree[i][j]++;
                    } else {
                        arrival.add(new int[]{ni, nj});
                    }
                }
                arrivals[i][j] = arrival;
            }
        }

        // 这一步是省不掉的，无论是通过入度为0的有效出发点开始寻找所有的到达点直到无法到达，还是通过出度为0的有效终点寻找所有的出发点直到有效原始出发点
        // (入度与其到达点集合)(出度与其出发点集合)这2个组合都是逆向的，故省不掉，要么就像LeetCode官方那样(longestIncreasingPathBFSAnother)，没有出发点或者到达点集合，在bfs的过程中重新走出来也是可以的
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (inDegree[i][j] == 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        int layerSize;
        int[] point;
        while (!q.isEmpty()) {
            ans++;
            layerSize = q.size();
            for (int i = 0; i < layerSize; i++) {
                point = q.poll();
                arrival = arrivals[point[0]][point[1]];
                for (final int[] arrPoint : arrival) {
                    // 只有当前层中所有以arrPoint为到达点的情况都遍历完成后才能添加到队列，不然就会重复
                    if ((--inDegree[arrPoint[0]][arrPoint[1]]) == 0) {
                        q.offer(new int[]{arrPoint[0], arrPoint[1]});
                    }
                }
            }
        }

        return ans;
    }

    /**
     * DFS记忆化搜索
     * - 遍历每个点，从该点出发，看最远能走到第几个点，其自身是第一个点
     * - 根据m, n的范围，使用short[][]记忆每个点最远能走到第几个点
     * - 当一个点[i, j]将每个方向都遍历完成后，得到自己能走多远，此时设置short[i][j]
     * - 时间复杂度 O(m*n)
     * - 空间复杂度 O(m*n)
     *
     * @param matrix
     * @return
     */
    private int longestIncreasingPathDFS(final int[][] matrix) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.matrix = matrix;
        this.depth = new short[m][n];

        short result = 0;
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                result = max(result, dfs(x, y));
            }
        }
        return result;
    }

    private short dfs(final int x, final int y) {
        if (depth[x][y] > 0) {
            return depth[x][y];
        }

        int nx, ny;
        short steps = 1;
        for (int k = 0; k < DX.length; k++) {
            nx = x + DX[k];
            ny = y + DY[k];
            if (isInvalid(nx, ny) || matrix[nx][ny] <= matrix[x][y]) {
                continue;
            }
            steps = max(steps, (short) (dfs(nx, ny) + 1));
        }
        depth[x][y] = steps;
        return steps;
    }

    private static final int[] DX = new int[]{1, 0, -1, 0};
    private static final int[] DY = new int[]{0, -1, 0, 1};

    private int m;
    private int n;
    private int[][] matrix;
    private short[][] depth;

    private boolean isInvalid(final int x, final int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    private short max(final short thisOne, final short anotherOne) {
        return thisOne >= anotherOne ? thisOne : anotherOne;
    }

    /*****************************************************************LeetCode官方BFS拓扑排序解法*********************************************************************/

    private int longestIncreasingPathBFSAnother(final int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;

        int[][] outdegrees = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        int ni, nj;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int k = 0; k < DX.length; k++) {
                    ni = i + DX[k];
                    nj = j + DY[k];
                    if (isInvalid(ni, nj) || matrix[ni][nj] <= matrix[i][j]) {
                        continue;
                    }
                    // [ni,nj]是[i,j]的到达点，[ni,nj]即是[i,j]的出度
                    ++outdegrees[i][j];
                }
                if (outdegrees[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            ++ans;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                for (int k = 0; k < DX.length; k++) {
                    ni = row + DX[k];
                    nj = column + DY[k];
                    // 对于每个点，找有效的出发点[ni,nj]，然后不断去找出发点 (这里要确保queue里面不被添加重复的点，那么只有该大层遍历完所以可能从该点出发的情况后才将该点添加到下一层)
                    if (!isInvalid(ni, nj) && matrix[ni][nj] < matrix[row][column]) {
                        --outdegrees[ni][nj];
                        if (outdegrees[ni][nj] == 0) {
                            queue.offer(new int[]{ni, nj});
                        }
                    }
                }
            }
        }
        return ans;
    }
}
