package com.dailytraining.month202203;

/**
 * 算法训练(2022-03-01) Z字形变换
 * - https://leetcode-cn.com/problems/zigzag-conversion/ (6题)
 * <p>
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-01 11:05:57
 */
public class ZigZagConversionSolution {
    public static void main(String[] args) {
        final ZigZagConversionSolution solution = new ZigZagConversionSolution();

        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println("Input s : " + s + ", numRows : " + numRows);
        System.out.println("Output target string : " + solution.convert(s, numRows));
        System.out.println();

        numRows = 4;
        System.out.println("Input s : " + s + ", numRows : " + numRows);
        System.out.println("Output target string : " + solution.convert(s, numRows));
        System.out.println();
    }

    String convert(final String s, final int numRows) {
        final int n = s.length();
        if (numRows == 1 || numRows >= n) {
            return s;
        }

        final StringBuilder[] mat = new StringBuilder[numRows];
        for (int i = 0; i < numRows; ++i) {
            mat[i] = new StringBuilder();
        }
        for (int i = 0, x = 0, t = (numRows << 1) - 2; i < n; ++i) {
            mat[x].append(s.charAt(i));
            if (i % t < numRows - 1) {
                ++x;
            } else {
                --x;
            }
        }
        final StringBuilder ans = new StringBuilder();
        for (StringBuilder row : mat) {
            ans.append(row);
        }
        return ans.toString();
    }

    String convertBuilding(final String s, final int numRows) {
        // 可以按照周期规律直接构造 (每个位置，都要计算一遍全部的算法，不过节约了空间)
        return "";
    }
}
