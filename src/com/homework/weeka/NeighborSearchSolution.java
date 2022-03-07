package com.homework.weeka;

/**
 * 算法实现：平衡二叉树 - 邻值查找
 * - https://www.acwing.com/problem/content/description/138/
 * <p>
 * 给定一个长度为 n 的序列 A，A 中的数各不相同。
 * <p>
 * 对于 A 中的每一个数 Ai，求：
 * <p>
 * min1≤j<i|Ai−Aj|
 * 以及令上式取到最小值的 j（记为 Pi）。若最小值点不唯一，则选择使 Aj 较小的那个。
 * <p>
 * 输入格式
 * 第一行输入整数 n，代表序列长度。
 * <p>
 * 第二行输入 n 个整数A1…An,代表序列的具体数值，数值之间用空格隔开。
 * <p>
 * 输出格式
 * 输出共 n−1 行，每行输出两个整数，数值之间用空格隔开。
 * <p>
 * 分别表示当 i 取 2∼n 时，对应的 min1≤j<i|Ai−Aj| 和 Pi 的值。
 * <p>
 * 数据范围
 * n≤105,|Ai|≤109
 * 输入样例：
 * 3
 * 1 5 3
 * 输出样例：
 * 4 1
 * 2 1
 * <p>
 * 要求：尝试使用语言内置的有序集合库，或写一棵平衡树，来实现
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-27 10:38:05
 */
public class NeighborSearchSolution {
    public static void main(String[] args) {

    }
}
