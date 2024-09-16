package com.teachingpractice.week9;

/**
 * 算法实现：字符串处理 - 回文串系列问题 - 验证回文串
 * - https://leetcode-cn.com/problems/valid-palindrome/ (125题).c
 * <p>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * - 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * <p>
 * - 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * <p>
 * 1 <= s.length <= 2 * 10^5
 * 字符串 s 由 ASCII 字符组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-17 04:17:03
 */
public class ValidPalindromeSolution {
    public static void main(String[] args) {
        final ValidPalindromeSolution solution = new ValidPalindromeSolution();

        String s = "A man, a plan, a canal: Panama";
        System.out.println("Input s : " + s);
        System.out.println("Output isPalindrome : " + solution.isPalindrome(s));
        System.out.println("Output isPalindrome (dual pointer) : " + solution.isPalindromeDualPointerClamp(s));
        System.out.println("Output isPalindrome (dual pointer another) : " + solution.isPalindromeDualPointerClampAnother(s));
        System.out.println();

        s = "race a car";
        System.out.println("Input s : " + s);
        System.out.println("Output isPalindrome : " + solution.isPalindrome(s));
        System.out.println("Output isPalindrome (dual pointer) : " + solution.isPalindromeDualPointerClamp(s));
        System.out.println("Output isPalindrome (dual pointer another) : " + solution.isPalindromeDualPointerClampAnother(s));
        System.out.println();

        s = "";
        System.out.println("Input s : " + s);
        System.out.println("Output isPalindrome : " + solution.isPalindrome(s));
        System.out.println("Output isPalindrome (dual pointer) : " + solution.isPalindromeDualPointerClamp(s));
        System.out.println("Output isPalindrome (dual pointer another) : " + solution.isPalindromeDualPointerClampAnother(s));
        System.out.println();
    }

    /**
     * 使用jdk的reverse API
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(N)
     *
     * @param s
     * @return
     */
    boolean isPalindrome(final String s) {
        final StringBuilder sb = new StringBuilder();
        final int n = s.length();
        char c;
        for (int i = 0; i < n; i++) {
            c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        final String content = sb.toString();
        return content.equals(sb.reverse().toString());
    }

    /**
     * 双指针夹逼
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     *
     * @param s
     * @return
     */
    boolean isPalindromeDualPointerClamp(final String s) {
        int left = 0;
        int right = s.length();
        char c = 0;
        boolean isLeft = true;
        while (left < right) {
            if (isLeft) {
                if (Character.isLetterOrDigit(s.charAt(left))) {
                    c = Character.toLowerCase(s.charAt(left));
                    isLeft = false;
                    right--;
                } else {
                    left++;
                }
                continue;
            }
            if (Character.isLetterOrDigit(s.charAt(right))) {
                if (c != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                isLeft = true;
                left++;
            } else {
                right--;
            }
        }
        return true;
    }

    /**
     * 双指针夹逼的另一种比较简单的写法
     * - 时间复杂度 O(N)
     * - 空间复杂度 O(1)
     * - 如果不要求空间复杂度为O(1)，我们可以使用空间复杂度为O(n)的简单写法(模块化思想)
     * --- 1. 以小写方式取出所有的数字与字母字符
     * --- 2. 验证取出的字符序列为严格的回文串
     *
     * @param s
     * @return
     */
    boolean isPalindromeDualPointerClampAnother(final String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
        }
        return true;
    }
}
