package com.teachingpractice.week5;

import com.speed.week5.BinarySearchNotUniqueSolution;

import java.util.Arrays;

/**
 * 算法实现: 二分查找 (有序数组中包含重复元素的情形) - 找到target所在元素index的范围 (以第一个位置, 最后一个位置组成的int数组返回)
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * 输入：nums = [2,2], target = 2
 * 输出：[0,1]
 * <p>
 * 输入：nums = [1,2,3], target = 2
 * 输出：[1,1]
 * <p>
 * 输入: [0,0,1,2,3,3,4], target = 2
 * 输出: [3,3]
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see BinarySearchNotUniqueSolution 同一个题, 这里对解法进行总结与推荐
 * @since 2022-02-06 12:04:44
 */
public class BinarySearchFindElementRangeSolution {
    public static void main(String[] args) {
        final BinarySearchFindElementRangeSolution solution = new BinarySearchFindElementRangeSolution();

        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output findRange left and right indexes : " + Arrays.toString(solution.findRange(nums, target)));
        System.out.println("Output findRangeAnother left and right indexes : " + Arrays.toString(solution.findRangeAnother(nums, target)));

        target = 6;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output findRange left and right indexes : " + Arrays.toString(solution.findRange(nums, target)));
        System.out.println("Output findRangeAnother left and right indexes : " + Arrays.toString(solution.findRangeAnother(nums, target)));

        nums = new int[]{};
        target = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output findRange left and right indexes : " + Arrays.toString(solution.findRange(nums, target)));
        System.out.println("Output findRangeAnother left and right indexes : " + Arrays.toString(solution.findRangeAnother(nums, target)));

        nums = new int[]{3};
        target = 3;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output findRange left and right indexes : " + Arrays.toString(solution.findRange(nums, target)));
        System.out.println("Output findRangeAnother left and right indexes : " + Arrays.toString(solution.findRangeAnother(nums, target)));

        nums = new int[]{2, 2};
        target = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output findRange left and right indexes : " + Arrays.toString(solution.findRange(nums, target)));
        System.out.println("Output findRangeAnother left and right indexes : " + Arrays.toString(solution.findRangeAnother(nums, target)));

        nums = new int[]{1, 2, 3};
        target = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output findRange left and right indexes : " + Arrays.toString(solution.findRange(nums, target)));
        System.out.println("Output findRangeAnother left and right indexes : " + Arrays.toString(solution.findRangeAnother(nums, target)));

        nums = new int[]{0, 0, 1, 2, 3, 3, 4};
        target = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output findRange left and right indexes : " + Arrays.toString(solution.findRange(nums, target)));
        System.out.println("Output findRangeAnother left and right indexes : " + Arrays.toString(solution.findRangeAnother(nums, target)));
    }

    int[] findRange(final int[] nums, final int target) {
        final int n = nums.length;
        // 开始位置: 第一个值>=target的位置 (无解, 返回n)
        int right = n;
        int left = 0;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        final int startPosition = right;

        // 结束位置: 最后一个值<=target的位置 (无解, 返回-1)
        right = n - 1;
        left = -1;
        while (left < right) {
            mid = ((right - left - 1) >> 1) + left + 1;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        final int endPosition = left;
        // 当求解的开始位置与结束位置有交集时, 交集位置上的数刚好为target
        return startPosition <= endPosition ? new int[]{startPosition, endPosition} : new int[]{-1, -1};
    }

    /**
     * 一个小点的想法: 在求出开始位置的基础上, 再求结束位置
     *
     * @param nums
     * @param target
     * @return
     */
    int[] findRangeAnother(final int[] nums, final int target) {
        final int n = nums.length;
        // 开始位置: 第一个值>=target的位置 (无解, 返回n)
        int right = n;
        int left = 0;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        final int startPosition = right;
        if (startPosition == n || nums[startPosition] > target) {
            return new int[]{-1, -1};
        }

        // 结束位置: 最后一个值<=target的位置 (在开始位置有解的基础上来找结束位置, 必然有解)
        left = startPosition;
        right = n - 1;
        while (left < right) {
            mid = ((right - left - 1) >> 1) + left + 1;
            if (nums[mid] == target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{startPosition, left};
    }
}
