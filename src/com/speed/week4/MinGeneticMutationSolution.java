package com.speed.week4;

/**
 * 算法实现：最小基因变化
 * - https://leetcode-cn.com/problems/minimum-genetic-mutation/ (433题)
 * <p>
 * - 一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
 * <p>
 * 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。
 * 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。
 * 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。
 * <p>
 * - 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，
 * - 请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。
 * <p>
 * 注意点：
 * 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
 * 如果一个起始基因序列需要多次变化，那么它每一次变化之后的基因序列都必须是合法的。
 * 假定起始基因序列与目标基因序列是不一样的。
 *  
 * - start: "AACCGGTT"
 * - end:   "AACCGGTA"
 * - bank: ["AACCGGTA"]
 * - 返回值: 1
 * <p>
 * - start: "AACCGGTT"
 * - end:   "AAACGGTA"
 * - bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 * - 返回值: 2
 * <p>
 * - start: "AACCGGTT"
 * - end:   "AAACGGTA"
 * - bank: ["AACCGGTA", "AAACGGTA"]
 * - 返回值: -1
 * <p>
 * - start: "AAAAACCC"
 * - end:   "AACCCCCC"
 * - bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * - 返回值: 3
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-21 11:17:46
 */
public class MinGeneticMutationSolution {
    public static void main(String[] args) {
        String[] bank = new String[]{"AACCGGTA"};
        String start = "AACCGGTT";
        String end = "";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + bank);
        System.out.println("Output min mutation : " + minMutation(start, end, bank));

        bank = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        start = "AACCGGTT";
        end = "AAACGGTA";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + bank);
        System.out.println("Output min mutation : " + minMutation(start, end, bank));

        bank = new String[]{"AACCGGTA", "AAACGGTA"};
        start = "AACCGGTT";
        end = "AAACGGTA";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + bank);
        System.out.println("Output min mutation : " + minMutation(start, end, bank));

        bank = new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        start = "AAAAACCC";
        end = "AACCCCCC";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + bank);
        System.out.println("Output min mutation : " + minMutation(start, end, bank));
    }

    private static int minMutation(final String start, final String end, final String[] bank) {
        return -1;
    }
}
