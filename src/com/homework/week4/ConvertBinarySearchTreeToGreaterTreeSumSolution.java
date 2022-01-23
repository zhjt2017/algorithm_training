package com.homework.week4;

/**
 * 算法实现：转换二叉搜索树为累加树
 * - https://leetcode-cn.com/problems/convert-bst-to-greater-tree/ (538题)
 * <p>
 * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * <p>
 * - 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * <p>
 * - 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * <p>
 * - 输入：root = [1,0,2]
 * 输出：[3,3,2]
 * <p>
 * - 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 * <p>
 * 树中的节点数介于 0 和 10^4 之间。
 * 每个节点的值介于 -10^4 和 10^4 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-23 01:57:21
 */
public class ConvertBinarySearchTreeToGreaterTreeSumSolution {
    public static void main(String[] args) {

    }

    private static TreeNode convert(final TreeNode root) {
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
