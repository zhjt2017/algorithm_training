package com.teachingpractice.week3.tree;

/**
 * 算法实现：树-二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。(叶子节点是指没有子节点的节点)
 * <p>
 * - 输入：[3,9,20,null,null,15,7] (层序遍历全节点序列，可构成一种序列化方案)
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * - 输出：2
 * <p>
 * 设计思想：
 * - 总体思路与求二叉树的最大深度是一样的，但是需要注意一个问题，二叉树其实任何一个非叶子节点都可以只有一个子节点，于是base case上存在细微差别，需要特殊处理以下
 * - 求最大深度：可以单独看一个叶子节点即可，无论其是left还是right，只要它最深即可
 * - 求最小深度：不可以只看一个叶子节点，否则只要不是left,right都有，就会错判该非叶子节点为叶子节点。正解：在进入该子树节点自身逻辑前先将layer++，base case为自身有值，其左右子节点均为空
 * <p>
 * - 这里再说明一点，层序遍历全节点序列也是可以部分省略的，其也能够完整标识一个二叉树：
 * - 1、最后一层末尾处的null
 * - 2、如果上一层的全节点表示中，该分支已经是null，则后面那层，该分支的左右节点的null均可以省略 (举下面2个例子)
 * <p>
 * - 输入：[2,null,3,null,4,null,5,null,6]
 * - 输出：5
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 * <p>
 * - 输入：[1,2,3,4,5]
 * - 输出：2
 * 1
 * /  \
 * 2   3
 * / \
 * 4  5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-18 02:33:12
 */
public class MinDepthOfBinaryTreeSolution {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("Input binary tree : " + root);
        System.out.println("minDepth result : " + minDepth(root));

        root = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.right.right.right = new TreeNode(6);
        System.out.println("Input binary tree : " + root);
        System.out.println("minDepth result : " + minDepth(root));

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println("Input binary tree : " + root);
        System.out.println("minDepth result : " + minDepth(root));

        root = new TreeNode(1);
        System.out.println("Input binary tree : " + root);
        System.out.println("minDepth result : " + minDepth(root));
    }

    private static int minDepth(final TreeNode root) {
        if (root == null) {
            return 0;
        }

        final MinDepthOfBinaryTreeSolution solution = new MinDepthOfBinaryTreeSolution();
        solution.layer = 0;
        solution.minLayer = Integer.MAX_VALUE;
        solution.recur(root);
        return solution.minLayer;
    }

    private void recur(final TreeNode root) {
        if (root == null) {
            // 如果先进入到节点自身为空，则表示其父节点只有一个子节点，直接return，不做任何操作，因为该分支本就不存在 (这里不包含整棵树为空树的情况，此情况在recur入口前先处理掉)
            return;
        }

        // 进入该子树，先将layer++，以标识该子树是存在的 (特别注意：一旦对现场进行了操作，那么接下来无论有多少个分支，每个分支在结束前都要执行还原现场)
        this.layer++;
        // base case：没有任何子节点了
        if (root.left == null && root.right == null) {
            this.minLayer = Math.min(this.minLayer, this.layer);
            // 还原现场
            this.layer--;
            return;
        }
        if (root.left != null) {
            this.recur(root.left);
        }
        if (root.right != null) {
            this.recur(root.right);
        }
        // 还原现场
        this.layer--;
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

    private int minLayer;
    private int layer;
}
