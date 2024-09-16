package com.dailytraining.month202204;

/**
 * 算法训练(2022-04-04) 区域和检索 - 数组可修改
 * - https://leetcode-cn.com/problems/range-sum-query-mutable/ (307题)
 * <p>
 * 给你一个数组 nums ，请你完成两类查询。
 * <p>
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * <p>
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
 * 调用 pdate 和 sumRange 方法次数不大于 3 * 104
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-04 09:20:41
 */
public class RangeSumQueryMutableSolution {
    public static void main(String[] args) {

    }

    /**
     * 树状数组 + low bit
     */
    static class NumArrayLowBit {
        private int[] nums;
        private int[] sums;
        private int n;

        public NumArrayLowBit(final int[] nums) {
            this.n = nums.length;
            this.sums = new int[n + 1];
            this.nums = nums;
            for (int i = 1; i <= n; i++) {
                int j = i;
                while (j <= n) {
                    sums[j] += nums[i - 1];
                    j += lowBit(j);
                }
            }
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        public void update(final int index, final int val) {
            int x = index + 1;
            while (x <= n) {
                // 减去之前nums[index]的值, 加上新的值
                sums[x] = sums[x] - nums[index] + val;
                x += lowBit(x);
            }
            nums[index] = val;
        }

        public int sumRange(final int left, final int right) {
            return query(right + 1) - query(left);
        }

        private int query(int x) {
            int s = 0;
            while (x != 0) {
                s += sums[x];
                x -= lowBit(x);
            }
            return s;
        }
    }

    /**
     * 树状数组
     * 这是一道很经典的题目，通常还能拓展出一大类问题。
     * <p>
     * 针对不同的题目，我们有不同的方案可以选择（假设我们有一个数组）：
     * <p>
     * 数组不变，求区间和：「前缀和」、「树状数组」、「线段树」
     * 多次修改某个数（单点），求区间和：「树状数组」、「线段树」
     * 多次修改某个区间，输出最终结果：「差分」
     * 多次修改某个区间，求区间和：「线段树」、「树状数组」（看修改区间范围大小）
     * 多次将某个区间变成同一个数，求区间和：「线段树」、「树状数组」（看修改区间范围大小）
     * 这样看来，「线段树」能解决的问题是最多的，那我们是不是无论什么情况都写「线段树」呢？
     * <p>
     * 答案并不是，而且恰好相反，只有在我们遇到第 4 类问题，不得不写「线段树」的时候，我们才考虑线段树。
     * <p>
     * 因为「线段树」代码很长，而且常数很大，实际表现不算很好。我们只有在不得不用的时候才考虑「线段树」。
     * <p>
     * 总结一下，我们应该按这样的优先级进行考虑：
     * <p>
     * 简单求区间和，用「前缀和」
     * 多次将某个区间变成同一个数，用「线段树」
     * 其他情况，用「树状数组」
     * <p>
     * 本题显然属于第 2 类问题：多次修改某个数，求区间和。
     * <p>
     * 我们使用「树状数组」进行求解。
     * <p>
     * 「树状数组」本身是一个很简单的数据结构，但是要搞懂其为什么可以这样「查询」&「更新」还是比较困难的（特别是为什么可以这样更新），往往需要从「二进制分解」进行出发理解。
     * <p>
     * 因此我这里直接提供「树状数组」的代码，大家可以直接当做模板背过即可。
     */
    class NumArray {
        private int[] nums;
        private int[] sums;
        private int n;

        public NumArray(int[] nums) {
            this.n = nums.length;
            this.sums = new int[n + 1];
            this.nums = nums;
            for (int i = 1; i <= n; i++) {
                int j = i;
                while (j <= n) {
                    sums[j] += nums[i - 1];
                    j += lowBit(j);
                }
            }
        }

        public void update(int index, int val) {
            int x = index + 1;
            while (x <= n) {
                // 减去之前nums[index]的值, 加上新的值
                sums[x] = sums[x] - nums[index] + val;
                x += lowBit(x);
            }
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return query(right + 1) - query(left);
        }

        private int lowBit(int x) {
            return x & (-x);
        }

        private int query(int x) {
            int s = 0;
            while (x != 0) {
                s += sums[x];
                x -= lowBit(x);
            }
            return s;
        }
    }
}
