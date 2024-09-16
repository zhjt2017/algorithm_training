package com.dailytraining.month202204;

import java.util.Arrays;

/**
 * 算法训练(2022-04-03) 寻找比目标字母大的最小字母
 * - https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/ (744题)
 * <p>
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。
 * 另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * <p>
 * 在比较时，字母是依序循环出现的。举个例子：
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 * <p>
 * - 输入: letters = ["c", "f", "j"]，target = "a"
 * 输出: "c"
 * <p>
 * - 输入: letters = ["c","f","j"], target = "c"
 * 输出: "f"
 * <p>
 * - 输入: letters = ["c","f","j"], target = "d"
 * 输出: "f"
 * <p>
 * 2 <= letters.length <= 10^4
 * letters[i] 是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-03 04:36:32
 */
public class NextGreatestLetterSolution {
    public static void main(String[] args) {
        final NextGreatestLetterSolution solution = new NextGreatestLetterSolution();

        char[] letters = new char[]{'c', 'f', 'j'};
        char target = 'a';
        System.out.println("Input letters : " + Arrays.toString(letters) + ", target : " + target);
        System.out.println("Output next greatest letter : " + solution.nextGreatestLetter(letters, target));
        System.out.println();

        target = 'c';
        System.out.println("Input letters : " + Arrays.toString(letters) + ", target : " + target);
        System.out.println("Output next greatest letter : " + solution.nextGreatestLetter(letters, target));
        System.out.println();

        target = 'd';
        System.out.println("Input letters : " + Arrays.toString(letters) + ", target : " + target);
        System.out.println("Output next greatest letter : " + solution.nextGreatestLetter(letters, target));
        System.out.println();
    }

    /**
     * 时间复杂度 O(logn)
     * 空间复杂度 O(1)
     *
     * @param letters
     * @param target
     * @return
     */
    char nextGreatestLetter(final char[] letters, final char target) {
        final int n = letters.length;
        // 边界值
        if (target < letters[0] || target >= letters[n - 1]) {
            return letters[0];
        }

        // 其中值，二分查找 (第一个满足>target的数，且一定能找到)
        int left = 0;
        int right = n - 1;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (letters[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return letters[right];
    }
}
