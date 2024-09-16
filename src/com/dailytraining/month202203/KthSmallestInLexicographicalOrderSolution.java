package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-23) 字典序的第k小数字 (字节跳动常考)
 * - https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/ (440题)
 * <p>
 * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * <p>
 * - 输入: n = 13, k = 2
 * 输出: 10
 * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * <p>
 * - 输入: n = 1, k = 1
 * 输出: 1
 * <p>
 * - 1 <= k <= n <= 10^9
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-23 09:13:44
 */
public class KthSmallestInLexicographicalOrderSolution {
    public static void main(String[] args) {
        final KthSmallestInLexicographicalOrderSolution solution = new KthSmallestInLexicographicalOrderSolution();

        int n = 13;
        int k = 2;
        System.out.println("Input n = " + n + ", k = " + k);
        System.out.println("Output find kth smallest number in lexicographical order : " + solution.findKthNumber(n, k));

        n = 1;
        k = 1;
        System.out.println("Input n = " + n + ", k = " + k);
        System.out.println("Output find kth smallest number in lexicographical order : " + solution.findKthNumber(n, k));

        n = 681692778;
        k = 351251360;
        System.out.println("Input n = " + n + ", k = " + k);
        System.out.println("Output find kth smallest number in lexicographical order : " + solution.findKthNumber(n, k));
    }

    /**
     * 整个Trie order其实就是对十叉树进行先序遍历
     * - 1、如何确定一个前缀下所有子节点的个数
     * - 2、第k个数在当前前缀下，如何向下面的子节点去找
     * - 3、第k个数不在当前前缀下，如何扩大前缀，增大寻找范围
     *
     * @param n
     * @param k
     * @return
     */
    int findKthNumber(final int n, final int k) {
        int p = 1;
        int prefix = 1;
        while (p < k) {
            int count = getCount(n, prefix);
            if (p + count <= k) {
                // 不在范围内，继续访问十叉树的下一个兄弟节点
                prefix++;
                p += count;
                continue;
            }
            // 在范围内，向下访问到子树
            prefix *= 10;
            p++;
        }
        return prefix;
    }

    /**
     * 根据题意，n <= 10^9, 则prefix最大可能2*10^9-1, 则next最大可能4*10^9-1, 故定义prefix, next为long
     *
     * @param n
     * @param prefix
     * @return
     */
    private int getCount(final int n, final int prefix) {
        int count = 0;
        for (long curr = prefix, next = prefix + 1; curr <= n; curr *= TEN, next *= TEN) {
            count += Math.min(next, n + 1) - curr;
        }
        return count;
    }

    private static final int TEN = 10;
}
