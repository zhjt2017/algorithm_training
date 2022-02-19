package com.teachingpractice.week6;

import com.speed.week6.JumpGameSolution;

import java.util.Arrays;

/**
 * 算法实现：跳跃游戏 II
 * - https://leetcode-cn.com/problems/jump-game-ii/ (45题)
 * <p>
 * - 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * - 数组中的每个元素代表你在该位置可以跳跃的最大长度。 (从1到该长度都属于可跳范围)
 * - 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * - 假设你总是可以到达数组的最后一个位置。
 * <p>
 * - 输入: nums = [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * <p>
 * - 输入: nums = [2,3,0,1,4]
 * 输出: 2
 * <p>
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 1000
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see JumpGameSolution
 * @since 2022-02-11 11:41:26
 */
public class JumpGameSolutionII {
    public static void main(String[] args) {
        final JumpGameSolutionII solution = new JumpGameSolutionII();

        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min jump steps (while) : " + solution.jump(nums));
        System.out.println("Output min jump steps (for) : " + solution.jumpFor(nums));
        System.out.println();

        nums = new int[]{2, 3, 0, 1, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min jump steps (while) : " + solution.jump(nums));
        System.out.println("Output min jump steps (for) : " + solution.jumpFor(nums));
    }

    /**
     * 决策包容性：同样都是跳1步，从a跳到“能跳得更远”的b，未来的可达集合包含了跳到其他b的可达集合，所以这个局部最优决策是正确的
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param nums
     * @return
     */
    int jump(final int[] nums) {
        final int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int ans = 0;
        int index = 0;
        int right, secondRight;
        while (index < n) {
            right = index + nums[index];
            if (right >= n - 1) {
                return ans + 1;
            }
            ans++;
            int i = index + 1;
            secondRight = right;
            for (; i <= right; i++) {
                if (i + nums[i] >= n - 1) {
                    return ans + 1;
                }
                if (i + nums[i] > secondRight) {
                    secondRight = i + nums[i];
                    index = i;
                }
            }
        }
        return -1;
    }

    /**
     * 代码的写法上，也可以直接使用for循环：原理是一样的，即：
     * - 到达每步的最大值时，累加一步，在到达这个最大值的过程中，计算下一步到达的最大值
     * - 不过这里也可以取一个巧，即从每一个起点开始算作是一步，那么到最后一个起点 (目前的前面一个位置)，算作是最后一步，则不用再判断最后一步是否超过目标位置
     *
     * @param nums
     * @return
     */
    int jumpFor(final int[] nums) {
        final int n = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < n - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
