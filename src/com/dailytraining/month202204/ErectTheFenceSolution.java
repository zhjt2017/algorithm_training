package com.dailytraining.month202204;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 算法训练(2022-04-23) 安装栅栏
 * - https://leetcode-cn.com/problems/erect-the-fence/ (587题)
 * <p>
 * 在一个二维的花园中，有一些用 (x, y) 坐标表示的树。由于安装费用十分昂贵，你的任务是先用最短的绳子围起所有的树。
 * 只有当所有的树都被绳子包围时，花园才能围好栅栏。你需要找到正好位于栅栏边界上的树的坐标。
 * <p>
 * - 输入: [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
 * 输出: [[1,1],[2,0],[4,2],[3,3],[2,4]]
 * <p>
 * - 输入: [[1,2],[2,2],[4,2]]
 * 输出: [[1,2],[2,2],[4,2]]
 * 即使树都在一条直线上，你也需要先用绳子包围它们。
 * <p>
 * 所有的树应当被围在一起。你不能剪断绳子来包围树或者把树分成一组以上。
 * 输入的整数在 0 到 100 之间。
 * 花园至少有一棵树。
 * 所有树的坐标都是不同的。
 * 输入的点没有顺序。输出顺序也没有要求。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-23 10:11:14
 */
public class ErectTheFenceSolution {
    public static void main(String[] args) {
        final ErectTheFenceSolution solution = new ErectTheFenceSolution();

        int[][] trees = new int[][]{{1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}};
        System.out.println("Input trees : " + Arrays.deepToString(trees));
        System.out.println("Output outer trees : " + Arrays.deepToString(solution.outerTrees(trees)));
        System.out.println();

        trees = new int[][]{{1, 2}, {2, 2}, {4, 2}};
        System.out.println("Input trees : " + Arrays.deepToString(trees));
        System.out.println("Output outer trees : " + Arrays.deepToString(solution.outerTrees(trees)));
        System.out.println();
    }

    /**
     * 凸包问题(二维凸包) (Andrew算法)
     * Andrew 算法正是用于求解凸包上的所有点（围成所有点的最小周长），其算法逻辑将凸包分为「上凸壳」和「下凸壳」，并分别画出（蓝色分割线将凸包分为两部分）：
     * 基本流程为：
     * <p>
     * 对所有点进行双关键字排序，先根据 xx 坐标排升序，后根据 yy 排升序；
     * 根据 xx 排升序的目的，是为了我们能够往一个方向画出凸包边缘（从左往后画出一半凸壳，从右往左画出另外一半），而将 yy 升序目的是可以确保一旦我们现在从 aa 到 bb 进行连线，那么 aa 到 bb 之间的所有点能够确保被围住；
     * <p>
     * 使用栈来维护所有凸包上的点，或者说凸包上的边，会更为准确，凸包起点元素会在栈中出现两次（首尾），因此更为准确的描述应该是使用栈维护凸包的所有的边，栈中相邻元素代表凸包上的一条边；
     * <p>
     * 分别「从前往后」和「从后往前」处理排序好的所有点，来分别画出凸包的上下两部分，根据画的是第一部分还是第二部分，维护栈内元的处理逻辑稍有不同：
     * <p>
     * a. 画的是凸包的第一部分：
     * <p>
     * 若栈内元素少于 22 个，组成一条线至少需要两个点，说明此时第一条边都还没画出，直接将元素添加到栈中；
     * <p>
     * 若栈内元素不少于 22 个，考虑是否要将栈顶的边删掉（由栈顶前两个元素组成的边）假设栈顶元素为 bb，栈顶元素的下一位为 aa，即栈顶存在一条 aa 到 bb 的边，当前处理到的点为 cc，此时我们根据 acac 边是否在 abab 边的时针方向来决定是否要将 abab 边去掉：
     * <p>
     * <p>
     * <p>
     * 按照上述逻辑处理完所有点，凸包第一部分的点（边）都存在于栈中。
     * <p>
     * b. 画的是凸包的第二部分：逻辑同理，唯一需要注意的是，第一部分的凸包边我们不能删去，假定处理完第一部分凸包，我们栈内有 mm 个元素，我们需要将上述「栈顶元素不少于 22 个」的逻辑替换为「栈顶元素大于 mm 个」，同时已参与到凸包第一部分的点，不能再考虑，因此需要额外使用一个 visvis 数组来记录使用过的点。
     * <p>
     * 一些细节，为了方便取得栈顶的前两位元素，我们使用数组实现栈，stkstk 代表栈容器，tptp 代表栈顶元素下标。
     * <p>
     * 正如刚刚讲到，起点会被入栈两次（对应第一条边和最后一条边），因此输出方案时，栈顶和栈底我们只选其一即可。
     *
     * @param trees
     * @return
     */
    int[][] outerTrees(final int[][] trees) {
        Arrays.sort(trees, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int n = trees.length, tp = 0;
        int[] stk = new int[n + 10];
        boolean[] vis = new boolean[n + 10];
        // 不标记起点
        stk[++tp] = 0;
        for (int i = 1; i < n; i++) {
            int[] c = trees[i];
            while (tp >= 2) {
                int[] a = trees[stk[tp - 1]], b = trees[stk[tp]];
                if (getArea(a, b, c) >= 0) {
                    break;
                }
                vis[stk[tp--]] = false;
            }
            stk[++tp] = i;
            vis[i] = true;
        }
        int size = tp;
        for (int i = n - 1; i >= 0; i--) {
            if (vis[i]) {
                continue;
            }
            int[] c = trees[i];
            while (tp > size) {
                int[] a = trees[stk[tp - 1]], b = trees[stk[tp]];
                if (getArea(a, b, c) >= 0) {
                    break;
                }
                tp--;
            }
            stk[++tp] = i;
        }
        int[][] ans = new int[tp - 1][2];
        for (int i = 1; i < tp; i++) {
            ans[i - 1] = trees[stk[i]];
        }
        return ans;
    }

    /**
     * 向量相减
     *
     * @param a
     * @param b
     * @return
     */
    private int[] subtraction(int[] a, int[] b) {
        return new int[]{a[0] - b[0], a[1] - b[1]};
    }

    /**
     * 叉乘
     *
     * @param a
     * @param b
     * @return
     */
    private double cross(int[] a, int[] b) {
        return a[0] * b[1] - a[1] * b[0];
    }

    /**
     * 向量 ab 转为 向量 ac 过程中扫过的面积
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    private double getArea(int[] a, int[] b, int[] c) {
        return cross(subtraction(b, a), subtraction(c, a));
    }

    /**
     * Graham算法
     * 这个方法的具体实现为：首先选择一个凸包上的初始点 bottom。我们选择 yy 坐标最小的点为起始点，我们可以肯定 bottom 一定在凸包上，将给定点集按照相对的以 bottom 为原点的极角大小进行排序。
     * <p>
     * 这一排序过程大致给了我们在逆时针顺序选点时候的思路。为了将点排序，我们使用上一方法使用过的函数 cross 。极角顺序更小的点排在数组的前面。如果有两个点相对于点 \textit{bottom}bottom 的极角大小相同，则按照与点 bottom 的距离排序。
     * <p>
     * 我们还需要考虑另一种重要的情况，如果共线的点在凸壳的最后一条边上，我们需要从距离初始点最远的点开始考虑起。所以在将数组排序后，我们从尾开始遍历有序数组并将共线且朝有序数组尾部的点反转顺序，因为这些点是形成凸壳过程中尾部的点，所以在经过了这些处理以后，我们得到了求凸壳时正确的点的顺序。
     * <p>
     * 现在我们从有序数组最开始两个点开始考虑。我们将这条线上的点放入栈中。然后我们从第三个点开始遍历有序数组 trees。如果当前点与栈顶的点相比前一条线是一个「左拐」或者是同一条线段上，我们都将当前点添加到栈顶，表示这个点暂时被添加到凸壳上。
     * <p>
     * 检查左拐或者右拐使用的还是cross 函数。对于向量 pq, qr，计算向量的叉积(p,q,r) = cross(p,q,r)= pq× qr，如果叉积小于 00，
     * 可以知道向量 pq, qr顺时针旋转，则此时向右拐；如果叉积大于 00，可以知道向量 pq, qr逆时针旋转，表示是左拐；
     * 如果叉积等于 00，则 p,q,rp,q,r 在同一条直线上。
     * <p>
     * 如果当前点与上一条线之间的关系是右拐的，说明上一个点不应该被包括在凸壳里，因为它在边界的里面（正如动画中点 44），所以我们将它从栈中弹出并考虑倒数第二条线的方向。
     * 重复这一过程，弹栈的操作会一直进行，直到我们当前点在凸壳中出现了右拐。这表示这时凸壳中只包括边界上的点而不包括边界以内的点。在所有点被遍历了一遍以后，栈中的点就是构成凸壳的点。
     *
     * @param trees
     * @return
     */
    int[][] outerTreesGraham(final int[][] trees) {
        int n = trees.length;
        if (n < 4) {
            return trees;
        }
        int bottom = 0;
        /* 找到 y 最小的点 bottom*/
        for (int i = 0; i < n; i++) {
            if (trees[i][1] < trees[bottom][1]) {
                bottom = i;
            }
        }
        swap(trees, bottom, 0);
        /* 以 bottom 原点，按照极坐标的角度大小进行排序 */
        Arrays.sort(trees, 1, n, (a, b) -> {
            int diff = cross(trees[0], a, b) - cross(trees[0], b, a);
            if (diff == 0) {
                return distance(trees[0], a) - distance(trees[0], b);
            } else {
                return -diff;
            }
        });
        /* 对于凸包最后且在同一条直线的元素按照距离从小到大进行排序 */
        int r = n - 1;
        while (r >= 0 && cross(trees[0], trees[n - 1], trees[r]) == 0) {
            r--;
        }
        for (int l = r + 1, h = n - 1; l < h; l++, h--) {
            swap(trees, l, h);
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(0);
        stack.push(1);
        for (int i = 2; i < n; i++) {
            int top = stack.pop();
            /* 如果当前元素与栈顶的两个元素构成的向量顺时针旋转，则弹出栈顶元素 */
            while (!stack.isEmpty() && cross(trees[stack.peek()], trees[top], trees[i]) < 0) {
                top = stack.pop();
            }
            stack.push(top);
            stack.push(i);
        }

        int size = stack.size();
        int[][] res = new int[size][2];
        for (int i = 0; i < size; i++) {
            res[i] = trees[stack.pop()];
        }
        return res;
    }

    private int cross(int[] p, int[] q, int[] r) {
        return (q[0] - p[0]) * (r[1] - q[1]) - (q[1] - p[1]) * (r[0] - q[0]);
    }

    private int distance(int[] p, int[] q) {
        return (p[0] - q[0]) * (p[0] - q[0]) + (p[1] - q[1]) * (p[1] - q[1]);
    }

    private void swap(int[][] trees, int i, int j) {
        int temp0 = trees[i][0], temp1 = trees[i][1];
        trees[i][0] = trees[j][0];
        trees[i][1] = trees[j][1];
        trees[j][0] = temp0;
        trees[j][1] = temp1;
    }
}
