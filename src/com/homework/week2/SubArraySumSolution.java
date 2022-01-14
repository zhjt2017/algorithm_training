package com.homework.week2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 算法实现：和为K的子数组
 * - leetcode-cn 560题
 * - 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 * <p>
 * - 输入：nums = [1,1,1], k = 2
 * - 输出：2
 * <p>
 * - 输入：nums = [1,2,3], k = 3
 * - 输出：2
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 * <p>
 * 设计思想：
 * - int范围 (包括所有前缀和)
 * - 有负数，前缀和非单调，连续子数组的长度也不是固定的
 * - 则双指针滑动窗口不适用，只能前缀和HashMap containsKey判断, value=count计数
 * - 注意到无论k是否为负数，前缀和为pre-k的index一定在前缀和为pre之前，只有这样才能保证中间的子串和为k，
 * - (对于一个index，其前面如果有前缀和为pre-k的，直接可以从Map中get出来，如果其后面才有前缀和为pre-k的，则应该由其后的那个index去统计出来，而一个index其前面有多个前缀和为pre-k的，都算)
 * - (语义理解：每固定一个index，取其前面所有前缀和为pre-k的数量，这就是满足题意的所有遍历，只是使用HashMap对所有要计算和的子元素都收缩起来了，即优化了内存循环)
 * - 则可以在一次遍历中完成计数和containsKey判断，于是前缀和也只需要使用一个int而非数组来维护
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(n)
 * <p>
 * - 当然，凡是题目，也可以了解下当没有算法时，其本身硬算即完全枚举时的算法复杂度 (时间复杂度 O(n^2), 空间复杂度 O(1))
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-09 10:26:40
 */
public class SubArraySumSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("num of sub arrays : " + subArraySum(nums, k));

        nums = new int[]{1, 1, 1};
        k = 3;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("num of sub arrays : " + subArraySum(nums, k));

        nums = new int[]{1, 2, 3};
        k = 3;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("num of sub arrays : " + subArraySum(nums, k));
    }

    private static int subArraySum(final int[] nums, final int k) {
        final Map<Integer, Integer> hash = new HashMap<>(nums.length);
        // 初始边界条件，即保证从最左边开始的子串(哪怕只是一个元素的子串)也是成立的
        hash.put(0, 1);

        int preSum = 0;
        int resultCount = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            resultCount += hash.getOrDefault(preSum - k, 0);
            hash.put(preSum, hash.getOrDefault(preSum, 0) + 1);
        }
        return resultCount;
    }
}
