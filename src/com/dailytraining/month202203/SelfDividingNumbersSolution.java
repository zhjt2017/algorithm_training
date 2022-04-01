package com.dailytraining.month202203;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法训练(2022-03-31) 自除数
 * - https://leetcode-cn.com/problems/self-dividing-numbers/ (728题)
 * <p>
 * 自除数 是指可以被它包含的每一位数整除的数。
 * <p>
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * <p>
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 * <p>
 * - 输入：left = 1, right = 22
 * 输出：[1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 * <p>
 * - 输入：left = 47, right = 85
 * 输出：[48,55,66,77]
 * <p>
 * 1 <= left <= right <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-31 12:47:13
 */
public class SelfDividingNumbersSolution {
    public static void main(String[] args) {
        final SelfDividingNumbersSolution solution = new SelfDividingNumbersSolution();

        int left = 1;
        int right = 22;
        System.out.println("Input left : " + left + ", right : " + right);
        System.out.println("Output self dividing numbers : " + solution.selfDividingNumbers(left, right));
        System.out.println();

        left = 47;
        right = 85;
        System.out.println("Input left : " + left + ", right : " + right);
        System.out.println("Output self dividing numbers : " + solution.selfDividingNumbers(left, right));
        System.out.println();
    }

    /**
     * 我的初始题解：直接模拟
     *
     * @param left
     * @param right
     * @return
     */
    List<Integer> selfDividingNumbers(final int left, final int right) {
        final List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isSelfDividing(i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    private boolean isSelfDividing(final int n) {
        int value = n;
        int remaining = n % 10;
        while (value > 0 && remaining > 0 && n % remaining == 0) {
            value = value / 10;
            remaining = value % 10;
        }
        return value == 0;
    }
}
