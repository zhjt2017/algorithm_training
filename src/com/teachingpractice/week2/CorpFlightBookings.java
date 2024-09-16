package com.teachingpractice.week2;

import java.util.Arrays;

/**
 * 算法实现：航班预订统计
 * - https://leetcode-cn.com/problems/corporate-flight-bookings/ (1109题)
 * - [差分思想：每个元素增加相同的值]
 * <p>
 * - 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 * - 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 
 * - 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
 * - 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
 * <p>
 * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * 输出：[10,55,45,25,25]
 * 解释：
 * 航班编号        1   2   3   4   5
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       20  20
 * 预订记录 3 ：       25  25  25  25
 * 总座位数：      10  55  45  25  25
 * 因此，answer = [10,55,45,25,25]
 * <p>
 * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
 * 输出：[10,25]
 * 解释：
 * 航班编号        1   2
 * 预订记录 1 ：   10  10
 * 预订记录 2 ：       15
 * 总座位数：      10  25
 * 因此，answer = [10,25]
 * <p>
 * 1 <= n <= 2 * 104
 * 1 <= bookings.length <= 2 * 104
 * bookings[i].length == 3
 * 1 <= firsti <= lasti <= n
 * 1 <= seatsi <= 104
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-14 07:19:31
 */
public class CorpFlightBookings {
    public static void main(String[] args) {
        int[][] bookings = new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        int n = 5;
        System.out.println("bookings : " + Arrays.deepToString(bookings) + ", n = " + n);
        System.out.println("answer : " + Arrays.toString(corpFlightBookings(bookings, n)));

        bookings = new int[][]{{1, 2, 10}, {2, 2, 15}};
        n = 2;
        System.out.println("bookings : " + Arrays.deepToString(bookings) + ", n = " + n);
        System.out.println("answer : " + Arrays.toString(corpFlightBookings(bookings, n)));
    }

    private static int[] corpFlightBookings(final int[][] bookings, final int n) {
        // D(0) = A(0), D(i) = A(i) - A(i-1), D的前缀和就是原数组A, A
        final int[] diff = new int[n];
        for (final int[] booking : bookings) {
            diff[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                diff[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            diff[i] = diff[i - 1] + diff[i];
        }
        return diff;
    }
}
