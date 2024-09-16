package com.dailytraining.month202203;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 算法训练(2022-03-04) 子数组范围和
 * - https://leetcode-cn.com/problems/sum-of-subarray-ranges/ (2104题)
 * <p>
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * 返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续 非空 的元素序列。
 * <p>
 * - 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 * <p>
 * - 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 * <p>
 * - 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 * <p>
 * 1 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 * <p>
 * 你可以设计一种时间复杂度为 O(n) 的解决方案吗？
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-04 08:19:44
 */
public class SumOfSubarrayRangesSolution {
    public static void main(String[] args) {
        final SumOfSubarrayRangesSolution solution = new SumOfSubarrayRangesSolution();

        int[] nums = new int[]{1, 2, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output sum of subarray ranges : " + solution.subarrayRanges(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStack(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack official) : " + solution.subarrayRangesMonotonicStackOfficial(nums));
        System.out.println();

        nums = new int[]{1, 3, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output sum of subarray ranges : " + solution.subarrayRanges(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStack(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack official) : " + solution.subarrayRangesMonotonicStackOfficial(nums));
        System.out.println();

        nums = new int[]{4, 3, 9};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output sum of subarray ranges : " + solution.subarrayRanges(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStack(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack official) : " + solution.subarrayRangesMonotonicStackOfficial(nums));
        System.out.println();

