package com.speed.week4;

/**
 * 算法实现；二叉搜索树-删除节点
 * - 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * 一般来说，删除节点可分为两个步骤：先找到需要删除的节点；如果找到了，删除它。
 * <p>
 * - 说明：如果删除的节点不是叶子节点，其left或者right替换它的位置
 * - 如果不包含，则不删除任何节点
 * <p>
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * <p>
 * 输入: root = [], key = 0
 * 输出: []
 * <p>
 * 节点数的范围 [0, 104].
 * -105 <= Node.val <= 105
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -105 <= key <= 105
 *  
 * 设计思想：
 * <p>
 * 进阶：时间复杂度O(h) (h为树的高度)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-22 11:15:59
 */
public class DeleteNodeBinarySearchTreeSolution {
    public static void main(String[] args) {

    }

    private static TreeNode deleteNode(final TreeNode root, final int key) {
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
