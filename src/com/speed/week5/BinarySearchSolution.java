package com.speed.week5;

import java.util.Arrays;

/**
 * 算法实现: 二分查找
 * - https://leetcode-cn.com/problems/binary-search/ (704题)
 * - 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1
 * <p>
 * - 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * <p>
 * - 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-25 04:24:47
 */
public class BinarySearchSolution {
    public static void main(String[] args) {
        final BinarySearchSolution solution = new BinarySearchSolution();
        int[] nums = new int[]{-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output result index : " + solution.binarySearch(nums, target));
        System.out.println("Output result index (simple) : " + solution.binarySearchSimple(nums, target));

        nums = new int[]{-1, 0, 3, 5, 9, 12};
        target = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output result index : " + solution.binarySearch(nums, target));
        System.out.println("Output result index (simple) : " + solution.binarySearchSimple(nums, target));
    }

    /**
     * 双指针, 1二分查找 (nums升序)
     * - base case : start index >= end index (找不到的情况) mid value == target
     * - 可以用递归, 也可以不用递归, 这里就不用了, 因为每层只遍历一个元素, 属于线性数据结构主线的范畴
     * - 时间复杂度 O(logN), 空间复杂度 O(1)
     * - 唯一要注意的是, 在压缩范围时, 为保证不重复(浪费性能并可能产生死循环), mid不包含在下一次的范围中, 于是新的一次, 需要重新计算start, end与target的关系
     *
     * @param nums
     * @param target
     * @return
     */
    private int binarySearch(final int[] nums, final int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 进入二分查找前, 先判定掉header, tail作为resetRight=true在第一次二分查找时判定
        if (nums[0] == target) {
            return 0;
        }
        if (nums[0] > target) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        int mid;
        boolean resetRight = true;
        while (start < end) {
            if (resetRight) {
                if (nums[end] == target) {
                    return end;
                }
                if (nums[end] < target) {
                    return -1;
                }
            } else {
                if (nums[start] == target) {
                    return start;
                }
                if (nums[start] > target) {
                    return -1;
                }
            }

            mid = ((end - start) >> 1) + start;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
                resetRight = false;
            } else {
                end = mid - 1;
                resetRight = true;
            }
        }
        return -1;
    }

    /**
     * 当然, 如果我们想到一点, 就是当start==end时, 我们仍然可以计算mid, 那么只要while中多循环一次, 还是可以将所有的可能都包含 (如果index走过了, 只要不相等, 就一定是没有找到)
     * - 相当于是: 我们用多出的一次循环替换了不少的判断, 整体形式更简单
     *
     * @param nums
     * @param target
     * @return
     */
    private int binarySearchSimple(final int[] nums, final int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = ((end - start) >> 1) + start;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
