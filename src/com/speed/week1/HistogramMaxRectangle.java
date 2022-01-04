package com.speed.week1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 直方图(柱状图)求最大矩形
 * - 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。求在该柱状图中，能够勾勒出来的矩形的最大面积
 * - 输入示例1：heights = [2,1,5,6,2,3]
 * - 输出结果：10
 * - 输入示例2：heights = [2,4]
 * - 输出结果：4
 * - 输入示例3：heights = [2,0,4,2]
 * - 输出结果：4
 * - 输入示例4：heights = [2,1,2]
 * - 输出结果：3
 * <p>
 * - 主要思想：单调栈，维护局部最值
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-04 04:20:19
 */
public class HistogramMaxRectangle {
    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};
        System.out.println(Arrays.toString(heights));
        System.out.println(calMaxArea(heights));

        heights = new int[]{2, 4};
        System.out.println(Arrays.toString(heights));
        System.out.println(calMaxArea(heights));

        heights = new int[]{2, 0, 4, 2};
        System.out.println(Arrays.toString(heights));
        System.out.println(calMaxArea(heights));

        heights = new int[]{2, 1, 2};
        System.out.println(Arrays.toString(heights));
        System.out.println(calMaxArea(heights));
    }

    private static int calMaxArea(final int[] heights) {
        return calMaxArea(heights, new ArrayDeque<>());
    }

    /**
     * 直方图求最大矩形，抽象一下，同样可以作为MaximalRectangleSolution即0-1方阵遍历每个底时求该底所在直方图的最大矩形，故public
     * - 时间复杂度：O(n)
     * - 空间复杂度：<= O(n)
     *
     * @param heights
     * @param indexStack
     * @return
     */
    public static int calMaxArea(final int[] heights, final Deque<Integer> indexStack) {
        int result = 0;
        final int length = heights.length;
        if (length <= 0) {
            return result;
        }

        int popIndex = 0;
        for (int i = 0; i < length; i++) {
            if (heights[i] == 0) {
                result = maxAreaBoundary(indexStack, heights, i, result);
                continue;
            }

            if (indexStack.isEmpty() || heights[i] >= heights[indexStack.peekLast()]) {
                indexStack.addLast(i);
                continue;
            }

            while (!indexStack.isEmpty() && heights[i] < heights[indexStack.peekLast()]) {
                popIndex = indexStack.peekLast();
                result = Math.max(result, popAndCalArea(indexStack, heights, i));
            }
            // 最后一个pop掉的height, > height[i], 故height[i]需要向左延长到该位置
            indexStack.addLast(popIndex);
            heights[popIndex] = heights[i];
        }

        return maxAreaBoundary(indexStack, heights, length, result);
    }

    /**
     * 边界值考虑：最终栈中index元素必须全部pop出来(他们对应的height都<=h，包括index=0对应的height可能为0)，以枚举出所有可能最大矩形的面积 (并且清空后可以循环利用)
     * 边界值触发条件：heights遍历结束 or 遇到height=0的index
     *
     * @param indexStack
     * @param heights
     * @param boundaryIndex
     * @param resultBefore
     * @return
     */
    private static int maxAreaBoundary(final Deque<Integer> indexStack, final int[] heights, final int boundaryIndex, final int resultBefore) {
        int result = resultBefore;
        while (!indexStack.isEmpty()) {
            result = Math.max(result, popAndCalArea(indexStack, heights, boundaryIndex));
        }
        return result;
    }

    private static int popAndCalArea(final Deque<Integer> indexStack, final int[] heights, final int i) {
        final int area = (i - indexStack.peekLast()) * heights[indexStack.peekLast()];
        indexStack.pollLast();
        return area;
    }
}
