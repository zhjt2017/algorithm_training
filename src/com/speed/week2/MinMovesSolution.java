package com.speed.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 算法实现：得到连续 K 个 1 的最少相邻交换次数
 * - 给你一个整数数组 nums 和一个整数 k 。 nums 仅包含 0 和 1 。每一次移动，你可以选择 相邻 两个数字并将它们交换。
 * - 请你返回使 nums 中包含 k 个 连续 1 的 最少 交换次数。
 * <p>
 * 输入：nums = [1,0,0,1,0,1], k = 2
 * 输出：1
 * 解释：在第一次操作时，nums 可以变成 [1,0,0,0,1,1] 得到连续两个 1 。
 * <p>
 * 输入：nums = [1,0,0,0,0,0,1,1], k = 3
 * 输出：5
 * 解释：通过 5 次操作，最左边的 1 可以移到右边直到 nums 变为 [0,0,0,0,0,1,1,1] 。
 * <p>
 * 输入：nums = [1,1,0,1], k = 2
 * 输出：0
 * 解释：nums 已经有连续 2 个 1 了。
 * <p>
 * 1 <= nums.length <= 10^5
 * nums[i] 要么是 0 ，要么是 1 。
 * 1 <= k <= sum(nums)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-09 03:01:24
 */
public class MinMovesSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 0, 1, 0, 1};
        int k = 2;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("min moves : " + minMoves(nums, k));

        nums = new int[]{1, 0, 0, 0, 0, 0, 1, 1};
        k = 3;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("min moves : " + minMoves(nums, k));

        nums = new int[]{1, 1, 0, 1};
        k = 2;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("min moves : " + minMoves(nums, k));
    }

    /**
     * 设计思想：贪心+前缀和
     * @param nums
     * @param k
     * @return
     */
    private static int minMoves(final int[] nums, final int k) {
        int n = nums.length;
        List<Integer> list = new ArrayList(n);
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                list.add(i - list.size() - 1);
            }
        }
        int[] sum = new int[list.size() + 1];
        for (int i = 1; i <= list.size(); i++) {
            sum[i] = sum[i - 1] + list.get(i - 1);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i + k - 1 < list.size(); i++) {
            int j = i + k - 1;
            int mid = i + j >> 1;
            int left = list.get(mid) * (mid - i) - (sum[mid] - sum[i]);
            int right = sum[j + 1] - sum[mid + 1] - (j - mid) * list.get(mid);
            min = Math.min(min, left + right);
        }
        return min;
    }

    /**
     * 设计思想：新手思维
     * 1、长度为k的滑动窗口
     * 2、
     * @param nums
     * @param k
     * @return
     */
    private static int minMovesAnother(final int[] nums, final int k) {
        return 0;
    }
}
