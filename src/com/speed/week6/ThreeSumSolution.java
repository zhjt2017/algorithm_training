package com.speed.week6;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现: 三数之和
 * - https://leetcode-cn.com/problems/3sum/ (15题)
 * <p>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = target ？请你找出所有和为 target 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * - 输入：nums = [-1,0,1,2,-1,-4], target = 0
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * - 输入：nums = [], target = 0
 * 输出：[]
 * <p>
 * - 输入：nums = [0], target = 0
 * 输出：[]
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-08 12:24:20
 */
public class ThreeSumSolution {
    public static void main(String[] args) {
        final ThreeSumSolution solution = new ThreeSumSolution();

        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        int target = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output three nums : " + solution.threeSum(nums, target));

        nums = new int[]{};
        target = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output three nums : " + solution.threeSum(nums, target));

        nums = new int[]{0};
        target = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output three nums : " + solution.threeSum(nums, target));
    }

    private List<List<Integer>> threeSum(final int[] nums, final int target) {
        Arrays.sort(nums);

        final List<List<Integer>> result = new LinkedList<>();

        final int n = nums.length;
        int a, b, sum;
        for (int c = n - 1; c > 1; c--) {
            // 去重: 对于相同的c, 只取最大的那个, 这样可以给a与b最大的空间, 不遗漏不重复
            if (c < n - 1 && nums[c] == nums[c + 1]) {
                continue;
            }
            // 优化c的终止条件: 当c固定时, a与b取最大值时, sum仍然 < target, 则c及其更小的c都无效
            if (nums[c] + nums[c - 1] + nums[c - 2] < target) {
                break;
            }

            // c固定时, 采用双指针求a与b
            a = 0;
            b = c - 1;
            while (a < b) {
                sum = nums[a] + nums[b] + nums[c];
                if (sum < target) {
                    a++;
                    continue;
                }
                if (sum > target) {
                    b--;
                    continue;
                }
                result.add(Arrays.asList(nums[a], nums[b], nums[c]));
                // b固定时, 同样的a只能取一次, 那么就必须找到a的下一个不同值 (或者直到a=b也找不到, 那么就终止)
                while (a + 1 < b && nums[a] == nums[a + 1]) {
                    a++;
                }
                a++;
            }
        }
        return result;
    }
}
