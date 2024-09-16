package com.teachingpractice.week3.treeother;

/**
 * 算法实现：树的其他方面-二叉树的最近公共祖先
 * - https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/ (236题)
 * <p>
 * - 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * - 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * - 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * <p>
 * - 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * - 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 * <p>
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-20 05:44:30
 */
public class LowestCommonAncestorOfBinaryTreeSolution {
    public static void main(String[] args) {

    }

    private static TreeNode lowestCommonAncestor(final TreeNode root, final TreeNode p, final TreeNode q) {
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
