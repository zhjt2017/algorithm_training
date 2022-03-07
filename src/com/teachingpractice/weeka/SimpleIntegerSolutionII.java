package com.teachingpractice.weeka;

/**
 * 算法实现：树状数组(线段树) - 一个简单的整数问题2
 * - https://www.acwing.com/problem/content/description/244/
 * 给定一个长度为 N 的数列 A，以及 M 条指令，每条指令可能是以下两种之一：
 * <p>
 * C l r d，表示把 A[l],A[l+1],…,A[r] 都加上 d。
 * Q l r，表示询问数列中第 l∼r 个数的和。
 * 对于每个询问，输出一个整数表示答案。
 * <p>
 * 输入格式
 * 第一行两个整数 N,M。
 * <p>
 * 第二行 N 个整数 A[i]。
 * <p>
 * 接下来 M 行表示 M 条指令，每条指令的格式如题目描述所示。
 * <p>
 * 输出格式
 * 对于每个询问，输出一个整数表示答案。
 * <p>
 * 每个答案占一行。
 * <p>
 * 数据范围
 * 1≤N,M≤105,
 * |d|≤10000,
 * |A[i]|≤109
 * 输入样例：
 * 10 5
 * 1 2 3 4 5 6 7 8 9 10
 * Q 4 4
 * Q 1 10
 * Q 2 4
 * C 3 6 3
 * Q 2 4
 * 输出样例：
 * 4
 * 55
 * 9
 * 15
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 02:53:08
 */
public class SimpleIntegerSolutionII {
    public static void main(String[] args) {


    }
}
