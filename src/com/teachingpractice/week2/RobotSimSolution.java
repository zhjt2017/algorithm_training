package com.teachingpractice.week2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 模拟行走机器人
 * - 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 处开始出发，面向北方。该机器人可以接收以下三种类型的命令 commands
 * -2 ：向左转 90 度
 * -1 ：向右转 90 度
 * 1 <= x <= 9 ：向前移动 x 个单位长度
 * <p>
 * 在网格上有一些格子被视为障碍物 obstacles 。第 i 个障碍物位于网格点  obstacles[i] = (xi, yi)
 * 机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。
 * 返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）
 * 北表示 +Y 方向。
 * 东表示 +X 方向。
 * 南表示 -Y 方向。
 * 西表示 -X 方向。
 * <p>
 * 输入：commands = [4,-1,3], obstacles = []
 * 输出：25
 * 解释：
 * 机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 3 个单位，到达 (3, 4)
 * 距离原点最远的是 (3, 4) ，距离为 3^2 + 4^2 = 25
 * <p>
 * 输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * 输出：65
 * 解释：机器人开始位于 (0, 0)：
 * 1. 向北移动 4 个单位，到达 (0, 4)
 * 2. 右转
 * 3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
 * 4. 左转
 * 5. 向北走 4 个单位，到达 (1, 8)
 * 距离原点最远的是 (1, 8) ，距离为 1*2 + 8*2 = 65
 * <p>
 * 1 <= commands.length <= 10^4
 * commands[i] is one of the values in the list [-2,-1,1,2,3,4,5,6,7,8,9].
 * 0 <= obstacles.length <= 10^4
 * -3 * 10^4 <= xi, yi <= 3 * 10^4
 * 答案保证小于 2^31
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-07 08:13:48
 */
public class RobotSimSolution {
    public static void main(String[] args) {
        int[] commands = new int[]{4, -1, 3};
        int[][] obstacles = new int[][]{};
        System.out.println("commands = " + Arrays.toString(commands) + ", obstacles = " + obstacles);
        System.out.println("square of max distance : " + robotSim(commands, obstacles));

        commands = new int[]{4, -1, 4, -2, 4};
        obstacles = new int[][]{{2, 4}};
        System.out.println("commands = " + Arrays.toString(commands) + ", obstacles = " + obstacles);
        System.out.println("square of max distance : " + robotSim(commands, obstacles));
    }

    /**
     * 设计思想：每个点都有可能走到，但是为每个点都标记上其是否是障碍物，就很浪费空间，我们可以将所有障碍物所在坐标作为一个集合，O(1)查询走的下一步是否有障碍物
     * 时间复杂度：O(n+k)
     * 空间复杂度：O(k)
     * 如果一直超一个方向走，并且步数还不小，则很可能超出网格的边界范围，但根据题目本身的意思，不会发生这种情况，即所给出的commands配合obstacles可以保证一定能走完最后一步，则边界情况无须考虑
     * Set的hash算法问题，数组没有hash算法，我们自定义整数用来唯一标识每个障碍物的坐标，-3w-3w这个范围，分别占用高16位与低16位，进行位运算，则可以使用int范围来表达
     *
     * @param commands
     * @param obstacles
     * @return
     */
    private static int robotSim(final int[] commands, final int[][] obstacles) {
        int result = 0;
        final Set<Integer> obstaclesHashSet = new HashSet<>();
        for (final int[] obstacle : obstacles) {
            obstaclesHashSet.add(hash(obstacle[0], obstacle[1]));
        }

        // 初始方向为北方 (顺时针index递增：direction=0为北方, =1为东方, =2为南方, =3为西方), -2表示左转, -1表示右转 (dx结合dy表示在direction索引上，该方向走一步的坐标改变值)
        int direction = 0;
        // 定义robot当前所在点, 从原点开始
        int x = 0, y = 0;
        final int[] dx = new int[]{0, 1, 0, -1};
        final int[] dy = new int[]{1, 0, -1, 0};
        int nx, ny;
        for (final int step : commands) {
            if (step == -2) {
                direction = (direction + 3) % 4;
                continue;
            }
            if (step == -1) {
                direction = (direction + 1) % 4;
                continue;
            }
            for (int i = 0; i < step; i++) {
                nx = x + dx[direction];
                ny = y + dy[direction];
                // 遇障碍物停留在障碍物的前一个网格方块上, 并要换一个方向才能继续走
                if (obstaclesHashSet.contains(hash(nx, ny))) {
                    break;
                }
                x = nx;
                y = ny;
                result = Math.max(result, x * x + y * y);
            }
        }
        return result;
    }

    private static int hash(final int x, final int y) {
        return (x << 16) | (y & 0xFFFF);
    }

}
