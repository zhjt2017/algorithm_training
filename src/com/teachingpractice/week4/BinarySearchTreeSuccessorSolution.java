package com.teachingpractice.week4;

/**
 * 算法实现: BST - 后继者
 * - https://leetcode-cn.com/problems/successor-lcci/
 * <p>
 * - 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * - 如果指定节点没有对应的“下一个”节点，则返回null
 * <p>
 * - 输入: root = [2,1,3], p = 1
 * 输出: 2
 * <p>
 * - 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * 输出: null
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-02 08:35:58
 */
public class BinarySearchTreeSuccessorSolution {
    public static void main(String[] args) {
        final BinarySearchTreeSuccessorSolution solution = new BinarySearchTreeSuccessorSolution();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode p = new TreeNode(1);
        System.out.println("Input BST : " + root + ", p : " + p);
        System.out.println("Output successor of p in BST : " + solution.inorderSuccessor(root, p));

        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        p = new TreeNode(6);
        System.out.println("Input BST : " + root + ", p : " + p);
        System.out.println("Output successor of p in BST : " + solution.inorderSuccessor(root, p));
    }

    private TreeNode inorderSuccessor(final TreeNode root, final TreeNode p) {
        return root;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(final int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("\"val\":%d, \"left\":%s, \"right\":%s", this.val, this.left, this.right);
        }
    }
}
