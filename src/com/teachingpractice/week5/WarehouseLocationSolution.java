package com.teachingpractice.week5;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 算法实现：货仓选址问题
 * - https://www.acwing.com/problem/content/description/106/ (ACM-104题)
 * <p>
 * - 在一条数轴上有 N 家商店，它们的坐标分别为 A1∼AN。
 * - 现在需要在数轴上建立一家货仓，每天清晨，从货仓到每家商店都要运送一车商品。
 * - 为了提高效率，求把货仓建在何处，可以使得货仓到每家商店的距离之和最小。
 * - 输入格式
 * - 第一行输入整数 N。
 * - 第二行 N 个整数 A1∼AN。
 * <p>
 * - 输出格式
 * - 输出一个整数，表示距离之和的最小值。
 * <p>
 * 数据范围
 * 1≤N≤100000,
 * 0≤Ai≤40000
 * <p>
 * - 输入
 * 4
 * 6 2 9 1
 * - 输出
 * 12
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-14 08:10:29
 */
public class WarehouseLocationSolution {
    public static void main(String[] args) {
        /*
        两点之间线段最短，这个题实际上就是求数轴上这N个商店坐标的中位数 (无论奇数个还是偶数个，事实上，奇数个则必须刚好中位数，偶数个则在最中间的2个之间都可以，中位数同样满足)
        根据题目中数值的范围，40亿，我们求中位值(排序后，(n-1)/2或n/2位置上的数即为中位数)时，只算所有点到中位值的距离，左边1个+右边1个的距离和<=40000，故最大20亿，不会爆int
        时间复杂度 期望O(NlogN)
        空间复杂度 期望O(logN)
         */
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        String[] s = scan.nextLine().split(" ");
        if (n != s.length) {
            throw new RuntimeException("Input invalid, not match.");
        }

        final int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        Arrays.sort(nums);
        final int median = nums[n >> 1];
        int ans = 0;
        for (final int value : nums) {
            ans += Math.abs(median - value);
        }
        System.out.println(ans);
        scan.close();
    }
}
