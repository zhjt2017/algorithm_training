package com.speed.week5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 算法实现: 和为k的子数组
 * <p>
 * - 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * <p>
 * - 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * <p>
 * - 输入:nums = [1,2,3], k = 3
 * 输出: 2
 * <p>
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-31 05:09:30
 */
public class SubArraySumSolution {
    public static void main(String[] args) {
        final SubArraySumSolution solution = new SubArraySumSolution();
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output sub array total count : " + solution.subArraySum(nums, k));

        nums = new int[]{1, 2, 3};
        k = 3;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output sub array total count : " + solution.subArraySum(nums, k));
    }

    /**
     * 设计思想-核心点
     * 1. 前缀和preSum, preSum-k为一个符合条件的sub array
     * 2. 无论nums中是否有负数, k是否为负数, preSum-k的index可能在preSum前面, 也可能在preSum后面,
     * - 但是根据题意, 只有index在前面的preSum-k, 其count才能用于计算sub array, 故只需要遍历一次即可
     * <p>
     * 细节注意点
     * 1. preSumHash初始化时需要写入前缀和0的1个, 以支持从头开始就满足条件的sub array
     * <p>
     * 时间复杂度: O(N)
     * 空间复杂度: O(N)
     *
     * @param nums
     * @param k
     * @return
     */
    private int subArraySum(final int[] nums, final int k) {
        final Map<Integer, Integer> preSumHash = new HashMap<>(nums.length);
        preSumHash.put(0, 1);

        int preSum = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum = preSum + nums[i];
            result = result + preSumHash.getOrDefault(preSum - k, 0);
            preSumHash.put(preSum, preSumHash.getOrDefault(preSum, 0) + 1);
        }
        return result;
    }
}
