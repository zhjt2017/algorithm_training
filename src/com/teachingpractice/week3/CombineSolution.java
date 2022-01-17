package com.teachingpractice.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 算法实现：递归-组合
 * <p>
 * - 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * - 可以按任意顺序返回答案
 * <p>
 * - 输入：n = 4, k = 2
 * 输出：[[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 * <p>
 * - 输入：n = 1, k = 1
 * 输出：[[1]]
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * 设计思想：所谓组合就是在n个数中取出任意k个数，列出每种组合 (组合就是无序Set，每个组合内无论元素顺序如何，只算一种情况)
 * - 如果是选n个数中选k个数排列的话，情况数为：(n!)/((n-k)!)
 * - 现在是组合，情况数为：((n!)/((n-k)!))/(k!)，其必然小于2^n
 * - 实际上2^n即子集就是n个数取0个、取1个...取n个数，共n+1种组合的所有累加值
 * <p>
 * 时间复杂度：O(2^n) + 剪枝
 * 空间复杂度：O(n) (方法栈压栈深度为n)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-17 04:14:34
 */
public class CombineSolution {
    public static void main(String[] args) {
        combine(4, 2);
        combine(1, 1);
    }

    private static List<List<Integer>> combine(final int n, final int k) {
        System.out.println("n : " + n + ", k : " + k);

        if (n == k) {
            final List<List<Integer>> r = Arrays.asList(IntStream.range(1, n + 1).boxed().collect(Collectors.toList()));
            System.out.println("combine sets : " + r);
            return r;
        }
        if (n < k) {
            return new ArrayList<>();
        }

        final CombineSolution solution = new CombineSolution(n, k);
        solution.recur(1);

        System.out.println("combine sets : " + solution.result);

        return solution.result;
    }

    private void recur(final int serialNumber) {
        if (this.currentSet.size() == k) {
            this.result.add(new ArrayList<>(this.currentSet));
            return;
        }

        // 剪枝：由于之前的层，很多都没有选，剩下的层，就算全部选，也不够k的情形，不用再向下递归了
        if (this.currentSet.size() + n + 1 < serialNumber + k) {
            return;
        }

        this.recur(serialNumber + 1);
        this.currentSet.add(serialNumber);
        this.recur(serialNumber + 1);
        // 还原现场
        this.currentSet.remove(this.currentSet.size() - 1);
    }


    private CombineSolution(final int n, final int k) {
        this.n = n;
        this.k = k;
        this.currentSet = new ArrayList<>(k);
        this.result = new LinkedList<>();
    }

    private List<List<Integer>> result;
    private List<Integer> currentSet;
    private int n;
    private int k;
}
