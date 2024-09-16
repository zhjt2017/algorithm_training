package com.teachingpractice.week3.graph;

import com.homework.week3.CourseScheduleAdvancedSolution;

/**
 * 算法实现：图-课程表
 * - 相关链接：课程表2
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see CourseScheduleAdvancedSolution
 *
 * <p>
 * - 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1
 * - requisite, requisites, prerequisites
 * <p>
 * - 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * - 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * - 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * - 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * <p>
 * - 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * <p>
 * - 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 * @since 2022-01-16 03:44:37
 */
public class CourseScheduleSolution {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        System.out.println("Input numCourses : " + numCourses + ", prerequisites : " + prerequisites);
        System.out.println("Output canFinish : " + canFinish(numCourses, prerequisites));

        numCourses = 2;
        prerequisites = new int[][]{{1, 0}, {0, 1}};
        System.out.println("Input numCourses : " + numCourses + ", prerequisites : " + prerequisites);
        System.out.println("Output canFinish : " + canFinish(numCourses, prerequisites));

        numCourses = 1;
        prerequisites = new int[][]{};
        System.out.println("Input numCourses : " + numCourses + ", prerequisites : " + prerequisites);
        System.out.println("Output canFinish : " + canFinish(numCourses, prerequisites));

        numCourses = 4;
        prerequisites = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println("Input numCourses : " + numCourses + ", prerequisites : " + prerequisites);
        System.out.println("Output canFinish : " + canFinish(numCourses, prerequisites));
    }

    public static boolean canFinish(final int numCourses, final int[][] prerequisites) {
        return false;
    }

}
