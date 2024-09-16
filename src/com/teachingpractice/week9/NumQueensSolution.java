package com.teachingpractice.week9;

import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：高级搜索 - 搜索剪枝 - N皇后问题
 * - https://leetcode-cn.com/problems/n-queens/ (51题)
 * <p>
 * - n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * - 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <p>
 * - 输入：n = 1
 * 输出：[["Q"]]
 * <p>
 * 1 <= n <= 9
 * <p>
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 05:16:53
 */
public class NumQueensSolution {
    public static void main(String[] args) {
        final NumQueensSolution solution = new NumQueensSolution();

        for (int n = 1; n <= 9; n++) {
            System.out.println("Input n : " + n);
            List<List<String>> ans = solution.solve(n);
            System.out.println("Queens number : " + ans.size() + ", positions : " + ans);
            System.out.println();
        }
    }

    List<List<String>> solve(final int n) {
        final List<List<String>> result = new LinkedList<>();

        return result;
    }
}
