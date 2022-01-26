package com.speed.week5;

import java.util.Arrays;

/**
 * 算法实现: 二分查找 (源数据集升序, 但是有重复数据)
 * - https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/ (34题)
 * <p>
 * - 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * - 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * - 普通方案
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @see BinarySearchSolution
 * --- 先二分查找找到一个满足target的index, 然后将其前后所有一样的都遍历
 * --- 最坏情况下, 时间复杂度仍然是 O(N), 特殊情况下当target在nums中只有很少个数的时候, 查找就很快, 算法稳定性不够好
 * <p>
 * - 进阶
 * --- 于是我们就要想一下, 如何将一般情况下的查找也最终实现为log(N)
 * --- 拆解问题: 2次二分查找, 分别找最小index (=target时继续向start推进) 与 最大index (=target时继续向end推进)
 * --- 实现方法: searchRangeTwice
 * <p>
 * - 我们还可以再想一想
 * --- 先用二分查找找出一个index, 如果找不到就直接找不到了 (如果能找到, 记录下最终的start, mid, end)
 * --- 然后分别对(start, mid], [mid, end)执行二分查找, 得到最终结果
 * --- 时间复杂度也是log(N), 但我们利用了第一个二分查找所缩小的范围, 作为一般情况, 更高效, 最终的结果, 也更好判定
 * --- 实现方法: searchRangeRelay
 * <p>
 * 要说明一点: 当适用简单写法(while条件中left可以=right), 在本题场景下, 都需要一个变量, 记录=target值时每次往右或者往左推进到的index, 因为达到边界时, 无法获取该值
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 * <p>
 * 输入：nums = [2,2], target = 2
 * 输出：[0,1]
 * <p>
 * 输入：nums = [1,2,3], target = 2
 * 输出：[1,1]
 * <p>
 * 输入: [0,0,1,2,3,3,4], target = 2
 * 输出: [3,3]
 * @since 2022-01-26 11:42:14
 */
public class BinarySearchNotUniqueSolution {
    public static void main(String[] args) {
        final BinarySearchNotUniqueSolution solution = new BinarySearchNotUniqueSolution();

        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output searchRangeTwice left and right indexes : " + Arrays.toString(solution.searchRangeTwice(nums, target)));
        System.out.println("Output searchRangeRelay left and right indexes : " + Arrays.toString(solution.searchRangeRelay(nums, target)));

        target = 6;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output searchRangeTwice left and right indexes : " + Arrays.toString(solution.searchRangeTwice(nums, target)));
        System.out.println("Output searchRangeRelay left and right indexes : " + Arrays.toString(solution.searchRangeRelay(nums, target)));

        nums = new int[]{};
        target = 0;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output searchRangeTwice left and right indexes : " + Arrays.toString(solution.searchRangeTwice(nums, target)));
        System.out.println("Output searchRangeRelay left and right indexes : " + Arrays.toString(solution.searchRangeRelay(nums, target)));

        nums = new int[]{3};
        target = 3;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output searchRangeTwice left and right indexes : " + Arrays.toString(solution.searchRangeTwice(nums, target)));
        System.out.println("Output searchRangeRelay left and right indexes : " + Arrays.toString(solution.searchRangeRelay(nums, target)));

        nums = new int[]{2, 2};
        target = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output searchRangeTwice left and right indexes : " + Arrays.toString(solution.searchRangeTwice(nums, target)));
        System.out.println("Output searchRangeRelay left and right indexes : " + Arrays.toString(solution.searchRangeRelay(nums, target)));

        nums = new int[]{1, 2, 3};
        target = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output searchRangeTwice left and right indexes : " + Arrays.toString(solution.searchRangeTwice(nums, target)));
        System.out.println("Output searchRangeRelay left and right indexes : " + Arrays.toString(solution.searchRangeRelay(nums, target)));

        nums = new int[]{0, 0, 1, 2, 3, 3, 4};
        target = 2;
        System.out.println("Input nums : " + Arrays.toString(nums) + ", target : " + target);
        System.out.println("Output searchRangeTwice left and right indexes : " + Arrays.toString(solution.searchRangeTwice(nums, target)));
        System.out.println("Output searchRangeRelay left and right indexes : " + Arrays.toString(solution.searchRangeRelay(nums, target)));
    }

