package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法训练(2022-03-29) - 前置 ： 最大连续1的个数 III
 * - https://leetcode-cn.com/problems/max-consecutive-ones-iii/ (1004题)
 * <p>
 * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 * <p>
 * - 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * <p>
 * - 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * <p>
 * 1 <= nums.length <= 10^5
 * nums[i] 不是 0 就是 1
 * 0 <= k <= nums.length
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-29 10:26:02
 */
public class MaxConsecutiveOnesSolution {
    public static void main(String[] args) {
        final MaxConsecutiveOnesSolution solution = new MaxConsecutiveOnesSolution();

        int[] nums = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output longest ones : " + solution.longestOnes(nums, k));
        System.out.println("Output longest ones (sliding window) : " + solution.longestOnesSlidingWindow(nums, k));
        System.out.println("Output longest ones (sliding window view simple) : " + solution.longestOnesSlidingWindowViewSimple(nums, k));
        System.out.println();

        nums = new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        k = 3;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output longest ones : " + solution.longestOnes(nums, k));
        System.out.println("Output longest ones (sliding window) : " + solution.longestOnesSlidingWindow(nums, k));
        System.out.println("Output longest ones (sliding window view simple) : " + solution.longestOnesSlidingWindowViewSimple(nums, k));
        System.out.println();

        nums = new int[]{0, 0, 0, 1};
        k = 4;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output longest ones : " + solution.longestOnes(nums, k));
        System.out.println("Output longest ones (sliding window) : " + solution.longestOnesSlidingWindow(nums, k));
        System.out.println("Output longest ones (sliding window view simple) : " + solution.longestOnesSlidingWindowViewSimple(nums, k));
        System.out.println();

        nums = new int[]{0, 0, 0, 0};
        k = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output longest ones : " + solution.longestOnes(nums, k));
        System.out.println("Output longest ones (sliding window) : " + solution.longestOnesSlidingWindow(nums, k));
        System.out.println("Output longest ones (sliding window view simple : " + solution.longestOnesSlidingWindowViewSimple(nums, k));
        System.out.println();

        nums = new int[]{0, 0, 1, 1, 1, 0, 0};
        k = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", k : " + k);
        System.out.println("Output longest ones : " + solution.longestOnes(nums, k));
        System.out.println("Output longest ones (sliding window) : " + solution.longestOnesSlidingWindow(nums, k));
        System.out.println("Output longest ones (sliding window view simple) : " + solution.longestOnesSlidingWindowViewSimple(nums, k));
        System.out.println();
    }

    /**
     * 思路1：最多可以增加k个1，那么要使得最终连续的1个数最多，那么就是求原数组中满足<=k个0的最长串
     * - 前缀和 + 二分查找
     * - 对于固定的right, 找到满足<=k个0的最小left
     * - 时间复杂度 O(nlogn)
     * - 空间复杂度 O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    int longestOnes(final int[] nums, final int k) {
        final int n = nums.length;
        final int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (1 - nums[i - 1]);
        }

        int ans = 0;
        for (int i = n; i > ans; i--) {
            int left = binarySearch(preSum, preSum[i] - k, i);
            ans = Math.max(ans, i - left);
        }
        return ans;
    }

    private int binarySearch(final int[] preSum, final int targetMin, final int end) {
        int left = 0;
        int right = end;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (preSum[mid] >= targetMin) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 思路2：滑动窗口 (优化前缀和+二分查找中重复查找的项) (好理解但写得较为啰嗦)
     * - 滑动窗口的限制条件：窗口内最多有k个0
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     *
     * @param nums
     * @param k
     * @return
     */
    int longestOnesSlidingWindow(final int[] nums, final int k) {
        int zeroCount = 0;
        final int n = nums.length;
        int index = 0;
        for (; index < n; index++) {
            while (index < n && nums[index] == 1) {
                index++;
            }
            if (index == n || zeroCount >= k) {
                break;
            }
            zeroCount++;
        }
        if (index == n) {
            return n;
        }

        int ans = index;
        int diff = 0;

        int left = 0;
        index++;
        for (; index < n; index++) {
            while (index < n && nums[index] == 1) {
                index++;
                diff++;
            }
            while (left < n && nums[left] == 1) {
                left++;
                diff--;
            }
            left++;
            if (diff > 0) {
                ans += diff;
                diff = 0;
            }
        }
        return ans;
    }

    /**
     * 滑动窗口 (写得简单明了些)
     *
     * @param nums
     * @param k
     * @return
     */
    int longestOnesSlidingWindowViewSimple(final int[] nums, final int k) {
        final int n = nums.length;
        int ans = 0;
        for (int sum = 0, left = -1, right = 0; right < n; right++) {
            sum += 1 - nums[right];
            while (sum > k) {
                sum -= 1 - nums[++left];
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
