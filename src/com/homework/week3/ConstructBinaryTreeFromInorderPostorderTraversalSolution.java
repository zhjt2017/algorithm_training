package com.homework.week3;

import java.util.Arrays;

/**
 * 算法实现：树的遍历-使用中序遍历结果序列+后序遍历结果序列，构建(还原)二叉树
 * <p>
 * - 输入
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * - 输出
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-18 02:21:09
 */
public class ConstructBinaryTreeFromInorderPostorderTraversalSolution {
    public static void main(String[] args) {
        buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }

    private static TreeNode buildTree(final int[] inorder, final int[] postorder) {
        System.out.println("inorder : " + Arrays.toString(inorder) + ", postorder : " + Arrays.toString(postorder));

        TreeNode result = null;
        System.out.println("build tree success, root : " + result);
        return result;
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
}
