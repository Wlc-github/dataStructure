package sortalgorithm;

import java.util.Arrays;

/**
 * 桶排序（稳定）：计数排序、基数排序
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] a = {1, 3, 4, 5, 2, 6, 7, 7, 8, 9};
//        CountSort(a);
        radixSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 计数排序：
     * 申请一个空间为max-min+1的数组count，统计原数组中的值，count[i]为原数组中值为min+i的个数
     * 最后将c中的元素输出即可。
     *
     * @param a
     */
    public static void CountSort(int[] a) {//计数排序
        if (a == null || a.length <= 1) return;
        int max, min;
        max = min = a[0];
        for (int i : a) {
            if (i > max)
                max = i;
            if (i < min) min = i;
        }
        int[] count = new int[max - min + 1];
        for (int i : a) {
            count[i - min]++;
        }
        int x = 0;
        for (int i = 0; i <= max - min; i++) {
            while (count[i] != 0) {
                a[x++] = min + i;
                count[i]--;
            }
        }
    }

    /**
     * @param a 传入的数组
     *          基数排序：申请一个空间为[10][a.length]的二维数组
     *          计算出最大数的位数，取出每个数的个位、十位、百位至最高位（没有则补零）从个位开始排序，每次排序完成后放入原数组再次排序
     *          当最高位排序完成则整体排序完成
     */
    public static void radixSort(int[] a) {
        if (a == null || a.length <= 1) return;
        int[][] bucket = new int[10][a.length];
        int[] b = new int[10];//记录bucket[i]的个数(0<=i<=9)
        int max = a[0], i, j, m, n, k;
        for (int x : a) {
            if (x > max) max = x;
        }
        max = (max + "").length();
        for (i = 0, j = 1; i < max; i++, j *= 10) {
            k = 0;
            for (int x : a) {
                bucket[(x / j) % 10][b[(x / j) % 10]] = x;
                b[(x / j) % 10]++;
            }
            for (m = 0; m < 10; m++) {
                if (b[m] != 0) {
                    for (n = 0; n < b[m]; n++) {
                        a[k++] = bucket[m][n];
                    }
                    b[m] = 0;
                }
            }
        }
    }
}
