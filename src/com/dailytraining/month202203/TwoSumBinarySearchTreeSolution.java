package com.dailytraining.month202203;

import java.util.HashSet;
import java.util.Set;

/**
 * 算法训练(2022-03-21) 两数之和IV - 输入BST
 * - https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/ (653题)
 * <p>
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 * - 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 * <p>
 * - 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 * <p>
 * 二叉树的节点个数的范围是  [1, 10^4].
 * -10^4 <= Node.val <= 10^4
 * root 为二叉搜索树
 * -10^5 <= k <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-21 12:26:48
 */
public class TwoSumBinarySearchTreeSolution {

    public static void main(String[] args) {
        final TwoSumBinarySearchTreeSolution solution = new TwoSumBinarySearchTreeSolution();

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        int k = 9;
        System.out.println("Input binary tree : " + root + ", k = " + k);
        System.out.println("Output find target : " + solution.findTarget(root, k));
        System.out.println("Output find target (simple) : " + solution.findTargetSimple(root, k));
        System.out.println();

        k = 28;
        System.out.println("Input binary tree : " + root + ", k = " + k);
        System.out.println("Output find target : " + solution.findTarget(root, k));
        System.out.println("Output find target (simple) : " + solution.findTargetSimple(root, k));
        System.out.println();

        root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        k = 3;
        System.out.println("Input binary tree : " + root + ", k = " + k);
        System.out.println("Output find target : " + solution.findTarget(root, k));
        System.out.println("Output find target (simple) : " + solution.findTargetSimple(root, k));
        System.out.println();
    }

    /**
     * 针对BST求两数之和：
     * 思路1：中序遍历额外开一个升序数组，然后双指针夹逼求出满足的两数之和
     * 思路2：额外开一个HashSet，在一次dfs的遍历中求出满足的两数之和
     * 这里，我们实现思路2，针对本题更高效
     *
     * @param root
     * @param k
     * @return
     */
    boolean findTarget(final TreeNode root, final int k) {
        this.k = k;
        set = new HashSet<>();
        set.add(root.val);
        if ((k >> 1) >= root.val) {
            dfs(root.left);
            return dfsFind(root.right);
        }
        dfs(root.right);
        return dfsFind(root.left);
    }

    private void dfs(final TreeNode node) {
        if (node == null) {
            return;
        }
        set.add(node.val);
        dfs(node.left);
        dfs(node.right);
    }

    private boolean dfsFind(final TreeNode node) {
        if (node == null) {
            return false;
        }
        if (set.contains(k - node.val)) {
            return true;
        }
        set.add(node.val);
        return dfsFind(node.left) || dfsFind(node.right);
    }

    private HashSet<Integer> set;
    private int k;

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(final int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("{\"val\" : %d, \"left\" : %s, \"right\" : %s}", this.val, this.left, this.right);
        }
    }

    /**
     * 当然，由于计算两数之和的数据的随机性，其实BST的特性没有起到任何作用，与任意二叉树求两数之和，性能差不多，故可以直接使用任意二叉树的简单写法
     *
     * @param root
     * @param k
     * @return
     */
    boolean findTargetSimple(final TreeNode root, final int k) {
        if (root == null) {
            return false;
        }
        if (setSimple.contains(k - root.val)) {
            return true;
        }
        setSimple.add(root.val);
        return findTargetSimple(root.left, k) || findTargetSimple(root.right, k);
    }

    private Set<Integer> setSimple = new HashSet<>();
}
