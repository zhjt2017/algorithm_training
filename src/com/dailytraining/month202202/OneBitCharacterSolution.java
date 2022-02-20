package com.dailytraining.month202202;

import java.util.Arrays;

/**
 * 算法训练(2022-02-19) 煎饼排序
 * <p>
 * - 有两种特殊字符：
 * - 第一种字符可以用一个比特 0 来表示
 * - 第二种字符可以用两个比特(10 或 11)来表示、
 * - 给定一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一位字符，则返回 true 。
 * <p>
 * - 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的编码方式是一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 * <p>
 * - 输入: bits = [1, 1, 1, 0]
 * 输出: false
 * 解释: 唯一的编码方式是两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-20 01:03:09
 */
public class OneBitCharacterSolution {
    public static void main(String[] args) {
        final OneBitCharacterSolution solution = new OneBitCharacterSolution();

        int[] bits = new int[]{1, 0, 0};
        System.out.println("Input bits : " + Arrays.toString(bits));
        System.out.println("Output is one bit character : " + solution.isOneBitCharacter(bits));
        System.out.println();

        bits = new int[]{1, 1, 1, 0};
        System.out.println("Input bits : " + Arrays.toString(bits));
        System.out.println("Output is one bit character : " + solution.isOneBitCharacter(bits));
    }

    /**
     * 从左到右扫每个字符，从第一个1开始，跳过1个bit位，0不跳过，每个字符扫完后，标记该字符是否一个bit
     *
     * @param bits
     * @return
     */
    boolean isOneBitCharacter(final int[] bits) {
        boolean ans = true;
        int i = 0;
        for (; i < bits.length; i++) {
            System.out.println(i);
            if (bits[i] == 1) {
                i++;
                ans = false;
            } else {
                ans = true;
            }
        }
        return ans;
    }
}
