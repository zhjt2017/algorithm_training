package com.teachingpractice.week4;

/**
 * 算法实现: BST - 插入操作
 * - https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/ (701题)
 * <p>
 * - 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。
 * - 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 * <p>
 * - 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 * <p>
 * - 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 * <p>
 * - 输入：root = [40,20,60,10,30,50,70], val = 25
 * 输出：[40,20,60,10,30,50,70,null,null,25]
 * <p>
 * - 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * 输出：[4,2,7,1,3,5]
 * <p>
 * - 输入: [], val = 3
 * 输出: [3]
 * <p>
 * 树中的节点数将在 [0, 10^4]的范围内。
 * -10^8 <= Node.val <= 10^8
 * 所有值 Node.val 是 独一无二 的。
 * -10^8 <= val <= 10^8
 * 保证 val 在原始BST中不存在。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-02 08:13:41
 */
public class BinarySearchTreeInsertSolution {
    public static void main(String[] args) {
        final BinarySearchTreeInsertSolution solution = new BinarySearchTreeInsertSolution();

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        int val = 5;
        System.out.println("Input BST : " + root + ", insert val : " + val);
        System.out.println("Output BST : " + solution.insert(root, val));

        root = new TreeNode(40);
        root.left = new TreeNode(20);
        root.right = new TreeNode(60);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(30);
        root.right.left = new TreeNode(50);
        root.right.right = new TreeNode(70);
        val = 25;
        System.out.println("Input BST : " + root + ", insert val : " + val);
        System.out.println("Output BST : " + solution.insert(root, val));

        val = 3;
        System.out.println("Input BST : null, insert val : " + val);
        System.out.println("Output BST : " + solution.insert(null, val));

        /***************************************************************************************************************/
        root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        val = 5;
        System.out.println("Input BST : " + root + ", insert val : " + val);
        System.out.println("Output BST : " + solution.insertIteration(root, val));

        root = new TreeNode(40);
        root.left = new TreeNode(20);
        root.right = new TreeNode(60);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(30);
        root.right.left = new TreeNode(50);
        root.right.right = new TreeNode(70);
        val = 25;
        System.out.println("Input BST : " + root + ", insert val : " + val);
        System.out.println("Output BST : " + solution.insertIteration(root, val));

        val = 3;
        System.out.println("Input BST : null, insert val : " + val);
        System.out.println("Output BST : " + solution.insertIteration(null, val));
    }

    /**
     * 由于必然不存在val, 故一直找到不再有子节点, 插入作为子节点
     * - DSF 时间复杂度 O(logN) 空间复杂度 O(logN)
     *
     * @param root
     * @param val
     * @return
     */
    private TreeNode insert(final TreeNode root, final int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    /**
     * 这里的DFS也可以不使用递归
     * - 时间复杂度 O(logN) 空间复杂度 O(1)
     *
     * @param root
     * @param val
     * @return
     */
    private TreeNode insertIteration(final TreeNode root, final int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val > val) {
                if (node.left == null) {
                    node.left = new TreeNode(val);
                    break;
                }
                node = node.left;
                continue;
            }

            if (node.right == null) {
                node.right = new TreeNode(val);
                break;
            }
            node = node.right;
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
