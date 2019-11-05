package sortalgorithm;

import java.util.Arrays;

/**
 * 实现快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] a = new int[100];
        for (int i = 0; i < 100; i++) {
            a[i] = (int) (Math.random() * 1000);
        }
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] nums) {
        quickSort(nums, 0, nums.length);
    }

    private static void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        int pre = start;
        int last = end - 1;
        int key = nums[start];
        while (pre < last) {
            while (nums[last] >= key && pre < last)
                last--;
            while (nums[pre] <= key && pre < last)
                pre++;
            if (pre < last)
                Swap.swap(nums, pre, last);
        }
        nums[start] = nums[last];
        nums[last] = key;
        quickSort(nums, start, pre);
        quickSort(nums, pre + 1, end);
    }
}