    private static final int[] NOT_EXISTS = new int[]{-1, -1};

    private int[] searchRangeTwice(final int[] nums, final int target) {
        if (nums == null || nums.length == 0) {
            return NOT_EXISTS;
        }

        // 取到leftIdx：从左往右第一个满足>=target的位置
        final int leftIdx = binarySearchIndex(nums, target, true);
        // 取到rightIdx：从左往右最后一个满足<=target的位置
        final int rightIdx = binarySearchIndex(nums, target, false) - 1;
        // 只有这2个index都在范围，并且值均为target，才认为满足答案
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        }
        return new int[]{-1, -1};
    }

    /**
     * 我们在纸上人工模拟二分查找，我们不妨记录下每次向左侧方向二分前的mid位置，那么该值都是很有作用的 (即退出循环时该值为ans)
     * - 当该次左移后直接退出循环，则标志着ans为从左往右第一个满足值>=target的位置
     * - 当该次左移后又经过1-n次右移退出循环，则标志着ans为从左往右第一个不满足值<target的位置 (二者实际上一致)
     * - 换句话说，如果求从左往右最后一个满足<target的位置，就是该值-1
     * <p>
     * - 按照这个思路，binarySearchLeftIndex与binarySearchRightIndex也可以写得更简洁一些，见其Another方法
     *
     * @param nums
     * @param target
     * @param lower
     * @return
     */
    public int binarySearchIndex(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int[] searchRangeRelay(final int[] nums, final int target) {
        if (nums == null || nums.length == 0) {
            return NOT_EXISTS;
        }

        if (nums.length == 1) {
            return nums[0] == target ? new int[]{0, 0} : new int[]{-1, -1};
        }

        final int[] firstResult = binarySearch(nums, target);
        if (firstResult[1] == -1) {
            return NOT_EXISTS;
        }

//        return new int[]{binarySearchLeftIndex(nums, firstResult[0], firstResult[1], target), binarySearchRightIndex(nums, firstResult[1], firstResult[2], target)};
        // 虽说适用了Another方法，但是基于在人工处理时的判断流程，还是适用非Another更贴切，但是基于实践经验上的工程化，显然Another方法又是可行的，甚至直接使用searchRangeTwice解题更应该用。
        return new int[]{binarySearchLeftIndexAnother(nums, firstResult[0], firstResult[1], target), binarySearchRightIndexAnother(nums, firstResult[1], firstResult[2], target) - 1};
    }

    /**
     * 一次二分查找, 找到一个值为target的index
     *
     * @param nums   已经验证过有元素
     * @param target
     * @return int[start, mid, end]
     */
    private int[] binarySearch(final int[] nums, final int target) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start <= end) {
            mid = ((end - start) >> 1) + start;
            if (nums[mid] == target) {
                return new int[]{start, mid, end};
            }
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return new int[]{start, -1, end};
    }

    private int binarySearchLeftIndex(final int[] nums, final int start, final int end, final int target) {
        if (nums[start] == target) {
            return start;
        }
        int left = start;
        int right = end;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                right = mid - 1;
                if (nums[right] < target) {
                    return mid;
                }
            } else {
                left = mid + 1;
                if (nums[left] == target) {
                    return left;
                }
            }
        }
        return -1;
    }

    private int binarySearchRightIndex(final int[] nums, final int start, final int end, final int target) {
        if (target == nums[end]) {
            return end;
        }
        int left = start;
        int right = end;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                left = mid + 1;
                if (nums[left] > target) {
                    return mid;
                }
            } else {
                right = mid - 1;
                if (nums[right] == target) {
                    return right;
                }
            }
        }
        return -1;
    }

    private int binarySearchLeftIndexAnother(final int[] nums, final int start, final int end, final int target) {
        int left = start;
        int right = end;
        int ans = end + 1;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    private int binarySearchRightIndexAnother(final int[] nums, final int start, final int end, final int target) {
        int left = start;
        int right = end;
        int ans = end + 1;
        int mid;
        while (left <= right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }
        return ans;
    }
}
