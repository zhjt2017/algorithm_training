package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法训练(2022-03-09) 得分最高的最小轮调
 * - https://leetcode-cn.com/problems/smallest-rotation-with-highest-score/ (798题)
 * <p>
 * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，
 * 这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。
 * 此后，任何值小于或等于其索引的项都可以记作一分。
 * <p>
 * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。
 * 这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
 * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
 * <p>
 * - 输入：nums = [2,3,1,4,0]
 * 输出：3
 * 解释：
 * 下面列出了每个 k 的得分：
 * k = 0,  nums = [2,3,1,4,0],    score 2
 * k = 1,  nums = [3,1,4,0,2],    score 3
 * k = 2,  nums = [1,4,0,2,3],    score 3
 * k = 3,  nums = [4,0,2,3,1],    score 4
 * k = 4,  nums = [0,2,3,1,4],    score 3
 * 所以我们应当选择 k = 3，得分最高。
 * <p>
 * - 输入：nums = [1,3,0,2,4]
 * 输出：0
 * 解释：
 * nums 无论怎么变化总是有 3 分。
 * 所以我们将选择最小的 k，即 0。
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] < nums.length
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-09 10:40:13
 */
public class SmallestRotationWithHighestScoreSolution {
    public static void main(String[] args) {
        final SmallestRotationWithHighestScoreSolution solution = new SmallestRotationWithHighestScoreSolution();

        int[] nums = new int[]{2, 3, 1, 4, 0};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) : " + solution.bestRotation(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (move) : " + solution.bestRotationMove(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (dp) : " + solution.bestRotationDp(nums));
        System.out.println();

        nums = new int[]{1, 3, 0, 2, 4};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) : " + solution.bestRotation(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (move) : " + solution.bestRotationMove(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (dp) : " + solution.bestRotationDp(nums));
        System.out.println();

        nums = new int[]{2, 4, 1, 3, 0};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) : " + solution.bestRotation(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (move) : " + solution.bestRotationMove(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (dp) : " + solution.bestRotationDp(nums));
        System.out.println();

        nums = new int[]{6, 2, 8, 3, 5, 2, 4, 3, 7};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) : " + solution.bestRotation(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (move) : " + solution.bestRotationMove(nums));
        System.out.println("Output best rotation (smallest rotation with highest score) (dp) : " + solution.bestRotationDp(nums));
        System.out.println();
    }

    /**
     * 1 - 直接全部模拟，时间复杂度O(n*n) (共n种可能的轮调，每次轮调都需要重新计算所有的n个位置，空间复杂度(1))
     * 2 - 我们希望空间换时间，从而实现：时间复杂度O(n)，空间复杂度O(n)
     * 仔细想想，其实如果nums[i]与i的范围没有任何关系时，我们拿nums[i]与i来进行比对大小，并没有什么实际的意义，这样情况下也无法优化为O(n)的时间复杂度
     * <p>
     * 本题：0 <= nums[i] < nums.length
     * 对于任意一个index=i位置上的值nums[i]，当其index<=nums[i]-1时，就无法得分
     * <p>
     * 我们依旧优先使用动态规划来实现
     * - 这个是从网上摘录的题解，不是特别好理解
     * - 第一次循环，让每一个nums[i]从i位置移到nums[i]-1 (刚刚好不满足得分条件的K,对应的是score数组的下标)位置，得分减1 (如果i-nums[i]相同的，则该k对应的得分再减一份1)
     * - 第二次循环，score[i] += score[i - 1] + 1 (从i-1到i必然有一个数字从num[i]-1到了nums[i]等分加个1) - 还是没有特别理解，
     * <p>
     * 所以我的建议是使用bestRotationMove - 差分思想
     * 以及bestRotationDp - 动态规划
     * <p>
     * 在第一个循环中，score[i]在步骤i计算总损失点。
     * 在第二个循环中，score[i]通过添加从score[i - 1]更新总失分和得分。
     *
     * @param nums
     * @return
     */
    int bestRotation(final int[] nums) {
        int n = nums.length;
        int[] score = new int[n];
        for (int i = 0; i < n; i++) {
            score[(i - nums[i] + 1 + n) % n] -= 1;
        }

        int maxIdx = 0;
        for (int i = 1; i < n; i++) {
            score[i] += score[i - 1] + 1;
            maxIdx = score[i] > score[maxIdx] ? i : maxIdx;
        }
        return maxIdx;
    }

    /**
     * 差分思想：应用于左移move次得分产生变化的边界，则边界内的move元素同样进行变化 (move下标为move的步数，value为该move下的总的得分变化值)
     * - 这个比bestRotationDiffs更好理解，性能也优化了些
     * <p>
     * 差分 - 递推：D(0) = A(0), D(i) = A(i) - A(i-1), D的前缀和就是原数组A, A(i) = A(i-1) + D(i)
     * 差分 - 边界：作为得分起始点的++，作为不得分起始点的--
     *
     * @param nums
     * @return
     */
    int bestRotationMove(final int[] nums) {
        final int n = nums.length;
        final int[] move = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] <= i) {
                // 不移动时, nums[i]就可以得分 (不能去，否则案例[6, 2, 8, 3, 5, 2, 4, 3, 7]过不了) (对于nums[i]=i的情况为差分边界)
                move[0]++;
                // 左移不得分的右边界
                move[(i - nums[i] + 1) % n]--;
                // 左移突破0的位置移动到最右边得分(即左移不得分的左边界)
                move[(i + 1) % n]++;
            } else {
                // 左移突破0的位置移动到最右边得分(即左移不得分的左边界)
                move[(i + 1) % n]++;
                // 不得分的右边界
                move[(n - nums[i] + i + 1) % n]--;
            }
        }

        int score = 0;
        int maxScore = 0;
        int k = 0;
        for (int i = 0; i < n; i++) {
            score += move[i];
            if (score > maxScore) {
                maxScore = score;
                k = i;
            }
        }

        System.out.println("max score = " + maxScore + ", k = " + k);

        return k;
    }

    /**
     * [leetcode官方]差分数组做法的正确性证明需要考虑 low 和 high 的不同情况。
     * <p>
     * 如果 low≤high−1<n−1，则有 low<high<n，更新 diffs 等价于将数组 points 的下标范围 [low,high−1] 内的所有元素加 1。
     * <p>
     * 如果 low≤high+n−1=n−1，则有 0=high≤low，更新 diffs 等价于将数组 points 的下标范围 [low,n−1] 内的所有元素加 1，diffs[0] 先减 1 后加 1 因此 diffs[0] 没有变化，同第 1 种情况。
     * <p>
     * 如果 low≥high>0，则需要将 diffs[0] 加 1，更新 diffs 等价于将数组 points 的下标范围 [low,n−1] 和 [0,high−1] 内的所有元素加 1。
     *
     * @param nums
     * @return
     */
    int bestRotationDiffs(final int[] nums) {
        int n = nums.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n + 1) % n;
            diffs[low]++;
            diffs[high]--;
            if (low >= high) {
                diffs[0]++;
            }
        }
        int bestIndex = 0;
        int maxScore = 0;
        int score = 0;
        for (int i = 0; i < n; i++) {
            score += diffs[i];
            if (score > maxScore) {
                bestIndex = i;
                maxScore = score;
            }
        }
        return bestIndex;
    }

    /**
     * 动态规划(第2次循环)+ 预处理(第1次循环)
     *
     * @param nums
     * @return
     */
    int bestRotationDp(final int[] nums) {
        final int n = nums.length;
        // 定义数组：一个经过 (下标) 次轮换后，nums[i] = index (value为统计个数)
        int[] moveCurVal2ValIdxStep = new int[n];
        // 0 <= nums[i] < nums.length
        for (int i = 0; i < n; i++) {
            if (nums[i] > i) {
                moveCurVal2ValIdxStep[i + n - nums[i]]++;
            } else {
                moveCurVal2ValIdxStep[i - nums[i]]++;
            }
        }

        int k = 0;
        int maxDeltaScore = 0;
        int deltaScore = 0;
        for (int i = 1; i < n; i++) {
            // 状态转换方程 dp[i] = dp[i-1] + (k-1次轮换时此时nums[i]=i的个数) + 1
            // (满足nums[i - 1] = i - 1的，必然不满足nums[i - 1] = i) (加1是因为index=0位置上的数被移走后，必定+1)
            deltaScore = deltaScore - moveCurVal2ValIdxStep[i - 1] + 1;
            // 通过滚动变量val 找到最大值记录当前的转换次数k 得到转换 k 次能得到最大的分数
            if (deltaScore > maxDeltaScore) {
                maxDeltaScore = deltaScore;
                k = i;
            }
        }
        return k;
    }
}
