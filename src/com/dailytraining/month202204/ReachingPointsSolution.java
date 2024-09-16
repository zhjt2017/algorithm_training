package com.dailytraining.month202204;

/**
 * 算法训练(2022-04-09) 到达终点
 * - https://leetcode-cn.com/problems/reaching-points/ (780题)
 * <p>
 * 给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。
 * <p>
 * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
 * <p>
 * - 输入: sx = 1, sy = 1, tx = 3, ty = 5
 * 输出: true
 * 解释:
 * 可以通过以下一系列转换从起点转换到终点：
 * (1, 1) -> (1, 2)
 * (1, 2) -> (3, 2)
 * (3, 2) -> (3, 5)
 * <p>
 * - 输入: sx = 1, sy = 1, tx = 2, ty = 2
 * 输出: false
 * <p>
 * - 输入: sx = 1, sy = 1, tx = 1, ty = 1
 * 输出: true
 * <p>
 * 1 <= sx, sy, tx, ty <= 10^9
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-09 09:16:29
 */
public class ReachingPointsSolution {
    public static void main(String[] args) {
        final ReachingPointsSolution solution = new ReachingPointsSolution();

        int sx = 1;
        int sy = 1;
        int tx = 2;
        int ty = 2;
        System.out.println("Input sx : " + sx + ", sy : " + sy + ", tx : " + tx + ", ty : " + ty);
        System.out.println("Output reaching points : " + solution.reachingPoints(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization) : " + solution.reachingPointsOpti(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization further) : " + solution.reachingPointsOptiFurther(sx, sy, tx, ty));
        System.out.println();

        sx = 1;
        sy = 1;
        tx = 1;
        ty = 1;
        System.out.println("Input sx : " + sx + ", sy : " + sy + ", tx : " + tx + ", ty : " + ty);
        System.out.println("Output reaching points : " + solution.reachingPoints(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization) : " + solution.reachingPointsOpti(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization further) : " + solution.reachingPointsOptiFurther(sx, sy, tx, ty));
        System.out.println();

        sx = 1;
        sy = 1;
        tx = 3;
        ty = 5;
        System.out.println("Input sx : " + sx + ", sy : " + sy + ", tx : " + tx + ", ty : " + ty);
        System.out.println("Output reaching points : " + solution.reachingPoints(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization) : " + solution.reachingPointsOpti(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization further) : " + solution.reachingPointsOptiFurther(sx, sy, tx, ty));
        System.out.println();

        sx = 35;
        sy = 13;
        tx = 455955547;
        ty = 420098884;
        System.out.println("Input sx : " + sx + ", sy : " + sy + ", tx : " + tx + ", ty : " + ty);
        System.out.println("Output reaching points : " + solution.reachingPoints(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization) : " + solution.reachingPointsOpti(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization further) : " + solution.reachingPointsOptiFurther(sx, sy, tx, ty));
        System.out.println();

        sx = 1;
        sy = 3;
        tx = 1000000000;
        ty = 3;
        System.out.println("Input sx : " + sx + ", sy : " + sy + ", tx : " + tx + ", ty : " + ty);
        System.out.println("Output reaching points : " + solution.reachingPoints(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization) : " + solution.reachingPointsOpti(sx, sy, tx, ty));
        System.out.println("Output reaching points (optimization further) : " + solution.reachingPointsOptiFurther(sx, sy, tx, ty));
        System.out.println();
    }

    /**
     * 逆向递推：只有唯一确定的情况，不会有分支，如果一个已经相同了，看另一个能否同余
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    boolean reachingPoints(final int sx, final int sy, final int tx, final int ty) {
        int x = tx;
        int y = ty;
        while (x >= sx && y >= sy) {
            if (x == sx && y == sy) {
                return true;
            }
            if (x == y) {
                return false;
            }
            if (x > y) {
                x -= y;
            } else {
                y -= x;
            }
        }
        return false;
    }

    /**
     * 逆向递推：只有唯一确定的情况，不会有分支，如果一个已经相同了，看另一个能否同余
     * - 进一步优化：避免对单一的x或者y进行太多次连续的运算，导致超时，同余即可
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    boolean reachingPointsOpti(final int sx, final int sy, final int tx, final int ty) {
        int x = tx;
        int y = ty;
        while (x >= sx && y >= sy) {
            if (x == sx && y == sy) {
                return true;
            }
            if (x == y) {
                return false;
            }
            if (x == sx) {
                return (y - sy) % sx == 0;
            }
            if (y == sy) {
                return (x - sx) % sy == 0;
            }
            if (x > y) {
                x -= y;
            } else {
                y -= x;
            }
        }
        return false;
    }

    /**
     * 逆向递推：只有唯一确定的情况，不会有分支，如果一个已经相同了，看另一个能否同余
     * - 进一步优化：避免对单一的x或者y进行太多次连续的运算，导致超时，同余即可
     * - 再进一步优化，类似于辗转相除法，在将x/y其中一个减小到sx/sy其中一个前，都可以采用取余，能够形成闭环，没有边界被忽略。
     * - 时间复杂度 O(logn) (n = max(tx, ty))
     * - 空间复杂度 O(1)
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    boolean reachingPointsOptiFurther(final int sx, final int sy, final int tx, final int ty) {
        int x = tx;
        int y = ty;
        while (x > sx && y > sy) {
            if (x == y) {
                return false;
            }
            if (x > y) {
                x %= y;
            } else {
                y %= x;
            }
        }
        if (x < sx || y < sy) {
            return false;
        }
        return x == sx ? (y - sy) % sx == 0 : (x - sx) % sy == 0;
    }

    /**
     * 我的初始题解：dfs会造成方法栈溢出
     *
     * @param sx
     * @param sy
     * @param tx
     * @param ty
     * @return
     */
    boolean reachingPointsDfs(int sx, int sy, int tx, int ty) {
        this.tx = tx;
        this.ty = ty;
        return dfs(sx, sy);
    }

    private int tx;
    private int ty;

    private boolean dfs(final int x, final int y) {
        if (x == tx && y == ty) {
            return true;
        }
        if (x >= tx || y >= ty) {
            return false;
        }
        if (x + y < 0) {
            return false;
        }
        return dfs(x, x + y) || dfs(x + y, y);
    }
}
