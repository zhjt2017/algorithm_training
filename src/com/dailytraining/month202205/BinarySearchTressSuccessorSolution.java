package com.dailytraining.month202205;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 算法训练(2022-05-16) 二叉搜索树 - 后继者
 * - https://leetcode.cn/problems/successor-lcci/ (面试题)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-05-16 12:27:17
 */
public class BinarySearchTressSuccessorSolution {
    public static void main(String[] args) {

    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
    }

    /**
     * 实现1：中序遍历-记忆化搜索(利用栈)
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        boolean found = false;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            if (found) {
                return node;
            }
            if (node == p) {
                found = true;
            }
            // 递推右子树，如果为空，则上溯栈中节点
            node = node.right;
        }
        return null;
    }

    /**
     * 实现1：中序遍历-记忆化搜索(递归)
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessorBfs(TreeNode root, TreeNode p) {
        this.p = p;
        ans = null;
        dfs(root);
        return ans;
    }

    private TreeNode p;
    private TreeNode ans;
    private boolean found;

    private void dfs(TreeNode node) {
        if (node == null || ans != null) {
            return;
        }
        dfs(node.left);

        if (ans != null) {
            return;
        }
        if (found) {
            ans = node;
            return;
        }
        if (node == p) {
            found = true;
        }

        dfs(node.right);
    }

    /**
     * 实现2：利用二叉搜索树的单调特性(路径更优)：后继者是刚好是比p大的最小值节点(先取右子树再取根节点)
     * - 直接从根节点开始递推
     *
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessorAnother(TreeNode root, TreeNode p) {
        TreeNode node = root;
        TreeNode ans = null;
        while (node != null) {
//            // 从根节点开始，一旦找到节点p，就取其后继者返回(根节点作为ans已经设置，所以这里只要取自身右节点即可)
//            if (node == p) {
//                node = node.right;
//                while (node != null) {
//                    ans = node;
//                    node = node.left;
//                }
//                break;
//            }

            // 寻找节点p：如果节点比p大，就试探其左节点 (这里也要设置ans，因为一旦左节点试探成功，而其没有右节点，则根节点为答案)
            if (node.val > p.val) {
                ans = node;
                node = node.left;
                continue;
            }

            // 寻找节点p：如果节点比p小(其根节点不可能比自己小，否则走不到这里，所以只要递推右节点，没有就结束)
            node = node.right;
        }
        return ans;
    }
}
