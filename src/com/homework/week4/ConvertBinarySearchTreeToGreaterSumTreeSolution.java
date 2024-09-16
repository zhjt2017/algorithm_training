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
public class ConvertBinarySearchTreeToGreaterSumTreeSolution {
    public static void main(String[] args) {
        final ConvertBinarySearchTreeToGreaterSumTreeSolution solution = new ConvertBinarySearchTreeToGreaterSumTreeSolution();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree : " + solution.convert(root));

        root = new TreeNode(0);
        root.right = new TreeNode(1);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree : " + solution.convert(root));

        root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree : " + solution.convert(root));

        root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree : " + solution.convert(root));

        /*******************************************************************************************************************************/
        root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(8);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree (Morris Traversal) : " + solution.convertMorris(root));

        root = new TreeNode(0);
        root.right = new TreeNode(1);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree (Morris Traversal) : " + solution.convertMorris(root));

        root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(2);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree (Morris Traversal) : " + solution.convertMorris(root));

        root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        System.out.println("Input binary search tree : " + root);
        System.out.println("Output greater sum tree (Morris Traversal) : " + solution.convertMorris(root));

    }

    /**
     * 树的节点关系没有改变, 只是重新赋值
     * - 从最大的节点开始计算, 每个节点重新赋值: 原值 + 后继节点的新值
     * - 基于递归的回溯, 使用反序中序遍历 (基于反序中序遍历结果的有序性, 每个节点重新赋值的值可以使用一个共享变量来维护)
     * - 时间复杂度 O(N) 空间复杂度 O(logN ~ N)
     *
     * @param root
     * @return
     */
    private TreeNode convert(final TreeNode root) {
        if (root == null) {
            return null;
        }
        convert(root.right);
        sum = sum + root.val;
        root.val = sum;
        convert(root.left);
        return root;
    }

    private int sum = 0;

    /**
     * 特殊方法: Morris遍历 (在时间复杂度保持O(N)时, 以空间复杂度O(1)实现中序遍历) (一般情况下, 中序遍历需要有一个stack的数据结构来维护)
     * - 核心思想: 利用树的大量空闲指针, 实现空间开销的极限缩减
     * - 时间复杂度: 没有左子树的节点只访问一次, 有左子树的节点访问2次
     * - 空间复杂度: 只操作已经存在的指针 (树的空间指针)
     *
     * @param root
     * @return
     */
    private TreeNode convertMorris(final TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            // 没有右子树, 计算自己, 迭代左子树 (前提条件, 右子树已经被计算过)
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
                continue;
            }

            // 有右子树, 取右子树中最小节点 (有序计算的左子树中的右子树也走这个逻辑, 这样每个分支都包含了)
            TreeNode successor = node.right;
            while (successor.left != null && successor.left != node) {
                successor = successor.left;
            }

            // 右子树中的最小节点 (将根节点同时挂载引用到其右子树的最小节点的左节点)
            // (层层深入下去, 所有的右子树均加上如此的挂载, 直到没有右子树, 就会触发通用计算逻辑: 计算自己, 迭代左子树)
            if (successor.left == null) {
                successor.left = node;
                node = node.right;
                continue;
            }

            // 从最大节点开始计算后, 当计算完自己及其原始左子树后, 原始左子树最小节点上此时有父节点的挂载引用, 删除挂载, 并计算挂载引用的节点, 计算完之后指向挂在应用的左子树
            successor.left = null;
            sum += node.val;
            node.val = sum;
            node = node.left;
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
