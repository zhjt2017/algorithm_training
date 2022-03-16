package com.dailytraining.month202203;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 算法训练(2022-03-14) : 两个列表的最小索引总和
 * - https://leetcode-cn.com/problems/minimum-index-sum-of-two-lists/ (599题)
 * <p>
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 * <p>
 * - 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * <p>
 * - 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * <p>
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30 
 * list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。
 * list1 的所有字符串都是 唯一 的。
 * list2 中的所有字符串都是 唯一 的。
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-03-14 10:38:37
 */
public class MinIndexSumOfTwoListsSolution {
    public static void main(String[] args) {
        final MinIndexSumOfTwoListsSolution solution = new MinIndexSumOfTwoListsSolution();

        String[] list1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        System.out.println("Input list1 : " + Arrays.toString(list1) + ", list2 : " + Arrays.toString(list2));
        System.out.println("Output find restaurant : " + Arrays.toString(solution.findRestaurant(list1, list2)));
        System.out.println();

        list1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        list2 = new String[]{"KFC", "Shogun", "Burger King"};
        System.out.println("Input list1 : " + Arrays.toString(list1) + ", list2 : " + Arrays.toString(list2));
        System.out.println("Output find restaurant : " + Arrays.toString(solution.findRestaurant(list1, list2)));
        System.out.println();
    }

    /**
     * 分析题目的意义：如果list1与list2都是按照索引递减喜爱度，那么求得的最小索引和就有实际意义，在于结果餐厅符合两人综合喜爱度最高
     *
     * @param list1
     * @param list2
     * @return
     */
    String[] findRestaurant(final String[] list1, final String[] list2) {
        final int m = list1.length;
        final int n = list2.length;
        // 以小的建立HashMap
        if (m > n) {
            return findRestaurant(list2, list1);
        }

        final HashMap<String, Integer> indexHash = new HashMap<>();
        for (int i = 0; i < m; i++) {
            indexHash.put(list1[i], i);
        }

        int minIndexSum = m + n;
        String s;
        final List<String> ans = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (j > minIndexSum) {
                break;
            }
            s = list2[j];
            if (!indexHash.containsKey(s)) {
                continue;
            }
            int indexSum = indexHash.get(s) + j;
            if (indexSum == minIndexSum) {
                ans.add(s);
                continue;
            }
            if (indexSum < minIndexSum) {
                minIndexSum = indexSum;
                ans.clear();
                ans.add(s);
            }
        }

        return ans.toArray(new String[ans.size()]);
    }
}
