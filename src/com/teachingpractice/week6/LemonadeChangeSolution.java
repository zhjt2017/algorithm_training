package com.teachingpractice.week6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 算法实现：贪心 - 柠檬水找零
 * - https://leetcode-cn.com/problems/lemonade-change/description/ (860题)
 * <p>
 * - 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * <p>
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * <p>
 * 注意，一开始你手头没有任何零钱。
 * <p>
 * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 * <p>
 * - 输入：bills = [5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 * <p>
 * - 输入：bills = [5,5,10,10,20]
 * 输出：false
 * 解释：
 * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
 * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
 * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
 * 由于不是每位顾客都得到了正确的找零，所以答案是 false。
 * <p>
 * - 输入：bills = [5,5,10]
 * 输出：true
 * <p>
 * - 输入：bills = [10,10]
 * 输出：false
 * <p>
 * - 1 <= bills.length <= 10^5
 * bills[i] 不是 5 就是 10 或是 20
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-17 10:00:24
 */
public class LemonadeChangeSolution {
    public static void main(String[] args) {
        final LemonadeChangeSolution solution = new LemonadeChangeSolution();

        int[] bills = new int[]{5, 5, 5, 10, 20};
        System.out.println("Input bills : " + Arrays.toString(bills));
        System.out.println("Output lemonade change result : " + solution.lemonadeChange(bills));
        System.out.println("Output lemonade change result (generally) : " + solution.lemonadeChangeGenerally(bills));
        System.out.println();

        bills = new int[]{5, 5, 10, 10, 20};
        System.out.println("Input bills : " + Arrays.toString(bills));
        System.out.println("Output lemonade change result : " + solution.lemonadeChange(bills));
        System.out.println("Output lemonade change result (generally) : " + solution.lemonadeChangeGenerally(bills));
        System.out.println();

        bills = new int[]{5, 5, 10};
        System.out.println("Input bills : " + Arrays.toString(bills));
        System.out.println("Output lemonade change result : " + solution.lemonadeChange(bills));
        System.out.println("Output lemonade change result (generally) : " + solution.lemonadeChangeGenerally(bills));
        System.out.println();

        bills = new int[]{10, 10};
        System.out.println("Input bills : " + Arrays.toString(bills));
        System.out.println("Output lemonade change result : " + solution.lemonadeChange(bills));
        System.out.println("Output lemonade change result (generally) : " + solution.lemonadeChangeGenerally(bills));
        System.out.println();

        bills = new int[]{5, 5, 5, 10, 5, 5, 10, 20, 20, 20};
        System.out.println("Input bills : " + Arrays.toString(bills));
        System.out.println("Output lemonade change result : " + solution.lemonadeChange(bills));
        System.out.println("Output lemonade change result (generally) : " + solution.lemonadeChangeGenerally(bills));
    }

    /**
     * 店员的心理：优先找大面额的零钱，再找小面额的零钱 (面值成倍数，满足决策包容性：1个10优于2个5)
     * - 基于本例具体金额的特殊写法 (面额种类比较少)
     * - 时间复杂度 O(NK) (k为面额种类数)
     * - 空间复杂度 O(k) (k为面额种类数)
     *
     * @param bills
     * @return
     */
    boolean lemonadeChange(final int[] bills) {
        count5 = 0;
        count10 = 0;
        for (final int bill : bills) {
            if (!changeOnceSpecial(bill)) {
                return false;
            }
        }
        return true;
    }

    private int count5;
    private int count10;

    private boolean changeOnceSpecial(final int bill) {
        if (bill == 5) {
            count5++;
            return true;
        }
        if (bill == 10) {
            if (count5 > 0) {
                count10++;
                count5--;
                return true;
            }
            return false;
        }
        if (count5 <= 0) {
            return false;
        }
        if (count10 > 0) {
            count10--;
            count5--;
            return true;
        }
        if (count5 >= 3) {
            count5 -= 3;
            return true;
        }
        return false;
    }

    /**
     * 一般性的写法：支持多种类面额的更为复杂的情形
     * - 时间复杂度 O(NK) (k为面额种类数)
     * - 空间复杂度 O(k) (k为面额种类数)
     *
     * @param bills
     * @return
     */
    boolean lemonadeChangeGenerally(final int[] bills) {
        assets = new HashMap<>(3);
        orderedDenomination = new int[]{20, 10, 5};
        for (final int denomination : orderedDenomination) {
            assets.put(denomination, 0);
        }
        for (final int bill : bills) {
            if (!exchange(bill - 5)) {
                return false;
            }
            assets.put(bill, assets.get(bill) + 1);
        }
        return true;
    }

    private Map<Integer, Integer> assets = new HashMap<>();
    private int[] orderedDenomination;

    /**
     * 原子操作：将要找的零钱兑换开
     *
     * @param amount
     * @return
     */
    private boolean exchange(final int amount) {
        int remaining = amount;
        int count;
        for (final int denomination : orderedDenomination) {
            count = assets.get(denomination);
            while (remaining >= denomination && count > 0) {
                assets.put(denomination, --count);
                remaining -= denomination;
            }
        }
        return remaining == 0;
    }

}
