package com.homework.week5;

import java.util.Arrays;

/**
 * 算法实现：爱吃香蕉的珂珂 (能吃完的最小速度)
 * - https://leetcode-cn.com/problems/koko-eating-bananas/ (875题)
 * <p>
 * - 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 * - 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
 * - 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 * - 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * - 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 * <p>
 * - 输入: piles = [3,6,7,11], H = 8
 * 输出: 4
 * <p>
 * - 输入: piles = [30,11,23,4,20], H = 5
 * 输出: 30
 * <p>
 * - 输入: piles = [30,11,23,4,20], H = 6
 * 输出: 23
 * <p>
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 09:49:14
 */
public class KokoEatingBananasSolution {
    public static void main(String[] args) {
        final KokoEatingBananasSolution solution = new KokoEatingBananasSolution();

        int[] piles = new int[]{3, 6, 7, 11};
        int h = 8;
        System.out.println("Input piles : " + Arrays.toString(piles) + ", h = " + h);
        System.out.println("Output min eating speed : " + solution.minEatingSpeed(piles, h));
        System.out.println();

        piles = new int[]{30, 11, 23, 4, 20};
        h = 5;
        System.out.println("Input piles : " + Arrays.toString(piles) + ", h = " + h);
        System.out.println("Output min eating speed : " + solution.minEatingSpeed(piles, h));
        System.out.println();

        piles = new int[]{30, 11, 23, 4, 20};
        h = 6;
        System.out.println("Input piles : " + Arrays.toString(piles) + ", h = " + h);
        System.out.println("Output min eating speed : " + solution.minEatingSpeed(piles, h));
    }

    /**
     * 这也是一个经典的二分答案：二分试探出能够吃完的最小速度 (假设吃的速度是一直不变的，实际上也就是求最小平均速度，以小时为单位)
     * - 这里假设题目已经保证piles.length <= h
     *
     * @param piles
     * @param h
     * @return
     */
    int minEatingSpeed(final int[] piles, final int h) {
        int max = 0;
        for (final int p : piles) {
            max = Math.max(max, p);
        }
        int left = 1;
        int right = max;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (validate(piles, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    boolean validate(final int[] piles, final int h, final int speed) {
        int takingHours = 0;
        for (final int p : piles) {
            takingHours += (p - 1) / speed + 1;
            if (takingHours > h) {
                return false;
            }
        }
        return true;
    }
}
