package com.teachingpractice.week5;

import java.util.Arrays;

/**
 * 算法实现: 二分答案 - 制作m束花所需的最少天数
 * - https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/ (1482题)
 * - 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 * <p>
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 (每一束花的消耗) 。
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 * <p>
 * - 输入：bloomDay = [1,10,3,10,2], m = 3, k = 1
 * 输出：3
 * 解释：让我们一起观察这三天的花开过程，x 表示花开，而 _ 表示花还未开。
 * 现在需要制作 3 束花，每束只需要 1 朵。
 * 1 天后：[x, _, _, _, _]   // 只能制作 1 束花
 * 2 天后：[x, _, _, _, x]   // 只能制作 2 束花
 * 3 天后：[x, _, x, _, x]   // 可以制作 3 束花，答案为 3
 * <p>
 * - 输入：bloomDay = [1,10,3,10,2], m = 3, k = 2
 * 输出：-1
 * 解释：要制作 3 束花，每束需要 2 朵花，也就是一共需要 6 朵花。而花园中只有 5 朵花，无法满足制作要求，返回 -1 。
 * <p>
 * - 输入：bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * 输出：12
 * 解释：要制作 2 束花，每束需要 3 朵。
 * 花园在 7 天后和 12 天后的情况如下：
 * 7 天后：[x, x, x, x, _, x, x]
 * 可以用前 3 朵盛开的花制作第一束花。但不能使用后 3 朵盛开的花，因为它们不相邻。
 * 12 天后：[x, x, x, x, x, x, x]
 * 显然，我们可以用不同的方式制作两束花。
 * <p>
 * - 输入：bloomDay = [1000000000,1000000000], m = 1, k = 1
 * 输出：1000000000
 * 解释：需要等 1000000000 天才能采到花来制作花束
 * <p>
 * - 输入：bloomDay = [1,10,2,9,3,8,4,7,5,6], m = 4, k = 2
 * 输出：9
 * <p>
 * bloomDay.length == n
 * 1 <= n <= 10^5
 * 1 <= bloomDay[i] <= 10^9
 * 1 <= m <= 10^6
 * 1 <= k <= n
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-06 08:14:01
 */
public class MinDaysSolution {
    public static void main(String[] args) {
        final MinDaysSolution solution = new MinDaysSolution();

        int[] bloomDays = new int[]{1, 10, 3, 10, 2};
        int m = 3;
        int k = 1;
//        System.out.println("Input bloomDays : " + Arrays.toString(bloomDays) + ", m = " + m + ", k = " + k);
//        System.out.println("Output minDays : " + solution.minDays(bloomDays, m, k));
//
//        bloomDays = new int[]{1, 10, 3, 10, 2};
//        m = 3;
//        k = 2;
//        System.out.println("Input bloomDays : " + Arrays.toString(bloomDays) + ", m = " + m + ", k = " + k);
//        System.out.println("Output minDays : " + solution.minDays(bloomDays, m, k));
//
//        bloomDays = new int[]{7, 7, 7, 7, 12, 7, 7};
//        m = 2;
//        k = 3;
//        System.out.println("Input bloomDays : " + Arrays.toString(bloomDays) + ", m = " + m + ", k = " + k);
//        System.out.println("Output minDays : " + solution.minDays(bloomDays, m, k));
//
//        bloomDays = new int[]{1000000000, 1000000000};
//        m = 1;
//        k = 1;
//        System.out.println("Input bloomDays : " + Arrays.toString(bloomDays) + ", m = " + m + ", k = " + k);
//        System.out.println("Output minDays : " + solution.minDays(bloomDays, m, k));

        bloomDays = new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        m = 4;
        k = 2;
        System.out.println("Input bloomDays : " + Arrays.toString(bloomDays) + ", m = " + m + ", k = " + k);
        System.out.println("Output minDays : " + solution.minDays(bloomDays, m, k));
    }

    /**
     * 二分答案 - 求满足validate成功的最小天数 (无解时, 返回-1)
     * - 总体时间复杂度 O(N*logN), 空间复杂度 O(1)
     *
     * @param bloomDays
     * @param m
     * @param k
     * @return
     */
    private int minDays(final int[] bloomDays, final int m, final int k) {
        if (m * k > bloomDays.length) {
            return -1;
        }

        int latestBloomDay = 0;
        for (final int bloom : bloomDays) {
            latestBloomDay = max(latestBloomDay, bloom);
        }
        int left = 0;
        int right = latestBloomDay + 1;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (validate(bloomDays, m, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right == latestBloomDay + 1 ? -1 : right;
    }

    /**
     * 判定: 经过了days天数后, 能否制作出m束花 (每束消耗k朵连续的花朵)
     * - validate的复杂度: 时间复杂度 O(N), 空间复杂度 O(1)
     *
     * @param bloomDays
     * @param m
     * @param k
     * @param days
     * @return
     */
    private boolean validate(final int[] bloomDays, final int m, final int k, final int days) {
        int bouquet = 0;
        int consecutiveFlowers = 0;
        for (final int bloom : bloomDays) {
            if (bloom > days) {
                consecutiveFlowers = 0;
                continue;
            }
            consecutiveFlowers++;
            if (consecutiveFlowers == k) {
                consecutiveFlowers = 0;
                bouquet++;
                if (bouquet == m) {
                    return true;
                }
            }
        }
        return false;
    }

    private int max(final int a, final int b) {
        return a >= b ? a : b;
    }
}
