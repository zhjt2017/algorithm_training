package com.teachingpractice.week3.divide_and_conquer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 算法实现：分治-括号生成
 * - leetcode-cn 22题
 * <p>
 * - 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * - 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * <p>
 * - 输入：n = 1
 * 输出：["()"]
 * <p>
 * 1 <= n <= 8
 * <p>
 * 想法：
 * 1、递归的思想：验证括号组合是否有效：遍历整个字符串，右括号的计数永远<=左括号的计数，直到字符串结束，二者相等 (这是将左括号、右括号单独看待的思路)
 * 2、分治的思想：始终尝试拆分为左右2个部分，并要关注第一个不可划分的整体
 * <p>
 * 设计思想：
 * 1.1、有验证方法(O(n)验证)，那么我们可以把所有的左右括号的组合都列出来，然后筛选，整体时间复杂度：O(n*2^(2*n))，空间复杂度：O(n) (递归深度2*n)
 * 1.2、仍然使用枚举列出法，就先判断当前左括号-右括号的差，从而不用再进行事后筛选，空间复杂度：O(n) (递归深度2*n)不变，整体时间复杂度：O(2^(2*n))
 * - 这就相当于，第一个字符是左括号，第二个字符有左括号右括号2个子节点，第三个字符，就是二叉树的第三层，最右边一个节点是空的，依次往下，深度为2n的二叉树，有不少子树的右子节点是空的
 * - 额外使用2个变量leftCount,rightCount分别维护当前已经填了几个左括号几个右括号，于是base case为leftCount,rightCount都到达了最大值，则递归到达最大深度
 * - 该思路下，没有任何重复计算的环节，所以已经是最优了。这也是人工枚举时的一种做法。这是将左括号、右括号单独看待的思路。
 * <p>
 * 2、人工枚举，也有另一种思路，就是分治。就像是剥洋葱一样的，左右二分，超过1对的继续二分(为了保证不重复，定义左边不止一对括号的，那么必然其有一对括号是套在其最外面的)。
 * 这是左右括号放在一起一对一对看待的思路。这个相当于左边先把一对括号子套起来，然后递归的每一层都把该层左右拆分为不同的对数，分别对左和右进行下层递归，
 * 也是要求定义左边不止一对括号的，那么必然其有一对括号是套在其最外面的，于是递归深度为O(logN)
 * - 这种想法，要能够保证不遗漏且不重复，有一个要点要注意：找到第一个不可划分的整体，每层都是二分，即分为A和B两个不可划分的整体，并且无论元素多少A作为第一个不可划分的整体都必须外面有一个套子
 * - S (A)B (第一段A及其外面的一个括号作为套子，第二段B，第一段共k对括号，枚举k)
 * - n k-1 n-k
 * - k=1 A有0对括号 B为2对括号 ()()(), ()(())
 * - k=2 A有1对括号 B为1对括号 (())()
 * - k=3 A有2对括号 B为0对括号 (()()) ((()))
 * - 这里要说明分治思想的算法中的一个有意思的点：可以记忆各个分治子模块的计算值，重复利用 (这也是本题解中思路2比思路1来得更有价值的地方)
 * - 当然关于分治，于此其实我们还有一个更为深刻的思想：思路1就像是事必躬亲，思路2就是放权去做，不断分出去，直到最终完成，也就是说特别大的时候，可以采用分布式实现
 * ---- 另一方面，放权下去，自然也可以利用下面创造出来的价值，全局推广，重复利用，以高效组装出最终的输出价值 (如果是汇总各处的统计数据，而非逻辑出最终的所有花名册及其安排的位置，效果会更加显著)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-14 11:56:07
 */
public class GenerateParenthesisSolution {
    public static void main(String[] args) {
        int n = 1;
        System.out.println("n = " + n);
        System.out.println("result : " + generateParenthesis(n));
        System.out.println("result : " + generateParenthesisDivideAndConquer(n));
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());

