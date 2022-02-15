package com.homework.week6;

import java.util.Arrays;
import java.util.List;

/**
 * 算法实现：三角形最小路径和
 * - https://leetcode-cn.com/problems/triangle/description/ (120题)
 * <p>
 * - 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 * - 每一步只能移动到下一行中相邻的结点上。
 * - 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * - 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * <p>
 * - 输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * 输出：11
 * 解释：如下面简图所示：
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 输入：triangle = [[-10]]
 * 输出：-10
 * <p>
 * 1 <= triangle.length <= 200
 * triangle[0].length == 1
 * triangle[i].length == triangle[i - 1].length + 1
 * -10^4 <= triangle[i][j] <= 10^4
 * <p>
 * 要求：只使用 O(n) 的额外空间（n 为三角形的总行数）
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-15 06:18:39
 */
public class TriangleMinPathSolution {
    public static void main(String[] args) {
        final TriangleMinPathSolution solution = new TriangleMinPathSolution();

        List<List<Integer>> triangle = Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3));
        System.out.println("Input triangle : " + triangle);
        System.out.println("Output min path : " + solution.minPath(triangle));
        System.out.println();

        triangle = Arrays.asList(Arrays.asList(-10));
        System.out.println("Input triangle : " + triangle);
        System.out.println("Output min path : " + solution.minPath(triangle));
    }

    int minPath(final List<List<Integer>> triangle) {
        return 1;
    }
}
