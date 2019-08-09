package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 构造哈夫曼树
 */
public class HuffMan {
    public static Node createhuffMan(int[] a) {
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
        Node.preOrder(createhuffMan(a));
    }
}

class Node implements Comparable<Node> {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }

    public static void preOrder(Node node) {//先序遍历二叉树
        if (node.left == null && node.right == null)//只输出叶子结点
            System.out.print(node.val + " ");
        if (node.left != null)
            preOrder(node.left);
        if (node.right != null)
            preOrder(node.right);
    }
}