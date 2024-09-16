package com.dailytraining.month202205;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 算法训练(2022-05-26) 掉落的方块 - (线段树) TODO
 * - https://leetcode.cn/problems/falling-squares/ (699题)
 * <p>
 * 在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
 * <p>
 * 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left 表示该方块最左边的点位置(positions[i][0])，side_length 表示该方块的边长(positions[i][1])。
 * <p>
 * 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
 * <p>
 * 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
 * <p>
 * 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positions[i] 表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
 * <p>
 * - 输入: [[1, 2], [2, 3], [6, 1]]
 * 输出: [2, 5, 5]
 * 解释:
 * <p>
 * 第一个方块 positions[0] = [1, 2] 掉落：
 * _aa
 * _aa
 * -------
 * 方块最大高度为 2 。
 * <p>
 * 第二个方块 positions[1] = [2, 3] 掉落：
 * __aaa
 * __aaa
 * __aaa
 * _aa__
 * _aa__
 * --------------
 * 方块最大高度为5。
 * 大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
 * <p>
 * 第三个方块 positions[1] = [6, 1] 掉落：
 * __aaa
 * __aaa
 * __aaa
 * _aa
 * _aa___a
 * --------------
 * 方块最大高度为5。
 * <p>
 * 因此，我们返回结果[2, 5, 5]。
 *  
 * - 输入: [[100, 100], [200, 100]]
 * 输出: [100, 100]
 * 解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
 * <p>
 * 1 <= positions.length <= 1000.
 * 1 <= positions[i][0] <= 10^8.
 * 1 <= positions[i][1] <= 10^6.
 *
 * @author bruce.zhu@snowballtech.com
 * @since 2022-05-26 03:36:21
 */
public class FallingSquaresSolution {

    public static void main(String[] args) {
        final FallingSquaresSolution solution = new FallingSquaresSolution();

        int[][] positions = new int[][]{{1, 2}, {2, 3}, {6, 1}};
        System.out.println("Input positions : " + Arrays.deepToString(positions));
        List<Integer> ans = solution.fallingSquares(positions);
        System.out.println("Output falling squares (max height) : " + ans);

        positions = new int[][]{{100, 100}, {200, 100}};
        System.out.println("Input positions : " + Arrays.deepToString(positions));
        ans = solution.fallingSquares(positions);
        System.out.println("Output falling squares (max height) : " + ans);
    }

    /**
     * 暴力枚举
     *
     * @param positions
     * @return
     */
    private List<Integer> fallingSquaresFirst(final int[][] positions) {
        int n = positions.length;
        List<Integer> heights = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int left1 = positions[i][0], right1 = positions[i][0] + positions[i][1];
            int additionalHeight = 0;
            for (int j = 0; j < i; j++) {
                int left2 = positions[j][0], right2 = positions[j][0] + positions[j][1];
                if (right1 > left2 && left1 < right2) {
                    additionalHeight = Math.max(additionalHeight, heights.get(j));
                }
            }
            heights.add(additionalHeight + positions[i][1]);
        }
        for (int i = 1; i < n; i++) {
            heights.set(i, Math.max(heights.get(i), heights.get(i - 1)));
        }
        return heights;
    }

    static class Node {
        //左范围
        private final int leftX;
        //右范围
        private final int rightX;
        //最大高度
        private final int maxHeight;
        //右边界
        private int maxR;
        //左子树和右子树
        Node leftChild, rightChild;

        public Node(int leftX, int rightX, int height, int maxR) {
            this.leftX = leftX;
            this.rightX = rightX;
            this.maxHeight = height;
            this.maxR = maxR;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public List<Integer> fallingSquares(int[][] positions) {
        //创建返回值
        List<Integer> res = new ArrayList<>();
        //根节点，默认为零
        Node root = null;
        //目前最高的高度
        int curMax = 0;
        for (int[] position : positions) {
            //左坐标
            int left = position[0];
            //右坐标
            int right = position[0] + position[1];
            //边长
            int edge = position[1];
            //当前最高的高度
            int curMaxHigh = query(root, left, right);
            //更新线段树 给所有范围更新最大值为curMaxHigh + edge
            root = update(root, left, right, curMaxHigh + edge);
            //高度比较
            curMax = Math.max(curMax, curMaxHigh + edge);
            res.add(curMax);
        }
        return res;
    }

    /**
     * 做区间更新
     *
     * @param root   根节点
     * @param left   左范围
     * @param right  右范围
     * @param height 高度
     * @return
     */
    private Node update(Node root, int left, int right, int height) {
        if (root == null) {
            return new Node(left, right, height, right);
        }
        if (left <= root.leftX) {
            root.leftChild = update(root.leftChild, left, right, height);
        } else {
            root.rightChild = update(root.rightChild, left, right, height);
        }
        root.maxR = Math.max(right, root.maxR);
        return root;
    }

    /**
     * 查询范围内的最大高度
     *
     * @param root  根节点
     * @param left  左范围
     * @param right 右范围
     * @return
     */
    private int query(Node root, int left, int right) {
        //如果新节点的左边界大于等于当前树的maxR的话,不需要遍历整颗树
        if (root == null || left >= root.maxR) {
            return 0;
        }
        int curHeight = 0;
        //是否有交集
        if (right > root.leftX && left < root.rightX) {
            curHeight = root.maxHeight;
        }
        curHeight = Math.max(curHeight, query(root.leftChild, left, right));
        if (right > root.leftX) {
            curHeight = Math.max(curHeight, query(root.rightChild, left, right));
        }
        return curHeight;
    }
}
