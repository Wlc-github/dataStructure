package sortalgorithm;


/**
 * 冒泡排序（正序）（稳定算法）
 * 引入一个bool变量来判断数组是否提前有序，从而减少不必要的排序；
 * 双向冒泡排序
 * 从前后两端同时遍历，增加排序的速度
 * 快速排序（不稳定算法）
 * 选取一个基数对序列进行排序，使得序列中比基数小的数在基数左侧，比基数大的在基数右侧，不断分割直至子序列长度为一，此时排序完成
 */
public class BubbleSort {
    int i, j, temp;
    boolean flag = false;

    //原始算法从前向后遍历
    public void bubble(int[] a) {
        if (a == null || a.length <= 1) return;
        flag = false;//标识符用于改进冒泡排序
        for (i = 0; i < a.length - 1; i++) {
            for (j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    flag = true;
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            if (!flag)
                break;
            else flag = false;
        }
    }

    //原始算法从后向前遍历
    public void bubblepre(int[] a) {
        if (a == null || a.length <= 1) return;
        flag = false;//标识符用于改进冒泡排序
        for (i = 0; i < a.length - 1; i++) {
            for (j = a.length - 2; j >= i; j--) {
                if (a[j] > a[j + 1]) {
                    flag = true;
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            if (!flag)
                break;
            else flag = false;
        }
    }

    //使用双向排序改进冒泡排序
    public void bothWay(int[] a) {
        if (a == null || a.length <= 1) return;
        int preIndex = 0, backIndex = a.length - 1, i = 0;
        flag = false;
        while (preIndex < backIndex) {
            for (j = i; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {
                    flag = true;
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }

            }
            backIndex--;
            if (!flag) {
                break;
            } else flag = false;
            for (j = a.length - 2 - i; j >= i; j--) {//
                if (a[j] > a[j + 1]) {
                    flag = true;
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
            preIndex++;
            if (!flag) {
                break;
            } else flag = false;
            i++;//记录已经移动的位数以减少遍历的元素
        }
    }

    public void quicksort(int[] a, int preIndex, int backIndex) {//合并了两次交换的步骤
        if (a == null || a.length <= 1) return;
        int index = preIndex;
        int back = backIndex;
        if (preIndex >= backIndex) return;//递归结束条件
        while (preIndex < backIndex) {
            while (preIndex < backIndex && a[index] <= a[backIndex]) {//从右向左找到第一个小于索引的值的位置
                backIndex--;
            }
            while (preIndex < backIndex && a[index] >= a[preIndex]) {//从左向右找到第一个大于索引的位置
                preIndex++;
            }
            if (preIndex < backIndex) {//如果大于索引的值在小于索引的值的左边则交换
                temp = a[backIndex];
                a[backIndex] = a[preIndex];
                a[preIndex] = temp;
            }
        }
        temp = a[index];//最后交换索引和backIndex(此时backIndex指向的值为从右至左第一个小于索引的值）的值
        a[index] = a[backIndex];
        a[backIndex] = temp;
        if (index != backIndex)//将序列不断分割直至序列长度为一
            quicksort(a, index, backIndex);
        if (preIndex + 1 != back)
            quicksort(a, preIndex + 1, back);
    }
}
