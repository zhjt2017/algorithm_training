package com.homework.week6;

import com.teachingpractice.week6.LongestIncreasingSubSequenceSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：最长递增子序列的个数
 * - https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence/ (673题)
 * <p>
 * - 给定一个未排序的整数数组nums, 返回最长严格递增子序列的个数
 * - 最长严格递增子序列的最小长度可以是1
 * <p>
 * - 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * <p>
 * 1 <= nums.length <= 2000
 * -10^6 <= nums[i] <= 10^6
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see LongestIncreasingSubSequenceSolution (本题以此题为基础)
 * @since 2022-02-15 06:42:52
 */
public class NumOfLongestIncreasingSubsequenceSolution {
    public static void main(String[] args) {
        final NumOfLongestIncreasingSubsequenceSolution solution = new NumOfLongestIncreasingSubsequenceSolution();

        int[] nums = new int[]{1, 3, 5, 4, 7};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println("Output num of longest increasing subsequence (binary) : " + solution.findCountBinarySearch(nums));
        System.out.println();

        nums = new int[]{1, 3, 5, 5, 7};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println("Output num of longest increasing subsequence (binary) : " + solution.findCountBinarySearch(nums));
        System.out.println();

        nums = new int[]{2, 2, 2, 2, 2};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println("Output num of longest increasing subsequence (binary) : " + solution.findCountBinarySearch(nums));
        System.out.println();

        nums = new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println("Output num of longest increasing subsequence (binary) : " + solution.findCountBinarySearch(nums));
        System.out.println();

        nums = new int[]{1, 3, 8, 7, 6, 4, 8, 7, 9};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println("Output num of longest increasing subsequence (binary) : " + solution.findCountBinarySearch(nums));
        System.out.println();

        nums = new int[]{1, 3, 5, 4, 7};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println("Output num of longest increasing subsequence (binary) : " + solution.findCountBinarySearch(nums));
        System.out.println();

        nums = new int[]{0};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output num of longest increasing subsequence : " + solution.findCount(nums));
        System.out.println("Output num of longest increasing subsequence (binary) : " + solution.findCountBinarySearch(nums));
    }

    /**
     * LongestIncreasingSubSequenceSolution.longestLength基础上
     * - 内层对j的遍历中，最终f[i] == f[j] + 1的条数即为count (原理：对于同样的i, 可以由不同的j而来)
     * <p>
     * - 时间复杂度 O(N^2)
     * - 空间复杂度 O(N)
     *
     * @param nums
     * @return
     */
    int findCount(final int[] nums) {
        final int n = nums.length;
        final int[] f = new int[n];
        final int[] count = new int[n];
        Arrays.fill(count, 1);
        int maxLength = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (f[i] <= f[j]) {
                        count[i] = count[j];
                        f[i] = f[j] + 1;
                        maxLength = Math.max(maxLength, f[i]);
                        continue;
                    }
                    if (f[i] == f[j] + 1) {
                        count[i] += count[j];
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (maxLength == f[i]) {
                ans += count[i];
            }
        }
        return ans;
    }

    /**
     * LongestIncreasingSubSequenceSolution.longestLengthBinarySearch基础上
     * <p>
     * - 时间复杂度 期待O(NlogN)
     * - 空间复杂度 O(N)
     *
     * @param nums
     * @return
     */
    int findCountBinarySearch(final int[] nums) {
        // 外层ArrayList的下标为最大长度，内层LinkedList为该最大长度下的所有可能的末尾数，int[]长度为2，[0]为末尾数本身，[1]为该末尾数对应的前段情况数
        lengthValues = new ArrayList<>(nums.length);
        lengthValues.add(new LinkedList<>());
        lengthValues.get(0).add(new int[]{nums[0], 0});
        int ans = 0;
        int lastMinValue;
        int mid;
        for (int num : nums) {
            lastMinValue = lengthValues.get(ans).getFirst()[0];
            if (num == lastMinValue) {
                lengthValues.get(ans).getFirst()[1] += preCount(ans, num);
                continue;
            }
            if (num > lastMinValue) {
                ans++;
                lengthValues.add(new LinkedList<>());
                lengthValues.get(ans).add(new int[]{num, preCount(ans, num)});
                continue;
            }
            int left = -1, right = ans;
            while (left < right) {
                mid = ((right - left - 1) >> 1) + left + 1;
                if (lengthValues.get(mid).getFirst()[0] < num) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }

            insert(left + 1, num);
        }
        return preCount(ans + 1, Integer.MAX_VALUE);
    }

    private List<LinkedList<int[]>> lengthValues;

    private int preCount(final int ans, final int num) {
        if (ans == 0) {
            return 1;
        }
        int sum = 0;
        for (int[] data : lengthValues.get(ans - 1)) {
            if (data[0] >= num) {
                return sum;
            }
            sum += data[1];
        }
        return sum;
    }

    /**
     * 维护list升序
     *
     * @param ans
     * @param num
     */
    private void insert(final int ans, final int num) {
        final LinkedList<int[]> list = lengthValues.get(ans);
        int index = 0;
        for (int[] data : list) {
            if (data[0] == num) {
                data[1] += preCount(ans, num);
                return;
            }
            if (data[0] > num) {
                list.add(index, new int[]{num, preCount(ans, num), 1});
                return;
            }
            index++;
        }
    }
}
