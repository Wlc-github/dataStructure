package sortalgorithm;

import java.util.Arrays;

/**
 * 堆排序（不稳定算法）
 * 使用完全二叉树作为数据结构实现排序（最大堆）
 * 思想：父节点需大于左右节点，左右节点无大小要求，每一次将根节点置为最后一节点，树减一，重新调节树，重复
 */
public class Heapsort {
    public static void main(String[] args) {
        Heapsort heapsort = new Heapsort();
        int[] a = {5, 6, 8, 3, 0, 1, 44, 6, 3};
        heapsort.test(a);
        System.out.println(Arrays.toString(a));
    }

    public void test(int[] b) {
        if (b == null || b.length <= 1) return;
        int temp, length = b.length;
        for (int i = length / 2 - 1; i >= 0; i--) {//初始化堆
            disposal(b, length, i);
        }
        for (int i = 1; i < b.length; i++) {//将堆顶数字与堆底交换（进行排序，堆顶为当前最大数）将堆减一重新堆化
            temp = b[0];
            b[0] = b[length - 1];
            b[length - 1] = temp;
            length--;
            disposal(b, length, 0);
        }
    }

    /**
     * @param a      传入的数组
     * @param length 当前堆的长度
     * @param index  堆化的根节点
     */
    public static void disposal(int[] a, int length, int index) {//堆化
        int temp = a[index];//记录堆顶
        for (int i = 2 * index + 1, j = index; i < length; i = 2 * i + 1) {//从左孩子出发，并且每一次都寻找左孩子
            if (i + 1 < length && a[i] < a[i + 1]) i++;//如果右孩子存在并且大于左孩子则进入右孩子
            if (a[i] > temp) {//如果当前孩子大于堆顶则互换
                a[i] = a[i] ^ a[j];
                a[j] = a[i] ^ a[j];
                a[i] = a[i] ^ a[j];
                j = i;//将待换结点置为当前被更换的节点（从堆顶置换而来的值可能小于当前节点的孩子结点）所以需要继续判断
            } else break;
        }
    }
}
