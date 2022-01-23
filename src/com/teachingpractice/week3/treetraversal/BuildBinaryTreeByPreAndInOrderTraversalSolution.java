package com.teachingpractice.week3.treetraversal;

/**
 * 算法实现：树的遍历-从前序遍历与中序遍历的序列构造(还原)二叉树
 * - 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点
 * <p>
 * 想法：遍历的序列是没有包含满二叉树上为null的节点的，所以我们无法确定位置，而如果前序遍历序列(或后续遍历序列)，中序遍历序列相对照，就可以唯一定位二叉树的所有节点，即还原了二叉树
 * <p>
 * - Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * <p>
 * - Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-20 05:22:25
 */
public class BuildBinaryTreeByPreAndInOrderTraversalSolution {
    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        System.out.println("Input preorder : " + preorder + ", inorder : " + inorder);
        System.out.println("Build binary tree result : " + buildTree(preorder, inorder));

        preorder = new int[]{-1};
        inorder = new int[]{-1};
        System.out.println("Input preorder : " + preorder + ", inorder : " + inorder);
        System.out.println("Build binary tree result : " + buildTree(preorder, inorder));
    }

    private static TreeNode buildTree(final int[] preorder, final int[] inorder) {
        return null;
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
