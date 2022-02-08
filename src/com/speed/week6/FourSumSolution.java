package com.speed.week6;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现: 四数之和
 * - https://leetcode-cn.com/problems/4sum/ (18题)
 * - 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * - 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * - 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-07 05:03:12
 */
public class FourSumSolution {
    public static void main(String[] args) {
        final FourSumSolution solution = new FourSumSolution();

        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output four nums : " + solution.fourSum(nums, target));
        System.out.println("Output four nums : " + solution.fourSumInitialSolution(nums, target));

        nums = new int[]{2, 2, 2, 2, 2};
        target = 8;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output four nums : " + solution.fourSum(nums, target));
        System.out.println("Output four nums : " + solution.fourSumInitialSolution(nums, target));
    }

    private List<List<Integer>> fourSum(final int[] nums, final int target) {
        final List<List<Integer>> result = new LinkedList<>();

        return result;
    }

    /**
     * 我们最初的暴力枚举的方案
     * - 时间复杂度 约为 O(N^3)
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> fourSumInitialSolution(final int[] nums, final int target) {
        this.nums = nums;
        this.target = target;
        sum = 0;
        result = new LinkedList<>();

        // 先对nums进行升序排序
        Arrays.sort(nums);

        for (int d = nums.length - 1; d >= MIN_INDEX_D; d--) {
            // d去重
            if (nums[d] == nums[d - 1] && d > MIN_INDEX_D) {
                d--;
            }

            for (int c = d - 1; c >= MIN_INDEX_C; c--) {
                // c去重
                if (nums[c] == nums[c - 1] && c > MIN_INDEX_C) {
                    c--;
                }

                int b = c - 1;
                int a = MIN_INDEX_A;
                while (a < b) {
                    sum = nums[a] + nums[b] + nums[c] + nums[d];
                    if (sum > target) {
                        b--;
                        continue;
                    }
                    if (sum < target) {
                        a++;
                        continue;
                    }
                    result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    // 如果找到了一个答案, 记录答案, 并将a指向右边第一个不同的值
                    while (a + 1 < b && nums[a] == nums[a + 1]) {
                        a++;
                    }
                    a++;
                }
            }
        }

        return result;
    }

    private static final int MIN_INDEX_A = 0;
    private static final int MIN_INDEX_B = 1;
    private static final int MIN_INDEX_C = 2;
    private static final int MIN_INDEX_D = 3;

    private int[] nums;
    private int target;
    private int sum;
    private List<List<Integer>> result;
}
