package com.teachingpractice.week4;

/**
 * 算法实现: BST - 删除节点
 * - https://leetcode-cn.com/problems/delete-node-in-a-bst/ (450题)
 * <p>
 * - 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * - 一般来说，删除节点可分为两个步骤：1.首先找到需要删除的节点；2.如果找到了，删除它。
 * <p>
 * - 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * - 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * <p>
 * - 输入: root = [], key = 0
 * 输出: []
 * <p>
 * 节点数的范围 [0, 10^4].
 * -10^5 <= Node.val <= 10^5
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -10^5 <= key <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-03 12:49:24
 */
public class BinarySearchTreeDeleteNodeSolution {
    public static void main(String[] args) {
        final BinarySearchTreeDeleteNodeSolution solution = new BinarySearchTreeDeleteNodeSolution();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        int key = 3;
        System.out.println("Input BST : " + root + ", delete key : " + key);
        System.out.println("Output BST : " + solution.deleteNode(root, key));

        key = 0;
        System.out.println("Input BST : " + root + ", delete key : " + key);
        System.out.println("Output BST : " + solution.deleteNode(root, key));

        System.out.println("Input BST : null, delete key : " + key);
        System.out.println("Output BST : " + solution.deleteNode(null, key));
    }

    private TreeNode deleteNode(final TreeNode root, final int key) {
        if (root == null) {
            return null;
        }

        if (root.val == key) {
            // 当最多只有一个子树时, 该子树上移到root位置, root删除完成
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 当左右子树都存在时, 则必然在右子树上可以找到后继者, 将其原节点删除, 并将其值赋予root节点
            TreeNode next = root.right;
            while (next.left != null) {
                next = next.left;
            }
            root.val = next.val;
            // 删除其原节点, 由于原节点是叶子节点, 则直接递归执行即可
            root.right = deleteNode(root.right, next.val);
            return root;
        }

        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
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
