package com.dailytraining.month202204;

import java.util.Arrays;

/**
 * 算法训练(2022-04-01) 二倍数对 数组
 * - https://leetcode-cn.com/problems/array-of-doubled-pairs/ (954题)
 * <p>
 * 给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，
 * 都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 * <p>
 * - 输入：arr = [3,1,3,6]
 * 输出：false
 * <p>
 * - 输入：arr = [2,1,2,6]
 * 输出：false
 * <p>
 * - 输入：arr = [4,-2,2,-4]
 * 输出：true
 * 解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
 * <p>
 * 0 <= arr.length <= 3 * 10^4
 * arr.length 是偶数
 * -10^5 <= arr[i] <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-01 03:48:16
 */
public class ArrayOfDoubledPairsSolution {
    public static void main(String[] args) {
        final ArrayOfDoubledPairsSolution solution = new ArrayOfDoubledPairsSolution();

        int[] arr = new int[]{3, 1, 3, 6};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Output can reorder doubled : " + solution.canReorderDoubled(arr));
        System.out.println();

        arr = new int[]{2, 1, 2, 6};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Output can reorder doubled : " + solution.canReorderDoubled(arr));
        System.out.println();

        arr = new int[]{4, -2, 2, -4};
        System.out.println("Input arr : " + Arrays.toString(arr));
        System.out.println("Output can reorder doubled : " + solution.canReorderDoubled(arr));
        System.out.println();
    }

    /**
     * 我的初始题解：
     * 1 - 拆分为正数数组、负数数组，都必须是偶数个 (剩余的数必然是偶数个0，包括0个，0的部分满足Doubled Pair条件)
     * 2 - 负数数组也可以转换为相反数的正数数组，排序后，从小到大，每个数都要有其Pair的那个，并且增加一个visited数组
     * - 时间复杂度 O(nlogn)
     * - 空间复杂度 O(n)
     *
     * @param arr
     * @return
     */
    boolean canReorderDoubled(final int[] arr) {
        int positiveCount = 0;
        int zeroCount = 0;
        for (final int value : arr) {
            if (value == 0) {
                zeroCount++;
                continue;
            }
            if (value > 0) {
                positiveCount++;
            }
        }
        if ((zeroCount & 1) == 1 || (positiveCount & 1) == 1) {
            return false;
        }

        final int[] positive = new int[positiveCount];
        final int[] negative = new int[arr.length - positiveCount - zeroCount];
        int pIndex = 0;
        int nIndex = 0;
        for (final int value : arr) {
            if (value > 0) {
                positive[pIndex++] = value;
                continue;
            }
            if (value < 0) {
                negative[nIndex++] = -value;
            }
        }

        Arrays.sort(positive);
        Arrays.sort(negative);
        return valid(positive) && valid(negative);
    }

    private boolean valid(final int[] ordered) {
        final int n = ordered.length;
        final boolean[] visited = new boolean[n];
        int targetStartIndex = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            int pairIndex = binarySearch(ordered, visited, targetStartIndex, ordered[i] << 1);
            if (pairIndex == -1) {
                return false;
            }
            visited[pairIndex] = true;
        }
        return true;
    }

    /**
     * 二分查找出第一个满足值=target且visited为false的index
     * (当然二分查找也可以替换为使用HashMap计数的方式)
     *
     * @param ordered
     * @param visited
     * @param targetStartIndex
     * @param target
     * @return
     */
    private int binarySearch(final int[] ordered, final boolean[] visited, final int targetStartIndex, final int target) {
        int left = targetStartIndex;
        int right = ordered.length - 1;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (ordered[mid] > target || (ordered[mid] == target && !visited[mid])) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return ordered[right] == target && !visited[right] ? right : -1;
    }
}
