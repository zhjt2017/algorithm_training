package com.homework.week1;

import java.util.Arrays;

/**
 * 算法实现：加1
 * 场景：给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位，数组中每个元素只存储单个数字。(除了0以外，输入的整数不会以0开头)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-03 08:35:16
 */
public class PlusOneSolution {
    public static void main(String[] args) {
        int[] digits = new int[]{2, 4, 5, 0};
        int[] result = plusOne(digits);
        System.out.println(Arrays.toString(result));
        digits = new int[]{9, 9, 9, 9};
        result = plusOne(digits);
        System.out.println(Arrays.toString(result));
    }

    private static int[] plusOne(final int[] digits) {
        /*
         * 按我们在对2个非负整数手动相加时的算法：从低位向高位，逐位进行计算
         * 考虑到加数固定为1，即只有一个个位，故一旦不需要进位了，就无须再算高位
         * 边界情况：如果digits所有位都是9，那么需要长度+1
         * 时间复杂度：O(n)
         * 空间复杂度：O(1)
         */
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        final int[] result = new int[length + 1];
        result[0] = 1;
        return result;
    }
}
