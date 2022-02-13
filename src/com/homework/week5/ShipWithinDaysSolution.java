package com.homework.week5;

import java.util.Arrays;

/**
 * 算法实现: 二分答案(试探) - 在D天内送达包裹的能力
 * - https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/ (1011题)
 * <p>
 * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
 * <p>
 * 我的理解: 这里说的船的最低运载能力, 可能是一艘船, 也可能是多艘, 每艘运载能力也可能不一样,
 * - 这里是想说, 假定从A港运送到B港, 每天投入到这条航线上的整体运载能力固定, 那么这个固定值至少是多少才能满足当前的包裹运送需求
 * - (所有的包裹以天为单位按顺序运送, 即包裹先到的至少要比晚到的在同一天运送, 即装载不能跳过任何一个包裹)
 * - 于是这就是一个典型的二分答案问题了 (求满足包裹运送需求的最小日运载能力) (本题不存在无解的情况, 因为days > 0)
 * - 时间复杂度 O(N*logN) 空间复杂度 O(1)
 * <p>
 * - 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * <p>
 * - 输入：weights = [3,2,2,4,1,4], days = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * <p>
 * - 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 * <p>
 * 1 <= days <= weights.length <= 5 * 10^4
 * 1 <= weights[i] <= 500
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-07 10:21:50
 */
public class ShipWithinDaysSolution {
    public static void main(String[] args) {
        final ShipWithinDaysSolution solution = new ShipWithinDaysSolution();

        int[] weights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        System.out.println("Input weights : " + Arrays.toString(weights) + ", days : " + days);
        System.out.println("Output lowest daily carrying capacity : " + solution.shipWithinDays(weights, days));

        weights = new int[]{3, 2, 2, 4, 1, 4};
        days = 3;
        System.out.println("Input weights : " + Arrays.toString(weights) + ", days : " + days);
        System.out.println("Output lowest daily carrying capacity : " + solution.shipWithinDays(weights, days));

        weights = new int[]{1, 2, 3, 1, 1};
        days = 4;
        System.out.println("Input weights : " + Arrays.toString(weights) + ", days : " + days);
        System.out.println("Output lowest daily carrying capacity : " + solution.shipWithinDays(weights, days));
    }

    private int shipWithinDays(final int[] weights, final int days) {
        int singleMax = 0;
        int total = 0;
        for (final int w : weights) {
            singleMax = Math.max(singleMax, w);
            total += w;
        }

        int left = singleMax;
        // 本题不存在无解的情况, 因为days > 0, 故当无法取得更优解时, total一定能够满足运载能力
        int right = total;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (validate(weights, days, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 判定日运载能力是否足够
     *
     * @param weights
     * @param days
     * @param dailyCarryingCapacity 传入时, 已经保证日运载能力不小于单个最大包裹
     * @return
     */
    private boolean validate(final int[] weights, final int days, final int dailyCarryingCapacity) {
        int actualDays = 1;
        int dailyCarrying = 0;
        for (final int w : weights) {
            dailyCarrying += w;
            if (dailyCarrying > dailyCarryingCapacity) {
                dailyCarrying = w;
                actualDays++;
                if (actualDays > days) {
                    return false;
                }
            }
        }
        return true;
    }
}
