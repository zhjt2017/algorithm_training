package com.speed.week6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 算法实现: 找出数组中第K个最大元素
 * - https://leetcode-cn.com/problems/kth-largest-element-in-an-array/ (215题)
 * <p>
 * - 给定整数数组 nums (可能包含重复元素) 和整数 k，请返回数组中第 k 个最大的元素。
 * - 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * - 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * - 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 1 <= k <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-06 11:43:16
 */
public class FindKthLargestSolution {
    public static void main(String[] args) {
        final FindKthLargestSolution solution = new FindKthLargestSolution();

        int[] nums = new int[]{3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output kth largest num : " + solution.findKthLargest(nums, k));
        System.out.println("Output kth largest num : " + solution.findKthLargestPriorityQueue(nums, k));

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = 4;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output kth largest num : " + solution.findKthLargest(nums, k));
        System.out.println("Output kth largest num : " + solution.findKthLargestPriorityQueue(nums, k));
    }

    private Random random = new Random();

    /**
     * 快速排序 partition思想
     * - 根据《算法导论》9.2：期望为线性的选择算法, 期望时间复杂度 O(N), jdk默认快排 O(N*logN)
     * - 空间复杂度 O(logN)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * 使用优先队列实现
     * - 时间复杂度 O(N*logK)
     * - 空间复杂度 O(K)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestPriorityQueue(int[] nums, int k) {
        int len = nums.length;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(a -> a));
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        // 维护min优先队列, 每次将超出k个的那个最小值删除, 留下最后k个, 接下来优先队列的根元素就是答案 (第k个最大值)
        for (int i = k; i < len; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap.peek();
    }

}
