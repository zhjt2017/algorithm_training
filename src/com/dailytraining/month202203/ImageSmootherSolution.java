package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法训练(2022-03-24) 图片平滑器
 * - https://leetcode-cn.com/problems/image-smoother/ (661题)
 * <p>
 * 图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
 * 每个单元格的  平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
 * 如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
 * <p>
 * 给你一个表示图像灰度的 m x n 整数矩阵 img ，返回对图像的每个单元格平滑处理后的图像 。
 * <p>
 * - 输入:img = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出:[[0, 0, 0],[0, 0, 0], [0, 0, 0]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 * <p>
 * - 输入: img = [[100,200,100],[200,50,200],[100,200,100]]
 * 输出: [[137,141,137],[141,138,141],[137,141,137]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): floor((100+200+200+50)/4) = floor(137.5) = 137
 * 对于点 (0,1), (1,0), (1,2), (2,1): floor((200+200+50+200+100+100)/6) = floor(141.666667) = 141
 * 对于点 (1,1): floor((50+200+200+200+200+100+100+100+100)/9) = floor(138.888889) = 138
 * <p>
 * m == img.length
 * n == img[i].length
 * 1 <= m, n <= 200
 * 0 <= img[i][j] <= 255
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-24 10:23:58
 */
public class ImageSmootherSolution {
    public static void main(String[] args) {
        final ImageSmootherSolution solution = new ImageSmootherSolution();

        int[][] img = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println("Input img : " + Arrays.deepToString(img));
        System.out.println("Output image smoother : " + Arrays.deepToString(solution.imageSmoother(img)));
        System.out.println();

        img = new int[][]{{100, 200, 100}, {200, 50, 200}, {100, 200, 100}};
        System.out.println("Input img : " + Arrays.deepToString(img));
        System.out.println("Output image smoother : " + Arrays.deepToString(solution.imageSmoother(img)));
        System.out.println();
    }

    /**
     * 1、遍历模拟 (由于过滤器是3*3，比较小，这样就可以了。当比较大时，我们可以采用矩阵前缀和进行优化，从而可以减少不少重复的计算)
     * - 时间复杂度 O(nmC) (C=9)
     * - 空间复杂度 O(1)
     *
     * @param img
     * @return
     */
    int[][] imageSmoother(final int[][] img) {
        m = img.length;
        n = img[0].length;
        final int[][] ans = new int[m][n];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                ans[x][y] = mockValue(img, x, y);
            }
        }
        return ans;
    }

    private int m;
    private int n;

    private int mockValue(final int[][] img, final int x, final int y) {
        int sum = img[x][y];
        int count = 1;
        for (int k = 0; k < DX.length; k++) {
            int nx = x + DX[k];
            int ny = y + DY[k];
            if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                continue;
            }
            sum += img[nx][ny];
            count++;
        }
        return sum / count;
    }

    private static final int[] DX = new int[]{0, 0, 1, 1, 1, -1, -1, -1};
    private static final int[] DY = new int[]{1, -1, 0, 1, -1, 0, 1, -1};
}
