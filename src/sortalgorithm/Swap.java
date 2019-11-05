package sortalgorithm;

/**
 * 交换元素
 */
public class Swap {
    public static void swap(int[] nums, int x, int y) {
        nums[x] = nums[x] ^ nums[y];
        nums[y] = nums[x] ^ nums[y];
        nums[x] = nums[x] ^ nums[y];
    }
}
