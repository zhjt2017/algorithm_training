package com.teachingpractice.week5;

public class Sol {
    public int[] twoSum(int[] numbers, int target) {
        final int n = numbers.length;
        int left = 0;
        int right = n - 1;
        int sum;
        while (left < right) {
            sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }
}
