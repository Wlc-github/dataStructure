package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {//二叉线索树

    public static void main(String[] args) {
        List<TreeNode> tree = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            tree.add(new TreeNode(i));
        }
        TreeNode root = tree.get(0);
        for (int i = 0, size = tree.size(); i <= size / 2 - 1; i++) {
            tree.get(i).setLeft(tree.get(2 * i + 1));
            if ((2 * i + 2) < size)
                tree.get(i).setRight(tree.get(2 * i + 2));
        }
        BinaryTree binaryTree = new BinaryTree(root);
        System.out.println("pre:");
        binaryTree.preOrder(root);
        System.out.println("\nmid:");
        binaryTree.midOrder(root);
        System.out.println("\nback");
        binaryTree.backOrder(root);
        binaryTree.midthread();
        System.out.println("\nthread:");
        binaryTree.treeList(root);
        System.out.println("\nthread2:");
        binaryTree.treeListBack(root);
    }

    private TreeNode root;//根节点
    private TreeNode pre = null;//辅助指针

    public TreeNode getRoot() {
        return root;
    }

    public void midthread() {
        thread(root);
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public void thread(TreeNode node) {//中序线索化二叉树
        if (node == null) return;
        thread(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        thread(node.getRight());
    }

    public void preOrder(TreeNode node) {//先序遍历二叉树
        System.out.print(node.getNo());
        if (node.getLeft() != null && node.getLeftType() == 0)
            preOrder(node.getLeft());
        if (node.getRight() != null && node.getRightType() == 0)
            preOrder(node.getRight());
    }

    public void midOrder(TreeNode node) {//中序遍历二叉树
        if (node.getLeft() != null && node.getLeftType() == 0)
            midOrder(node.getLeft());
        System.out.print(node.getNo());
        if (node.getRight() != null && node.getRightType() == 0)
            midOrder(node.getRight());
    }

    public void backOrder(TreeNode node) {//后序遍历二叉树

        if (node.getLeft() != null && node.getLeftType() == 0)
            backOrder(node.getLeft());

        if (node.getRight() != null && node.getRightType() == 0)
            backOrder(node.getRight());
        System.out.print(node.getNo());
    }

    public void treeList(TreeNode node) {//按前驱遍历
        while (node.getLeft() != null)
            node = node.getLeft();
        while (node != null) {
            System.out.print(node.getNo() + ",");
            if (node.getRightType() == 1) {
                node = node.getRight();
            } else {
                node = node.getRight();
                while (node != null && node.getLeftType() == 0)
                    node = node.getLeft();
            }
        }
    }

    public void treeListBack(TreeNode node) {//按后继遍历
        while (node.getRight() != null)
            node = node.getRight();
        while (node != null) {
            System.out.print(node.getNo() + " ");
            if (node.getLeftType() == 1) {
                node = node.getLeft();
            } else {
                node = node.getLeft();
                while (node.getRight() != null && node.getRightType() == 0)
                    node = node.getRight();
            }
        }
    }
}

class TreeNode {
    private int no;
    private TreeNode left;
    private TreeNode right;
    private int leftType;//线索化二叉树左指针标识为1为前驱，为0为左结点
    private int rightType;//线索化二叉树右指针标识为1为后继，为0为右结点

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public TreeNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "no=" + no +
                '}';
    }
}

