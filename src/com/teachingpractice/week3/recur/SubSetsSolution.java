package com.teachingpractice.week3.recur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 算法实现：递归-子集
 * <p>
 * - 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * - 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 * <p>
 * - 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * <p>
 * - 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 * <p>
 * 设计思想：数组中的每个元素都是选与不选，共2^n种情况，没有排列，这跟多次抛硬币看正反求概率，是一类问题
 * - 每一层都有选与不选2种情形，就是一个满二叉树 (这里的找出所有子集就是一个dfs的过程)
 * <p>
 * 时间复杂度：O(n*(2^n)) (每个分支走完，会新创建出一个结果子集，其过程时间复杂度n)
 * 空间复杂度：O(n) (方法栈的压栈深度：会一个一个分支走，每个分支走完后，会释放该分支的压栈空间)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-17 02:57:28
 */
public class SubSetsSolution {
    public static void main(String[] args) {
        SubSetsSolution solution = new SubSetsSolution(new int[]{1, 2, 3});
        System.out.println("nums : " + Arrays.toString(solution.nums));
        System.out.println("sub sets : " + solution.subsets());

        solution = new SubSetsSolution(new int[]{0});
        System.out.println("nums : " + Arrays.toString(solution.nums));
        System.out.println("sub sets : " + solution.subsets());
    }

    private List<List<Integer>> subsets() {
        this.recur(0);
        return this.result;
    }

    private void recur(final int index) {
        if (index == this.nums.length) {
            this.result.add(new ArrayList<>(this.currentSet));
            return;
        }
        this.recur(index + 1);
        this.currentSet.add(this.nums[index]);
        this.recur(index + 1);
        // 还原现场
        this.currentSet.remove(this.currentSet.size() - 1);
    }

    private int[] nums;
    private List<Integer> currentSet;
    private List<List<Integer>> result;

    private SubSetsSolution(final int[] nums) {
        this.nums = nums;
        this.currentSet = new ArrayList<>(nums.length);
        this.result = new ArrayList<>(1 << nums.length);
    }
}
