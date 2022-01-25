package com.teachingpractice.week4;

import java.util.*;

/**
 * 算法实现：BFS-最小基因变化
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
 * <p>
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
 * - bank: ["AACCGGTA", "AAAAGGTA"]
 * - 返回值: -1
 * <p>
 * - start: "AAAAACCC"
 * - end:   "AACCCCCC"
 * - bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 * - 返回值: 3
 * <p>
 * 设计思想：
 * - 每次只变化一个基因因子, 所以是每层递归一个字符, 最多递归8次, 不变的位不递归
 * - 使用BFS更适合计算最小深度, 因为一旦达到 base case (构建出新的基因序列与end序列一致), 该时刻depth就是第一个最小深度, 后续就不用再计算了
 * - 这里有一个有趣的问题, 就是基因序列上的一个因子, 可以经过几次变化之后, 还变回自己, 这符合生物学基因性质 (由于求最小深度, 只要连续两次不是变化的同一个因子即可)
 * <p>
 * - 特别关注: BFS求最小depth问题, 该层的depth使用Map记录, 该层的每个节点作为key, 是一种简洁的实现模板 (当然我们也可以想办法让queue中poll出的数据清晰地知道自己的depth)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-21 11:17:46
 */
public class MinGeneticMutationSolution {
    public static void main(String[] args) {
        final MinGeneticMutationSolution solution = new MinGeneticMutationSolution();

        String[] bank = new String[]{"AACCGGTA"};
        String start = "AACCGGTT";
        String end = "AACCGGTA";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + Arrays.toString(bank));
        System.out.println("Output min mutation : " + solution.minMutation(start, end, bank));
        System.out.println("Output min mutation convert : " + solution.minMutationConvert(start, end, bank));

