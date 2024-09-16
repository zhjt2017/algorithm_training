package com.homework.week2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 算法实现：数组的度
 * <p>
 * 给定一个非空且只包含非负数整数的数组 nums，数组的 度 的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * 输入：nums = [1,2,2,3,1]
 * 输出：2
 * 解释：
 * 输入数组的度是 2 ，因为元素 1 和 2 的出现频数最大，均为 2 。
 * 连续子数组里面拥有相同度的有如下所示：
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组 [2, 2] 的长度为 2 ，所以返回 2 。
 * <p>
 * 输入：nums = [1,2,2,3,1,4,2]
 * 输出：6
 * 解释：
 * 数组的度是 3 ，因为元素 2 重复出现 3 次。
 * 所以 [2,2,3,1,4,2] 是最短子数组，因此返回 6 。
 * <p>
 * nums.length 在 1 到 50,000 范围内。
 * nums[i] 是一个在 0 到 49,999 范围内的整数。
 * <p>
 * 设计思想：
 * 1、遍历一次，HashMap计算每个元素的度，及其最左边与最右边
 * 2、遍历该HashMap取出最大的度，及其最小的最左边与最右边的距离
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-09 08:50:59
 */
public class DegreeOfArraySolution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 3, 1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("min sub string of degree : " + findShortestSubArray(nums));

        nums = new int[]{1, 2, 2, 3, 1, 4, 2};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("min sub string of degree : " + findShortestSubArray(nums));
    }

    private static int findShortestSubArray(final int[] nums) {
        // value是一个长度为3的int数组，第一个int表示该元素的度，第二个int表示该元素最左边的index，第三个int表示该元素最右边的index
        final Map<Integer, int[]> degreeMap = new HashMap<>(nums.length >> 1);
        int[] node;
        for (int i = 0; i < nums.length; i++) {
            node = degreeMap.get(nums[i]);
            if (node == null) {
                degreeMap.put(nums[i], new int[]{1, i, i});
            } else {
                node[0]++;
                node[2] = i;
            }
        }

        int maxDegree = 0;
        // 为了方便，这里存最右边-最左边的差，最后返回答案时+1得到最小子数组长度
        int minDistance = 0;

        for (Map.Entry<Integer, int[]> entry : degreeMap.entrySet()) {
            node = entry.getValue();
            if (maxDegree > node[0]) {
                continue;
            }
            if (maxDegree == node[0]) {
                minDistance = Math.min(minDistance, node[2] - node[1]);
                continue;
            }
            maxDegree = node[0];
            minDistance = node[2] - node[1];
        }
        return minDistance + 1;
    }
}
