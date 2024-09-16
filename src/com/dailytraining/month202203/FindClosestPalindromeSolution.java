package com.dailytraining.month202203;

import java.util.HashSet;
import java.util.Set;

/**
 * 算法训练(2022-03-02) 寻找最近的回文数字
 * - https://leetcode-cn.com/problems/find-the-closest-palindrome/ (564题)
 * <p>
 * - 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * - “最近的”定义为两个整数差的绝对值最小。
 * <p>
 * - 输入: n = "123"
 * 输出: "121"
 * <p>
 * - 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 * <p>
 * - 输入: n = "63533"
 * * 输出: "63536"
 * <p>
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 10^18 - 1] 范围内的整数
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-02 12:47:31
 */
public class FindClosestPalindromeSolution {
    public static void main(String[] args) {
        final FindClosestPalindromeSolution solution = new FindClosestPalindromeSolution();

        String n = "123";
        System.out.println("Input n : " + n);
        System.out.println("Output closest palindrome : " + solution.nearestPalindromic(n));
        System.out.println();

        n = "1";
        System.out.println("Input n : " + n);
        System.out.println("Output closest palindrome : " + solution.nearestPalindromic(n));
        System.out.println();

        n = "63533";
        System.out.println("Input n : " + n);
        System.out.println("Output closest palindrome : " + solution.nearestPalindromic(n));
        System.out.println();
    }

    /**
     * 时间复杂度 O(logN)
     * 空间复杂度 O(logN)
     *
     * @param s
     * @return
     */
    String nearestPalindromic(final String s) {
        int n = s.length();
        long cur = Long.parseLong(s);
        Set<Long> set = new HashSet<>();
        // 发生进位或退位时的2个可能边界值
        set.add((long) Math.pow(10, (n - 1)) - 1);
        set.add((long) Math.pow(10, n) + 1);
        // 前一半(其本身、+1、-1，作为回文的一侧)
        long t = Long.parseLong(s.substring(0, (n + 1) / 2));
        for (long i = t - 1; i <= t + 1; i++) {
            long temp = getNum(i, (n & 1) == 0);
            if (temp != cur) {
                set.add(temp);
            }
        }
        long ans = -1;
        for (long i : set) {
            if (ans == -1) {
                ans = i;
                continue;
            }
            if (Math.abs(i - cur) < Math.abs(ans - cur)) {
                ans = i;
                continue;
            }
            if (Math.abs(i - cur) == Math.abs(ans - cur) && i < ans) {
                ans = i;
            }
        }
        return String.valueOf(ans);
    }

    /**
     * 核心逻辑：根据前一半生成回文
     *
     * @param k
     * @param isEven
     * @return
     */
    long getNum(long k, boolean isEven) {
        StringBuilder sb = new StringBuilder();
        sb.append(k);
        int n = sb.length(), idx = isEven ? n - 1 : n - 2;
        while (idx >= 0) {
            sb.append(sb.charAt(idx--));
        }
        return Long.parseLong(sb.toString());
    }

}
