package com.homework.week3;

import com.teachingpractice.week3.PermutationSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 算法实现：递归-全排列II
 * - 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * - 输入：nums = [1,1,2]
 * 输出：[[1,1,2],[1,2,1],[2,1,1]]
 * <p>
 * - 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-16 10:39:52
 */
public class PermutationAdvancedSolution {
    public static void main(String[] args) {
        final PermutationAdvancedSolution solution = new PermutationAdvancedSolution();

        int[] nums = new int[]{1, 1, 2};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));

        nums = new int[]{1, 2, 3};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));

        nums = new int[]{1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));

        nums = new int[]{1, 2, 2, 3};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));

        nums = new int[]{1, 1, 2, 2};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));

        nums = new int[]{1, 1, 1, 2};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));

        nums = new int[]{0, 1, 0, 0, 9};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));

        nums = new int[]{0, 1, 0, 0, 0};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permuteUnique(nums));
    }

    /**
     * 设计思想：主要实现还是参考
     *
     * @param nums
     * @return
     * @see PermutationSolution
     * - 特别的地方：需要去重，我们先将原数组进行升序排序，保证重复的数字都是相邻的，然后我们在枚举时，只取重复数字中的第一个，其他的直接跳过
     * - 复杂度不变：时间复杂度O(n*n!)，空间复杂度O(n)
     */
    private List<List<Integer>> permuteUnique(final int[] nums) {
        this.result = new ArrayList<>();
        Arrays.sort(nums);
        this.nums = nums;
        backtrack(0);
        return result;
    }

    private void backtrack(final int first) {
        // base case : 所有数都填完了
        if (first == nums.length) {
            result.add(buildList(nums));
        }
        /*
        对于[1,1,2]，如果不去重(一样的共2份(阶乘), 本身共3种情况)，[[1, 1, 2], [1, 2, 1], [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 1, 1]]，去掉3条重复的
        对于[1, 1, 2, 2]，如果不去重(一样的共4份(阶乘1*阶乘2),, 本身共6种情况)，[[1, 1, 2, 2], [1, 1, 2, 2], [1, 2, 1, 2], [1, 2, 2, 1], [1, 2, 2, 1], [1, 2, 1, 2], [1, 1, 2, 2], [1, 1, 2, 2],
        [1, 2, 1, 2], [1, 2, 2, 1], [1, 2, 2, 1], [1, 2, 1, 2], [2, 1, 1, 2], [2, 1, 2, 1], [2, 1, 1, 2], [2, 1, 2, 1],
        [2, 2, 1, 1], [2, 2, 1, 1], [2, 1, 2, 1], [2, 1, 1, 2], [2, 2, 1, 1], [2, 2, 1, 1], [2, 1, 2, 1], [2, 1, 1, 2]]
        对于[1, 1, 1, 2]，如果不重复(一样的共6份(阶乘),, 本身共4种情况)，[[1, 1, 1, 2], [1, 1, 2, 1], [1, 1, 1, 2], [1, 1, 2, 1], [1, 2, 1, 1], [1, 2, 1, 1], [1, 1, 1, 2], [1, 1, 2, 1],
         [1, 1, 1, 2], [1, 1, 2, 1], [1, 2, 1, 1], [1, 2, 1, 1], [1, 1, 1, 2], [1, 1, 2, 1], [1, 1, 1, 2], [1, 1, 2, 1],
         [1, 2, 1, 1], [1, 2, 1, 1], [2, 1, 1, 1], [2, 1, 1, 1], [2, 1, 1, 1], [2, 1, 1, 1], [2, 1, 1, 1], [2, 1, 1, 1]]
         总结：在每一层，遇到重复的，只要该层不处理即可
         (注意，由于我们替换了位置，就不是只取前面一个进行比较了，只取前面一个比较当交换后的是重复数字时会导致判断不出来，于是我们需要在每一层都维护一个usedValues的Set)
         (这样，空间复杂度就高了，于是swap元素所优化的空间就荡然无存了，放弃swap)
         (懒人机器做法：维护一个全局的currentAnswer，作为当前分支下的一种结果，而nums本身作为原数据使用保持全量不变，每层都遍历nums，
         从前往后，当从nums中取出index位置上的数填写一个位置时，该index位置的used状态变更为true，则剪枝成功，同一层，nums[i]==nums[i-1]删选掉重复的)
         (模拟人工处理时的做法：nums中取出一个插入到first位置上，则剩下的部分，顺序保持不变，则可以在同一层使用nums[i]==nums[i-1]删选掉重复的，
         由于需要移位，则与懒人机器做法相比，时间复杂度完全相同，只是空间复杂度微小优化)
         总体：时间复杂度：n*n!，空间复杂度：O(n)
         */
        for (int i = first; i < nums.length; i++) {
            if (i > first && nums[i] == nums[i - 1]) {
                continue;
            }
            // 选当前的数，并移位
            tmp = nums[i];
            for (int j = i; j > first; j--) {
                nums[j] = nums[j - 1];
            }
            nums[first] = tmp;
            // 继续递归填下一个数 (填好了first, 再填first后面的位置)
            backtrack(first + 1);
            // 还原现场
            tmp = nums[first];
            for (int j = first; j < i; j++) {
                nums[j] = nums[j + 1];
            }
            nums[i] = tmp;
        }
    }

    private List<Integer> buildList(final int[] data) {
        final List<Integer> list = new ArrayList<>();
        for (final int value : data) {
            list.add(value);
        }
        return list;
    }

    private List<List<Integer>> result;
    private int[] nums;
    private int tmp;
}
