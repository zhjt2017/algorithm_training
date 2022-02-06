package com.speed.week5;

/**
 * 算法实现: 猜数字大小 (标准的普通二分)
 * <p>
 * - 猜数字游戏的规则如下：
 * - 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * - 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * - 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * - 返回我选出的数字。
 * <p>
 * - 输入：n = 10, pick = 6
 * 输出：6
 * <p>
 * - 输入：n = 1, pick = 1
 * 输出：1
 * <p>
 * - 输入：n = 2, pick = 1
 * 输出：1
 * <p>
 * - 输入：n = 2, pick = 2
 * 输出：2
 * <p>
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-29 11:16:25
 */
public class GuessNumberHigherOrLowerSolution {
    public static void main(String[] args) {
        int pick = 6;
        int n = 10;
        System.out.println(String.format("n : %d, pick : %d", n, pick));
        System.out.println("Output guessNumber result : " + new GuessNumberHigherOrLowerSolution(pick).guessNumber(n));

        pick = 1;
        n = 1;
        System.out.println(String.format("n : %d, pick : %d", n, pick));
        System.out.println("Output guessNumber result : " + new GuessNumberHigherOrLowerSolution(pick).guessNumber(n));

        pick = 1;
        n = 2;
        System.out.println(String.format("n : %d, pick : %d", n, pick));
        System.out.println("Output guessNumber result : " + new GuessNumberHigherOrLowerSolution(pick).guessNumber(n));

        pick = 2;
        n = 2;
        System.out.println(String.format("n : %d, pick : %d", n, pick));
        System.out.println("Output guessNumber result : " + new GuessNumberHigherOrLowerSolution(pick).guessNumber(n));
    }

    private int guessNumber(final int n) {
        // 要想最少次数(平均几率)访问guess得出最终答案, 那么采用二分
        if (n == 1) {
            return 1;
        }

        int left = 1;
        int right = n;
        int mid;
        int guessResult;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            guessResult = guess(mid);
            if (guessResult == 0) {
                return mid;
            }
            if (guessResult > 0) {
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }
        return -1;
    }

    private int pick;

    private GuessNumberHigherOrLowerSolution(final int pick) {
        this.pick = pick;
    }

    private int guess(final int num) {
        final int diff = this.pick - num;
        if (diff == 0) {
            return 0;
        }
        if (diff > 0) {
            return 1;
        }
        return -1;
    }
}
