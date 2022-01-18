package com.teachingpractice.week3;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 算法实现：树-验证二叉搜索树
 * <p>
 * - 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * - 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * - 输入：root = [5,1,4,null,null,3,6] (层序遍历序列化)
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4
 * <p>
 * - 输入：root = [2,1,3]
 * 输出：true
 * <p>
 * - 树中节点数目范围在[1, 104] 内
 * -2^31 <= Node.val <= 2^31 - 1
 * <p>
 * <p>
 * - 设计思想：递归，判断时，先验证所有的子树满足二叉搜索树，向上归并，数值范围限定，自顶向下，root缩小left的上限，缩小right的下限 (无论是left还是right都有自己的左右子树，则同时传入min, max)
 * - 单层逻辑：子树root自身val满足范围 (不包含边界值，即二叉搜索树上的值都是唯一的，就像是唯一索引的实现)
 * - 迭代逻辑：单层逻辑满足后，要求left与right均满足要求
 * - 终止条件：子树为空，即root=null
 * - 特别注意：由于二叉树的节点的val允许到达Integer的最大值与最小值，则使用long初始化边界 (仍然使用int表示val)
 * <p>
 * - 说明1：不使用递归也是可以做的，但是时间复杂度会在同一数量级下增加一倍左右，循环与判断比较多，见isValidBSTWithoutRecursion方法 (不推荐)
 * <p>
 * - 说明2 (有意思)：这里定义的二叉搜索树，当采用中序遍历时，得到的List是完全升序的
 *
 * <p>
 * 时间复杂度：O(n)
 * 空间复杂度：O(logN) - O(n) (最坏情况下树为一条链)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-17 10:04:01
 */
public class ValidateBinarySearchTreeSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.left.left = null;
        root.left.right = null;
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);

        isValidBST(root);
        System.out.println("isValidBSTWithoutRecursion : " + isValidBSTWithoutRecursion(root));

        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        isValidBST(root);
        System.out.println("isValidBSTWithoutRecursion : " + isValidBSTWithoutRecursion(root));

        root = new TreeNode(Integer.MAX_VALUE);

        isValidBST(root);
        System.out.println("isValidBSTWithoutRecursion : " + isValidBSTWithoutRecursion(root));
    }

    private static boolean isValidBST(final TreeNode root) {
        System.out.println("Input binary tree : " + root);
        ValidateBinarySearchTreeSolution solution = new ValidateBinarySearchTreeSolution();
        boolean result = solution.recur(root, Integer.MIN_VALUE - 1L, Integer.MAX_VALUE + 1L);
        System.out.println("Is binary search tree : " + result);
        return result;
    }

    private boolean recur(final TreeNode root, final long min, final long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= max || root.val <= min) {
            return false;
        }
        return this.recur(root.left, min, root.val) && this.recur(root.right, root.val, max);
    }

    private static boolean isValidBSTWithoutRecursion(final TreeNode root) {
        final Deque<TreeNode> stack = new LinkedList<>();
        double inorder = -Double.MAX_VALUE;

        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (node.val <= inorder) {
                return false;
            }
            inorder = node.val;
            node = node.right;
        }
        return true;
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
