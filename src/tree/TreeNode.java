package tree;

public class TreeNode {
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
