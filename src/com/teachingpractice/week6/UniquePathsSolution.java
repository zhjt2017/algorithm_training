package com.teachingpractice.week6;

/**
 * 算法实现：动态规划 - 不同路径
 * - https://leetcode-cn.com/problems/unique-paths/ (62题)
 * <p>
 * - 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * - 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * - 问总共有多少条不同的路径？
 * <p>
 * - 输入：m = 3, n = 7
 * 输出：28
 * <p>
 * - 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 * <p>
 * - 输入：m = 7, n = 3
 * 输出：28
 * <p>
 * - 输入：m = 3, n = 3
 * 输出：6
 * <p>
 * <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-21 10:43:20
 */
public class UniquePathsSolution {
    public static void main(String[] args) {
        final UniquePathsSolution solution = new UniquePathsSolution();

        int m = 3;
        int n = 7;
        System.out.println("Input m = " + m + ", n = " + n);
        System.out.println("Output unique paths : " + solution.uniquePaths(m, n));
        System.out.println("Output unique paths (memory search) : " + solution.uniquePathsByDp(m, n));
        System.out.println();

        m = 3;
        n = 2;
        System.out.println("Input m = " + m + ", n = " + n);
        System.out.println("Output unique paths : " + solution.uniquePaths(m, n));
        System.out.println("Output unique paths (memory search) : " + solution.uniquePathsByDp(m, n));
        System.out.println();

        m = 7;
        n = 3;
        System.out.println("Input m = " + m + ", n = " + n);
        System.out.println("Output unique paths : " + solution.uniquePaths(m, n));
        System.out.println("Output unique paths (memory search) : " + solution.uniquePathsByDp(m, n));
        System.out.println();

        m = 51;
        n = 9;
        System.out.println("Input m = " + m + ", n = " + n);
        System.out.println("Output unique paths : " + solution.uniquePaths(m, n));
        System.out.println("Output unique paths (memory search) : " + solution.uniquePathsByDp(m, n));
        System.out.println("Output unique paths (dp) : " + solution.uniquePathsByDpTopDown(m, n));
        System.out.println();

        m = 3;
        n = 3;
        System.out.println("Input m = " + m + ", n = " + n);
        System.out.println("Output unique paths : " + solution.uniquePaths(m, n));
        System.out.println("Output unique paths (memory search) : " + solution.uniquePathsByDp(m, n));
        System.out.println("Output unique paths (dp) : " + solution.uniquePathsByDpTopDown(m, n));
    }

    /**
     * 组合数学：总共m+n-2个路径中选取m-1个横向路径的组合数为多少
     * - 根据题意最终的组合数可以到达2 * 10^9，那么我们将小的分子放在前面计算，可以保证ans递增，于是每步计算ans都不会超出100*2*10^9, 即保证不会超过long, 但已经超过了int
     * - (另外，每一步的k与i都是连续的数，于是每步的ans都刚好是整数)
     * <p>
     * - 时间复杂度 O(min(m, n))
     * - 空间复杂度 O(1)
     *
     * @param m
     * @param n
     * @return
     */
    int uniquePaths(final int m, final int n) {
        long ans = 1;
        int longSide = Math.max(m - 1, n - 1);
        int shortSide = Math.min(m - 1, n - 1);
        for (int k = longSide + 1, i = 1; i <= shortSide; k++, i++) {
            ans = ans * k / i;
        }
        return (int) ans;
    }

    /**
     * 动态规划求解 (bottom - up) (递归、分治思想 - 记忆化搜索)
     * - 目标 (最优子结构) f(x, y)表示从点(x, y)到Finish的走法数
     * - 边界 f(m - 1, y) = 1, f(x, n - 1) = 1
     * - 状态转移方程 f(x, y) = f(x + 1, y) + f(x, y + 1) (越界坐标的f值为0)
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(MN)
     *
     * @param m
     * @param n
     * @return
     */
    int uniquePathsByDp(final int m, final int n) {
        this.m = m;
        this.n = n;
        this.paths = new int[m - 1][n - 1];
        return dfs(0, 0);
    }

    private int m;
    private int n;
    private int[][] paths;

    private int dfs(final int i, final int j) {
        if (i >= m || j >= n) {
            return 0;
        }
        if (i == m - 1 || j == n - 1) {
            return 1;
        }
        if (paths[i][j] > 0) {
            return paths[i][j];
        }
        paths[i][j] = dfs(i + 1, j) + dfs(i, j + 1);
        return paths[i][j];
    }

    /**
     * 动态规划求解 (top - down)
     * - 目标 (最优子结构) f(x, y)表示从Start到点(x, y)的走法数
     * - 边界 f(0, 1) = 1, f(1, 0) = 1
     * - 状态转移方程 f(x, y) = f(x - 1, y) + f(x, y - 1) (越界坐标的f值为0)
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(min(M,N))
     *
     * @param m
     * @param n
     * @return
     */
    int uniquePathsByDpTopDown(final int m, final int n) {
        if (m > n) {
            return uniquePathsByDpTopDown(n, m);
        }
        // 不妨设m<=n
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = 1;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 1; i < m; i++) {
                dp[i] = dp[i] + dp[i - 1];
            }
        }
        return dp[m - 1];
    }
}
