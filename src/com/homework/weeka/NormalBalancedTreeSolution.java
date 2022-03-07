package com.homework.weeka;

/**
 * 算法实现：普通平衡树
 * - https://www.acwing.com/problem/content/255/
 * <p>
 * 您需要写一种数据结构（可参考题目标题），来维护一些数，其中需要提供以下操作：
 * <p>
 * 插入数值 x。
 * 删除数值 x(若有多个相同的数，应只删除一个)。
 * 查询数值 x 的排名(若有多个相同的数，应输出最小的排名)。
 * 查询排名为 x 的数值。
 * 求数值 x 的前驱(前驱定义为小于 x 的最大的数)。
 * 求数值 x 的后继(后继定义为大于 x 的最小的数)。
 * 注意： 数据保证查询的结果一定存在。
 * <p>
 * 输入格式
 * 第一行为 n，表示操作的个数。
 * <p>
 * 接下来 n 行每行有两个数 opt 和 x，opt 表示操作的序号(1≤opt≤6)。
 * <p>
 * 输出格式
 * 对于操作 3,4,5,6 每行输出一个数，表示对应答案。
 * <p>
 * 数据范围
 * 1≤n≤100000,所有数均在 −107 到 107 内。
 * <p>
 * 输入样例：
 * 8
 * 1 10
 * 1 20
 * 1 30
 * 3 20
 * 4 2
 * 2 10
 * 5 25
 * 6 -1
 * 输出样例：
 * 2
 * 20
 * 20
 * 20
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 03:48:19
 */
public class NormalBalancedTreeSolution {
    public static void main(String[] args) {

    }
}
