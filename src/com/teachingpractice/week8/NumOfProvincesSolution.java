package com.teachingpractice.week8;

import java.util.Arrays;

/**
 * 算法实现：并查集 - 省份数量
 * - https://leetcode-cn.com/problems/number-of-provinces/ (547题)
 * <p>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。
 * 如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * <p>
 * - 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * <p>
 * - 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-12 10:44:17
 */
public class NumOfProvincesSolution {
    public static void main(String[] args) {
        final NumOfProvincesSolution solution = new NumOfProvincesSolution();

        int[][] isConnected = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println("Input isConnected : " + Arrays.deepToString(isConnected));
        System.out.println("Output number of provinces (circle number) (dfs) : " + solution.findCircleNumDfs(isConnected));
        System.out.println("Output number of provinces (circle number) (Disjoint Sets) : " + solution.findCircleNum(isConnected));
        System.out.println();

        isConnected = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println("Input isConnected : " + Arrays.deepToString(isConnected));
        System.out.println("Output number of provinces (circle number) (dfs) : " + solution.findCircleNumDfs(isConnected));
        System.out.println("Output number of provinces (circle number) (Disjoint Sets) : " + solution.findCircleNum(isConnected));
        System.out.println();
    }

    /**
     * 这里采用并查集：将连通块看作是一个集合
     * - 时间复杂度 O(n²log(n))
     * - 空间复杂度 O(n)
     * - 并查集3要素
     * --- Make Sets : root[n]
     * --- unionSets(x, y) :
     * --- find(x) : 使用路径压缩
     *
     * @param isConnected
     * @return
     */
    int findCircleNum(final int[][] isConnected) {
        n = isConnected.length;
        makeSets();
        for (int x = 0; x < n; x++) {
            // 注意：此处的迭代，可以保证<=x的所有y都已经访问过，故可以直接从x+1开始，则省去半个矩阵的访问
            for (int y = x + 1; y < n; y++) {
                if (isConnected[x][y] == 1) {
                    unionSets(x, y);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (father[i] == i) {
                ans++;
            }
        }
        return ans;
    }

    private int[] father;

    private void makeSets() {
        father = new int[n];
        for (int i = 0; i < n; i++) {
            father[i] = i;
        }
    }

    private void unionSets(final int x, final int y) {
        if (father[x] != father[y]) {
            father[find(x)] = find(y);
        }
    }

    private int find(final int x) {
        if (x != father[x]) {
            father[x] = find(father[x]);
        }
        return father[x];
    }

    /**
     * 省份也就是连通块 (DFS记忆化搜索可以实现)
     * - 时间复杂度 O(n²)
     * - 空间复杂度 O(n)
     *
     * @param isConnected
     * @return
     */
    int findCircleNumDfs(final int[][] isConnected) {
        provinces = 0;
        this.isConnected = isConnected;
        n = isConnected.length;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfs(i);
            provinces++;
        }
        return provinces;
    }

    /**
     * dfs的目的是将该条深度路径上的城市都设置为visited
     *
     * @param index
     */
    void dfs(final int index) {
        // 注意，由于是dfs，不能保证i<index的所有j都被访问过了，故每次j都要从0开始，执行记忆化搜索即可
        for (int j = 0; j < n; j++) {
            if (isConnected[index][j] == 0 || visited[j]) {
                continue;
            }
            visited[j] = true;
            dfs(j);
        }
    }

    private int[][] isConnected;
    private int n;
    private boolean[] visited;
    private int provinces;
}
