package com.teachingpractice.week3.treeother;


/**
 * 算法实现：树的其他方面-二叉树的直径
 * -- leetcode-cn 543
 * <p>
 * - 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * <p>
 * - 输入：[1,2,3,4,5]
 * 输出：3
 * <p>
 * - 输入：[1,2,3,4,5,null,null,6,7,null,8,9,null,10,null,11]
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-20 04:48:49
 */
public class DiameterOfBinaryTreeSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println("Input binary tree : " + root);
        // diameter : 4
        System.out.println("Diameter : " + diameterOfBinaryTree(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.right = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.left.left.left.left = new TreeNode(9);
        root.left.left.right.left = new TreeNode(10);
        root.left.right.right.left = new TreeNode(11);
        System.out.println("Input binary tree : " + root);
        // diameter : 7
        System.out.println("Diameter : " + diameterOfBinaryTree(root));
    }

    private static int diameterOfBinaryTree(final TreeNode root) {
        return 0;
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
