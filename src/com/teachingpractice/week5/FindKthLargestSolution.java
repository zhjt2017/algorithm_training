package com.teachingpractice.week5;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
        nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println("Output kth largest num (base) : " + solution.findKthLargestBase(nums, k));
        nums = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println("Output kth largest num (heap sort) : " + solution.findKthLargestPriorityQueue(nums, k));

        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = 4;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output kth largest num : " + solution.findKthLargest(nums, k));
        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("Output kth largest num (base) : " + solution.findKthLargestBase(nums, k));
        nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println("Output kth largest num (heap sort) : " + solution.findKthLargestPriorityQueue(nums, k));
    }

    /**
     * 不针对当前题目的场景进行优化，直接使用通用的jdk快速排序
     * - 时间复杂度 期待O(NlogN)
     * - 空间复杂度 期待O(logN)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestBase(final int[] nums, final int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 快速排序 partition思想 (通用的快速排序，取两边 O(NlogN), 当前只要求第k小，并不需要全部排序，每层都只取一边，于是O(N) (<2N))
     * - 要的是最终nums.length - k的位置上的数是几，其他数在不在自己的位置上无所谓，只要该位置上的数刚好位于自己的位置就满足了
     * - 根据《算法导论》9.2：期望为线性的选择算法, 期望时间复杂度 O(N)
     * - 空间复杂度 O(logN)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSort(final int[] arr, final int left, final int right, final int index) {
        if (left >= right) {
            return arr[left];
        }
        int pivot = partition(arr, left, right);
        return index <= pivot ? quickSort(arr, left, pivot, index) : quickSort(arr, pivot + 1, right, index);
    }

    private int partition(final int[] arr, final int head, final int tail) {
        int left = head;
        int right = tail;
        int pivotVal = arr[left + (int) (Math.random() * (right - left + 1))];
        int temp;
        while (left <= right) {
            while (arr[left] < pivotVal) {
                left++;
            }
            while (arr[right] > pivotVal) {
                right--;
            }
            if (left == right) {
                break;
            }
            if (left < right) {
                temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        return right;
    }

    /**
     * 使用优先队列实现
     * - 时间复杂度 O(NlogK)
     * - 空间复杂度 O(K)
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestPriorityQueue(final int[] nums, final int k) {
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
