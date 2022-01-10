package com.teachingpractice.week2;

import java.util.Arrays;

/**
 * 统计：优美子数组
 * - 给你一个整数数组 nums 和一个整数 k。
 * - 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * - 请返回这个数组中「优美子数组」的数目。
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 * <p>
 * 输入：nums = [91473,45388,24720,35841,29648,77363,86290,58032,53752,87188,34428,85343,19801,73201], k = 4
 * 输出：6
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 * <p>
 * 设计思想：
 * - 注意到：1 <= k <= nums.length (k=0，会变成自己的阶乘，算法就不一样了，故排除掉这无关的边界值)
 * - 统计奇数数字个数，我们将奇数记为1，偶数记为0(使用&1操作实现)，取和即可
 * - 我们具体实现了方法1与方法2
 * - 方法1：先取前缀和(记为s[i])，然后枚举右端点，计算2个前缀和的差=k的个数，此计算O(n^2)
 * -- 优化：利用类似于两数之和的思想。先对每个s[i]进行计数，然后遍历count数组，累计所有count[s[i]-k]
 * -- 再优化：由于前缀中只有1和0，故前缀和递增并且可能相邻的值一样，s[i]-k必然在s[i]前面
 * -- -- 故当i+1时如果s[i]不变，则count[s[i]-k]再来一次即翻倍，而count[s[i]]++，作为s[j]-k=s[i]的基数，
 * -- -- 这个其实本质上并没有什么优化，只不过可以不用乘法，而是遇到0则复制累加一份
 * <p>
 * - 方法2：模拟人工算法，空间复杂度O(1)，双指针法
 * -- left保持index=0，right右移，形成第一个刚好有k个奇数的(同样是令奇数1，偶数=0，求和)的子数组，
 * -- 如果是偶数就不断右移累加，即贪心，直到再遇到奇数
 * -- 计算左边贪心个数leftCount与右边贪心个数rightCount
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-09 01:15:26
 */
