package com.teachingpractice.weeka;

import java.util.Arrays;

/**
 * 算法实现：树状数组 - 区域和检索(数组可修改)
 * - https://leetcode-cn.com/problems/range-sum-query-mutable/ (307题)
 * <p>
 * 给你一个数组 nums ，请你完成两类查询。
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * <p>
 * - 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 02:34:54
 */
public class RangeSumQueryMutableSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5};
        NumArray numArray = new NumArray(nums);
        System.out.println("NumArray : " + Arrays.toString(nums));
        int result = numArray.sumRange(0, 2);
        System.out.println("sum range result [0, 2] : " + result);
        numArray.update(1, 2);
        System.out.println("update [1, 2] :  success");
        result = numArray.sumRange(0, 2);
        System.out.println("sum range result [0, 2] : " + result);
    }

    static class NumArray {

        public NumArray(final int[] nums) {

        }

        /**
         * 更新 数组 nums 下标对应的值
         * - 时间复杂度 O(1)
         * - 空间复杂度 O(1)
         *
         * @param index
         * @param value
         */
        public void update(final int index, final int value) {

        }

        /**
         * 获取数组 nums 中索引 left 和索引 right 之间的元素和
         *
         * @param left  包含
         * @param right 包含
         * @return
         */
        public int sumRange(final int left, final int right) {
            return 0;
        }
    }
}
