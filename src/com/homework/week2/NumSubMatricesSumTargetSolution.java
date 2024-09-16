package com.homework.week2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
 * <p>
 * 分析：
 * - int范围 (m行, n列, 组成二维数组int[m][n]的矩阵，每个元素范围[-1000,1000], 最多100行, 最多100列, 则最大或最小矩阵元素和仍在int范围内，并且元素和没有单调性)
 * - 这个题就是“和为K的子数组”扩展到二维数组的实现
 * <p>
 * 设计思想：
 * 1、遍历求一次子矩阵的(每个matrix[i][j]作为子矩阵右下方节点时)前序和，复杂度O(m*n)
 * 2、然后遍历求另一半，但是2个前序和的元素差，并不能构成一个子矩阵，故完全没有实际意义
 * <p>
 * 这是思维定势了
 * <p>
 * 于是我们发现要想两子矩阵差仍然是一个子矩阵，则必须有一个方向是相同的，即要么相同的高度，要么相同的宽度
 * 那么不同的那个方向上，我们使用“一维数组和为K的子数组”算法即可，时间复杂度O(n)
 * 另外一个方向上，我们需要分别遍历上底与下底，来提供高度，时间复杂度O(m^2)
 * 优化：避免m>2n的情况下，导致m^2为太多浪费的复杂度，此时值得使用额外的m*n的空间转换int[m][n]到int[n][m]
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
        final int m = matrix.length;
        final int n = matrix[0].length;
        if (m <= (n << 1)) {
            return core(matrix, m, n, target);
        }

        final int[][] matrixConverted = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrixConverted[j][i] = matrix[i][j];
            }
        }
        return core(matrixConverted, n, m, target);
    }

    private static int core(final int[][] matrix, final int m, final int n, final int target) {
        int sum = 0;
        final int[] nums = new int[n];
        for (int top = 0; top < m; top++) {
            // 外部，新一次的高度迭代开始, 设置nums初始值
            for (int j = 0; j < n; j++) {
                nums[j] = 0;
            }

            for (int bottom = top; bottom < m; bottom++) {
                for (int j = 0; j < n; j++) {
                    nums[j] += matrix[bottom][j];
                }
                sum += subArraySum(nums, target, n);
            }
        }

        return sum;
    }

    private static int subArraySum(final int[] nums, final int k, final int n) {
        final Map<Integer, Integer> hash = new HashMap<>(n);
        // 初始边界条件，即保证从最左边开始的子串(哪怕只是一个元素的子串)也是成立的
        hash.put(0, 1);

        int preSum = 0;
        int resultCount = 0;
        for (int i = 0; i < n; i++) {
            preSum += nums[i];
            resultCount += hash.getOrDefault(preSum - k, 0);
            hash.put(preSum, hash.getOrDefault(preSum, 0) + 1);
        }
        return resultCount;
    }
}
