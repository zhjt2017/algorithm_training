package com.homework.week1;

import com.speed.week1.HistogramMaxRectangle;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 算法实现：找最大(面积的)矩形问题
 * - 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积
 * - 输入示例1：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * - 1 0 1 0 0
 * - 1 0 1 1 1
 * - 1 1 1 1 1
 * - 1 0 0 1 0
 * - 输出结果：6
 * - 输入示例2：matrix = []
 * - 输出结果：0
 * - 输入示例3：matrix = [["0"]]
 * - 输出结果：0
 * - 输入示例4：matrix = [["1"]]
 * - 输出结果：1
 * - 输入示例5：matrix = [["0", "1"]]
 * - 输出结果：1
 * - 输入示例6：matrix = [["0", "0"]]
 * - 输出结果：0
 * - 输入示例6：matrix = [["0", "1"], ["1", "0"]]
 * - 输出结果：1
 * - 输入示例7：matrix = [["0","0","1","0"],["1","1","1","1"],["1","1","1","1"],["1","1","1","0"],["1","1","0","0"],["1","1","1","1"],["1","1","1","0"]]
 * - 0 0 1 0
 * - 1 1 1 1
 * - 1 1 1 1
 * - 1 1 1 0
 * - 1 1 0 0
 * - 1 1 1 1
 * - 1 1 1 0
 * - 输出结果：12
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-03 11:08:23
 */
public class MaximalRectangleSolution {
    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));

        matrix = new char[][]{};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));

        matrix = new char[][]{{'0'}};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));

        matrix = new char[][]{{'1'}};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));

        matrix = new char[][]{{'0', '1'}};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));

        matrix = new char[][]{{'0', '0'}};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));

        matrix = new char[][]{{'0', '1'}, {'1', '0'}};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));

        matrix = new char[][]{{'0', '0', '1', '0'}, {'1', '1', '1', '1'}, {'1', '1', '1', '1'}, {'1', '1', '1', '0'}, {'1', '1', '0', '0'}, {'1', '1', '1', '1'}, {'1', '1', '1', '0'}};
        printlnMatrix(matrix);
        System.out.println("area: " + maximalRectangleArea(matrix));
    }

    private static int maximalRectangleArea(final char[][] matrix) {
        /*
        设计思想：以每一行为底求其局部面积最大值，每个局部最大面积的矩形的底即宽度，使用单调(递增)栈，栈中存的是底上原子正方形的索引
        时间复杂度：O(m*n)
        空间复杂度：O(n), 一个int[]存储每一列的高度(新底会刷新), n, 2个维护宽度的index的栈, 最大n，故为O(n)
         */
        if (matrix.length == 0) {
            return 0;
        }
        final int n = matrix[0].length;
        int result = 0;
        final Deque<Integer> indexStack = new ArrayDeque<>();
        final int[] heights = new int[n];
        final int[] histogramHeights = new int[n];
        for (final char[] valuesNewLine : matrix) {
            buildHeights(heights, valuesNewLine);
            synchronizeHeights(heights, histogramHeights, n);
            result = Math.max(result, HistogramMaxRectangle.calMaxArea(histogramHeights, indexStack));
        }

        return result;
    }

    private static void synchronizeHeights(final int[] heights, final int[] histogramHeights, final int length) {
        for (int i = 0; i < length; i++) {
            histogramHeights[i] = heights[i];
        }
    }

    private static void buildHeights(final int[] heights, final char[] valuesNewLine) {
        for (int i = 0; i < heights.length; i++) {
            heights[i] = valuesNewLine[i] == '0' ? 0 : heights[i] + 1;
        }
    }

    private static void printlnMatrix(final char[][] matrix) {
        for (final char[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
    }
}
