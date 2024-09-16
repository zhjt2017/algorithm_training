package com.teachingpractice.week5;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数组的相对排序
 * - https://leetcode-cn.com/problems/relative-sort-array/ (1122题)
 * <p>
 * - 给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中。
 * - 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * - 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 * <p>
 * - 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * - 输入：arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
 * 输出：[22,28,8,6,17,44]
 * <p>
 * 1 <= arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i]  各不相同 
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-13 09:40:49
 */
public class RelativeSortArraySolution {
    public static void main(String[] args) {
        final RelativeSortArraySolution solution = new RelativeSortArraySolution();

        int[] arr1 = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = new int[]{2, 1, 4, 3, 9, 6};
        System.out.println("Input arr1 : " + Arrays.toString(arr1) + ", arr2 : " + Arrays.toString(arr2));
        System.out.println("Output sorted arr1 (quick sort) : " + Arrays.toString(solution.relativeSortArrayQuickSort(arr1, arr2)));
        arr1 = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        System.out.println("Output sorted arr1 (counting sort) : " + Arrays.toString(solution.relativeSortArrayCountingSort(arr1, arr2)));
        System.out.println();

        arr1 = new int[]{28, 6, 22, 8, 44, 17};
        arr2 = new int[]{22, 28, 8, 6};
        System.out.println("Input arr1 : " + Arrays.toString(arr1) + ", arr2 : " + Arrays.toString(arr2));
        System.out.println("Output sorted arr1 (quick sort) : " + Arrays.toString(solution.relativeSortArrayQuickSort(arr1, arr2)));
        arr1 = new int[]{28, 6, 22, 8, 44, 17};
        System.out.println("Output sorted arr1 (counting sort) : " + Arrays.toString(solution.relativeSortArrayCountingSort(arr1, arr2)));
    }

    /**
     * 比较类-快速排序 (通用方法，没有使用场景的限制)
     * - 自定义比较的规则：优先比较其在arr2中的position，不在arr2中的比较其自身大小
     * - C++, Go语言的内置语言包都可以直接对基础数据类型的 数组 / 切片 进行自定义排序
     * - Java只有自定义类型才可以进行自定义排序
     * - 时间复杂度: 期望O(NlogN)
     * - 空间复杂度: 期望O(logN)
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private int[] relativeSortArrayQuickSort(final int[] arr1, final int[] arr2) {
        final int n = arr2.length;
        final Map<Integer, Integer> arr2Orders = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            arr2Orders.put(arr2[i], i);
        }
        final List<Integer> list = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        Collections.sort(list, (a, b) -> ((arr2Orders.containsKey(a) || arr2Orders.containsKey(b))
                ? arr2Orders.getOrDefault(a, n) - arr2Orders.getOrDefault(b, n) : a - b));
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = list.get(i);
        }
        return arr1;
    }

    /**
     * 非比较类-计数排序 (有使用场景的限制)
     * - 根据题目中0 <= arr1[i], arr2[i] <= 1000元素的范围，范围确定并且也不太大，那么可以采用计数排序
     * - 对于能够正常使用计数排序实现的场景，自然优先使用计数排序，其时间复杂度只有 O(N + M)
     * - 时间复杂度 O(N) (M < N)
     * - 空间复杂度 O(U) (U - upper表示数组范围的最大值)
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private int[] relativeSortArrayCountingSort(int[] arr1, int[] arr2) {
        final int[] count = new int[1001];
        for (final int value : arr1) {
            count[value]++;
        }

        int i = 0;
        for (final int value : arr2) {
            while (count[value] > 0) {
                arr1[i] = value;
                count[value]--;
                i++;
            }
        }

        for (int k = 0; k < count.length; k++) {
            while (count[k] > 0) {
                arr1[i] = k;
                count[k]--;
                i++;
            }
        }
        return arr1;
    }
}
