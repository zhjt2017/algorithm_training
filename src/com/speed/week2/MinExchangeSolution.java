package com.speed.week2;

import java.util.Arrays;

/**
 * 最小交换次数 (自定义题目)
 * <p>
 * - 题意：int数组nums，由0与1两种元素组成，循环数组，即index=length-1后面的元素就是index=0的元素，即认为他们是连续的
 * - 要求：每次交换，可以任意2个元素彼此交换位置，求可以让所有的1连在一起(也即所有的0连在一起)的最小交换次数
 * <p>
 * 设计思想1：
 * - 遍历第一遍发现1的个数与0的个数，然后使用滑动窗口，
 * - 由于是循环数组，那么从index=0开始，从第一个不同的值开始统计该数值为最省时间
 * - 时间复杂度：O(n)
 * - 空间复杂度：O(1)
 * <p>
 * 设计思想2：
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-09 07:25:50
 */
public class MinExchangeSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 0, 1, 1, 0, 1, 1, 1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("min exchanges : " + minExchange(nums));

        nums = new int[]{1, 0, 1, 1, 0, 0, 1, 1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("min exchanges : " + minExchange(nums));

        nums = new int[]{1, 0, 1, 1, 0, 0, 1, 0, 1, 1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("min exchanges : " + minExchange(nums));

        nums = new int[]{1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("min exchanges : " + minExchange(nums));

        nums = new int[]{0, 1, 0, 1, 1, 0};
        System.out.println("nums : " + Arrays.toString(nums));
        System.out.println("min exchanges : " + minExchange(nums));
    }

    private static int minExchange(final int[] nums) {
        if (nums[0] == 0) {
            // 循环数组，0开头，将中间交换为连续的1
            return minExchange1(nums);
        }
        // 循环数组，1开头，将中间交换为连续的0
        return minExchange0(nums);
    }

    private static int minExchange1(final int[] nums) {
        int slidingLength = 0;
        for (final int value : nums) {
            slidingLength += value;
        }
        int left = 0;
        int right = 0;
        int sum = 0;
        int maxStatic = 0;
        while (nums[left] == 0) {
            left++;
        }
        right = left;
        // 建立第一个滑动窗口
        while (left + slidingLength - 1 >= right) {
            sum += nums[right];
            right++;
        }
        while (right < nums.length) {
            sum = sum + nums[right] - nums[left];
            maxStatic = Math.max(maxStatic, sum);
            left++;
            right++;
        }
        return slidingLength - maxStatic;
    }

    private static int minExchange0(final int[] nums) {
        int slidingLength = 0;
        for (final int value : nums) {
            slidingLength += value;
        }
        slidingLength = nums.length - slidingLength;
        int left = 0;
        int right = 0;
        int sum = 0;
        int minExchange = slidingLength;
        while (nums[left] == 1) {
            left++;
        }
        right = left;
        // 建立第一个滑动窗口
        while (left + slidingLength - 1 >= right) {
            sum += nums[right];
            right++;
        }
        while (right < nums.length) {
            sum = sum + nums[right] - nums[left];
            minExchange = Math.min(minExchange, sum);
            left++;
            right++;
        }
        return minExchange;
    }
}
