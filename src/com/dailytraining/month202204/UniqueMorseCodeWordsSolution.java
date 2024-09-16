package com.dailytraining.month202204;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 算法训练(2022-04-10) 唯一摩尔斯密码词
 * - https://leetcode-cn.com/problems/unique-morse-code-words/ (804题)
 * <p>
 * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如:
 * <p>
 * 'a' 对应 ".-" ，
 * 'b' 对应 "-..." ，
 * 'c' 对应 "-.-." ，以此类推。
 * 为了方便，所有 26 个英文字母的摩尔斯密码表如下：
 * <p>
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * 给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。
 * <p>
 * 例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单词翻译 。
 * 对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。
 * <p>
 * - 输入: words = ["gin", "zen", "gig", "msg"]
 * 输出: 2
 * 解释:
 * 各单词翻译如下:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * <p>
 * 共有 2 种不同翻译, "--...-." 和 "--...--.".
 * <p>
 * - 输入：words = ["a"]
 * 输出：1
 * <p>
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 12
 * words[i] 由小写英文字母组成
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-04-10 09:39:13
 */
public class UniqueMorseCodeWordsSolution {
    public static void main(String[] args) {
        final UniqueMorseCodeWordsSolution solution = new UniqueMorseCodeWordsSolution();

        String[] words = new String[]{"gin", "zen", "gig", "msg"};
        System.out.println("Input words : " + Arrays.toString(words));
        System.out.println("Output unique morse representations : " + solution.uniqueMorseRepresentations(words));
        System.out.println();

        words = new String[]{"a"};
        System.out.println("Input words : " + Arrays.toString(words));
        System.out.println("Output unique morse representations : " + solution.uniqueMorseRepresentations(words));
        System.out.println();
    }

    int uniqueMorseRepresentations(final String[] words) {
        if (words.length == 1) {
            return 1;
        }
        final Set<String> morseSet = new HashSet<>();
        for (final String w : words) {
            addWord(w, morseSet);
        }
        return morseSet.size();
    }

    private void addWord(final String w, final Set<String> morseSet) {
        final int length = w.length();
        final StringBuilder sb = new StringBuilder(length << 2);
        for (int i = 0; i < length; i++) {
            sb.append(MORSE_DICT[w.charAt(i)]);
        }
        morseSet.add(sb.toString());
    }

    private static final String[] MORSE_DICT = new String['z' + 1];

    static {
        final String[] source = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        for (int i = 'a'; i <= 'z'; i++) {
            MORSE_DICT[i] = source[i - 'a'];
        }
    }
}
