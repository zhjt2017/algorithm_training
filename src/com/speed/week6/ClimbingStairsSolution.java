package com.speed.week6;

/**
 * 算法实现: 爬楼梯问题
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
 * @since 2022-02-08 03:49:03
 */
public class ClimbingStairsSolution {
    public static void main(String[] args) {
        final ClimbingStairsSolution solution = new ClimbingStairsSolution();

        long start = System.currentTimeMillis();
        for (int i = 45; i <= 45; i++) {
            System.out.println("Input stairs : " + i);
            System.out.println("Output paths (climbStairsFn) : " + solution.climbStairsFn(i));
        }
        long end = System.currentTimeMillis();
        System.out.println("millis in total : " + (end - start));

        start = end;
        for (int i = 45; i <= 45; i++) {
            System.out.println("Input stairs : " + i);
            System.out.println("Output paths (climbStairsDfs) : " + solution.climbStairsDfs(i));
        }
        end = System.currentTimeMillis();
        System.out.println("millis in total : " + (end - start));

        start = end;
        for (int i = 41; i <= 45; i++) {
            System.out.println("Input stairs : " + i);
            System.out.println("Output paths (climbStairsFnMemorization) : " + solution.climbStairsFnMemorization(i));
        }
        end = System.currentTimeMillis();
        System.out.println("millis in total : " + (end - start));

        start = end;
        for (int i = 41; i <= 45; i++) {
            System.out.println("Input stairs : " + i);
            System.out.println("Output paths (climbStairs) : " + solution.climbStairs(i));
        }
        end = System.currentTimeMillis();
        System.out.println("millis in total : " + (end - start));

        start = end;
        for (int i = 41; i <= 45; i++) {
            System.out.println("Input stairs : " + i);
            System.out.println("Output paths (climbStairsMatrixFastExponentiation) : " + solution.climbStairsMatrixFastExponentiation(i));
        }
        end = System.currentTimeMillis();
        System.out.println("millis in total : " + (end - start));
    }

    /**
     * 演进 - 1
     * 最笨的方法: dfs求解 (即完全遍历)
     * - 时间复杂度 O(2^N)
     * - 空间复杂度 O(N)
     *
     * @param n
     * @return
     */
    int climbStairsDfs(final int n) {
        this.paths = 0;
        this.n = n;
        this.dfs(0);
        return this.paths;
    }

    void dfs(final int current) {
        if (current == n) {
            paths++;
            return;
        }
        dfs(current + 1);
        if (current + 1 < n) {
            dfs(current + 2);
        }
    }

    private int paths;
    private int n;

    /**
     * 演进 - 2
     * 由于是在一条不变的方向上前进, 本质上可以不涉及组合 (看上去像组合)
     * 深入到爬楼梯的场景中, 每一阶楼梯都可以通过前一阶或者前2阶而来, 即f(n)=f(n-1)+f(n-2) (就是一个斐波那契数列)
     * - 这里先直接使用递归一元函数实现
     * <p>
     * - 这里, 使用的递归, 其本质上仍然是dfs, 只不过少了不少判断, 并且在累加计算上进行了一些简化, 所以其耗时也只是少了一半左右, 因为其递归中的每个子函数都会不断二叉
     *
     * @param n
     * @return
     */
    int climbStairsFn(final int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairsFn(n - 1) + climbStairsFn(n - 2);
    }

    /**
     * 演进 - 3
     * 我们在2的基础上, 空间换时间进行一些记忆化
     * - 此时递归也可以优化为迭代
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(N)
     * <p>
     * 这就直接从指数级降到了线性级
     * <p>
     * - 举例: n=45时, ans = 1836311903, 就是计算了那么多次, 现在只计算了45次, 约提升4千万倍, n更大时, 提升更大
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
     * 演进 - 4
     * 基本方法: 动态规划求解 (采用了"滚动数组思想"将空间复杂度优化成了O(1))
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param n
     * @return
     */
    int climbStairs(final int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 演进 - 5
     * 基本方法: 矩阵快速幂 (这个里面运算步骤很多, 对于本题n=45, 结果result就已经接近int最大值, 故意义不大, 本题重点在于动态规划的实践)
     * - 时间复杂度 O(logN)
     * - 空间复杂度 O(1)
     * <p>
     * - 先将数据的递推关系转化为矩阵的递推关系, 然后应用矩阵快速幂 (Fibonacci场景可以将非齐次线性递推转化为齐次线性递推)
     *
     * @param n
     * @return
     */
    int climbStairsMatrixFastExponentiation(final int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}
