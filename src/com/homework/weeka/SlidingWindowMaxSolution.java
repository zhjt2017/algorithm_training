package com.homework.weeka;

import java.util.*;

/**
 * 算法实现：平衡二叉树 - 滑动窗口最大值问题
 * - https://leetcode-cn.com/problems/sliding-window-maximum/ (239题)
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * <p>
 * - 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * - 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 * <p>
 * 要求：尝试使用语言内置的有序集合库，或写一棵平衡树，来实现
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 03:04:24
 */
public class SlidingWindowMaxSolution {
    public static void main(String[] args) {
        final SlidingWindowMaxSolution solution = new SlidingWindowMaxSolution();

        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output max (value of) sliding window (balanced bst) : " + Arrays.toString(solution.maxSlidingWindow(nums, k)));
        System.out.println("Output max (value of) sliding window (priority queue) : " + Arrays.toString(solution.maxSlidingWindowPriorityQueue(nums, k)));
        System.out.println("Output max (value of) sliding window (deque monotone) : " + Arrays.toString(solution.maxSlidingWindowDequeMonotone(nums, k)));
        System.out.println();

        nums = new int[]{1};
        k = 1;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output max (value of) sliding window (balanced bst) : " + Arrays.toString(solution.maxSlidingWindow(nums, k)));
        System.out.println("Output max (value of) sliding window (priority queue) : " + Arrays.toString(solution.maxSlidingWindowPriorityQueue(nums, k)));
        System.out.println("Output max (value of) sliding window (deque monotone): " + Arrays.toString(solution.maxSlidingWindowDequeMonotone(nums, k)));
        System.out.println();
    }

    /**
     * 本课：我们要使用平衡二叉树来实现
     * - AVL树与红黑树这两种平衡二叉树，内部实现不要求手写，由于滑动窗口插入与删除比较频繁，更适合红黑树实现 (由于红黑树需要维护整体的顺序，而二叉堆只需要维护最值即可，故性能要略微差一点，然大O没有变)
     * - jdk的TreeMap就是实现的红黑树的数据结构, 此处我们就使用TreeMap来解
     * - TreeMap的key为元素值，value为count
     * <p>
     * - 时间复杂度 O(nlogk)
     * - 空间复杂度 O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    int[] maxSlidingWindow(final int[] nums, final int k) {
        final int n = nums.length;
        if (k > n) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        final int[] ans = new int[n - k + 1];
        final TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < k; i++) {
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
        }
        ans[0] = treeMap.lastKey();

        for (int i = k; i < n; i++) {
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
            int old = nums[i - k];
            int oldCount = treeMap.get(old);
            if (oldCount == 1) {
                treeMap.remove(old);
            } else {
                treeMap.put(old, oldCount - 1);
            }
            ans[i - k + 1] = treeMap.lastKey();
        }
        return ans;
    }

    /**
     * 对比之前做过的滑动区间维持单调(递减)栈的解法
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(k)
     *
     * @param nums
     * @param k
     * @return
     */
    int[] maxSlidingWindowDequeMonotone(final int[] nums, final int k) {
        final int n = nums.length;
        if (k > n) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        final int[] result = new int[n - k + 1];
        final Deque<Integer> deque = new LinkedList<>();

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

    void addLastAndKeepDequeMonotone(final Deque<Integer> deque, final int[] nums, final int i) {
        while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
            deque.pollLast();
        }
        deque.addLast(i);
    }

    /**
     * 对比之前的解法：优先队列
     * - 时间复杂度 O(nlogn)
     * - 空间复杂度 O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    int[] maxSlidingWindowPriorityQueue(final int[] nums, final int k) {
        final PriorityQueue<ValueIndex> pq = new PriorityQueue<>(k << 1);
        final int n = nums.length;
        final int[] result = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            pq.offer(new ValueIndex(nums[i], i));
            // 懒惰删除(最大值所在index已经超过被滑动窗口越过后再删除)后, 取最值
            if (i >= k - 1) {
                while (pq.peek().index <= i - k) {
                    pq.poll();
                }
                result[i - k + 1] = pq.peek().val;
            }
        }
        return result;
    }

    static class ValueIndex implements Comparable<ValueIndex> {
        private int val;
        private int index;

        ValueIndex(final int val, final int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(ValueIndex o) {
            // 因为我们不可能存储null, 故直接使用val进行比较即可 (由于jdk的PriorityQueue默认是最小堆(C++默认是最大堆), 故compare取相反值)
            return o.val - this.val;
        }
    }
}
