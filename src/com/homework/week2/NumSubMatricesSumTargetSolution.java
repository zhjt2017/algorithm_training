package com.homework.week2;

import java.util.Arrays;

/**
 * 算法实现：元素和为目标值的子矩阵数量
 * - leetcode-cn 1074题
 * - 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * - 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * - 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 * <p>
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * <p>
 * 输入：matrix = [[1,-1],[-1,1]], target = 0
 * 输出：5
 * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * <p>
 * 输入：matrix = [[904]], target = 0
 * 输出：0
 * <p>
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-09 09:41:25
 */
public class NumSubMatricesSumTargetSolution {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
        int target = 0;
        System.out.println("matrix : " + Arrays.deepToString(matrix) + ", target =" + target);
        System.out.println("num of sub matrices : " + numSubMatricesSumTarget(matrix, target));

        matrix = new int[][]{{1, -1}, {-1, 1}};
        target = 0;
        System.out.println("matrix : " + Arrays.deepToString(matrix) + ", target =" + target);
        System.out.println("num of sub matrices : " + numSubMatricesSumTarget(matrix, target));

        matrix = new int[][]{{904}};
        target = 0;
        System.out.println("matrix : " + Arrays.deepToString(matrix) + ", target =" + target);
        System.out.println("num of sub matrices : " + numSubMatricesSumTarget(matrix, target));
    }

    private static int numSubMatricesSumTarget(final int[][] matrix, final int target) {
        return 0;
    }

}
