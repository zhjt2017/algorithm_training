package com.teachingpractice.week5;

import java.util.Arrays;

/**
 * 算法实现: 拓展二分查找 - 寻找旋转排序数组中的最小值
 * - https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/ (153题)
 * <p>
 * - 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * - 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 * <p>
 * - 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 * <p>
 * - 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 * <p>
 * - n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-05 10:54:03
 */
public class RotatedSortedArrayFindMinSolution {
    public static void main(String[] args) {
        final RotatedSortedArrayFindMinSolution solution = new RotatedSortedArrayFindMinSolution();

        int[] nums = new int[]{3, 4, 5, 1, 2};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));
        System.out.println("Output min value : " + solution.findMinAnother(nums));

        nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));
        System.out.println("Output min value : " + solution.findMinAnother(nums));

        nums = new int[]{11, 13, 15, 17};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output min value : " + solution.findMin(nums));
        System.out.println("Output min value : " + solution.findMinAnother(nums));
    }

    /**
     * 二分查找更为广泛的应用
     * - 条件(表达式) 在整体上满足单调性, 即可使用二分
     * <p>
     * - 本题: 与最后一个数last比较, 大于 last 的认为是0, 小于等于 last 的认为是1, 那么整体上就是递增序列, 即求 第一个 大于等于1 的位置上的数
     * - 我们将 大于等于1 认为是满足条件, 那么题解的描述即为:
     * - 从左往右, 求第一个满足nums[index]小于等于last的位置上的数 (后继)
     *
     * @param nums
     * @return
     */
    private int findMin(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        final int last = nums[right];
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] <= last) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[right];
    }

    /**
     * 使用可能不断变小的最新nums[right]替代last进行条件比较
     *
     * @param nums
     * @return
     */
    private int findMinAnother(final int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] <= nums[right]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return nums[right];
    }

    /**
     * 适用性更广的二分查找 - 模板 1 (推荐, 相对简单完美)
     * - 模板1.1 - 求后继的场景 (如: 查找第一个 >= / > target的数, 若不存在, 返回n)
     *
     * @param arr
     * @param target
     * @return
     */
    int binarySearchTemplateExtended1Successor(final int[] arr, final int target) {
        final int n = arr.length;
        int left = 0;
        // 求后继, right作为答案 (如果全部不满足, 答案边界值)
        int right = n;
        int mid;
        /*
        终止于 left == right (当left < right时, mid<right, 故一开始取right=n即无解时的答案, mid计算不会越界)
        (情况1: mid不满足, 设置left=mid+1 向右缩小范围, 使得left = right-1, 新的mid=left, 满足, 取right=新mid返回, 终止, 答案OK)
        (情况2: mid满足, 设置right=mid, 向左缩小范围, 使得right=left+1, 新的mid=right-1, 仍然满足, 取新的right=新mid返回, 终止, 答案OK)
        (情况3: mid不满足, 设置left=mid+1 向右缩小范围, 使得left = right-1, 新的mid=left, 仍然不满足, 保持原right值返回, 终止, 答案OK)
        (情况4: mid满足, 设置right=mid, 向左缩小范围, 使得right=left+1, 新的mid=right-1, 不满足, 保持right=原left+1返回, 终止, 答案OK)
         */
        while (left < right) {
            // mid偏向于left, 即保证能取到index=0位置上满足 >= / > target的mid
            mid = ((right - left) >> 1) + left;
            if (arr[mid] >= target) {
                // 满足, 作为新的答案边界值
                right = mid;
            } else {
                // 不满足, 跳过
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * 适用性更广的二分查找 - 模板 1 (推荐, 相对简单完美)
     * - 模板1.2 - 求前驱的场景 (如: 查找最后一个 <= / < target的数, 若不存在, 返回-1)
     *
     * @param arr
     * @param target
     * @return
     */
    int binarySearchTemplateExtended1Precursor(final int[] arr, final int target) {
        final int n = arr.length;
        // 求前驱, left作为答案 (如果全部不满足, 答案边界值)
        int left = -1;
        int right = n - 1;
        int mid;
        while (left < right) {
            // mid偏向于right, 即保证能取到index=n-1位置上满足 <= / < target的mid
            mid = ((right - left - 1) >> 1) + left + 1;
            if (arr[mid] <= target) {
                // 满足, 作为新的答案边界值
                left = mid;
            } else {
                // 不满足, 跳过
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 适用性更广的二分查找 - 模板2 (非推荐)
     * - 双侧都不包含, 使用ans维护答案, 终止于left > right
     * <p>
     * - 模板题意: 查找最后一个满足<=target的数 (找前驱)
     *
     * @param arr
     * @param target
     * @return
     */
    int binarySearchTemplateExtended2(final int[] arr, final int target) {
        final int n = arr.length;
        int left = 0;
        int right = n - 1;
        int ans = -1;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (target >= arr[mid]) {
                ans = max(ans, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 适用性更广的二分查找 - 模板2 (非推荐)
     * - 双侧都不包含(参考普通二分查找), 最后再检查答案 (我意)
     * - 模板题意: 查找最后一个满足<=target的数 (找前驱)
     *
     * @param arr
     * @param target
     * @return
     */
    int binarySearchTemplateExtended2AnotherCheck(final int[] arr, final int target) {
        final int n = arr.length;
        int left = 0;
        int right = n - 1;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (mid == target) {
                return mid;
            }
            if (target >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // 终止条件 left > right
        for (int i = right; i <= left; i++) {
            if (arr[i] > target) {
                return i - 1;
            }
        }
        return left;
    }

    /**
     * 适用性更广的二分查找 - 模板3 (非推荐)
     * - 双侧都不包含, 使用ans维护答案, 终止于left > right
     * <p>
     * - 模板题意: 查找最后一个满足<=target的数 (找前驱)
     *
     * @param arr
     * @param target
     * @return
     */
    int binarySearchTemplateExtended3(final int[] arr, final int target) {
        final int n = arr.length;
        int left = 0;
        int right = n - 1;
        int mid;
        while (left + 1 < right) {
            mid = ((right - left) >> 1) + left;
            if (target >= arr[mid]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // 答案要么是left, 要么是right, 要么不存在 (终止于left + 1 = right, 此处检查left 与 right, 返回一个合适的结果)
        if (arr[left] > target) {
            return left - 1;
        }
        if (arr[right] > target) {
            return right - 1;
        }
        return right;
    }

    private int max(final int a, final int b) {
        return a >= b ? a : b;
    }
}
