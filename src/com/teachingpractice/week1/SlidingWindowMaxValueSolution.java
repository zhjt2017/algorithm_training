package com.teachingpractice.week1;

import com.teachingpractice.week5.SlidingWindowMaxValueByHeapSolution;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 滑动窗口求最值问题 (这里不妨求最大值)
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * <p>
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * <p>
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see SlidingWindowMaxValueByHeapSolution
 * @since 2022-01-05 12:57:30
 */
public class SlidingWindowMaxValueSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValues(nums, k))));

        nums = new int[]{1};
        k = 1;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValues(nums, k))));

        nums = new int[]{1, -1};
        k = 1;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValues(nums, k))));

        nums = new int[]{9, 11};
        k = 2;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValues(nums, k))));

        nums = new int[]{4, -2};
        k = 2;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValues(nums, k))));
    }

    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位，求每个滑动窗口中的最大值
     * <p>
     * 针对每个滑动窗口进行内部排序，会发现除边界外的值的比较是重复的
     * 设计思想：维护一个双端单调队列(存储index)，这样可以把肯定不符合的数字先筛选掉，并具有单调性，于是滑动窗口可以直接取得最大值
     * 时间复杂度：O(n)
     * 空间复杂度：O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    private static int[] slidingWindowMaxValues(final int[] nums, final int k) {
        final int n = nums.length;
        if (k > n) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        final int[] result = new int[n - k + 1];
        final Deque<Integer> deque = new ArrayDeque<>();

        // 滑动窗口未达成时，先放入元素 (此时只有一个规则：维持单调性)
        for (int i = 0; i < k - 1; i++) {
            addLastAndKeepDequeMonotone(deque, nums, i);
        }
        for (int i = k - 1; i < n; i++) {
            // 放入新元素 (准备工作：删除头部出界元素、维持单调性)
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            addLastAndKeepDequeMonotone(deque, nums, i);

            // 放入新元素后，计算滑动窗口最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    private static void addLastAndKeepDequeMonotone(final Deque<Integer> deque, final int[] nums, final int i) {
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
            deque.pollLast();
        }
        deque.addLast(i);
    }
}
