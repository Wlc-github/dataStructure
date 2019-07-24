package sortalgorithm;

/**
 * 实现希尔排序，希尔排序是对插入排序的优化（不稳定）
 * 采用增量分割方式
 * 引用一个增量将序列分组，在每一组中使用插入排序，不断缩小增量，直至增量为一。
 */
public class ShellSort {
    public void shell(int[] a) {
        if (a == null || a.length <= 1) return;
        int d = a.length / 2, i, j, x, temp;
        while (d > 0) {
            for (i = 0; i < d; i++) {
                for (j = i + d; j < a.length; j = j + d) {
                    for (x = j; x >= d; x = x - d) {
                        temp = a[x - d];
                        if (a[x] < temp) {
                            a[x - d] = a[x];
                            a[x] = temp;
                        } else break;
                    }
                }
            }
            d = d / 2;
        }
    }
}
