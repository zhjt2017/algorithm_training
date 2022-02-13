package com.teachingpractice.week5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现: 合并区间
 * - https://leetcode-cn.com/problems/merge-intervals/ (56题)
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * <p>
 * 分析题意: 或的关系, 区间(重叠部分)求并集, 即合并区间(的表示)
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 06:19:03
 */
public class MergeIntervalsSolution {
    public static void main(String[] args) {
        final MergeIntervalsSolution solution = new MergeIntervalsSolution();

        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        System.out.println("Input intervals : " + Arrays.deepToString(intervals));
        System.out.println("Output merged intervals : " + Arrays.deepToString(solution.merge(intervals)));
        System.out.println();
        intervals = new int[][]{{1, 4}, {4, 5}};
        System.out.println("Input intervals : " + Arrays.deepToString(intervals));
        System.out.println("Output merged intervals : " + Arrays.deepToString(solution.merge(intervals)));
    }

    /**
     * 先将区间进行排序 (不妨就使用jdk自带的快速排序)，那么直到找到某一个区间元素，其左区间 > 其前一个区间元素的右区间，其才是第一个不重合区间(其再作为新的一组)，重合区间刷新前面一组的右区间
     * - 时间复杂度 O(NlogN)
     * - 空间复杂度 O(logN)
     *
     * @param intervals
     * @return
     */
    int[][] merge(final int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        final List<int[]> list = new LinkedList<>();
        // 放入结果集中的每一个解都是新建的，这样原区间数组不受影响
        int[] ans = new int[]{intervals[0][0], intervals[0][1]};
        list.add(ans);
        for (final int[] one : intervals) {
            if (one[0] > ans[1]) {
                ans = new int[]{one[0], one[1]};
                list.add(ans);
            } else if (one[1] > ans[1]) {
                // 对于start小的区间, end未必小, 故end需要取较大值
                ans[1] = one[1];
            }
        }
        return list.toArray(new int[list.size()][2]);
    }

}
