package tree;

public class Node implements Comparable<Node> {
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

    public void preOrder() {//先序遍历二叉树
        if (this.left == null && this.right == null)//只输出叶子结点
            System.out.print(this.val + " ");
        if (this.left != null)
            this.left.preOrder();
        if (this.right != null)
            this.right.preOrder();
    }
}