        nums = new int[]{4, 3, 6, 9};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output sum of subarray ranges : " + solution.subarrayRanges(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStack(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack official) : " + solution.subarrayRangesMonotonicStackOfficial(nums));
        System.out.println();

        nums = new int[]{4, -2, -3, 4, 1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output sum of subarray ranges : " + solution.subarrayRanges(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStack(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack official) : " + solution.subarrayRangesMonotonicStackOfficial(nums));
        System.out.println();

        nums = new int[]{4, -2, -3, 3, 1};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output sum of subarray ranges : " + solution.subarrayRanges(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStack(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStackOfficial(nums));
        System.out.println();

        nums = new int[]{8, 2, 1, 7, 5};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output sum of subarray ranges : " + solution.subarrayRanges(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStack(nums));
        System.out.println("Output sum of subarray ranges (monotonic stack) : " + solution.subarrayRangesMonotonicStackOfficial(nums));
        System.out.println();
    }

    /**
     * 1、枚举子数组的左边界i与右边界j
     * - 时间复杂度 O(n^2)
     * - 空间复杂度 O(1)
     *
     * @param nums
     * @return
     */
    long subarrayRanges(final int[] nums) {
        final int maxIndex = nums.length - 1;
        long ans = 0;

        int minValue;
        int maxValue;
        for (int i = 0; i < maxIndex; i++) {
            minValue = nums[i];
            maxValue = nums[i];
            for (int j = i + 1; j <= maxIndex; j++) {
                minValue = Math.min(minValue, nums[j]);
                maxValue = Math.max(maxValue, nums[j]);
                ans = ans + maxValue - minValue;
            }
        }
        return ans;
    }

    /**
     * 试想一下，枚举法，实际上将每个小段中的max与min的差，平均(包含式)计算了n/2次，那么如何优化这样的重复计算呢
     * - 针对所有的i,j，所有组(最大元素与最小元素的差值, 都是正的)，其实等价于所有最大元素的和(单调栈O(n)计算出) - 所有最小元素的和(单调栈O(n)计算出)
     * - 对于取最大元素，我们维护单调递减栈，栈中存放元素在nums中的索引，则在出现更大的元素时，从栈中弹出，得到最大元素及其影响范围
     * - 对于取最小元素，我们维护单调递增栈，栈中存放元素在nums中的索引，则在出现更小的元素时，从栈中弹出，得到最小元素及其影响范围
     * <p>
     * (这里的实现尚有点问题，但是方法论并未见明显问题 TODO)
     * <p>
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(n)
     *
     * @param nums
     * @return
     */
    long subarrayRangesMonotonicStack(final int[] nums) {
        final int n = nums.length;
        final Deque<Integer> minStack = new LinkedList<>();
        final Deque<Integer> maxStack = new LinkedList<>();

        int popIndex = 0;
        long ans = 0;
        int startIndex;
        for (int i = 0; i < n; i++) {
            while (!minStack.isEmpty() && nums[popIndex = minStack.peekLast()] > nums[i]) {
                // popIndex作为起始点
                ans -= (long) (i - popIndex - 1) * nums[popIndex];
                minStack.pollLast();
                // 补充popIndex作为经过点(不包括起始点, 包括终点)
                startIndex = minStack.isEmpty() ? 0 : minStack.peekLast() + 1;
                ans -= (long) (popIndex - startIndex) * (i - popIndex) * nums[popIndex];
            }
            minStack.offerLast(i);

            while (!maxStack.isEmpty() && nums[popIndex = maxStack.peekLast()] < nums[i]) {
                ans += (long) (i - popIndex - 1) * nums[popIndex];
                maxStack.pollLast();
                startIndex = maxStack.isEmpty() ? 0 : maxStack.peekLast() + 1;
                ans += (long) (popIndex - startIndex) * (i - popIndex) * nums[popIndex];
            }
            maxStack.offerLast(i);
        }

        // 处理栈中剩余的数据
        while (!minStack.isEmpty()) {
            popIndex = minStack.pollLast();
            ans -= (long) (n - popIndex - 1) * nums[popIndex];
            startIndex = minStack.isEmpty() ? 0 : minStack.peekLast() + 1;
            ans -= (long) (popIndex - startIndex) * (n - popIndex) * nums[popIndex];
        }

        while (!maxStack.isEmpty()) {
            popIndex = maxStack.pollLast();
            ans += (long) (n - popIndex - 1) * nums[popIndex];
            startIndex = maxStack.isEmpty() ? 0 : maxStack.peekLast() + 1;
            ans += (long) (popIndex - startIndex) * (n - popIndex) * nums[popIndex];
        }

        return ans;
    }

    long subarrayRangesMonotonicStackOfficial(final int[] nums) {
        int n = nums.length;
        int[] minLeft = new int[n];
        int[] minRight = new int[n];
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        Deque<Integer> minStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!minStack.isEmpty() && nums[minStack.peek()] > nums[i]) {
                minStack.pop();
            }
            minLeft[i] = minStack.isEmpty() ? -1 : minStack.peek();
            minStack.push(i);

            // 如果 nums[maxStack.peek()] == nums[i], 那么根据定义，
            // nums[maxStack.peek()] 逻辑上小于 nums[i]，因为 maxStack.peek() < i
            while (!maxStack.isEmpty() && nums[maxStack.peek()] <= nums[i]) {
                maxStack.pop();
            }
            maxLeft[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
            maxStack.push(i);
        }
        minStack.clear();
        maxStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            // 如果 nums[minStack.peek()] == nums[i], 那么根据定义，
            // nums[minStack.peek()] 逻辑上大于 nums[i]，因为 minStack.peek() > i
            while (!minStack.isEmpty() && nums[minStack.peek()] >= nums[i]) {
                minStack.pop();
            }
            minRight[i] = minStack.isEmpty() ? n : minStack.peek();
            minStack.push(i);

            while (!maxStack.isEmpty() && nums[maxStack.peek()] < nums[i]) {
                maxStack.pop();
            }
            maxRight[i] = maxStack.isEmpty() ? n : maxStack.peek();
            maxStack.push(i);
        }

        long sumMax = 0, sumMin = 0;
        for (int i = 0; i < n; i++) {
            sumMax += (long) (maxRight[i] - i) * (i - maxLeft[i]) * nums[i];
            sumMin += (long) (minRight[i] - i) * (i - minLeft[i]) * nums[i];
        }
        return sumMax - sumMin;
    }
}
