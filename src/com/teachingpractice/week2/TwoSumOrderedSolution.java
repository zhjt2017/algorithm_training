package com.teachingpractice.week2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * TwoSumSolution的特例，给定的整数数组是已经按大小排好序的 (这里不妨设为递增排序)
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-01-06 08:01:28
 */
public class TwoSumOrderedSolution {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println("nums = " + Arrays.toString(nums) + ",target = " + target);
        System.out.println("result : " + Arrays.toString(twoSum(nums, target)));
        System.out.println("result : " + Arrays.toString(twoSumFurther(nums, target)));

        nums = new int[]{10, 26, 30, 31, 47, 60};
        target = 40;
        System.out.println("nums = " + Arrays.toString(nums) + ",target = " + target);
        System.out.println("result : " + Arrays.toString(twoSum(nums, target)));
        System.out.println("result : " + Arrays.toString(twoSumFurther(nums, target)));

        nums = new int[]{5, 25, 75};
        target = 100;
        System.out.println("nums = " + Arrays.toString(nums) + ",target = " + target);
        System.out.println("result : " + Arrays.toString(twoSum(nums, target)));
        System.out.println("result : " + Arrays.toString(twoSumFurther(nums, target)));
    }

    /**
     * 设计思想：Hash的思想可以优化为双指针，向内测靠拢，称为“对撞双指针”
     * - 索引i从左侧开始，索引j从右侧开始，基于每个被放弃掉的i或者j都是相当于已经遍历过另一半即可保证该索引的值一定不是一个答案，从而保证正确性
     * 总体时间复杂度：O(n)
     * 总体空间复杂度：O(1)
     * - while循环的写法 (while写法更好理解, while与for写法, 演绎过程是一样的)
     *
     * @param nums   输入方自己保证2个已上的元素
     * @param target
     * @return
     */
    private static int[] twoSum(final int[] nums, final int target) {
        int i = 0;
        int j = nums.length - 1;
        int sum;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{nums[i], nums[j]};
            }
            if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{};
    }

    /**
     * 关于双指针夹逼，在nums数据量很大的时候，还可以进一步使用二分查找来进行优化 (空间换时间)
     * - 时间复杂度 O(logN)
     * - 空间复杂度 O(logN)
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSumFurther(final int[] nums, final int target) {
        int i = 0;
        int j = nums.length - 1;
        int sum;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{nums[i], nums[j]};
            }
            if (sum > target) {
                // 如果大了，就二分查找到使得<=target的最大值
                j = maxLowerIndex(nums, target - nums[i], i, j);
            } else {
                // 如果小了，就二分查找到使得>=target的最小值
                i = minUpperIndex(nums, target - nums[j], i, j);
            }
        }
        return new int[]{};
    }

    private static int maxLowerIndex(final int[] nums, final int targetValue, final int i, final int j) {
        int left = i;
        int right = j - 1;
        int mid;
        while (left < right) {
            mid = ((right - left + 1) >> 1) + left;
            if (nums[mid] <= targetValue) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static int minUpperIndex(final int[] nums, final int targetValue, final int i, final int j) {
        int left = i + 1;
        int right = j;
        int mid;
        while (left < right) {
            mid = ((right - left) >> 1) + left;
            if (nums[mid] >= targetValue) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    /**
     * for循环的写法
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSumFor(final int[] nums, final int target) {
        int sum;
        int i = 0;
        for (int j = nums.length - 1; j > i; j--) {
            for (; i < j; i++) {
                sum = nums[i] + nums[j];
                if (sum > target) {
                    break;
                }
                if (sum < target) {
                    continue;
                }
                return new int[]{nums[i], nums[j]};
            }
        }

        return new int[]{};
    }


    private static List<int[]> twoSumMultiNoDuplicateResults(final int[] nums, final int target) {
        final List<int[]> results = new LinkedList<>();
        int i = 0;
        int j = nums.length - 1;
        int sum;
        while (i < j) {
            sum = nums[i] + nums[j];
            if (sum > target) {
                j--;
                continue;
            }
            if (sum < target) {
                i++;
                continue;
            }
            results.add(new int[]{nums[i], nums[j]});
            while (i + 1 < j && nums[i] == nums[i + 1]) {
                i++;
            }
            i++;
        }
        return results;
    }

    private static List<int[]> twoSumMultiNoDuplicateResultsFor(final int[] nums, final int target) {
        final List<int[]> results = new LinkedList<>();

        int sum;
        int i = 0;
        for (int j = nums.length - 1; j > i; j--) {
            for (; i < j; i++) {
                sum = nums[i] + nums[j];
                if (sum > target) {
                    break;
                }
                if (sum < target) {
                    continue;
                }
                results.add(new int[]{nums[i], nums[j]});
                while (i + 1 < j && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }

        return results;
    }
}
