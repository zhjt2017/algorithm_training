package com.dailytraining.month202203;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法训练(2022-03-06) 适合打劫银行的日子
 * - https://leetcode-cn.com/problems/find-good-days-to-rob-the-bank/ (2100题)
 * <p>
 * 你和一群强盗准备打劫银行。给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。日子从 0 开始编号。同时给你一个整数 time 。
 * <p>
 * 如果第 i 天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * <p>
 * 第 i 天前和后都分别至少有 time 天。
 * 第 i 天前连续 time 天警卫数目都是非递增的。
 * 第 i 天后连续 time 天警卫数目都是非递减的。
 * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * <p>
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0 开始）。返回的日子可以 任意 顺序排列。
 * <p>
 * - 输入：security = [5,3,3,3,5,6,2], time = 2
 * 输出：[2,3]
 * 解释：
 * 第 2 天，我们有 security[0] >= security[1] >= security[2] <= security[3] <= security[4] 。
 * 第 3 天，我们有 security[1] >= security[2] >= security[3] <= security[4] <= security[5] 。
 * 没有其他日子符合这个条件，所以日子 2 和 3 是适合打劫银行的日子。
 * <p>
 * - 输入：security = [1,1,1,1,1], time = 0
 * 输出：[0,1,2,3,4]
 * 解释：
 * 因为 time 等于 0 ，所以每一天都是适合打劫银行的日子，所以返回每一天。
 * <p>
 * - 输入：security = [1,2,3,4,5,6], time = 2
 * 输出：[]
 * 解释：
 * 没有任何一天的前 2 天警卫数目是非递增的。
 * 所以没有适合打劫银行的日子，返回空数组。
 * <p>
 * - 输入：security = [1], time = 5
 * 输出：[]
 * 解释：
 * 没有日子前面和后面有 5 天时间。
 * 所以没有适合打劫银行的日子，返回空数组。
 * <p>
 * 1 <= security.length <= 10^5
 * 0 <= security[i], time <= 10^5
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-06 08:42:08
 */
public class GoodDaysToRobBankSolution {
    public static void main(String[] args) {
        final GoodDaysToRobBankSolution solution = new GoodDaysToRobBankSolution();

        int[] security = new int[]{5, 3, 3, 3, 5, 6, 2};
        int time = 2;
        System.out.println("Input security : " + Arrays.toString(security) + ", time : " + time);
        System.out.println("Output good days to rob bank : " + solution.goodDaysToRobBank(security, time));
        System.out.println("Output good days to rob bank (double pointer) : " + solution.goodDaysToRobBankDoublePointer(security, time));
        System.out.println("Output good days to rob bank (pre sum) : " + solution.goodDaysToRobBankPreSum(security, time));
        System.out.println();

        security = new int[]{1, 1, 1, 1, 1};
        time = 0;
        System.out.println("Input security : " + Arrays.toString(security) + ", time : " + time);
        System.out.println("Output good days to rob bank : " + solution.goodDaysToRobBank(security, time));
        System.out.println("Output good days to rob bank (double pointer) : " + solution.goodDaysToRobBankDoublePointer(security, time));
        System.out.println("Output good days to rob bank (pre sum) : " + solution.goodDaysToRobBankPreSum(security, time));
        System.out.println();

        security = new int[]{1, 2, 3, 4, 5, 6};
        time = 2;
        System.out.println("Input security : " + Arrays.toString(security) + ", time : " + time);
        System.out.println("Output good days to rob bank : " + solution.goodDaysToRobBank(security, time));
        System.out.println("Output good days to rob bank (double pointer) : " + solution.goodDaysToRobBankDoublePointer(security, time));
        System.out.println("Output good days to rob bank (pre sum) : " + solution.goodDaysToRobBankPreSum(security, time));
        System.out.println();

        security = new int[]{1};
        time = 5;
        System.out.println("Input security : " + Arrays.toString(security) + ", time : " + time);
        System.out.println("Output good days to rob bank : " + solution.goodDaysToRobBank(security, time));
        System.out.println("Output good days to rob bank (double pointer) : " + solution.goodDaysToRobBankDoublePointer(security, time));
        System.out.println("Output good days to rob bank (pre sum) : " + solution.goodDaysToRobBankPreSum(security, time));
        System.out.println();
    }

