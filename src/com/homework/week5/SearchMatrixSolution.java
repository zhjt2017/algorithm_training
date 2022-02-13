package com.homework.week5;

import java.util.Arrays;

/**
 * 算法实现：搜索二维矩阵
 * - https://leetcode-cn.com/problems/search-a-2d-matrix/ (74题)
 * <p>
 * - 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * - 每行中的整数从左到右按升序排列。
 * - 每行的第一个整数大于前一行的最后一个整数。
 * <p>
 * - 输入：matrix = [
 * [1,3,5,7],
 * [10,11,16,20],
 * [23,30,34,60]],
 * target = 3
 * 输出：true
 * <p>
 * - 输入：matrix = [
 * [1,3,5,7],
 * [10,11,16,20],
 * [23,30,34,60]],
 * target = 13
 * 输出：false
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 10:34:21
 */
public class SearchMatrixSolution {
    public static void main(String[] args) {
        final SearchMatrixSolution solution = new SearchMatrixSolution();

        int[][] matrix = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;
        System.out.println("Input matrix : " + Arrays.deepToString(matrix) + ", target :" + target);
        System.out.println("Output search matrix result : " + solution.searchMatrixBase(matrix, target));
        System.out.println("Output search matrix result : " + solution.searchMatrix(matrix, target));
        System.out.println();

        target = 13;
        System.out.println("Input matrix : " + Arrays.deepToString(matrix) + ", target :" + target);
        System.out.println("Output search matrix result : " + solution.searchMatrixBase(matrix, target));
        System.out.println("Output search matrix result : " + solution.searchMatrix(matrix, target));
    }

    /**
     * 万不得已的方式：将matrix的每个元素，转换为一维数组，然后二分查找
     * - 时间复杂度 O(MN)
     * - 空间复杂度 O(MN)
     *
     * @param matrix
     * @param target
     * @return
     */
    boolean searchMatrixBase(final int[][] matrix, final int target) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        final int[] nums = new int[m * n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nums[index] = matrix[i][j];
                index++;
            }
        }
        // 二分查找
        int left = 0;
        int right = m * n - 1;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                return true;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    /**
     * 矩阵本身也有自己的特性，所以我们要掌握这种专属于矩阵的搜索
     * - 先求第几行(针对每行第一个元素取<=target的最大元素)
     * - 然后对该行进行二分查找
     *
     * @param matrix
     * @param target
     * @return
     */
    boolean searchMatrix(final int[][] matrix, final int target) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        int up = -1;
        int down = m - 1;
        int mid;
        while (up < down) {
            mid = ((down - up - 1) >> 1) + up + 1;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] < target) {
                up = mid;
            } else {
                down = mid - 1;
            }
        }
        if (up == -1 || n == 1) {
            return false;
        }

        int left = 1;
        int right = n - 1;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (matrix[up][mid] == target) {
                return true;
            }
            if (target > matrix[up][mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