        bank = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        start = "AACCGGTT";
        end = "AAACGGTA";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + Arrays.toString(bank));
        System.out.println("Output min mutation : " + solution.minMutation(start, end, bank));
        System.out.println("Output min mutation convert : " + solution.minMutationConvert(start, end, bank));

        bank = new String[]{"AACCGGTA", "AAAAGGTA"};
        start = "AACCGGTT";
        end = "AAACGGTA";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + Arrays.toString(bank));
        System.out.println("Output min mutation : " + solution.minMutation(start, end, bank));
        System.out.println("Output min mutation convert : " + solution.minMutationConvert(start, end, bank));

        bank = new String[]{"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        start = "AAAAACCC";
        end = "AACCCCCC";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + Arrays.toString(bank));
        System.out.println("Output min mutation : " + solution.minMutation(start, end, bank));
        System.out.println("Output min mutation convert : " + solution.minMutationConvert(start, end, bank));

        bank = new String[]{"AACCGATT", "AACCGATA", "AAACGATA", "AAACGGTA"};
        start = "AACCGGTT";
        end = "AAACGGTA";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + Arrays.toString(bank));
        System.out.println("Output min mutation : " + solution.minMutation(start, end, bank));
        System.out.println("Output min mutation convert : " + solution.minMutationConvert(start, end, bank));

        bank = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};
        start = "AACCGGTT";
        end = "AACCGCTA";
        System.out.println("Input start : " + start + ", end : " + end + ", bank : " + Arrays.toString(bank));
        System.out.println("Output min mutation : " + solution.minMutation(start, end, bank));
        System.out.println("Output min mutation convert : " + solution.minMutationConvert(start, end, bank));
    }

    /**
     * 采用BFS实现
     * (时间复杂度O(4^n) 空间复杂度O(n))
     *
     * @param start
     * @param end
     * @param bank
     * @return
     */
    private int minMutation(final String start, final String end, final String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        if (bank == null || bank.length == 0) {
            return -1;
        }

        final Set<String> bankHash = new HashSet<>(bank.length);
        for (final String s : bank) {
            bankHash.add(s);
        }
        if (!bankHash.contains(end)) {
            return -1;
        }

        final Set<String> used = new HashSet<>();
        currentChangeIndex = 8;

        final Map<String, Integer> depthMap = new HashMap<>(TOTAL_LENGTH);
        depthMap.put(start, 0);
        String temp, after;
        final StringBuilder sb = new StringBuilder();

        final Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            used.add(temp);

            for (int i = 0; i < TOTAL_LENGTH; i++) {
                if (currentChangeIndex == i) {
                    continue;
                }
                currentChangeIndex = i;

                for (int j = 0; j < CHANGE_CHAR.length; j++) {
                    sb.delete(0, sb.length());
                    sb.append(temp);
                    if (sb.charAt(i) == CHANGE_CHAR[j]) {
                        continue;
                    }
                    sb.setCharAt(i, CHANGE_CHAR[j]);
                    after = sb.toString();

                    // base case (after == end)
                    if (after.equals(end)) {
                        return depthMap.get(temp) + 1;
                    }

                    if (!bankHash.contains(after)) {
                        continue;
                    }
                    if (used.contains(after)) {
                        continue;
                    }

                    // 成功完成一个点后, 深度+1
                    depthMap.put(after, depthMap.get(temp) + 1);
                    used.add(after);
                    queue.offer(after);
                }
            }
        }

        return -1;
    }

    private static final int TOTAL_LENGTH = 8;
    private static final char[] CHANGE_CHAR = new char[]{'A', 'C', 'G', 'T'};

    /**
     * 基于求最小depth, 那么连续2次在同一个index上进行变化, 是无效的 (为剪枝此场景, 与上一个设置的currentChangeIndex相比即可, 这里的index是指changeIndexes的下标)
     */
    private int currentChangeIndex;

    /******************************************************************************************************************************************************/

    private int minMutationConvert(final String start, final String end, final String[] bank) {
        // 特殊边界1: end与start相同, 基因序列无须变化
        if (end.equals(start)) {
            return 0;
        }
        // 特殊边界2: bank中没有元素, 基因序列没有合法的变化可能
        if (bank == null || bank.length == 0) {
            return -1;
        }
        // start, end, bank均转化为int进行处理
        final int startValue = gene2Int(start);
        final int endValue = gene2Int(end);
        final Set<Integer> validBank = new HashSet<>();
        for (final String s : bank) {
            validBank.add(gene2Int(s));
        }
        // 特殊边界3: end不在bank中, 基因序列没有合法的变化可能
        if (!validBank.contains(endValue)) {
            return -1;
        }

        currentChangeIndex = 8;
        final Set<Integer> used = new HashSet<>();
        used.add(startValue);

        int[] temp;
        int singlePart;
        int afterValue;
        // queue存储的元素: 包含2个int的数组 (分别存储gene, depth)
        final Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startValue, 0});
        while (!queue.isEmpty()) {
            temp = queue.poll();

            for (int i = 0; i < TOTAL_LENGTH; i++) {
                if (currentChangeIndex == i) {
                    // 连续2次都在同一个位置变化, 与最小路径没有意义
                    continue;
                }
                currentChangeIndex = i;

                singlePart = (temp[0] >> (i << 1)) & 3;
                for (int j = 0; j < CHANGE_CHAR.length; j++) {
                    if (singlePart == j) {
                        // 只有不一样才算是变化了一次
                        continue;
                    }
                    afterValue = temp[0] + ((j - singlePart) << (i << 1));

                    // base case (afterValue = endValue)
                    if (afterValue == endValue) {
                        return temp[1] + 1;
                    }

                    // 如果重复, 该分支无效
                    if (used.contains(afterValue)) {
                        continue;
                    }

                    // invalid, 该分支无效
                    if (!validBank.contains(afterValue)) {
                        continue;
                    }

                    // valid, 入队列, 允许访问更深层 (并在访问其他分支前先标记为used)
                    used.add(afterValue);
                    queue.offer(new int[]{afterValue, temp[1] + 1});
                }
            }
        }
        return -1;
    }

    /**
     * 最大只有8位基因字符, 每个字符映射为2个bit位(使用二进制而不是十进制, 有利于后面的计算), 在int范围内
     *
     * @param gene
     * @return
     */
    private int gene2Int(final String gene) {
        int result = 0;
        for (int i = 0; i < gene.length(); i++) {
            result = (result << 2) | geneChar2Index(gene.charAt(i));
        }
        return result;
    }

    private int geneChar2Index(final char c) {
        for (int i = 0; i < CHANGE_CHAR.length; i++) {
            if (CHANGE_CHAR[i] == c) {
                return i;
            }
        }
        throw new RuntimeException("Input Gene data error");
    }
}
