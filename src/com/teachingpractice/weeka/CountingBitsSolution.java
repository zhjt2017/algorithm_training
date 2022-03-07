package com.teachingpractice.weeka;

import java.util.Arrays;

/**
 * 算法实现：位运算 - 比特位计数
 * - https://leetcode-cn.com/problems/counting-bits/ (338题)
 * <p>
 * 给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，返回一个长度为 n + 1 的数组 ans 作为答案。
 * <p>
 * - 输入：n = 2
 * 输出：[0,1,1]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * <p>
 * - 输入：n = 5
 * 输出：[0,1,1,2,1,2]
 * 解释：
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 * <p>
 * 0 <= n <= 10^5
 * <p>
 * 进阶：以O(n)的时间复杂度原生实现
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 11:06:37
 */
public class CountingBitsSolution {
    public static void main(String[] args) {
        final CountingBitsSolution solution = new CountingBitsSolution();

        int n = 2;
        System.out.println("Input n : " + n);
        System.out.println("Output count bits result (0 - n) : " + Arrays.toString(solution.countBits(n)));
        System.out.println();

        n = 5;
        System.out.println("Input n : " + n);
        System.out.println("Output count bits result (0 - n) : " + Arrays.toString(solution.countBits(n)));
        System.out.println();
    }

    /**
     * 优化：利用lowbit清零进行递推 (想要O(n), 就递推找规律)
     * - count[i] = count[i & (i - 1)] + 1
     * - 边界：count[0] = 0
     *
     * @param n
     * @return
     * @see CountOfBitOneSolution 每个i都去调用一下hammingWeight方法，时间复杂度O(NlogN)
     */
    int[] countBits(final int n) {
        final int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            count[i] = count[i & (i - 1)] + 1;
        }
        return count;
    }
}
