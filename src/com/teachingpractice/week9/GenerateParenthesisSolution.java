package com.teachingpractice.week9;

import java.util.LinkedList;
import java.util.List;

/**
 * 算法实现：高级搜索 - 搜索剪枝 - 括号生成
 * - https://leetcode-cn.com/problems/generate-parentheses/ (22题)
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
 * <p>
 * 设计思想1与设计思想2 见 com.teachingpractice.week3.divideandconquer.GenerateParenthesisSolution
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see com.teachingpractice.week3.divideandconquer.GenerateParenthesisSolution
 * @since 2022-03-17 05:24:51
 */
public class GenerateParenthesisSolution {
    public static void main(String[] args) {
        final GenerateParenthesisSolution solution = new GenerateParenthesisSolution();

        for (int n = 1; n <= 8; n++) {
            System.out.println("n = " + n);
            List<String> ans = solution.generateParenthesis(n);
            if (n >= 6) {
                System.out.println("result size : " + ans.size());
            } else {
                System.out.println("result size : " + ans.size() + ", content : " + ans);
            }
            System.out.println();
        }
    }

    private List<String> generateParenthesis(final int n) {
        final List<String> ans = new LinkedList<>();
        return ans;
    }

}
