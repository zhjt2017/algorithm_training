package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-19) 根据二叉树创建字符串
 * - https://leetcode-cn.com/problems/construct-string-from-binary-tree/ (606题)
 * <p>
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * <p>
 * - 输入: 二叉树: [1,2,3,4]
 * 1
 * /   \
 * 2     3
 * /
 * 4
 * <p>
 * 输出: "1(2(4))(3)"
 * <p>
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * <p>
 * - 输入: 二叉树: [1,2,3,null,4]
 * 1
 * /   \
 * 2     3
 * \
 * 4
 * <p>
 * 输出: "1(2()(4))(3)"
 * <p>
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-19 04:08:55
 */
public class ConstructStringFromBinaryTreeSolution {
    public static void main(String[] args) {
        final ConstructStringFromBinaryTreeSolution solution = new ConstructStringFromBinaryTreeSolution();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println("Input binary tree : " + root);
        System.out.println("Output string from binary tree : " + solution.tree2Str(root));
        System.out.println();

        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        System.out.println("Input binary tree : " + root);
        System.out.println("Output string from binary tree : " + solution.tree2Str(root));
        System.out.println();
    }

    /**
     * 时间复杂度 O(n)
     * 空间复杂度 O(logn) - O(n)
     *
     * @param root
     * @return
     */
    String tree2Str(final TreeNode root) {
        if (root == null) {
            return "";
        }
        sb = new StringBuilder();
        dfs(root);
        return sb.substring(1, sb.length() - 1);
    }

    /**
     * 直接进行dfs前序遍历
     * - 每一个root都要给自己为root的子树的值使用一对括号进行包裹
     * - 当子树为空时，一般都是省略括号，唯一不省略括号的情形是：该子树作为左子树为空，而对应的右子树不为空
     *
     * @param root
     */
    void dfs(final TreeNode root) {
        sb.append(LEFT_PARENTHESIS);
        sb.append(root.val);

        if (root.left == null && root.right != null) {
            sb.append(LEFT_PARENTHESIS).append(RIGHT_PARENTHESIS);
        }

        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }

        sb.append(RIGHT_PARENTHESIS);
    }

    private static final char LEFT_PARENTHESIS = '(';
    private static final char RIGHT_PARENTHESIS = ')';
    private StringBuilder sb;

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(final int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.format("{\"val\" : %d, \"left\" : %s, \"right\" : %s}", val, left, right);
        }
    }
}
