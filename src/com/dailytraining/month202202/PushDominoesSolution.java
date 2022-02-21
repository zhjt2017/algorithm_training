package com.dailytraining.month202202;

/**
 * 算法训练(2022-02-21)：推多米诺排骨
 * - https://leetcode-cn.com/problems/push-dominoes/ (838题)
 * <p>
 * - n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * - 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * - 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * - 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * <p>
 * - 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * - dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * - dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * - dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * - 返回表示最终状态的字符串。
 * <p>
 * - 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * <p>
 * - 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 * <p>
 * n == dominoes.length
 * 1 <= n <= 10^5
 * dominoes[i] 为 'L'、'R' 或 '.'
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-21 10:29:54
 */
public class PushDominoesSolution {
    public static void main(String[] args) {
        final PushDominoesSolution solution = new PushDominoesSolution();

        String dominoes = "RR.L";
        System.out.println("Input dominoes : " + dominoes);
        System.out.println("Output push dominoes result : " + solution.pushDominoes(dominoes));
        System.out.println("Output push dominoes (mock) result : " + solution.pushDominoesMock(dominoes));
        System.out.println();

        dominoes = ".L.R...LR..L..";
        System.out.println("Input dominoes : " + dominoes);
        System.out.println("Output push dominoes result : " + solution.pushDominoes(dominoes));
        System.out.println("Output push dominoes (mock) result : " + solution.pushDominoesMock(dominoes));
    }

    /**
     * @param dominoes
     * @return
     */
    String pushDominoes(final String dominoes) {
        return "";
    }

    /**
     * 模拟：
     * 如果两边的骨牌同向(LL或RR)，那么这段连续的竖立骨牌会倒向同一方向。
     * 如果两边的骨牌相对(RL)，那么这段骨牌会向中间倒。
     * 如果两边的骨牌相反(LR)，那么这段骨牌会保持竖立。
     * 从左到右依次模拟每段没有被推动的骨牌
     * <p>
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param dominoes
     * @return
     */
    String pushDominoesMock(final String dominoes) {
        final int n = dominoes.length();
        final StringBuilder sb = new StringBuilder(n);
        char left = L;
        int start;
        for (int i = 0; i < n; ) {
            start = i;
            while (i < n && dominoes.charAt(i) == DOT) {
                i++;
            }
            char right = i < n ? dominoes.charAt(i) : R;
            if (left == right) {
                while (start < i) {
                    sb.append(right);
                    start++;
                }
            } else if (left == R && right == L) {
                int k = i - 1;
                while (start < k) {
                    sb.append(left);
                    start++;
                    k--;
                }
                if (start == k) {
                    sb.append(DOT);
                }
                for (k = k + 1; k < i; k++) {
                    sb.append(right);
                }
            } else {
                while (start < i) {
                    sb.append(DOT);
                    start++;
                }
            }
            while (i < n && dominoes.charAt(i) != DOT) {
                sb.append(dominoes.charAt(i));
                i++;
            }
            left = dominoes.charAt(i - 1);
        }
        return sb.toString();
    }

    private static final char L = 'L';
    private static final char R = 'R';
    private static final char DOT = '.';
}
