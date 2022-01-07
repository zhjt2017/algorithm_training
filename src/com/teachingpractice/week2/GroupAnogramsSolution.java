package com.teachingpractice.week2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 字母异位词分组 (anagram 相同字母异序词)
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 * <p>
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-07 08:55:07
 */
public class GroupAnogramsSolution {
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("strs : " + Arrays.toString(strs));
        System.out.println("result : " + groupAnagramSort1(strs));

        strs = new String[]{};
        System.out.println("strs : " + Arrays.toString(strs));
        System.out.println("result : " + groupAnagramSort1(strs));

        strs = new String[]{"a"};
        System.out.println("strs : " + Arrays.toString(strs));
        System.out.println("result : " + groupAnagramSort1(strs));
    }

    /**
     * 设计思想1：排序作为hash的key，进行分组
     * 时间复杂度：O(n*k*log(k))
     * 空间复杂度：O(n*k)
     * <p>
     * 写法1：stream API
     * - leetcode执行时间8ms, 内存消耗40MB
     *
     * @return
     */
    private static List<List<String>> groupAnagramSort1(final String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str -> sortString(str))).values());
    }

    /**
     * 设计思想1的写法1，同时使用IntStream进行排序 (实不可取)
     * - leetcode执行时间18ms, 内存消耗42MB
     *
     * @param strs
     * @return
     */
    private static List<List<String>> groupAnagramSort1IntStream(final String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors.groupingBy(str ->
                str.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString())).values());
    }

    /**
     * 设计思想1的第二种写法：不利用stream API (这在返回的List还保证了一定的顺序)
     * - leetcode执行时间5ms, 内存消耗40MB
     *
     * @param strs
     * @return
     */
    private static List<List<String>> groupAnagramSort2(final String[] strs) {
        // 不妨假设平均每个分组有4个单词, capacity = strs >> 2
        final int capacity = strs.length >> 2;
        final List<List<String>> result = new ArrayList<>(capacity);
        final Map<String, List<String>> hash = new HashMap<>(capacity);
        String sortedString;
        List<String> groupData;
        for (final String s : strs) {
            sortedString = sortString(s);
            if (hash.containsKey(sortedString)) {
                hash.get(sortedString).add(s);
            } else {
                groupData = new ArrayList<>();
                groupData.add(s);
                result.add(groupData);
                hash.put(sortedString, groupData);
            }
        }
        return result;
    }

    private static String sortString(final String s) {
        final char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    /**
     * 设计思想2：计数作为hash的key，进行分组 (其实这2种设计思想的细节区别就在于hash算法的实现哪个更优)
     * 时间复杂度：O(n*k)
     * 空间复杂度：O(n*k)
     * <p>
     * - leetcode执行时间20ms, 内存消耗43MB
     *
     * @param strs
     * @return
     */
    private static List<List<String>> groupAnagramCount(final String[] strs) {
        final int capacity = strs.length >> 2;
        final List<List<String>> result = new ArrayList<>(capacity);
        final Map<List<Integer>, List<String>> hash = new HashMap<>(capacity);
        List<Integer> count;
        List<String> groupData;
        for (final String s : strs) {
            count = countChars(s);
            if (hash.containsKey(count)) {
                hash.get(count).add(s);
            } else {
                groupData = new ArrayList<>();
                groupData.add(s);
                result.add(groupData);
                hash.put(count, groupData);
            }
        }
        return result;
    }

    private static List<Integer> countChars(final String s) {
        final char[] chars = s.toCharArray();
        final int[] counters = new int[26];
        for (final char c : chars) {
            counters[c - 'a']++;
        }
        return Arrays.stream(counters).boxed().collect(Collectors.toList());
    }

    /**
     * 设计思想2的写法2：append字符串替代List<Integer>作为hash key
     * <p>
     * - leetcode执行时间7ms, 内存消耗41MB
     *
     * @param strs
     * @return
     */
    private static List<List<String>> groupAnagramCount2(final String[] strs) {
        final int capacity = strs.length >> 2;
        final List<List<String>> result = new ArrayList<>(capacity);
        final Map<String, List<String>> hash = new HashMap<>(capacity);
        String countString;
        List<String> groupData;
        for (final String s : strs) {
            countString = countChars2String(s);
            if (hash.containsKey(countString)) {
                hash.get(countString).add(s);
            } else {
                groupData = new ArrayList<>();
                groupData.add(s);
                result.add(groupData);
                hash.put(countString, groupData);
            }
        }
        return result;
    }

    private static String countChars2String(final String s) {
        final char[] chars = s.toCharArray();
        final int[] counters = new int[26];
        for (final char c : chars) {
            counters[c - 'a']++;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (counters[i] > 0) {
                sb.append((char) ('a' + i));
                sb.append(counters[i]);
            }
        }
        return sb.toString();
    }
}
