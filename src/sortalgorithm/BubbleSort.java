package sortalgorithm;


import java.util.Arrays;

/**
 * 冒泡排序（正序）（稳定算法）
 * 引入一个bool变量来判断数组是否提前有序，从而减少不必要的排序；
 * 双向冒泡排序
 * 从前后两端同时遍历，增加排序的速度
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] num = new int[300];
        for (int i = 0; i < 300; i++) {
            num[i] = (int) (Math.random() * 300);
        }
        int[] a = {1, 4, 5, 7, 2, 3, 4, 8, 7};
        sort(num);
        Arrays.stream(num).forEach(System.out::println);
    }

    public static void sort(int[] a) {//单向排序
        boolean isOk;
        for (int i = 0, len = a.length; i < len - 1; i++) {
            isOk = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    isOk = false;
                }
            }
            if (isOk)
                break;
        }
    }

    public static void doubleSort(int[] a) {//双向冒泡
        boolean isOk;
        for (int i = 0, len = a.length; i < len - 1; i++) {
            isOk = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    isOk = false;
                }
            }
            if (isOk)
                break;
        }
        for (int i = a.length - 1; i > 0; i--) {
            isOk = true;
            for (int j = a.length - 1, len = a.length; j >= len - i; j--) {
                if (a[j] < a[j - 1]) {
                    swap(a, j, j - 1);
                    isOk = false;
                }
            }
            if (isOk)
                break;
        }
    }

    public static void swap(int[] a, int i, int j) {
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }
}
