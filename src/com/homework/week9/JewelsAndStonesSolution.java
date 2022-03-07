package com.homework.week9;

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
        System.out.println("Output number of jewels in stones : " + solution.numJewelsInStones(jewels, stones));
        System.out.println();

        jewels = "z";
        stones = "ZZ";
        System.out.println("Input jewels : " + jewels + ", stones : " + stones);
        System.out.println("Output number of jewels in stones : " + solution.numJewelsInStones(jewels, stones));
        System.out.println();
    }

    int numJewelsInStones(String jewels, String stones) {
        return 0;
    }
}
