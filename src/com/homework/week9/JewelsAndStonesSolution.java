package com.homework.week9;

import java.util.HashSet;

/**
 * 算法实现：字符串处理 - 基础问题 - 宝石与石头
 * - https://leetcode-cn.com/problems/jewels-and-stones/ (771题)
 * <p>
 *  给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 * <p>
 * - 输入：jewels = "aA", stones = "aAAbbbb"
 * 输出：3
 * <p>
 * - 输入：jewels = "z", stones = "ZZ"
 * 输出：0
 * <p>
 * 1 <= jewels.length, stones.length <= 50
 * jewels 和 stones 仅由英文字母组成
 * jewels 中的所有字符都是 唯一的
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-07 07:56:42
 */
public class JewelsAndStonesSolution {
    public static void main(String[] args) {
        final JewelsAndStonesSolution solution = new JewelsAndStonesSolution();

        String jewels = "aA";
        String stones = "aAAbbbb";
        System.out.println("Input jewels : " + jewels + ", stones : " + stones);
        System.out.println("Output number of jewels in stones (set) : " + solution.numJewelsInStones(jewels, stones));
        System.out.println("Output number of jewels in stones (boolean[]) : " + solution.numJewelsInStonesArray(jewels, stones));
        System.out.println();

        jewels = "z";
        stones = "ZZ";
        System.out.println("Input jewels : " + jewels + ", stones : " + stones);
        System.out.println("Output number of jewels in stones (set) : " + solution.numJewelsInStones(jewels, stones));
        System.out.println("Output number of jewels in stones (boolean[]) : " + solution.numJewelsInStonesArray(jewels, stones));
        System.out.println();
    }

    /**
     * 本题求的是总共拥有多少颗宝石
     * 还可以求拥有多少类型的宝石
     *
     * @param jewels
     * @param stones
     * @return
     */
    int numJewelsInStones(final String jewels, final String stones) {
        final HashSet<Character> validSet = new HashSet<>();
        final int k = jewels.length();
        for (int i = 0; i < k; i++) {
            validSet.add(jewels.charAt(i));
        }
        final int n = stones.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (validSet.contains(stones.charAt(i))) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 根据题意，由于jewels 和 stones 仅由英文字母组成(量有限且浪费较少)，则可以在数据结构不使用HashSet，而是用boolean[]
     *
     * @param jewels
     * @param stones
     * @return
     */
    int numJewelsInStonesArray(final String jewels, final String stones) {
        final boolean[] valid = new boolean['z' + 1];
        final int k = jewels.length();
        for (int i = 0; i < k; i++) {
            valid[jewels.charAt(i)] = true;
        }
        final int n = stones.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (valid[stones.charAt(i)]) {
                ans++;
            }
        }
        return ans;
    }
}
