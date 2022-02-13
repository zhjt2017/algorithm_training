package com.teachingpractice.week5;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 算法实现: 对数组进行排序
 * - https://leetcode-cn.com/problems/sort-an-array/ (912题)
 * <p>
 * - 给你一个整数数组 nums，请你将该数组升序排列。
 * <p>
 * - 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * <p>
 * - 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * <p>
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-09 04:18:21
 */
public class SortAnArraySolution {
    public static void main(String[] args) {
        final SortAnArraySolution solution = new SortAnArraySolution();

        int[] nums = new int[]{5, 2, 3, 1};
        System.out.println("Input array : " + Arrays.toString(nums));
        System.out.println("Output sorted : " + Arrays.toString(solution.sortArray(nums)));
        System.out.println("Output sorted : " + Arrays.toString(solution.sortArrayHeap(nums)));

        nums = new int[]{5, 1, 1, 2, 0, 0};
        System.out.println("Input array : " + Arrays.toString(nums));
        System.out.println("Output sorted : " + Arrays.toString(solution.sortArray(nums)));
        System.out.println("Output sorted : " + Arrays.toString(solution.sortArrayHeap(nums)));
    }

    /**
     * 这里呢，我们就使用快速排序来实现 (交换排序-快速排序)
     * - 时间复杂度: 期望O(NlogN)
     * - 空间复杂度: 期望O(logN)
     * - leetcode执行: 15ms, 49.5MB
     * - (通过对比堆排序，我们可以看出，当使用良好的pivot时，快速排序确实是速度最快的，也比较稳定)
     *
     * @param nums
     * @return
     */
    int[] sortArray(final int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(final int[] arr, final int left, final int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(arr, left, right);
        quickSort(arr, left, pivot);
        quickSort(arr, pivot + 1, right);
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
     * 我们再使用堆排序来实现
     * - 时间复杂度 O(NlogN)
     * - 空间复杂度 O(N)
     * - leetcode执行: 38ms, 49.6MB
     *
     * @param nums
     * @return
     */
    int[] sortArrayHeap(final int[] nums) {
        final int n = nums.length;
        final PriorityQueue<Integer> minHeap = new PriorityQueue<>(nums.length);
        for (final int value : nums) {
            minHeap.offer(value);
        }
        for (int i = 0; i < n; i++) {
            nums[i] = minHeap.poll();
        }
        return nums;
    }
}
