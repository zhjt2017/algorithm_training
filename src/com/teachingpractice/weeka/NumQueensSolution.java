package com.teachingpractice.weeka;

import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：位运算优化 - N皇后问题
 * - https://leetcode-cn.com/problems/n-queens/
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
 * 设计思想：
 * - 皇后攻击规则：所在行任意攻击，所在列任意攻击，所在两个对角线任意攻击
 * - 对角线上的元素：2种情况：i+1,j+1或者i+1,j-1 (即i-j相等或者i+j相等)
 * - 1、排列问题，每行找到一个，然后递归到下一行，遍历每一列，不在used的列中满足，可以继续递归
 * - 2、中间不满足条件无法继续递归的，就走不到base case(最后一行也找到)，即被舍弃
 * - 3、判断时增加对角线是否used的判断，则剪枝完成
 * - 时间复杂度O(n!)
 * - 空间复杂度O(n)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 09:45:36
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
