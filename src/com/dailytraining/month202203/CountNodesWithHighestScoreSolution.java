package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法实现：统计最高分的节点数目
 * - https://leetcode-cn.com/problems/count-nodes-with-the-highest-score/ (2049题)
 * <p>
 * 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。
 * 同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。
 * 由于节点 0 是根，所以 parents[0] == -1 。
 * <p>
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。
 * 求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * 请你返回有 最高得分 节点的 数目 。
 * <p>
 * - 输入：parents = [-1,2,0,2,0]
 * 输出：3
 * 解释：
 * - 节点 0 的分数为：3 * 1 = 3
 * - 节点 1 的分数为：4 = 4
 * - 节点 2 的分数为：1 * 1 * 2 = 2
 * - 节点 3 的分数为：4 = 4
 * - 节点 4 的分数为：4 = 4
 * 最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
 * <p>
 * - 输入：parents = [-1,2,0]
 * 输出：2
 * 解释：
 * - 节点 0 的分数为：2 = 2
 * - 节点 1 的分数为：2 = 2
 * - 节点 2 的分数为：1 * 1 = 1
 * 最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
 * <p>
 * n == parents.length
 * 2 <= n <= 10^5
 * parents[0] == -1
 * 对于 i != 0 ，有 0 <= parents[i] <= n - 1
 * parents 表示一棵二叉树。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-11 02:43:56
 */
public class CountNodesWithHighestScoreSolution {
    public static void main(String[] args) {
        final CountNodesWithHighestScoreSolution solution = new CountNodesWithHighestScoreSolution();

        int[] parents = new int[]{-1, 2, 0, 2, 0};
        System.out.println("Input parents : " + Arrays.toString(parents));
        System.out.println("Output count nodes with highest score : " + solution.countHighestScoreNodes(parents));
        System.out.println();

        parents = new int[]{-1, 2, 0};
        System.out.println("Input parents : " + Arrays.toString(parents));
        System.out.println("Output count nodes with highest score : " + solution.countHighestScoreNodes(parents));
        System.out.println();
    }

    /**
     * DFS记忆化搜索
     * - 一个节点的得分为除自己外3个(自己的大小固定为1，不影响得分，故3个)连通块的大小的乘积(其中2个为左子树、右子树，还有一个由除该节点作为根节点所在子树外的所有节点)
     * - 记忆(左右子树互用) (说明，由于记忆最多被使用一次，并且对于左右子树只存在一个的情况没有任何好处只有额外负担，故本题以不进行记忆为佳)
     * <p>
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(n)
     *
     * @param parents
     * @return
     */
    int countHighestScoreNodes(final int[] parents) {
        if (parents.length == 1) {
            return 1;
        }

        count = 0;
        maxScore = 1;
        n = parents.length;
        left = new int[n];
        right = new int[n];
        nodeSize = new int[n];
        Arrays.fill(left, NOT_EXISTS);
        Arrays.fill(right, NOT_EXISTS);
        Arrays.fill(nodeSize, NOT_EXISTS);
        for (int i = 1; i < n; i++) {
            // 不妨优先填充左节点，再填充右节点
            if (left[parents[i]] == NOT_EXISTS) {
                left[parents[i]] = i;
            } else {
                right[parents[i]] = i;
            }
        }
        dfs(0);
        return count;
    }

    /**
     * 返回以node作为根节点的子树大小 (并在内部计算完该子树各节点上的分数)
     *
     * @param node 作为根节点(当前递归处理节点)的节点编号
     * @return
     */
    private int dfs(final int node) {
        int leftSize = dfsLeftSize(node);
        int rightSize = dfsRightSize(node);
        final long score = Math.max(leftSize, 1) * Math.max(rightSize, 1) * (long) Math.max(n - leftSize - rightSize - 1, 1);
        System.out.println(String.format("node=%d, leftSize=%d, rightSize=%d, score=%d", node, leftSize, rightSize, score));
        if (score > maxScore) {
            maxScore = score;
            count = 1;
        } else if (score == maxScore) {
            count++;
        }
        return leftSize + rightSize + 1;
    }

    private int dfsLeftSize(final int node) {
        if (left[node] == NOT_EXISTS) {
            return 0;
        }
        if (nodeSize[left[node]] == NOT_EXISTS) {
            nodeSize[left[node]] = dfs(left[node]);
        }
        return nodeSize[left[node]];
    }

    private int dfsRightSize(final int node) {
        if (right[node] == NOT_EXISTS) {
            return 0;
        }
        if (nodeSize[right[node]] == NOT_EXISTS) {
            nodeSize[right[node]] = dfs(right[node]);
        }
        return nodeSize[right[node]];
    }

    private static final int NOT_EXISTS = -1;

    private int n;
    /**
     * 下标为父节点编号，标识左节点编号 (left[i]值为-1标识下标所在父节点没有左节点)
     */
    private int[] left;
    /**
     * 下标为父节点编号，标识右节点编号 (right[i]值为-1标识下标所在父节点没有左节点)
     */
    private int[] right;
    /**
     * 最高分数 (根据题目数据范围2 <= n <= 10^5，最高分数最大可能达到10^14，故使用long)
     */
    private long maxScore;
    /**
     * 最高分数对应的数量
     */
    private int count;
    /**
     * 记忆：以下标作为节点编号的根节点子树大小
     */
    private int[] nodeSize;
}
