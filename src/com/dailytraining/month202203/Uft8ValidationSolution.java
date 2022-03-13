package com.dailytraining.month202203;

import java.util.Arrays;

/**
 * 算法训练(2022-03-13) UTF-8编码验证
 * - https://leetcode-cn.com/problems/utf-8-validation/ (393题)
 * <p>
 * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * - 输入：data = [197,130,1]
 * 输出：true
 * 解释：数据表示字节序列:11000101 10000010 00000001。
 * 这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
 * <p>
 * - 输入：data = [235,140,4]
 * 输出：false
 * 解释：数据表示 8 位的序列: 11101011 10001100 00000100.
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 * <p>
 * 1 <= data.length <= 2 * 10^4
 * 0 <= data[i] <= 255
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-13 08:39:38
 */
public class Uft8ValidationSolution {
    public static void main(String[] args) {
        final Uft8ValidationSolution solution = new Uft8ValidationSolution();

        int[] data = new int[]{197, 130, 1};
        System.out.println("Input data : " + Arrays.toString(data));
        System.out.println("Output valid UTF-8 : " + solution.validUtf8(data));
        System.out.println();

        data = new int[]{235, 140, 4};
        System.out.println("Input data : " + Arrays.toString(data));
        System.out.println("Output valid UTF-8 : " + solution.validUtf8(data));
        System.out.println();

        data = new int[]{237};
        System.out.println("Input data : " + Arrays.toString(data));
        System.out.println("Output valid UTF-8 : " + solution.validUtf8(data));
        System.out.println();
    }

    /**
     * 直接模拟，扫描每个字节，以逐个UTF-8字符为组进行扫描
     * - 时间复杂度 O(n)
     * - 空间复杂度 O(1)
     *
     * @param data 根据题意，至少有一个字节的数据
     * @return
     */
    boolean validUtf8(final int[] data) {
        int bodyRemaining = 0;
        for (final int byteValue : data) {
            if (notExists(byteValue)) {
                return false;
            }
            if (bodyRemaining > 0) {
                if (isMultiBody(byteValue)) {
                    bodyRemaining--;
                    continue;
                }
                return false;
            }
            if (isMultiBody(byteValue)) {
                return false;
            }

            if (isHead1(byteValue)) {
                continue;
            }

            if (isHead2(byteValue)) {
                bodyRemaining = 1;
                continue;
            }

            if (isHead3(byteValue)) {
                bodyRemaining = 2;
                continue;
            }

            if (isHead4(byteValue)) {
                bodyRemaining = 3;
                continue;
            }
        }
        return bodyRemaining == 0;
    }

    private boolean notExists(final int byteValue) {
        return (byteValue & NOT_EXIST) == NOT_EXIST;
    }

    private boolean isMultiBody(final int byteValue) {
        return (byteValue & MULTI_BODY) == MULTI_BODY_RESULT;
    }

    private boolean isHead1(final int byteValue) {
        return (byteValue & HEADER_1) == HEADER_1_RESULT;
    }

    private boolean isHead2(final int byteValue) {
        return (byteValue & HEADER_2) == HEADER_2_RESULT;
    }

    private boolean isHead3(final int byteValue) {
        return (byteValue & HEADER_3) == HEADER_3_RESULT;
    }

    private boolean isHead4(final int byteValue) {
        return (byteValue & HEADER_4) == HEADER_4_RESULT;
    }

    private static final int NOT_EXIST = 0xF8;
    private static final int MULTI_BODY = 0xC0;
    private static final int MULTI_BODY_RESULT = 0x80;
    private static final int HEADER_1 = 0x80;
    private static final int HEADER_1_RESULT = 0x0;
    private static final int HEADER_2 = 0xE0;
    private static final int HEADER_2_RESULT = 0xC0;
    private static final int HEADER_3 = 0xF0;
    private static final int HEADER_3_RESULT = 0xE0;
    private static final int HEADER_4 = 0xF8;
    private static final int HEADER_4_RESULT = 0xF0;
}
