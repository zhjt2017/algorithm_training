package com.teachingpractice.week3;

/**
 * 算法实现：树-翻转二叉树
 * <p>
 * - 输入：
 * <p>
 * 4
 * /   \
 * 2     7
 * / \   / \
 * 1   3 6   9
 * <p>
 * - 输出：
 * <p>
 * 4
 * /   \
 * 7     2
 * / \   / \
 * 9   6 3   1
 * <p>
 * 设计思想：树包括所有子树，根不变，左右节点互换
 * - 递归实现：子树节点互换，向上归并。base case: root=null
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(logN) - O(n) (最坏情况下树为一条链)
 *
 * <p>
 * - 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-17 09:29:51
 */
public class InvertBinaryTreeSolution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        invertTree(root);
    }

    private static TreeNode invertTree(final TreeNode root) {
        System.out.println("Input binary tree : " + root);

        InvertBinaryTreeSolution solution = new InvertBinaryTreeSolution();
        solution.recur(root);

        System.out.println("Inverted binary tree : " + root);

        return root;
    }

    private void recur(final TreeNode root) {
        if (root == null) {
            return;
        }

        this.recur(root.left);
        this.recur(root.right);

        this.tmp = root.left;
        root.left = root.right;
        root.right = tmp;
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

    private TreeNode tmp;
}
