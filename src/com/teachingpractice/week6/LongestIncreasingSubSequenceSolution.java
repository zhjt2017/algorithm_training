package com.teachingpractice.week6;

import java.util.*;

/**
 * 算法实现： 动态规划 - 最长递增子序列
 * - https://leetcode-cn.com/problems/longest-increasing-subsequence/ (300题)
 * - 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * - 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * - 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * - 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * - 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 * <p>
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 * <p>
 * 要求：将时间复杂度控制在 O(NlogN)
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-02-14 10:56:46
 */
public class LongestIncreasingSubSequenceSolution {
    public static void main(String[] args) {
        final LongestIncreasingSubSequenceSolution solution = new LongestIncreasingSubSequenceSolution();

        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));
        System.out.println("Output longest length of increasing sub sequence (with path) : " + solution.longestLengthWithPrint(nums));
        System.out.println("Output longest length of increasing sub sequence (hash) : " + solution.longestLengthHash(nums));
        System.out.println("Output longest length of increasing sub sequence (dfs) : " + solution.longestLengthDfs(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search) : " + solution.longestLengthBinarySearch(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search another) : " + solution.longestLengthBinarySearchAnotherAndPrint(nums));
        System.out.println();

        nums = new int[]{0, 1, 0, 3, 2, 3};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));
        System.out.println("Output longest length of increasing sub sequence (with path) : " + solution.longestLengthWithPrint(nums));
        System.out.println("Output longest length of increasing sub sequence (hash) : " + solution.longestLengthHash(nums));
        System.out.println("Output longest length of increasing sub sequence (dfs) : " + solution.longestLengthDfs(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search) : " + solution.longestLengthBinarySearch(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search another) : " + solution.longestLengthBinarySearchAnotherAndPrint(nums));
        System.out.println();

        nums = new int[]{7, 7, 7, 7, 7, 7, 7};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));
        System.out.println("Output longest length of increasing sub sequence (with path) : " + solution.longestLengthWithPrint(nums));
        System.out.println("Output longest length of increasing sub sequence (hash) : " + solution.longestLengthHash(nums));
        System.out.println("Output longest length of increasing sub sequence (dfs) : " + solution.longestLengthDfs(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search) : " + solution.longestLengthBinarySearch(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search another) : " + solution.longestLengthBinarySearchAnotherAndPrint(nums));
        System.out.println();

        nums = new int[]{3, 5, 6, 2, 5, 4, 19, 5, 6, 7, 12};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));
        System.out.println("Output longest length of increasing sub sequence (with path) : " + solution.longestLengthWithPrint(nums));
        System.out.println("Output longest length of increasing sub sequence (hash) : " + solution.longestLengthHash(nums));
        System.out.println("Output longest length of increasing sub sequence (dfs) : " + solution.longestLengthDfs(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search) : " + solution.longestLengthBinarySearch(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search another) : " + solution.longestLengthBinarySearchAnotherAndPrint(nums));
        System.out.println();

        nums = new int[]{1, 3, 8, 7, 6, 4, 8, 7, 9};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));
        System.out.println("Output longest length of increasing sub sequence (with path) : " + solution.longestLengthWithPrint(nums));
        System.out.println("Output longest length of increasing sub sequence (hash) : " + solution.longestLengthHash(nums));
        System.out.println("Output longest length of increasing sub sequence (dfs) : " + solution.longestLengthDfs(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search) : " + solution.longestLengthBinarySearch(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search another) : " + solution.longestLengthBinarySearchAnotherAndPrint(nums));
        System.out.println();

        nums = new int[]{-813, 82, -728, -82, -432, 887, -551, 324, -315, 306, -164, -499, -873, -613, 932, 177, 61, 52, 1000, -710, 372, -306, -584, -332, -500, 407, 399, -648, 290, -866, 222, 562, 993, -338, -590, 303, -16, -134, 226, -648, 909, 582, 177, 899, -343, 55, 629, 248, 333, 1, -921, 143, 629, 981, -435, 681, 844, 349, 613, 457, 797, 695, 485, 15, 710, -450, -775, 961, -445, -905, 466, 942, 995, -289, -397, 434, -14, 34, -903, 314, 862, -441, 507, -966, 525, 624, -706, 39, 152, 536, 874, -364, 747, -35, 446, -608, -554, -411, 987, -354, -700, -34, 395, -977, 544, -330, 596, 335, -612, 28, 586, 228, -664, -841, -999, -100, -620, 718, 489, 346, 450, 772, 941, 952, -560, 58, 999, -879, 396, -101, 897, -1000, -566, -296, -555, 938, 941, 475, -260, -52, 193, 379, 866, 226, -611, -177, 507, 910, -594, -856, 156, 71, -946, -660, -716, -295, -927, 148, 620, 201, 706, 570, -659, 174, 637, -293, 736, -735, 377, -687, -962, 768, 430, 576, 160, 577, -329, 175, 51, 699, -113, 950, -364, 383, 5, 748, -250, -644, -576, -227, 603, 832, -483, -237, 235, 893, -336, 452, -526, 372, -418, 356, 325, -180, 134, -698};
        System.out.println("Input nums : " + Arrays.toString(nums));
        System.out.println("Output longest length of increasing sub sequence : " + solution.longestLength(nums));
        System.out.println("Output longest length of increasing sub sequence (with path) : " + solution.longestLengthWithPrint(nums));
        System.out.println("Output longest length of increasing sub sequence (hash) : " + solution.longestLengthHash(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search) : " + solution.longestLengthBinarySearch(nums));
        System.out.println("Output longest length of increasing sub sequence (binary search another) : " + solution.longestLengthBinarySearchAnotherAndPrint(nums));
//        System.out.println("Output longest length of increasing sub sequence (dfs) : " + solution.longestLengthDfs(nums));
    }

    /**
     * 注意：最长递增序列，不是最长连续递增序列
     * - “结尾数、最长长度”作为一系列序列的代表
     * - f[i]表示前i个数构成的以a[i]为结尾的最长上升子序列的长度
     * - 状态转移方程：f[i] = max{f[j] + 1} (j < i, a[j] < a[i])
     * - 边界：f[0] = 1 (0 <= i < n)
     * - 目标：max{f[i]} (0 <= i < n)
     * <p>
     * - 时间复杂度 O(N^2)
     * - 空间复杂度 O(N)
     *
     * @param nums
     * @return
     */
    int longestLength(final int[] nums) {
        final int n = nums.length;
        // 其实每个位置上的f[i]都应该初始化为1，因为即使其前面没有任何一个比自己小的，其自己也算是1个，这里统一默认为0，返回时在结果上+1即可
        final int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        int ans = 0;
        for (final int value : f) {
            ans = Math.max(ans, value);
        }
        return ans + 1;
    }

    /**
     * 追加Path打印
     *
     * @param nums
     * @return
     */
    int longestLengthWithPrint(final int[] nums) {
        final int n = nums.length;
        // 其实每个位置上的f[i]都应该初始化为1，因为即使其前面没有任何一个比自己小的，其自己也算是1个，这里统一默认为0，返回时在结果上+1即可
        final int[] f = new int[n];
        final int[] pre = new int[n];
        for (int i = 0; i < n; i++) {
            pre[i] = -1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                    pre[i] = j;
                }
            }
        }
        int ans = 0;
        int end = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > ans) {
                ans = f[i];
                end = i;
            }
        }
        final List<List<Integer>> indexValues = new LinkedList<>();
        while (end >= 0) {
            indexValues.add(0, Arrays.asList(end, nums[end]));
            end = pre[end];
        }
        System.out.println("Path : " + indexValues);
        return ans + 1;
    }

    /**
     * 我这里稍作优化
     * 1、同样长度且其结尾数更大，或者同样的结尾数(就按当前结尾数)长度小的，可以被剪枝。(这是被留下的本质：要么结尾数严格更小，有希望，要么长度已经严格更大，有希望)
     * - HashMap的key存储最长长度，value存储该长度对应的最小结尾数
     * - 时间复杂度仍然O(N^2)，并且具体能优化多少，还要看数据分布
     * - 空间复杂度 O(N)
     * <p>
     * - 特别说明：这在整体执行上，效果甚至不如longestLength。这是人工计算时的一个好方法。
     *
     * @param nums
     * @return
     */
    int longestLengthHash(final int[] nums) {
        final int n = nums.length;
        // value为2个元素的int数组，分别存储结尾数与结尾数的上一个数
        final Map<Integer, int[]> lengthHash = new HashMap<>(nums.length);
        lengthHash.put(1, new int[]{nums[0], Integer.MIN_VALUE});
        int ans = 1;
        int remaining;
        int[] tails;
        for (int i = 1; i < n; i++) {
            remaining = n - i;
            for (int key = Math.max(1, ans - remaining + 1); key <= ans; key++) {
                tails = lengthHash.get(key);
                // 贪心：与结尾数相同，不做处理。不大于结尾数的上一个数，不属于该key来处理，而应该由更小的key进行处理。
                if (nums[i] == tails[0] || nums[i] <= tails[1]) {
                    continue;
                }
                // 刚好小于结尾数的，替代结尾数，长度不变 (并且由于越长的长度，结尾数必然越大，则此情形下直接break即可)
                if (nums[i] < tails[0]) {
                    tails[0] = nums[i];
                    break;
                }

                // 大于结尾数的(余下的都满足这个情形)，若key+1已经存在，可以去争取覆盖(结尾数及其前面一个数同时覆盖)。如果key+1不存在，表示已经到达ans，ans+1后break
                if (lengthHash.containsKey(key + 1)) {
                    int[] nextTails = lengthHash.get(key + 1);
                    if (nums[i] < nextTails[0]) {
                        nextTails[0] = nums[i];
                        nextTails[1] = tails[0];
                        continue;
                    }
                    if (nums[i] == nextTails[0] && tails[0] < nextTails[1]) {
                        nextTails[1] = tails[0];
                    }
                    continue;
                }

                ans = key + 1;
                lengthHash.put(ans, new int[]{nums[i], tails[0]});
                break;
            }
        }
        return ans;
    }

    /**
     * 动态规划 + 内层循环优化为二分查找
     * - 时间复杂度 O(NlogN)
     * - 空间复杂度 O(N)
     * <p>
     * - 思路：如上述longestLengthHash的思想，维护lengthHash(以数组的方式来维护即可, 不妨取名lengthValues，下标是递增长度，value为每个递增长度对应的最小末尾元素值)
     * --- lengthValues是严格递增的，基于这样的单调性
     * --- 于是整体算法流程为：遍历数组nums, 对每个nums[i]
     * --- --- 如果nums[i] == lengthValues[ans], 忽略
     * --- --- 如果nums[i] > lengthValues[ans], 则直接加入到lengthValues末尾 (更新ans = ans+1, lengthValues[ans] = nums[i])
     * --- --- 如果nums[i] < lengthValues[ans], 使用二分查找找到满足 < nums[i]的最后一个lengthValues[k], 更新lengthValues[k+1]=nums[i]
     * --- --- --- (极端情况下，nums[i]为lengthValues中的最小数，于是找不到，即left=-1，所以更新lengthValues[0]=nums[i])
     *
     * @param nums
     * @return
     */
    int longestLengthBinarySearch(final int[] nums) {
        final int[] lengthValues = new int[nums.length];
        lengthValues[0] = nums[0];
        int ans = 0;
        int mid;
        for (int num : nums) {
            if (num == lengthValues[ans]) {
                continue;
            }
            if (num > lengthValues[ans]) {
                ans++;
                lengthValues[ans] = num;
                continue;
            }
            int left = -1, right = ans;
            while (left < right) {
                mid = ((right - left - 1) >> 1) + left + 1;
                if (lengthValues[mid] < num) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            lengthValues[left + 1] = num;
        }
        return ans + 1;
    }

    /**
     * 诚如longestLengthBinarySearch，也可以是二分查找找到满足 >= nums[i]的第一个lengthValues[k], 更新lengthValues[k]=nums[i]
     * - 另外，我们这里增加打印功能
     *
     * @param nums
     * @return
     */
    int longestLengthBinarySearchAnotherAndPrint(final int[] nums) {
        final int[] lengthIndexes = new int[nums.length];
        final int[] pre = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pre[i] = -1;
        }
        int ans = 0;
        int mid;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[lengthIndexes[ans]]) {
                continue;
            }
            if (nums[i] > nums[lengthIndexes[ans]]) {
                pre[i] = lengthIndexes[ans];
                ans++;
                lengthIndexes[ans] = i;
                continue;
            }
            int left = 0, right = ans;
            while (left < right) {
                mid = ((right - left) >> 1) + left;
                if (nums[lengthIndexes[mid]] >= nums[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            nums[lengthIndexes[right]] = nums[i];
        }
        int end = lengthIndexes[ans];
        final List<List<Integer>> indexValues = new LinkedList<>();
        while (end >= 0) {
            indexValues.add(0, Arrays.asList(end, nums[end]));
            end = pre[end];
        }
        System.out.println("Path : " + indexValues);
        return ans + 1;
    }

    /**
     * dfs
     * - 时间复杂度 O(2^N)
     *
     * @param nums
     * @return
     */
    int longestLengthDfs(final int[] nums) {
        this.nums = nums;
        maxValue = -10001;
        minValue = 10001;
        maxLength = 0;
        ans = 0;
        opened = new boolean[nums.length];
        dfs(0);
        return ans;
    }

    private int[] nums;
    private int maxValue;
    private int maxLength;
    private int ans;
    private int minValue;
    private boolean[] opened;

    /**
     * 要点：
     * 1、统一先走选择的分支，再走不选择的分支
     * 2、不选择的分支都是要走的。
     * 3、每个分支结束时，刷新ans
     * 4、对于选择的分支：
     * - 4.1、nums[i] <= maxValue && nums[i] >= minValue 时没有该分支 (该分支无实际意义)
     * - 4.2、nums[i] > maxValue 时必然有该分支
     * - 4.3、nums[i] < maxValue 时在点i处进行选择，并从0开启新的长度拼接 (每个i点处只要开启一次新的长度拼接，维护一个boolean[])
     * - 4.4、对于选择的分支，需要还原现场
     *
     * @param i
     */
    private void dfs(final int i) {
        // 分支结束的标志：到达n-1 或者 来不及了
        if (i == nums.length - 1) {
            ans = Math.max(ans, maxValue < nums[i] ? maxLength + 1 : maxLength);
            return;
        }
        if (ans - maxLength >= nums.length - i) {
            return;
        }

        // 如果与maxValue相等，没有意义，直接跳过
        if (nums[i] <= maxValue && nums[i] >= minValue) {
            dfs(i + 1);
            return;
        }


        // 分支1：nums[i]入选
        int tempMaxValue = maxValue;
        int tempMinValue = minValue;
        int tempMaxLength = maxLength;

        if (nums[i] < minValue) {
            if (opened[i]) {
                dfs(i + 1);
                return;
            }
            minValue = nums[i];
            maxLength = 0;
            opened[i] = true;
        }
        maxValue = nums[i];
        maxLength++;
        dfs(i + 1);
        // 还原现场
        maxValue = tempMaxValue;
        minValue = tempMinValue;
        maxLength = tempMaxLength;

        // 分支2：nums[i]不入选
        dfs(i + 1);
    }
}