        n = 2;
        System.out.println("n = " + n);
        System.out.println("result : " + generateParenthesis(n));
        System.out.println("result : " + generateParenthesisDivideAndConquer(n));
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());

        n = 3;
        System.out.println("n = " + n);
        System.out.println("result : " + generateParenthesis(n));
        System.out.println("result : " + generateParenthesisDivideAndConquer(n));
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());

        n = 4;
        System.out.println("n = " + n);
        System.out.println("result : " + generateParenthesis(n));
        System.out.println("result : " + generateParenthesisDivideAndConquer(n));
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());

        n = 5;
        System.out.println("n = " + n);
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());

        n = 6;
        System.out.println("n = " + n);
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());

        n = 7;
        System.out.println("n = " + n);
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());

        n = 8;
        System.out.println("n = " + n);
        System.out.println("result size : " + generateParenthesisDivideAndConquer(n).size());
    }

    private static List<String> generateParenthesis(final int n) {
        GenerateParenthesisSolution solution = new GenerateParenthesisSolution();
        solution.leftCount = 0;
        solution.rightCount = 0;
        solution.maxPairs = n;
        solution.sb = new StringBuilder();
        solution.result = new LinkedList<>();
        solution.recur();
        return solution.result;
    }

    private static List<String> generateParenthesisDivideAndConquer(final int n) {
        GenerateParenthesisSolution solution = new GenerateParenthesisSolution();
        solution.restore = new HashMap<>();
        return solution.divideAndConquer(n);
    }

    private List<String> divideAndConquer(final int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        if (this.restore.containsKey(n)) {
            return this.restore.get(n);
        }

        final List<String> result = new LinkedList<>();
        final StringBuilder sb = new StringBuilder();
        List<String> partA, partB;
        // k的遍历为“加法原理”，partA与partB的内部组合，为乘法原理，于是直接构成最终的输出结果集 (直奔最终结果，不用剪枝)
        for (int k = 1; k <= n; k++) {
            partA = divideAndConquer(k - 1);
            partB = divideAndConquer(n - k);
            // 注意一点，在partA没有数据的时候，A外面还有一个壳，当partB没有数据的时候，整体其实就是A及其外面那个壳，这也是一个解，不要忽略它
            sb.delete(0, sb.length());
            if (partA.isEmpty()) {
                sb.append(PARENTHESES_LEFT).append(PARENTHESES_RIGHT);
                if (partB.isEmpty()) {
                    result.add(sb.toString());
                    continue;
                }
                for (final String b : partB) {
                    result.add(sb.append(b).toString());
                    sb.delete(2, sb.length());
                }
                continue;
            }

            for (final String a : partA) {
                sb.delete(0, sb.length());
                sb.append(PARENTHESES_LEFT).append(a).append(PARENTHESES_RIGHT);
                if (partB.isEmpty()) {
                    result.add(sb.toString());
                    continue;
                }
                for (final String b : partB) {
                    result.add(sb.append(b).toString());
                    sb.delete(a.length() + 2, sb.length());
                }
            }
        }
        // 记忆f(n)
        this.restore.put(n, result);
        return result;
    }

    private void recur() {
        /*
        1、左括号全部填完的情形：如果右括号也全部填完，到达base case，否则本次填写右括号
        2、左括号全部填完的情形：一定可以填写左括号，也可以在满足条件下填写右括号 (即可能二叉)
         */
        final int restoreIndex = this.leftCount + this.rightCount;
        if (this.leftCount == this.maxPairs) {
            this.rightCount++;
            this.sb.append(PARENTHESES_RIGHT);
            if (this.rightCount == this.maxPairs) {
                this.result.add(sb.toString());
            } else {
                this.recur();
            }
            // 还原现场
            this.rightCount--;
            this.sb.delete(restoreIndex, restoreIndex + 1);
            return;
        }

        this.leftCount++;
        this.sb.append(PARENTHESES_LEFT);
        this.recur();
        // 还原现场
        this.leftCount--;
        this.sb.delete(restoreIndex, restoreIndex + 1);

        if (this.leftCount > this.rightCount) {
            this.rightCount++;
            this.sb.append(PARENTHESES_RIGHT);
            this.recur();
            // 还原现场
            this.rightCount--;
            this.sb.delete(restoreIndex, restoreIndex + 1);
        }
    }

    private static final char PARENTHESES_LEFT = '(';
    private static final char PARENTHESES_RIGHT = ')';
    private int leftCount;
    private int rightCount;
    private int maxPairs;
    private StringBuilder sb;
    private List<String> result;

    private Map<Integer, List<String>> restore;
}
