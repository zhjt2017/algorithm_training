package com.speed.week8;

/**
 * 算法实现：动态规划 - 打家劫舍III
 * - https://leetcode-cn.com/problems/house-robber-iii/ (337题)
 * <p>
 * - 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
 * - 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。
 * - 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * - 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * - 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。
 * <p>
 * - 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * - 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
 * <p>
 * 树的节点数在 [1, 10^4] 范围内
 * 0 <= Node.val <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-20 01:52:25
 */
public class HouseRobberSolutionIII {
    public static void main(String[] args) {

    }

    int rob(final TreeNode root) {
        return 0;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(final int val) {
            this.val = val;
        }
    }
}
