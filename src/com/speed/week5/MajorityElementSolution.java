package com.speed.week5;

import java.util.Arrays;

/**
 * 算法实现: 多数元素
 * - 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * - 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * - 输入：[3,2,3]
 * 输出：3
 * <p>
 * - 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 设计思想:
 * 1. 排序后直接取后面位置上的, 但是时间上重复计算很多
 * 2. HashMap统计每个不同元素的计数, 提取出count>n/2的
 * 3. 由于一定存在一个元素其计数>n/2的, 即大于其他所有元素的计数总和 故我们可以优化为使用摩尔投票法, 从而将空间复杂度优化为O(1), 时间复杂度没有提高 (甚至特殊情形下略有降低, 因为HashMap可能提前返回)
 * -- 时间复杂度 O(n) 空间复杂度 O(1)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-01 11:20:20
 */
public class MajorityElementSolution {
    public static void main(String[] args) {
        final MajorityElementSolution solution = new MajorityElementSolution();

        int[] nums = new int[]{3, 2, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output majority element : " + solution.majorityElement(nums));

        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output majority element : " + solution.majorityElement(nums));
    }

    /**
     * 摩尔投票法
     *
     * @param nums
     * @return
     */
    private int majorityElement(final int[] nums) {
        int count = 1;
        int candidate = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
                continue;
            }
            if (count == 1) {
                candidate = nums[i];
                continue;
            }
            count--;
        }
        return candidate;
    }
}
