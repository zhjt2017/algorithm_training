package com.teachingpractice.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 算法实现：全排列I
 * - 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * - 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * - 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * - 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-16 10:39:52
 */
public class PermutationSolution {

    public static void main(String[] args) {
        final PermutationSolution solution = new PermutationSolution();

        int[] nums = new int[]{1, 2, 3};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permute(nums));

        nums = new int[]{0, 1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permute(nums));

        nums = new int[]{1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permute(nums));

        nums = new int[]{1, 1, 2};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permute(nums));

        nums = new int[]{1, 1, 2, 2};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permute(nums));

        nums = new int[]{1, 1, 1, 2};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("output permutations : " + solution.permute(nums));
    }

    /**
     * 回溯算法：一条路往前走，能进则进，不能进则退回来，换一条路再试
     * - 递归实现，层次n
     * - 第一层可以枚举1-n, 第二层开始剩下的, 缩小问题规模, 直到base case, 只剩一个元素
     * - 时间复杂度n*n! (递归调用排列出结果数组，n!，每个结果数组变成List添加到结果集中，n，故n*n!)
     * - 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    private List<List<Integer>> permute(final int[] nums) {
        this.result = new ArrayList<>();
        this.nums = nums;
        backtrack(0);
        return result;
    }

    private void backtrack(final int first) {
        // base case : 所有数都填完了
        if (first == nums.length) {
            result.add(buildList(nums));
        }
        // 这里其实是一处细节的优化, 让下一次遍历从first+1位置上开始(这需要将未填的数都在first+1及之后位置, 于是就将每次填的值赋予到first位置上, first位置上的未填的值互换位置)
        // 如果不做优化, 那么每次都是for 0->n-1, 使用长度为n的boolean数组标识该index上的数是否已经选过了, false就跳过, true, 递归调用后, 还原现场, 改回false
        // Collections.swap(list, i, j)可以对List直接实现位置替换
        for (int i = first; i < nums.length; i++) {
            swap(first, i);
            // 继续递归填下一个数 (填好了first, 再填first后面的位置)
            backtrack(first + 1);
            // 还原现场
            swap(first, i);
        }
    }

    private List<Integer> buildList(final int[] data) {
        final List<Integer> list = new ArrayList<>();
        for (final int value : data) {
            list.add(value);
        }
        return list;
    }

    private void swap(final int i, final int j) {
        tmp = this.nums[i];
        this.nums[i] = this.nums[j];
        this.nums[j] = tmp;
    }

    private List<List<Integer>> result;
    private int[] nums;
    private int tmp;
}
