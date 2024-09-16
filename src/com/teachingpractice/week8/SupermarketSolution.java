package com.teachingpractice.week8;

import java.util.Scanner;

/**
 * 算法实现：并查集 - 超市
 * - https://www.acwing.com/problem/content/147/ (145题)
 * <p>
 * - 超市里有 N 件商品，每件商品都有利润 pi 和过期时间 di，每天只能卖一件商品，过期商品不能再卖。
 * - 求合理安排每天卖的商品的情况下，可以得到的最大收益是多少。
 * <p>
 * - 输入格式
 * 输入包含多组测试用例。
 * <p>
 * - 每组测试用例，以输入整数 N 开始，接下来输入 N 对 pi 和 di，分别代表第 i 件商品的利润和过期时间。
 * - 在输入中，数据之间可以自由穿插任意个空格或空行，输入至文件结尾时终止输入，保证数据正确。
 * <p>
 * - 输出格式
 * 对于每组产品，输出一个该组的最大收益值。
 * 每个结果占一行。
 * <p>
 * 数据范围
 * 0≤N≤10000,
 * 1≤pi,di≤10000
 * 最多有 14 组测试样例
 * <p>
 * 输入样例：
 * 4  50 2  10 1   20 2   30 1
 * <p>
 * 7  20 1   2 1   10 3  100 2   8 2
 * 5 20  50 10
 * 输出样例：
 * 80
 * 185
 *
 * - 思想1：“价值最大的物品在临期最后一天卖” == “从后向前，卖当天最大价值的物品，不然就提前卖掉”
 * --- 大根堆，维护当前最大值，求和
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-23 10:36:11
 */
public class SupermarketSolution {
    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            System.out.println("start...");
            int n = scan.nextInt();
            System.out.println("n=" + n);
            for (int i = 0; i < n; i++) {
                System.out.println("" + scan.nextInt() + ", " + scan.nextInt());
            }
            System.out.println("end...next test");
        }

    }


}
