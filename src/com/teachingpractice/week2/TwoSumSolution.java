package com.teachingpractice.week2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * - 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数 (假设只有一组答案)，并返回它们的数组下标
 * - 输入：nums = [2,7,11,15], target = 9
 * - 输出：[0,1]
 * - 输入：nums = [3,2,4], target = 6
 * - 输出：[1,2]
 * - 输入：nums = [3,3], target = 6
 * - 输出：[0,1]
 * - 输入：nums = [5,3,3,4], target = 6
 * - 输出：[1,2]
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-06 08:01:28
 */
public class TwoSumSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println("nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("result = " + Arrays.toString(twoSum(nums, target)));

        nums = new int[]{3, 2, 4};
        target = 6;
        System.out.println("nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("result = " + Arrays.toString(twoSum(nums, target)));

        nums = new int[]{3, 3};
        target = 6;
        System.out.println("nums = " + Arrays.toString(nums) + ", target = " + target);
        System.out.println("result = " + Arrays.toString(twoSum(nums, target)));
    }

    /**
     * 设计思想：找准问题本质，对多重循环进行优化，外层选定一个值后，内层求其另一半
     * 也可以理解为，字典上不断丰富的数都是还没有配对成功的，那么外层总会遍历到一个数，该数为字典中的数的另一半
     * (这种必要性也可以理解为如果每一个数，都遍历其右边的，仍然是O(n^2)，所以我们根据确定一个值后，另一半的确定性，通过构建字段将找过一次的直接O(1)搜索)
     * 总体时间复杂度：O(n)
     * 总体空间复杂度：O(n)
     *
     * @param nums   输入方自己保证2个已上的元素
     * @param target
     * @return
     */
    private static int[] twoSum(final int[] nums, final int target) {
        final int length = nums.length;
        if (length == 2) {
            return new int[]{0, 1};
        }

        final Map<Integer, Integer> hash = new HashMap<>();
        int firstValue;
        for (int i = 0; i < length; i++) {
            firstValue = target - nums[i];
            if (hash.containsKey(firstValue)) {
                return new int[]{hash.get(firstValue), i};
            }
            hash.put(nums[i], i);
        }
        throw new RuntimeException("not exists");
    }
}
