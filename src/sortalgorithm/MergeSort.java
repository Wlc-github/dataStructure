package sortalgorithm;

import java.util.Arrays;

/**
 * 归并算法-采用分治思想（稳定算法）
 * 使用递归实现
 * 将序列不断分割直至分割为子序列长度为一，再将子序列进行拼接，在拼接的过程中进行排序，使得排序过程为两个有序序列拼接为一个有序序列
 */
public class MergeSort {
    /**
     * @param a     传入数组
     * @param left  起点
     * @param right 终点
     * @param temp  临时数组
     */
    public static void cutOff(int[] a, int left, int right, int[] temp) {
        if (a == null || a.length <= 1) return;
        if (left < right) {
            int mid = (right + left) / 2;
            cutOff(a, left, mid, temp);
            cutOff(a, mid + 1, right, temp);
            merge(a, left, mid, right, temp);
        }
    }

    /**
     * @param a     传入的数组
     * @param left  拼接串的起点
     * @param mid   拼接串的中点：拼接串为[left,min]和[mid+1,right]
     * @param right 拼接串的终点
     * @param temp  临时数组
     */
    public static void merge(int[] a, int left, int mid, int right, int[] temp) {
        int t = 0, rIndex = mid + 1, lIndex = left;
        while (lIndex <= mid && rIndex <= right) {
            if (a[lIndex] < a[rIndex]) {
                temp[t] = a[lIndex];
                t++;
                lIndex++;
            } else {
                temp[t] = a[rIndex];
                t++;
                rIndex++;
            }
        }
        while (lIndex <= mid) {
            temp[t] = a[lIndex];
            t++;
            lIndex++;
        }
        while (rIndex <= right) {
            temp[t] = a[rIndex];
            t++;
            rIndex++;
        }
        t = 0;
        while (left <= right) {
            a[left] = temp[t];
            t++;
            left++;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[30];
        for (int i = 0; i < 30; i++) {
            a[i] = (int) (Math.random() * 300);
        }
        cutOff(a, 0, 29, new int[30]);
        System.out.println(Arrays.toString(a));
    }
}
