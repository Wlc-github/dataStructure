package AVL;

public class AVL {
    public static void main(String[] args) {
        int[] a = {10, 11, 7, 6, 8, 9};
        AVL avl = new AVL(a);
        avl.root.midOrder();
        System.out.println(avl.root.left.countHeight());
        System.out.println(avl.root.right.countHeight());
    }

    private Node root;

    public AVL(int[] a) {
        root = new Node(a[0]);
        for (int i = 1, len = a.length; i < len; i++) {
            add(new Node(a[i]));
        }
    }

    public void add(Node node) {
        this.root = root.add(node);
    }


}

class Node {
    int data;
    int balance;//平衡因子(计算为左树减右树
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }

    public Node(Node node) {
        this.balance = node.balance;
        this.right = node.right;
        this.data = node.data;
        this.left = node.left;
    }

    public void midOrder() {
        if (this.left != null)
            this.left.midOrder();
        System.out.println(this.data);
        if (this.right != null)
            this.right.midOrder();

    }

    public Node add(Node node) {
        Node root = this;
        if (root.data < node.data) {
            if (root.right == null)
                root.right = node;
            else
                root.right.add(node);
        } else {
            if (root.left == null)
                root.left = node;
            else root.left.add(node);
        }
        balance = countBalance();
        if (balance == 2) {//右旋
            root = dextrorotation(root);
        }
        if (balance == -2) {//左旋
            root = levorotation(root);
        }
        return root;
    }

    public int countBalance() {
        return this.left == null ? this.right == null ? 0 : -this.right.countHeight() : this.right == null ? this.left.countHeight() : this.left.countHeight() - this.right.countHeight();
    }

    public int countHeight() {
        return Math.max(this.left == null ? 0 : this.left.countHeight(), this.right == null ? 0 : this.right.countHeight()) + 1;
    }

    ///Levorotation and dextrorotation
    public Node levorotation(Node root) {
        if (root.right.balance >= 1) //右子树的左子树超出范围先右旋再左旋
            dextrorotation(root.right);
        Node temp = new Node(root);
        temp.right = root.right.left;
        root.data = root.right.data;
        root.right = root.right.right;
        root.left = temp;
        root.balance = root.countBalance();
        root.left.balance = root.left.countBalance();
        return root;
    }

    public Node dextrorotation(Node root) {
        if (root.left.balance <= -1)
            levorotation(root.left);
        Node temp = new Node(root);
        temp.left = root.left.right;
        root.data = root.left.data;
        root.left = root.left.left;
        root.right = temp;
        root.balance = root.countBalance();
        root.right.balance = root.right.countBalance();
        return root;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                '}';
    }
}