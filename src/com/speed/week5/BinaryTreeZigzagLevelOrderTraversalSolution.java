package com.speed.week5;

import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现: 二叉树的 锯齿形 层序遍历
 * - 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * - 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * <p>
 * - 输入：root = [1]
 * 输出：[[1]]
 * <p>
 * - 输入：root = []
 * 输出：[]
 * <p>
 * - 输入：root = [1,2,3,4,null,null,5]
 * 输出：[[1],[3,2],[4,5]]
 * <p>
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-01 02:46:20
 */
public class BinaryTreeZigzagLevelOrderTraversalSolution {
    public static void main(String[] args) {
        final BinaryTreeZigzagLevelOrderTraversalSolution solution = new BinaryTreeZigzagLevelOrderTraversalSolution();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("Input binary tree : " + root);
        System.out.println("Output zigzag level order : " + solution.zigzagLevelOrder(root));

        root = new TreeNode(1);
        System.out.println("Input binary tree : " + root);
        System.out.println("Output zigzag level order : " + solution.zigzagLevelOrder(root));

        System.out.println("Input binary tree : null");
        System.out.println("Output zigzag level order : " + solution.zigzagLevelOrder(null));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        System.out.println("Input binary tree : " + root);
        System.out.println("Output zigzag level order : " + solution.zigzagLevelOrder(root));
    }

    /**
     * zigzag level order traversal
     * 核心: root节点的层数为1, 那么偶数层先访问自己的left节点, 再访问自己的right节点, 奇数层先访问自己的right节点, 再访问自己的left节点
     * (这需要使用stack来存储每一层, 于是在执行当层逻辑时需要将上一层的Node全部poll出来, 浪费了一些存储, 于是意义就不大了)
     * (所以我们选择保持bfs正常遍历不变, 仍然使用Queue, 在执行每层逻辑时, 还是统一按left在前right在后offer进去, 在下一层poll出来, 以正向插入与反向插入LinkedList来形成zigzag)
     *
     * @param root
     * @return
     */
    private List<List<Integer>> zigzagLevelOrder(final TreeNode root) {
        final List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }

        boolean left2Right = false;
        final LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int levelSize;
        LinkedList<Integer> levelList;
        TreeNode node;
        while (!queue.isEmpty()) {
            left2Right = !left2Right;
            levelSize = queue.size();
            levelList = new LinkedList<>();
            result.add(levelList);
            for (int i = 0; i < levelSize; i++) {
                node = queue.poll();

                if (left2Right) {
                    levelList.offer(node.val);
                } else {
                    levelList.offerFirst(node.val);
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return result;
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
            return String.format("{\"val\": %d, \"left\": %s, \"right\": %s}", this.val, this.left, this.right);
        }
    }
}
