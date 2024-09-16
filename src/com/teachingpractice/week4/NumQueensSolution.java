package com.teachingpractice.week4;

import java.util.*;

/**
 * 算法实现：搜索-N皇后问题
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
 * @since 2022-01-23 07:49:48
 */
public class NumQueensSolution {
    public static void main(String[] args) {
        System.out.println("Input : " + 1 + ", Output : " + solve(1));
        System.out.println("Input : " + 2 + ", Output : " + solve(2));
        System.out.println("Input : " + 3 + ", Output : " + solve(3));
        System.out.println("Input : " + 4 + ", Output : " + solve(4));
        System.out.println("Input : " + 5 + ", Output : " + solve(5));
        System.out.println("Input : " + 6 + ", Output : " + solve(6));
        System.out.println("Input : " + 7 + ", Output : " + solve(7));
        System.out.println("Input : " + 8 + ", Output : " + solve(8));
        System.out.println("Input : " + 9 + ", Output : " + solve(9));
    }

    private static List<List<String>> solve(final int n) {
        final NumQueensSolution solution = new NumQueensSolution();
        solution.n = n;
        solution.columnUsed = new boolean[n];
        solution.plusUsed = new HashSet<>();
        solution.minusUsed = new HashSet<>();
        solution.columnPositions = new int[n];
        solution.result = new LinkedList<>();
        solution.dfs(0);
        return solution.result;
    }

    private void dfs(final int row) {
        // base case : 最后一行也找到了对应的列可以放置皇后，则为一种可行的选择
        if (row == n) {
            result.add(buildCurrentAnswer());
            return;
        }

        for (int i = 0; i < n; i++) {
//            System.out.println(String.format("before, n=%d, row=%d, i=%d, columnUsed=%s, plusUsed=%s, minusUsed=%s, columnPositions=%s",
//                    n, row, i, Arrays.toString(columnUsed), plusUsed, minusUsed, Arrays.toString(columnPositions)));

            if (columnUsed[i] || plusUsed.contains(row + i) || minusUsed.contains(row - i)) {
                // 不符合条件，不再向下迭代，舍弃此分支 (continue而不是return)
                continue;
            }
            // 满足条件时，记录columnPositions(每个row独一份重新赋值，不用还原现场)，记忆当前row的数据(用于剪枝的条件，需要还原现场)，并递归下一个row
            columnPositions[row] = i;
            columnUsed[i] = true;
            plusUsed.add(row + i);
            minusUsed.add(row - i);
//
//            System.out.println(String.format("after, n=%d, row=%d, i=%d, columnUsed=%s, plusUsed=%s, minusUsed=%s, columnPositions=%s",
//                    n, row, i, Arrays.toString(columnUsed), plusUsed, minusUsed, Arrays.toString(columnPositions)));

            this.dfs(row + 1);
            // 还原现场
            columnUsed[i] = false;
            plusUsed.remove(row + i);
            minusUsed.remove(row - i);
        }
    }

    private List<String> buildCurrentAnswer() {
        final List<String> answer = new ArrayList<>(n);
        char[] rowData = new char[n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(rowData, DOT);
            rowData[columnPositions[i]] = Q;
            answer.add(String.valueOf(rowData));
        }
        return answer;
    }

    private static final char Q = 'Q';
    private static final char DOT = '.';
    private int n;
    private boolean[] columnUsed;
    private Set<Integer> plusUsed;
    private Set<Integer> minusUsed;
    private int[] columnPositions;
    private List<List<String>> result;
}
