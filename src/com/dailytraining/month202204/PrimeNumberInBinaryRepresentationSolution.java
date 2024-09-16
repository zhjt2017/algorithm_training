package com.dailytraining.month202204;

/**
 * 算法训练(2022-04-05) 二进制表示中质数个计算置位
 * - https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/ (762题)
 * <p>
 * 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
 * <p>
 * 计算置位位数 就是二进制表示中 1 的个数。
 * <p>
 * 例如， 21 的二进制表示 10101 有 3 个计算置位。
 *  
 * - 输入：left = 6, right = 10
 * 输出：4
 * 解释：
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 * 共计 4 个计算置位为质数的数字。
 * <p>
 * - 输入：left = 10, right = 15
 * 输出：5
 * 解释：
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 共计 5 个计算置位为质数的数字。
 * <p>
 * 1 <= left <= right <= 10^6
 * 0 <= right - left <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-05 04:47:01
 */
public class PrimeNumberInBinaryRepresentationSolution {
    public static void main(String[] args) {
        final PrimeNumberInBinaryRepresentationSolution solution = new PrimeNumberInBinaryRepresentationSolution();

        int left = 6;
        int right = 10;
        System.out.println("Input left : " + left + ", right : " + right);
        System.out.println("Output count prime set bits : " + solution.countPrimeSetBits(left, right));
        System.out.println("Output count prime set bits (count) : " + solution.countPrimeSetBitsAnother(left, right));
        System.out.println();

        left = 10;
        right = 15;
        System.out.println("Input left : " + left + ", right : " + right);
        System.out.println("Output count prime set bits : " + solution.countPrimeSetBits(left, right));
        System.out.println("Output count prime set bits (count) : " + solution.countPrimeSetBitsAnother(left, right));
        System.out.println();
    }

    int countPrimeSetBits(final int left, final int right) {
        // 0x100000 = 1024^2 为>10^6最小的2的次方， 次方幂 = 20, 则最多20个bit位
        final boolean[] flag = new boolean[21];
        flag[2] = true;
        flag[3] = true;
        flag[5] = true;
        flag[7] = true;
        flag[11] = true;
        flag[13] = true;
        flag[17] = true;
        flag[19] = true;
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (flag[Integer.bitCount(i)]) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 除了使用Integer.bitCount(i)外，我们还可以使用i&(i-1)来计算1的个数 (这个效率没有Integer.bitCount(i)高)
     * Integer.bitCount(i)
     * // HD, Figure 5-2
     * i = i - ((i >>> 1) & 0x55555555);
     * i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
     * i = (i + (i >>> 4)) & 0x0f0f0f0f;
     * i = i + (i >>> 8);
     * i = i + (i >>> 16);
     * return i & 0x3f;
     *
     * @param left
     * @param right
     * @return
     */
    int countPrimeSetBitsAnother(final int left, final int right) {
        int ans = 0;
        for (int i = left; i <= right; i++) {
            if (singleJudge(i)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean singleJudge(final int i) {
        int count = 0;
        int x = i;
        while (x > 0) {
            count++;
            x &= (x - 1);
        }
        return FLAG[count];
    }

    private static final boolean[] FLAG = new boolean[21];

    static {
        FLAG[2] = true;
        FLAG[3] = true;
        FLAG[5] = true;
        FLAG[7] = true;
        FLAG[11] = true;
        FLAG[13] = true;
        FLAG[17] = true;
        FLAG[19] = true;
    }

}
