package com.teachingpractice.week3.treetraversal;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：树的遍历-二叉树中序遍历
 * <p>
 * - 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * <p>
 * - 输入：root = []
 * 输出：[]
 * <p>
 * - 输入：root = [1,2]
 * 输出：[2,1]
 * <p>
 * - 输入：root = [1,null,2]
 * 输出：[1,2]
 * <p>
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * <p>
 * 说明：关于上述输入(测试用例)参数，层序遍历全节点序列是可以部分省略的，其也能够完整标识一个二叉树：有2点：
 * 1、最后一层末尾处的null
 * 2、如果上一层的全节点表示中，该分支已经是null，则后面那层，该分支的左右节点的null均可以省略
 * <p>
 * 基本实现方式：(递归，时间复杂度O(n) 空间复杂度最多O(n))
 * - 每层自身逻辑：先读取左节点，再读取根节点，然后读取右节点，base case为该子树的左节点为空
 * <p>
 * 进阶实现方式：(迭代，时间复杂度O(n) 空间复杂度最多O(n))
 * - 遍历每一层，只往下遍历左节点，栈中压入每层节点，供后面出栈
 * - 最后压入的(左)节点(pop出的第一个)如果左节点即为第一个写入result的
 * - 然后写入其右节点，如果其存在，则以其为根节点继续往下遍历子树的左节点，同上面写入左节点情形一致，如果其不存在，则该子树已经遍历完成
 * - 然后再从栈中pop出一个，相当于访问其父节点，然后其父节点再访问右节点
 * - 不断迭代，直接整棵树都遍历完成，此时栈中不再有元素(父节点)，下一个节点也为空(右节点为空，或者左右节点均为空)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-19 11:49:50
 */
public class BinaryTreeInorderTraversalSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        System.out.println("Input binary tree : " + root);
        System.out.println("inorderTraversal result : " + inorderTraversal(root));
        System.out.println("inorderTraversal result : " + inorderTraversalIteration(root));

        System.out.println("Input binary tree : null");
        System.out.println("inorderTraversal result : " + inorderTraversal(null));
        System.out.println("inorderTraversal result : " + inorderTraversalIteration(null));

        root = new TreeNode(1);
        System.out.println("Input binary tree : " + root);
        System.out.println("inorderTraversal result : " + inorderTraversal(root));
        System.out.println("inorderTraversal result : " + inorderTraversalIteration(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println("Input binary tree : " + root);
        System.out.println("inorderTraversal result : " + inorderTraversal(root));
        System.out.println("inorderTraversal result : " + inorderTraversalIteration(root));

        root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println("Input binary tree : " + root);
        System.out.println("inorderTraversal result : " + inorderTraversal(root));
        System.out.println("inorderTraversal result : " + inorderTraversalIteration(root));
    }

    private static List<Integer> inorderTraversal(final TreeNode root) {
        final BinaryTreeInorderTraversalSolution solution = new BinaryTreeInorderTraversalSolution();
        solution.result = new LinkedList<>();
        if (root != null) {
            solution.recur(root);
        }
        return solution.result;
    }

    private static List<Integer> inorderTraversalIteration(final TreeNode root) {
        TreeNode node = root;
        final List<Integer> result = new LinkedList<>();
        final Deque<TreeNode> stack = new LinkedList<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.val);
            node = node.right;
        }
        return result;
    }

    private void recur(final TreeNode root) {
        // 读取左节点
        if (root.left != null) {
            this.recur(root.left);
        }

        // 读取自身节点
        this.result.add(root.val);

        // 读取右节点
        if (root.right != null) {
            this.recur(root.right);
        }
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

    private List<Integer> result;
}
