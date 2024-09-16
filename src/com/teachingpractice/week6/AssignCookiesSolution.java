package com.teachingpractice.week6;

import java.util.Arrays;

/**
 * 算法实现：贪心 - 分配饼干问题
 * - https://leetcode-cn.com/problems/assign-cookies/description/ (455题)
 * <p>
 * - 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 * - 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * <p>
 * - 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 * <p>
 * 1 <= g.length <= 3 * 10^4
 * 0 <= s.length <= 3 * 10^4
 * 1 <= g[i], s[j] <= 2^31 - 1
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-18 10:14:04
 */
public class AssignCookiesSolution {
    public static void main(String[] args) {
        final AssignCookiesSolution solution = new AssignCookiesSolution();

        int[] g = new int[]{1, 2, 3};
        int[] s = new int[]{1, 1};
        System.out.println("Input g : " + Arrays.toString(g) + ", s : " + Arrays.toString(s));
        System.out.println("Output maximum satisfied : " + solution.findContentChildren(g, s));
        System.out.println("Output maximum satisfied (second) : " + solution.findContentChildrenSecond(g, s));
        System.out.println();

        g = new int[]{1, 2};
        s = new int[]{1, 2, 3};
        System.out.println("Input g : " + Arrays.toString(g) + ", s : " + Arrays.toString(s));
        System.out.println("Output maximum satisfied : " + solution.findContentChildren(g, s));
        System.out.println("Output maximum satisfied (second) : " + solution.findContentChildrenSecond(g, s));
    }

    /**
     * 贪心：小孩子分小饼干，大孩子分大饼干
     * - 方案1：太小的饼干没人吃 (找到可以被该饼干满足的最小胃口) - 从最大的饼干开始
     *
     * @param g
     * @param s
     * @return
     */
    int findContentChildren(final int[] g, final int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        int i = g.length - 1;
        for (int j = s.length - 1; j >= 0; j--) {
            while (i >= 0 && g[i] > s[j]) {
                i--;
            }
            if (i >= 0) {
                ans++;
                i--;
            }
        }
        return ans;
    }

    /**
     * 贪心：小孩子分小饼干，大孩子分大饼干
     * - 方案2：太大的胃口无法满足 (找到能满足胃口的最小的饼干) - 从最小的胃口开始
     *
     * @param g
     * @param s
     * @return
     */
    int findContentChildrenSecond(final int[] g, final int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ans = 0;
        int j = 0;
        for (int i = 0; i < g.length; i++) {
            while (j < s.length && g[i] > s[j]) {
                j++;
            }
            if (j < s.length) {
                ans++;
                j++;
            }
        }
        return ans;
    }
}
