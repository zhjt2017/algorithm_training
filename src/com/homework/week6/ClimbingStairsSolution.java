package com.homework.week6;

/**
 * 算法实现: 动态规划 - 爬楼梯问题
 * - https://leetcode-cn.com/problems/climbing-stairs/description/ (70题)
 * <p>
 * - 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * - 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * - 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * - 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * 1 <= n <= 45
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 03:33:08
 */
public class ClimbingStairsSolution {
    public static void main(String[] args) {
        final ClimbingStairsSolution solution = new ClimbingStairsSolution();

        long start = System.currentTimeMillis();
        for (int i = 41; i <= 45; i++) {
            System.out.println("Input stairs : " + i);
            System.out.println("Output paths (climbStairsFnMemorization) : " + solution.climbStairsFnMemorization(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("millis in total : " + (end - start));

        start = end;
        for (int i = 41; i <= 45; i++) {
            System.out.println("Input stairs : " + i);
            System.out.println("Output paths (climbStairs) : " + solution.climbStairs(i));
        }
        end = System.currentTimeMillis();
        System.out.println("millis in total : " + (end - start));
    }

    /**
     * 数学归纳：第n层的走法数 = 第n-1层的走法数 + 第n-2层的走法数 (第n层可以由n-1或者n-2层走到)
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(N)
     *
     * @param n
     * @return
     */
    int climbStairsFnMemorization(final int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[] memory = new int[n];
        memory[1] = 1;
        memory[2] = 2;
        for (int i = 3; i <= n - 1; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n - 2] + memory[n - 1];
    }

    /**
     * 数学归纳的基础上，使用动态规划求解 (采用了"滚动数组思想"将空间复杂度优化成了O(1))
     * - (上述方法的memory数组每次只用到3个位置，则有3个变量进行滚动即可)
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    int climbStairs(final int n) {
        int q = 0, r = 1;
        int p;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
