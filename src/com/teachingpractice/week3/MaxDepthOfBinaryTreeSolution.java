package com.teachingpractice.week3;

/**
 * 算法实现：二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。(叶子节点是指没有子节点的节点)
 * <p>
 * - 输入：[3,9,20,null,null,15,7] (层序遍历全节点序列，可构成一种序列化方案)
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * - 输出：3
 * <p>
 * 设计思想：
 * 1、1+2+4+...+2^n+1=2^(n+1) 根据层序遍历全节点序列的长度O(log(N))就可以判断
 * 2、如果是通过root节点来判定，必须访问到所有的叶子节点，才能找到哪个叶子节点最深，O(N)
 * - 递归实现：当任何分支到达叶子节点时，就维护全局变量maxLayer，初始layer=0，每层自己逻辑将layer+1表示当前层所在深度，直到base case节点为null时，没有该层，触发维护maxLayer，该分支结束
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-18 02:33:12
 */
public class MaxDepthOfBinaryTreeSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("Input binary tree : " + root);
        System.out.println("maxDepth result : " + maxDepth(root));
    }

    private static int maxDepth(final TreeNode root) {
        final MaxDepthOfBinaryTreeSolution solution = new MaxDepthOfBinaryTreeSolution();
        solution.layer = 0;
        solution.maxLayer = 0;
        solution.recur(root);
        return solution.maxLayer;
    }

    private void recur(final TreeNode root) {
        if (root == null) {
            this.maxLayer = Math.max(this.maxLayer, this.layer);
            return;
        }

        this.layer++;
        this.recur(root.left);
        this.recur(root.right);
        this.layer--;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode() {
        }

        TreeNode(final int val) {
            this.val = val;
        }

        TreeNode(final int val, final TreeNode left, final TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("{\"val\":%d, \"left\":%s, \"right\":%s}", val, left, right);
        }
    }

    private int maxLayer;
    private int layer;
}
