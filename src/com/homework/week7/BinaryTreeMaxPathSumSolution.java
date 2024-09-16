package com.homework.week7;

/**
 * 算法实现：树形动态规划 - 二叉树中的最大路径和 (并考虑具体方案的打印)
 * - https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/ (124题)
 *
 * - 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * - 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * - 路径和 是路径中各节点值的总和。
 * - 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * - 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * - 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-20 02:05:55
 */
public class BinaryTreeMaxPathSumSolution {
    public static void main(String[] args) {
        final BinaryTreeMaxPathSumSolution solution = new BinaryTreeMaxPathSumSolution();

        TreeNode root = new TreeNode(1);
        System.out.println("Input binary tree : " + root);
        System.out.println("Output max path sum : " + solution.maxPathSum(root));
    }
    int maxPathSum(final TreeNode root) {
        return 0;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode(final int val) {
            this.val = val;
        }
    }
}
