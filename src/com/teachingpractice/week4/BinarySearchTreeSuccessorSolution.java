package com.teachingpractice.week4;

/**
 * 算法实现: BST - 后继者
 * -
 * <p>
 * - 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * - 如果指定节点没有对应的“下一个”节点，则返回null
 * <p>
 * - 输入: root = [2,1,3], p = 1
 * 输出: 2
 * <p>
 * - 输入: root = [5,3,6,2,4,null,null,1], p = 6
 * 输出: null
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-02 08:35:58
 */
public class BinarySearchTreeSuccessorSolution {
    public static void main(String[] args) {
        final BinarySearchTreeSuccessorSolution solution = new BinarySearchTreeSuccessorSolution();

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        TreeNode p = new TreeNode(1);
        System.out.println("Input BST : " + root + ", p : " + p);
        System.out.println("Output successor of p in BST : " + solution.inorderSuccessor(root, p));

        root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        p = new TreeNode(6);
        System.out.println("Input BST : " + root + ", p : " + p);
        System.out.println("Output successor of p in BST : " + solution.inorderSuccessor(root, p));
    }

    /**
     * 原题给定节点p, 求节点p的后继者
     *
     * @param root
     * @param p
     * @return
     */
    private TreeNode inorderSuccessor(final TreeNode root, final TreeNode p) {
        return inorderSuccessor(root, p.val);
    }

    /**
     * 更一般性的场景: 给定val, 求val对应节点的后继者 (即求比val大的最小值是多少)
     * - 时间复杂度 O(logN)
     * - 空间复杂度 O(1)
     *
     * @param root
     * @param val
     * @return
     */
    private TreeNode inorderSuccessor(final TreeNode root, final int val) {
        TreeNode ans = null;
        TreeNode node = root;
        while (node != null) {
            if (node.val == val) {
                // 节点存在, 求其右子树的最小节点 (层次越深, 越是覆盖)
                node = node.right;
                while (node != null) {
                    ans = node;
                    node = node.left;
                }
                break;
            }

            if (node.val > val) {
                // 层次越深, 越是覆盖
                ans = node;
                node = node.left;
                continue;
            }

            node = node.right;
        }
        return ans;
    }

    /**
     * 在这里, recursion的写法反而显得略微复杂了
     *
     * @param root
     * @param val
     * @return
     */
    private TreeNode inorderSuccessorRecursion(final TreeNode root, final int val) {
        this.recur(root, val);
        return ans;
    }

    private void recur(final TreeNode root, final int val) {
        if (root == null) {
            // 找不到, 直接返回
            return;
        }

        if (root.val == val) {
            // 找到节点, 求其右子树的最小节点 (层次越深, 越是覆盖)
            if (root.right == null) {
                return;
            }
            ans = root.right;
            while (ans.left != null) {
                ans = ans.left;
            }
            return;
        }

        if (root.val > val) {
            // 层次越深, 越是覆盖
            ans = root;
            this.recur(root.left, val);
        } else {
            this.recur(root.right, val);
        }
    }

    private TreeNode ans = null;

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
