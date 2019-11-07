package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造哈夫曼树
 */
public class HuffMan {

    public static Node createHuffMan(int[] a) {

        List<Node> list = new ArrayList<>();
        for (int i : a) {
            list.add(new Node(i));
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(left.val + right.val);
            parent.left = left;
            parent.right = right;
            list.remove(1);//使用位置删除，比使用值删除更高效
            list.remove(0);
            list.add(parent);
        }
        return list.get(0);
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 5, 7};
        createHuffMan(a).preOrder();
    }
}