package com.teachingpractice.week8;

import java.util.Arrays;

/**
 * 算法实现：并查集 - 被围绕的区域 (有点儿像围棋吃子)
 * - https://leetcode-cn.com/problems/surrounded-regions/ (130题)
 * <p>
 * - 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * - 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * - 输入：board = [["X"]]
 * 输出：[["X"]]
 * <p>
 * - 输入: [["X","O","X","X"],["O","X","O","X"],["X","O","X","O"],["O","X","O","X"],["X","O","X","O"],["O","X","O","X"]]
 * 输出: [["X","O","X","X"],["O","X","X","X"],["X","X","X","O"],["O","X","X","X"],["X","X","X","O"],["O","X","O","X"]]
 * <p>
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 * <p>
 * 设计思想：区分边边角角的O与中间被包围的O
 * - 没有被包围的充分必要条件: O直接或间接与边界相邻
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-23 01:10:28
 */
public class SurroundedRegionsSolution {
    public static void main(String[] args) {
        final SurroundedRegionsSolution solution = new SurroundedRegionsSolution();

        char[][] board = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board (dfs) : " + Arrays.deepToString(board));
        board = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solution.solveByDisjointSets(board);
        System.out.println("Output board (disjoint sets) : " + Arrays.deepToString(board));

        board = new char[][]{{'X'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board (dfs) : " + Arrays.deepToString(board));
        board = new char[][]{{'X'}};
        solution.solveByDisjointSets(board);
        System.out.println("Output board (disjoint sets) : " + Arrays.deepToString(board));

        board = new char[][]{{'X', 'O', 'X', 'X'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board (dfs) : " + Arrays.deepToString(board));
        board = new char[][]{{'X', 'O', 'X', 'X'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}, {'X', 'O', 'X', 'O'}, {'O', 'X', 'O', 'X'}};
        solution.solveByDisjointSets(board);
        System.out.println("Output board (disjoint sets) : " + Arrays.deepToString(board));

        board = new char[][]{{'X', 'O', 'X'}, {'X', 'O', 'X'}, {'X', 'O', 'X'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board (dfs) : " + Arrays.deepToString(board));
        board = new char[][]{{'X', 'O', 'X'}, {'X', 'O', 'X'}, {'X', 'O', 'X'}};
        solution.solveByDisjointSets(board);
        System.out.println("Output board (disjoint sets) : " + Arrays.deepToString(board));

        board = new char[][]{{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board (dfs) : " + Arrays.deepToString(board));
        board = new char[][]{{'O', 'O', 'O'}, {'O', 'O', 'O'}, {'O', 'O', 'O'}};
        solution.solveByDisjointSets(board);
        System.out.println("Output board (disjoint sets) : " + Arrays.deepToString(board));

        board = new char[][]{{'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'X', 'O'}, {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X'}, {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X'}, {'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'O'}, {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'}, {'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'X'}, {'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'}};
        System.out.println("Input board : " + Arrays.deepToString(board));
        solution.solve(board);
        System.out.println("Output board (dfs) : " + Arrays.deepToString(board));
        board = new char[][]{{'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'X', 'O'}, {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X'}, {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X'}, {'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X'}, {'O', 'O', 'X', 'X', 'O', 'X', 'X', 'O', 'O', 'O'}, {'X', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'}, {'X', 'O', 'X', 'O', 'O', 'X', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'X'}, {'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O'}, {'X', 'X', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'}};
        solution.solveByDisjointSets(board);
        System.out.println("Output board (disjoint sets) : " + Arrays.deepToString(board));
    }

    /**
     * 并查集实现：合并所有与outside连通的区域到outside上，剩下来的O就是被 'X' 围绕的区域
     * <p>
     * [X, O, X, O, X, O, O, O, X, O],
     * [X, O, O, X, X, X, O, O, O, X],
     * [O, O, O, O, O, O, O, O, X, X],
     * [O, O, O, O, O, O, X, O, O, X],
     * [O, O, X, X, O, X, X, O, O, O],
     * [X, O, O, X, X, X, X, X, X, O],
     * [X, O, X, X, X, X, X, O, X, O],
     * [X, X, O, X, X, X, X, O, O, X],
     * [O, O, O, O, X, X, X, O, X, O],
     * [X, X, O, X, X, X, X, O, O, O]
     * <p>
     * 并查集问题解
     * <p>
     * [X, O, X, O, X, O, O, O, X, O],
     * [X, O, O, X, X, X, O, O, O, X],
     * [O, O, O, O, O, O, O, O, X, X],
     * [O, O, O, O, O, O, X, O, O, X],
     * [O, O, X, X, O, X, X, O, O, O],
     * [X, O, O, X, X, X, X, X, X, O],
     * [X, O, X, X, X, X, X, X, X, O],
     * [X, X, O, X, X, X, X, O, X, X],
     * [O, O, O, O, X, X, X, O, X, O],
     * [X, X, O, X, X, X, X, O, O, O]
     * <p>
     * 主要问题在于一个从下面往上推的O没有连通
     * <p>
     * 于是我发现最好的办法还是从左上方开始扫一遍，然后从右下方再开始反过来扫一遍
     * - 另外，由于此处的并查集的合并，最终只有一个集合，则可以用boolean[i][j] isOutside来替代int[num(i,j)] parent
     * <p>
     * 但是这仍然缺少考虑一种情况：
     * 当前输出：
     * ["X","O","X","O","X","O","O","O","X","O"],
     * ["X","O","O","X","X","X","O","O","O","X"],
     * ["O","O","O","O","O","O","O","O","X","X"],
     * ["O","O","O","O","O","O","X","O","O","X"],
     * ["O","O","X","X","O","X","X","O","O","O"],
     * ["X","O","O","X","X","X","X","X","X","O"],
     * ["X","O","X","X","X","X","X","O","X","O"],
     * ["X","X","O","X","X","X","X","O","X","X"],
     * ["O","O","O","O","X","X","X","O","X","O"],
     * ["X","X","O","X","X","X","X","O","O","O"]
     * 期待输出：
     * ["X","O","X","O","X","O","O","O","X","O"],
     * ["X","O","O","X","X","X","O","O","O","X"],
     * ["O","O","O","O","O","O","O","O","X","X"],
     * ["O","O","O","O","O","O","X","O","O","X"],
     * ["O","O","X","X","O","X","X","O","O","O"],
     * ["X","O","O","X","X","X","X","X","X","O"],
     * ["X","O","X","X","X","X","X","O","X","O"],
     * ["X","X","O","X","X","X","X","O","O","X"],
     * ["O","O","O","O","X","X","X","O","X","O"],
     * ["X","X","O","X","X","X","X","O","O","O"]
     * <p>
     * 即如果是通过下面的outside而连通的，那么还需要重新再向右扫一遍再填充X
     * <p>
     * 然而实现了这个后，还有一个问题
     * 当前输出：
     * ["O","X","X","X","X","X","O","O"],
     * ["O","O","O","X","X","X","X","O"],
     * ["X","X","X","X","O","O","O","O"],
     * ["X","X","X","X","X","X","X","X"],
     * ["O","X","X","X","X","X","O","O"],
     * ["O","X","X","X","X","X","X","O"],
     * ["O","X","X","X","X","X","O","O"],
     * ["O","X","X","X","X","O","X","X"]
     * 期待输出：
     * ["O","X","X","X","X","X","O","O"],
     * ["O","O","O","X","X","X","X","O"],
     * ["X","X","X","X","O","O","O","O"],
     * ["X","X","X","O","O","X","X","X"],
     * ["O","X","X","X","X","X","O","O"],
     * ["O","X","X","X","X","X","X","O"],
     * ["O","X","X","X","X","X","O","O"],
     * ["O","X","X","X","X","O","X","X"]
     * <p>
     * 这也就是说还是要从左上方到右下方再扫一遍，其过程中进行X的覆盖。这就跟并查集中最终在设置X的时候还是需要再过一遍find方法是同样的效果
     * <p>
     * 然后，如果单独从4个方向走一遍，会发现还有蜿蜒很长的搞不定
     * 当前输出：
     * ["X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","O","O","X","X","X","X","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"]
     * 期待输出：
     * ["X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","X","X","X","X","O","O","O","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","O","O","X","O","X","O","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","X","O","X","O","X","O","O","O","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","X","O","O","O","X","X","X","X","X","X","X","X","X","X"],
     * ["X","X","X","X","X","O","X","X","X","X","X","X","X","X","X","X","X","X","X","X"]
     * <p>
     * 这就凸显出了另一个基本问题，就是如果U字型环绕多一圈，我们就得多一个来回的扫描，这是不科学的
     * 所以依然得使用int[] father，这在合并集合时可以不断通过father来向上寻找，即连通块
     * <p>
     * 故仍然需要采用并查集得数据结构，解决连通性一类的问题
     * (比如判断两个点之间是否存在路径，实际上就是求是否连通)
     *
     * @param board
     */
    void solveByDisjointSets(final char[][] board) {
        m = board.length;
        n = board[0].length;
        outside = m * n;

        fa = new int[outside + 1];
        for (int i = 0; i <= outside; i++) {
            fa[i] = i;
        }
        for (int y = 0; y < n; y++) {
            initBorder(board, 0, y);
            initBorder(board, m - 1, y);
        }
        for (int x = 1; x < m; x++) {
            initBorder(board, x, 0);
            initBorder(board, x, n - 1);
        }

        for (int x = 1; x < m - 1; x++) {
            for (int y = 1; y < n - 1; y++) {
                if (board[x][y] == X) {
                    continue;
                }
                // 这里还是必须要上下左右都有
                for (int k = 0; k < 4; k++) {
                    int nx = x + DX[k];
                    int ny = y + DY[k];
                    if (board[nx][ny] == O) {
                        unionSets(num(x, y), num(nx, ny));
                    }
                }
            }
        }

        final int root = find(outside);
        for (int x = 1; x < m - 1; x++) {
            for (int y = 1; y < n - 1; y++) {
                if (board[x][y] == O && find(num(x, y)) != root) {
                    board[x][y] = X;
                }
            }
        }
    }

    private void initBorder(final char[][] board, final int x, final int y) {
        if (board[x][y] == O) {
            fa[num(x, y)] = outside;
        }
    }

    private int outside;
    private int[] fa;

    private void unionSets(final int x, final int y) {
        final int index1 = find(x);
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

    /**
     * dfs记忆化搜索
     * - 时间复杂度 O(m*n)
     * - 空间复杂度 O(m*n)
     *
     * @param board
     */
    private void solve(final char[][] board) {
        this.m = board.length;
        if (m == 0) {
            return;
        }
        this.n = board[0].length;
        this.board = board;

        // 上下边界起始点遍历, 设置未包围标记
        for (int j = 0; j < n; j++) {
            dfs(0, j);
            dfs(m - 1, j);
        }
        // 左右边界起始点遍历, 设置未包围标记
        for (int i = 1; i < m - 1; i++) {
            dfs(i, 0);
            dfs(i, n - 1);
        }
        // 遍历矩阵, 没有未包围标记的表示被包围, 根据题意设置为X (被X收降), 有标记的恢复为O
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == O) {
                    board[i][j] = X;
                    continue;
                }
                if (board[i][j] == A) {
                    board[i][j] = O;
                }
            }
        }
    }

    /**
     * 四周边界的点作为起点, 进行4个方向的不断延伸, 经过的点都使用A标记为没有被包围 (由于连通的O很可能延伸到多个边界点, 故使用记忆, 已经被标记过的, 直接终止即可)
     *
     * @param i
     * @param j
     */
    private void dfs(final int i, final int j) {
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        if (board[i][j] == O) {
            board[i][j] = A;
            for (int k = 0; k < DX.length; k++) {
                dfs(i + DX[k], j + DY[k]);
            }
        }
    }

    private static final char X = 'X';
    private static final char O = 'O';
    private static final char A = 'A';
    /**
     * DX, DY, k=0,1,2,3, 分别对应向下，向右，向上，向左 (当x,y作为board 的索引时)
     */
    private static final int[] DX = new int[]{1, 0, -1, 0};
    private static final int[] DY = new int[]{0, 1, 0, -1};
    private int m;
    private int n;
    private char[][] board;
}
