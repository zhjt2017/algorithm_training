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
        final int minLength = 4;
        if (nums == null || nums.length < minLength) {
            return result;
        }

        Arrays.sort(nums);
        System.out.println("Sorted nums : " + Arrays.toString(nums));

        // 本题需要考虑一个问题: nums[i]的取值范围, int范围最多容纳2个数的和 (3个数及以上就有可能越界), 而target也在这个范围, 故sum得使用long来进行计算

        final int n = nums.length;
        final long minValueABC = (long) nums[0] + nums[1] + nums[2];
        final long minValueAB = nums[0] + nums[1];
        int a, b, c;
        long sum, abcTarget, abTarget;
        for (int d = n - 1; d >= minLength - 1; d--) {
            // 去重: 对于相同的d, 只取最大的那个, 这样可以给a, b, c最大的空间, 不遗漏不重复
            if (d < n - 1 && nums[d] == nums[d + 1]) {
                continue;
            }
            // 优化d的终止条件: 当d固定时, a+b+c取最大值时, sum仍然 < target, 则d及其更小的d都无效 (如果刚好找到, 也是该d下的唯一解)
            sum = (long) nums[d - 3] + nums[d - 2] + nums[d - 1] + nums[d];
            if (sum <= target) {
                if (sum == target) {
                    result.add(Arrays.asList(nums[d - 3], nums[d - 2], nums[d - 1], nums[d]));
                }
                break;
            }
            // 优化d的迭代条件: 当d固定时, a+b+c取最小值时, sum仍然 > target, 则当前d不用再进行内部迭代 (如果刚好找到, 也是该d下的唯一解)
            sum = minValueABC + nums[d];
            if (sum >= target) {
                if (sum == target) {
                    result.add(Arrays.asList(nums[0], nums[1], nums[2], nums[d]));
                }
                continue;
            }

            // d固定时, 求三数之和
            abcTarget = target - nums[d];
            for (c = d - 1; c > 1; c--) {
                // 去重: 对于相同的c, 只取最大的那个, 这样可以给a, b最大的空间, 不遗漏不重复
                if (c < d - 1 && nums[c] == nums[c + 1]) {
                    continue;
                }
                // 优化c的终止条件: 当c固定时, a+b取最大值时, sum仍然 < target, 则c及其更小的c都无效 (如果刚好找到, 也是该c下的唯一解)
                sum = (long) nums[c - 2] + nums[c - 1] + nums[c];
                if (sum <= abcTarget) {
                    if (sum == abcTarget) {
                        result.add(Arrays.asList(nums[c - 2], nums[c - 1], nums[c], nums[d]));
                    }
                    break;
                }
                // 优化c的迭代条件: 当c固定时, a+b取最小值时, sum仍然 > target, 则当前c不用再进行内部迭代 (如果刚好找到, 也是该c下的唯一解)
                sum = minValueAB + nums[c];
                if (sum >= abcTarget) {
                    if (sum == abcTarget) {
                        result.add(Arrays.asList(nums[0], nums[1], nums[c], nums[d]));
                    }
                    continue;
                }

                // c固定时, 采用双指针求a与b
                abTarget = abcTarget - nums[c];
                a = 0;
                b = c - 1;
                while (a < b) {
                    sum = (long) nums[a] + nums[b];
                    if (sum < abTarget) {
                        a++;
                        continue;
                    }
                    if (sum > abTarget) {
                        b--;
                        continue;
                    }
                    result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                    // b固定时, 同样的a只能取一次, 那么就必须找到a的下一个不同值 (或者直到a=b也找不到, 那么就终止)
                    while (a + 1 < b && nums[a] == nums[a + 1]) {
                        a++;
                    }
                    a++;
                }
            }
        }
        return result;
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
