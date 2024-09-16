package com.dailytraining.month202204;

import java.util.ArrayList;
import java.util.List;

/**
 * 算法训练(2022-04-18) 字典序排数
 * - https://leetcode-cn.com/problems/lexicographical-numbers/ (386题)
 * <p>
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 * <p>
 * - 输入：n = 13
 * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
 * <p>
 * - 输入：n = 2
 * 输出：[1,2]
 * <p>
 * 1 <= n <= 5 * 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-18 02:09:10
 */
public class LexicographicalNumbersSolution {
    public static void main(String[] args) {
        final LexicographicalNumbersSolution solution = new LexicographicalNumbersSolution();

        int n = 13;
        System.out.println("Input n : " + n);
        System.out.println("Output lexical order : " + solution.lexicalOrder(n));
        System.out.println();

        n = 2;
        System.out.println("Input n : " + n);
        System.out.println("Output lexical order : " + solution.lexicalOrder(n));
        System.out.println();
    }

    /**
     * 最简单的写法，执行n次，每次放入一个数据，然后组装下一个数据
     *
     * @param n
     * @return
     */
    List<Integer> lexicalOrder(final int n) {
        final List<Integer> ans = new ArrayList<>(n);
        int value = 1;
        for (int i = 1; i <= n; i++) {
            ans.add(value);
            // 按字典序，优先做内层
            if (value * 10 <= n) {
                value = value * 10;
                continue;
            }
            // 做本层，本层如果做完，回到外层的下一个，继续做
            while (value % 10 == 9 || value >= n) {
                value = value / 10;
            }
            value++;
        }
        return ans;
    }
}