    /**
     * 动态规划
     * - 最优子结构
     * --- left[i], right[i] 分别表示第i天其前面非递增的天数、其后面非递减的天数
     * --- 其中right[i][j]从后往前推
     * - 递推关系
     * ---(security[i] <= security[i-1]) : left[i] = left[i-1] + 1
     * ---(security[i] > security[i-1]) : left[i] = 0
     * ---(security[i] <= security[i+1]) : right[i] = right[i+1] + 1
     * ---(security[i] > security[i+1]) : right[i] = 0
     * - 边界：left[0]=0, right[n-1]=0
     * - 决策：left[i] >= time && right[i] >= time, 将i加入到ans
     * - 目标：ans
     *
     * @param security
     * @param time
     * @return
     */
    List<Integer> goodDaysToRobBank(final int[] security, final int time) {
        final List<Integer> ans = new LinkedList<>();
        final int n = security.length;
        final int[] left = new int[n];
        final int[] right = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 0;
            }
        }

        if (time == 0) {
            ans.add(0);
        }
        for (int i = 1; i < n - time; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 前缀和
     * - adjacentIncreasing[i] 表示从0到i位置相邻位置严格递增的数量
     * - adjacentDecreasing[i] 表示从0到i位置相邻位置严格递减的数量
     * - 再次遍历一遍i，如果i前面time个位置没有adjacentIncreasing的情况并且后面time个位置没有adjacentDecreasing的情况，则该位置符合条件
     * <p>
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(n)
     *
     * @param security
     * @param time
     * @return
     */
    List<Integer> goodDaysToRobBankPreSum(final int[] security, final int time) {
        final List<Integer> ans = new LinkedList<>();
        if (security.length < (time << 1) + 1) {
            return ans;
        }
        final int maxIndex = security.length - time - 1;
        if (time == 0) {
            for (int i = 0; i <= maxIndex; i++) {
                ans.add(i);
            }
            return ans;
        }

        final int n = security.length;
        final int[] adjacentIncreasing = new int[n];
        final int[] adjacentDecreasing = new int[n];
        for (int i = 1; i < n; i++) {
            adjacentIncreasing[i] = adjacentIncreasing[i - 1] + (security[i] > security[i - 1] ? 1 : 0);
            adjacentDecreasing[i] = adjacentDecreasing[i - 1] + (security[i] < security[i - 1] ? 1 : 0);
        }
        for (int i = time; i <= maxIndex; i++) {
            if (adjacentIncreasing[i] == adjacentIncreasing[i - time] && adjacentDecreasing[i] == adjacentDecreasing[i + time]) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 双指针直接模拟
     * - 针对每个日子来验证，如果其满足其前面time天越来越小(并且不比该日小)、其后面time天越来越大(并且不日该日小)，就满足
     * <p>
     * - 时间复杂度 O(nk) (当k即time比较大时，可能超出时间限制) (所以这个题必须将时间复杂度控制在O(n))
     * - 空间复杂度 O(1)
     *
     * @param security
     * @param time
     * @return
     */
    List<Integer> goodDaysToRobBankMock(final int[] security, final int time) {
        final List<Integer> ans = new LinkedList<>();
        if (security.length < (time << 1) + 1) {
            return ans;
        }
        final int maxIndex = security.length - time - 1;
        if (time == 0) {
            for (int i = 0; i <= maxIndex; i++) {
                ans.add(i);
            }
            return ans;
        }

        for (int i = time; i <= maxIndex; i++) {
            boolean can = true;
            for (int k = 1; k <= time; k++) {
                if (security[i - k] < security[i - k + 1]) {
                    can = false;
                    break;
                }
            }
            if (!can) {
                continue;
            }
            for (int k = 1; k <= time; k++) {
                if (security[i + k] < security[i + k - 1]) {
                    can = false;
                    break;
                }
            }
            if (can) {
                ans.add(i);
            }
        }
        return ans;
    }

    /**
     * 双指针法：思路来自“钰娘娘”
     * - 构建一段满足条件的最长left与right，然后对其中的每一个位置判断是否满足
     * - 手动模拟的话可以直接在left与right的一段区间固定后，直接取其中间满足(并且彼此相等的)的个数 (但是这样的判定过于复杂，对于计算机来说不友好，还是每次Math.min(i - left, right - i) >= time较简单)
     */
    List<Integer> goodDaysToRobBankDoublePointer(final int[] security, final int time) {
        final List<Integer> ans = new LinkedList<>();
        if (security.length < (time << 1) + 1) {
            return ans;
        }
        final int n = security.length;
        if (time == 0) {
            for (int i = 0; i < n; i++) {
                ans.add(i);
            }
            return ans;
        }

        int left = 0;
        int right = -1;
        for (int i = 1; i < n; i++) {
            // 找到最后一个递增的位置作为可以满足答案的left位置
            if (security[i] > security[i - 1]) {
                left = i;
            }
            right = Math.max(right, i);
            while (right + 1 < n && security[right] <= security[right + 1]) {
                right++;
            }
            if (Math.min(i - left, right - i) >= time) {
                ans.add(i);
            }
        }
        return ans;
    }
}
