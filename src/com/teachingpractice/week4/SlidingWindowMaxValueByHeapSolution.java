package com.teachingpractice.week4;

import com.teachingpractice.week1.SlidingWindowMaxValueSolution;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 算法实现: 二叉堆-滑动窗口求最值问题 (这里不妨求最大值)
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
 * @see SlidingWindowMaxValueSolution
 * @since 2022-01-31 12:17:31
 */
public class SlidingWindowMaxValueByHeapSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValuesByBinaryHeap(nums, k))));

        nums = new int[]{1};
        k = 1;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValuesByBinaryHeap(nums, k))));

        nums = new int[]{1, -1};
        k = 1;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValuesByBinaryHeap(nums, k))));

        nums = new int[]{9, 11};
        k = 2;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValuesByBinaryHeap(nums, k))));

        nums = new int[]{4, -2};
        k = 2;
        System.out.println(String.format("nums = %s, k = %d", Arrays.toString(nums), k));
        System.out.println(String.format("result = %s", Arrays.toString(slidingWindowMaxValuesByBinaryHeap(nums, k))));
    }

    /**
     * 维护最大值, 天生的数据结构: 最大二叉堆
     * - 优化: 懒惰删除法 (按理说每滑动一下, 都要insert + delete, 然而不是堆顶的元素, 删了对答案也不错产生影响, 那么我们将所有的delete操作延迟到堆顶时再删)
     *
     * @param nums
     * @param k
     * @return
     */
    private static int[] slidingWindowMaxValuesByBinaryHeap(final int[] nums, final int k) {
        final PriorityQueue<ValueIndex> pq = new PriorityQueue<>();
        final int n = nums.length;
        final int[] result = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            pq.offer(new ValueIndex(nums[i], i));
            // 懒惰删除后, 取最值
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
