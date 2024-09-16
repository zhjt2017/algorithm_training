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
 * - 输入: nums = [1,0,-1,0,-2,2], target = 0
 * 输出: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * - 输入: nums = [2,2,2,2,2], target = 8
 * 输出: [[2,2,2,2]]
 * <p>
 * - 输入: nums = [0, 1, 5, 0, 1, 5, 5, -4], target = 11
 * 输出: [[-4, 5, 5, 5], [0, 1, 5, 5]]
 * <p>
 * - 输入: nums = [0,0,0,1000000000,1000000000,1000000000,1000000000], target = 1000000000
 * 输出: [[0,0,0,1000000000]]
 *
 * <p>
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * <p>
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
        System.out.println("Output four nums (another) : " + solution.fourSumAnother(nums, target));
        System.out.println();

        nums = new int[]{2, 2, 2, 2, 2};
        target = 8;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output four nums : " + solution.fourSum(nums, target));
        System.out.println("Output four nums (another) : " + solution.fourSumAnother(nums, target));
        System.out.println();

        nums = new int[]{0, 1, 5, 0, 1, 5, 5, -4};
        target = 11;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output four nums : " + solution.fourSum(nums, target));
        System.out.println("Output four nums (another) : " + solution.fourSumAnother(nums, target));
        System.out.println();

        nums = new int[]{0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000};
        target = 1000000000;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output four nums : " + solution.fourSum(nums, target));
        System.out.println("Output four nums (another) : " + solution.fourSumAnother(nums, target));
    }

    /**
     * 这里我们考虑的顺序是: d, c, b, a
     * - 时间复杂度 O(n^3)
     * - 空间复杂度 O(1) (由于改变了nums, 也可以认为需要一个副本O(N))
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> fourSum(final int[] nums, final int target) {
        final List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        final int n = nums.length;
        // 注意到题意中nums中元素的取值范围, 则使用long计算sum
        long sum, abcTarget;
        final long minValueABC = (long) nums[0] + nums[1] + nums[2];
        final long minValueAB = nums[0] + nums[1];
        for (int d = n - 1; d >= 3; d--) {
            // 去重:
            if (d < n - 1 && nums[d] == nums[d + 1]) {
                continue;
            }
            // 优化d的终止条件: a+b+c取最大值时, sum还是小了, 则d太小了 (并取sum刚好相等时的唯一解)
            sum = (long) nums[d - 3] + nums[d - 2] + nums[d - 1] + nums[d];
            if (sum <= target) {
                addUniqueAns(result, nums, sum, target, d - 3, d - 2, d - 1, d);
                break;
            }
            // 优化d的迭代条件: a+b+c取最小值时, sum还是大了, 则d太大了 (并取sum刚好相等时的唯一解)
            sum = minValueABC + nums[d];
            if (sum >= target) {
                addUniqueAns(result, nums, sum, target, 0, 1, 2, d);
                continue;
            }

            // d固定时, 求三数之和
            abcTarget = target - nums[d];
            for (int c = d - 1; c > 1; c--) {
                // 去重: 对于相同的c, 只取最大的那个, 这样可以给a, b最大的空间, 不遗漏不重复
                if (c < d - 1 && nums[c] == nums[c + 1]) {
                    continue;
                }
                // 优化c的终止条件: a+b取最大值时, sum还是小了, 则c太小了 (并取sum刚好相等时的唯一解)
                sum = (long) nums[c - 2] + nums[c - 1] + nums[c];
                if (sum <= abcTarget) {
                    addUniqueAns(result, nums, sum, abcTarget, c - 2, c - 1, c, d);
                    break;
                }
                // 优化c的迭代条件: a+b取最小值时, sum还是大了, 则c太大了 (并取sum刚好相等时的唯一解)
                sum = minValueAB + nums[c];
                if (sum >= abcTarget) {
                    addUniqueAns(result, nums, sum, abcTarget, 0, 1, c, d);
                    continue;
                }

                // c固定时, 采用双指针求a与b
                doTwoSum(result, nums, abcTarget - nums[c], c, d);
            }
        }
        return result;
    }

    private void addUniqueAns(final List<List<Integer>> result, final int[] nums, final long sum, final long target,
                              final int a, final int b, final int c, final int d) {
        if (sum == target) {
            result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
        }
    }

    /**
     * c与d的取值的变化，已经将a、b的范围进行了极大的压缩，此时如果在计算a与b的两数之和时，双指针夹逼指针逐个移动即可，二分查找来移动意义已经不大了，消耗大于收益
     *
     * @param result
     * @param nums
     * @param target
     * @param c
     * @param d
     */
    private void doTwoSum(final List<List<Integer>> result, final int[] nums, final long target, final int c, final int d) {
        int a = 0;
        int b = c - 1;
        while (a < b) {
            if (nums[a] + nums[b] < target) {
                a++;
                continue;
            }
            if (nums[a] + nums[b] > target) {
                b--;
                continue;
            }
            result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
            // 去重, b固定时, 同样的a只取第一个
            a++;
            while (a < b && nums[a] == nums[a - 1]) {
                a++;
            }
        }
    }

    /**
     * 这里我们考虑的顺序是: a, b, c, d (本身的设计思想与fourSum是一样的)
     * - 时间复杂度 O(n^3)
     * - 空间复杂度 O(1) (由于改变了nums, 也可以认为需要一个副本O(N))
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> fourSumAnother(final int[] nums, final int target) {
        final List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);
        final int n = nums.length;
        // 注意到题意中nums中元素的取值范围, 则使用long计算sum
        long sum, targetBCD, targetCD;
        final long maxSumBCD = (long) nums[n - 1] + nums[n - 2] + nums[n - 3];
        final long maxSumCD = (long) nums[n - 1] + nums[n - 2];
        for (int a = 0; a < n - 3; a++) {
            // 去重: 同样的a只取第一个
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            // 优化a的终止条件: a固定, 取最小的bcd, 仍然>=target, 则终止 (取等于时为当前a对应的唯一解)
            sum = (long) nums[a] + nums[a + 1] + nums[a + 2] + nums[a + 3];
            if (sum >= target) {
                if (sum == target) {
                    result.add(Arrays.asList(nums[a], nums[a + 1], nums[a + 2], nums[a + 3]));
                }
                break;
            }
            // 优化a的迭代条件: a固定, 取最大的bcd, 仍然<=target, 则直接跳到下一个a (取等于时为当前a对应的唯一解)
            sum = (long) nums[a] + maxSumBCD;
            if (sum <= target) {
                if (sum == target) {
                    result.add(Arrays.asList(nums[a], nums[n - 3], nums[n - 2], nums[n - 1]));
                }
                continue;
            }

            targetBCD = (long) target - nums[a];
            for (int b = a + 1; b < n - 2; b++) {
                // 去重: 同样的b只取第一个
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                // 优化b的终止条件: b固定, 取最小的cd, 仍然>=target, 则终止 (取等于时为当前b对应的唯一解)
                sum = (long) nums[b] + nums[b + 1] + nums[b + 2];
                if (sum >= targetBCD) {
                    if (sum == targetBCD) {
                        result.add(Arrays.asList(nums[a], nums[b], nums[b + 1], nums[b + 2]));
                    }
                    break;
                }
                // 优化b的迭代条件: b固定, 取最大的cd, 仍然<=target, 则直接跳到下一个b (取等于时为当前b对应的唯一解)
                sum = (long) nums[b] + maxSumCD;
                if (sum <= targetBCD) {
                    if (sum == targetBCD) {
                        result.add(Arrays.asList(nums[a], nums[b], nums[n - 2], nums[n - 1]));
                    }
                    continue;
                }

                targetCD = targetBCD - nums[b];
                int c = b + 1;
                int d = n - 1;
                int twoSum;
                while (c < d) {
                    twoSum = nums[c] + nums[d];
                    if (twoSum < targetCD) {
                        c++;
                        continue;
                    }
                    if (twoSum > targetCD) {
                        d--;
                        continue;
                    }
                    result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    // 去重: d固定时, 同样的c只取第一个
                    c++;
                    while (c < d && nums[c] == nums[c - 1]) {
                        c++;
                    }
                }
            }

        }
        return result;
    }
}
