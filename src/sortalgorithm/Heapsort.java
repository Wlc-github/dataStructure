package sortalgorithm;

import java.util.Arrays;

/**
 * 堆排序（不稳定算法）
 * 使用完全二叉树作为数据结构实现排序（最大堆）
 * 思想：父节点需大于左右节点，左右节点无大小要求，每一次将根节点置为最后一节点，树减一，重新调节树，重复
 */
public class Heapsort {
    public static void main(String[] args) {
        int[] a = {5, 6, 8, 3, 0, 1, 44, 6, 3};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] nums) {
        int len = nums.length;
        for (int j = len / 2; j >= 0; j--) {
            heap(nums, j, len);
        }
        for (int i = 0, length = len; i < length; i++) {
            heap(nums, 0, len--);
            Swap.swap(nums, 0, len);
        }
    }

    public static void heap(int[] nums, int index, int length) {
        int len = length;
        int largest = index;
        if ((index * 2 + 2) < len) {//左右子树都存在
            if (nums[index * 2 + 1] < nums[index * 2 + 2]) {//右子树大
                if (nums[index] < nums[index * 2 + 2])
                    largest = index * 2 + 2;
            } else {
                if (nums[index] < nums[index * 2 + 1])
                    largest = index * 2 + 1;
            }
        } else if ((index * 2 + 1) < len) {//只有左子树存在
            if (nums[index] < nums[index * 2 + 1])
                largest = index * 2 + 1;
        } else return;
        if (largest != index) {//如果最大值不是父节点则交换然后递归子节点
            Swap.swap(nums, largest, index);
            heap(nums, largest, len);
        }
    }
}