public class CountSubArraysSolution {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 1, 1};
        int k = 3;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("count of sub arrays : " + countSubArrays(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimization : " + countSubArraysOptimization(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimizationAgain : " + countSubArraysOptimizationAgain(nums, k));
        System.out.println("count of sub arrays countSubArraysAnother : " + countSubArraysAnother(nums, k));
        System.out.println();

        nums = new int[]{2, 4, 6};
        k = 1;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("count of sub arrays : " + countSubArrays(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimization : " + countSubArraysOptimization(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimizationAgain : " + countSubArraysOptimizationAgain(nums, k));
        System.out.println("count of sub arrays countSubArraysAnother : " + countSubArraysAnother(nums, k));
        System.out.println();

        nums = new int[]{1, 1, 1, 1};
        k = 1;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("count of sub arrays : " + countSubArrays(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimization : " + countSubArraysOptimization(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimizationAgain : " + countSubArraysOptimizationAgain(nums, k));
        System.out.println("count of sub arrays countSubArraysAnother : " + countSubArraysAnother(nums, k));
        System.out.println();

        nums = new int[]{1, 1};
        k = 1;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("count of sub arrays : " + countSubArrays(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimization : " + countSubArraysOptimization(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimizationAgain : " + countSubArraysOptimizationAgain(nums, k));
        System.out.println("count of sub arrays countSubArraysAnother : " + countSubArraysAnother(nums, k));
        System.out.println();

        nums = new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        k = 2;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("count of sub arrays : " + countSubArrays(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimization : " + countSubArraysOptimization(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimizationAgain : " + countSubArraysOptimizationAgain(nums, k));
        System.out.println("count of sub arrays countSubArraysAnother : " + countSubArraysAnother(nums, k));
        System.out.println();

        nums = new int[]{91473, 45388, 24720, 35841, 29648, 77363, 86290, 58032, 53752, 87188, 34428, 85343, 19801, 73201};
        k = 4;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("count of sub arrays : " + countSubArrays(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimization : " + countSubArraysOptimization(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimizationAgain : " + countSubArraysOptimizationAgain(nums, k));
        System.out.println("count of sub arrays countSubArraysAnother : " + countSubArraysAnother(nums, k));

        nums = new int[]{45627, 50891, 94884, 11286, 35337, 46414, 62029, 20247, 72789, 89158, 54203, 79628, 25920, 16832, 47469, 80909};
        k = 1;
        System.out.println("nums : " + Arrays.toString(nums) + ", k = " + k);
        System.out.println("count of sub arrays : " + countSubArrays(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimization : " + countSubArraysOptimization(nums, k));
        System.out.println("count of sub arrays countSubArraysOptimizationAgain : " + countSubArraysOptimizationAgain(nums, k));
        System.out.println("count of sub arrays countSubArraysAnother : " + countSubArraysAnother(nums, k));
    }

    /**
     * 方法1：
     * 先取前缀和(记为s[i])，然后枚举右端点，计算2个前缀和的差=k的个数，此计算O(n^2)
     * 优化：利用类似于两数之和的思想。先对每个s[i]进行计数，然后遍历count数组，累计所有count[s[i]-k]，其数为count[s[i]-k] * s[i]
     * <p>
     * 本方法是普遍通用的，但是对于负数求前缀和，count下标可能出界，故需要使用HashMap进行统计
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param k
     * @return
     */
    private static int countSubArrays(final int[] nums, final int k) {
        final int n = nums.length;
        final int[] preSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + (nums[i] & 1);
        }

        System.out.println("preSums : " + Arrays.toString(preSums));

        final int[] count = new int[n + 1];
        count[preSums[0]]++;
        for (int i = 1; i <= n; i++) {
            count[preSums[i]]++;
        }

        System.out.println("count : " + Arrays.toString(count));

        int result = 0;
        for (int i = k; i <= preSums[n]; i++) {
            result += (count[i - k] * count[i]);
        }
        return result;
    }

    /**
     * 方法一的进一步优化的写法：其本质并没有什么改变,只不过可以不用乘法，而是遇到0则复制累加一份
     * - 故当i+1时如果s[i]不变，则count[s[i]-k]再来一次即翻倍，而count[s[i]]++，作为s[j]-k=s[i]的基数
     *
     * @param nums
     * @param k
     * @return
     */
    private static int countSubArraysOptimization(final int[] nums, final int k) {
        final int n = nums.length;
        final int[] preSums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSums[i + 1] = preSums[i] + (nums[i] & 1);
        }

        final int[] count = new int[n + 1];
        count[preSums[0]]++;

        int result = 0;
        for (int i = 1; i <= n; i++) {
            count[preSums[i]]++;
            if (preSums[i] >= k) {
                result += count[preSums[i] - k];
            }
        }

        return result;
    }

    /**
     * 这其实与countSubArraysOptimization本质是一样的，方法没有变，但是优化了一下写法
     * - preSums数组只用到当前遍历的那个元素，故只要使用单个int维护当前遍历的preSum即可，优化了一半空间，并减少一次遍历中的一些步骤的时间
     *
     * @param nums
     * @param k
     * @return
     */
    private static int countSubArraysOptimizationAgain(final int[] nums, final int k) {
        final int n = nums.length;
        final int[] count = new int[n + 1];
        int preSum = 0, result = 0;
        count[0] = 1;
        for (int i = 0; i < n; ++i) {
            preSum += nums[i] & 1;
            if (preSum >= k) {
                result += count[preSum - k];
            }
            count[preSum]++;
        }
        return result;
    }

    /**
     * 方法2：模拟人工算法，空间复杂度O(1)，双指针法
     * -- left保持index=0，right右移，形成第一个刚好有k个奇数的(同样是令奇数1，偶数=0，求和)的子数组，
     * -- 如果是偶数就不断右移累加，即贪心，直到再遇到奇数
     * -- 计算左边贪心个数leftCount，右边贪心每加一个，result+=leftCount
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度O(1)
     * <p>
     * 心得：虽然这是空间复杂度是O(1)，当内存空间不是特别紧缺时，不建议这个方案，
     * 因为一来比较容易忽略迭代条件，而来计算的变量较多理解起来不方便，三来left指针的路线其实是多走的，所以时间复杂度甚至不如方法1
     *
     * @param nums
     * @param k
     * @return
     */
    private static int countSubArraysAnother(final int[] nums, final int k) {
        System.out.print("odd : ");
        for (final int num : nums) {
            System.out.print((num & 1) + " ");
        }
        System.out.println();

        final int n = nums.length;
        // left, right在下个迭代时都是从下一个奇数开始
        int left = 0;
        int right = 0;
        int leftCount = 1;
        int sum = 0;
        int result = 0;

        while (sum < k && right < n) {
            sum += (nums[right] & 1);
            right++;
        }
        if (right == n) {
            return 0;
        }

        while (right <= n) {
            while ((nums[left] & 1) == 0) {
                left++;
                leftCount++;
                if (left == n) {
                    break;
                }
            }
            result += leftCount;

            if (right == n) {
                return result;
            }
            while ((nums[right] & 1) == 0) {
                right++;
                result += leftCount;
                if (right == n) {
                    return result;
                }
            }
            right++;
            left++;
            leftCount = 1;
        }

        return result;
    }
}
