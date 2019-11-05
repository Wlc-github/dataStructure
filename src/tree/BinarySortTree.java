package tree;

/**
 * 对于树中重复值的删除与查询问题，可在树插入节点时设置，如相等则置为结点的左子树
 * 删除和查询时找到节点后判断它的左子树是否相同即可
 */
public class BinarySortTree {
    private tNode root;

    public BinarySortTree(int[] a) {
        root = new tNode(a[0]);
        for (int i = 1, len = a.length; i < len; i++) {
            root.add(new tNode(a[i]));
        }
    }

    public void add(tNode node) {
        if (root == null) {
            System.out.println("该二叉排序树不存在");
        } else root.add(node);
    }

    /**
     * 删除节点根据采取的策略不同生成的子树便不同
     * 本方法采用的策略为将右子树上移后将原结点的左子树作为新节点的最左节点的左子树
     * 该方法存在缺陷，如果树中存在多个元素，无法删除多个元素，只能删除一个
     *
     * @param val
     */
    public boolean delete(int val, tNode node) {//根据值来删除
        if (node.val > val) {
            if (node.left != null) {
                if (delete(val, node.left)) {
                    if (node.left.left != null && node.left.right == null)
                        node.left = node.left.left;
                    else node.left = node.left.right;
                }
            } else {
                System.out.println("树无此结点");
                return false;
            }
        } else if (node.val < val) {
            if (node.right != null) {
                if (delete(val, node.right)) {
                    if (node.right.left != null && node.right.right == null)
                        node.right = node.right.left;
                    else
                        node.right = node.right.right;
                }
            } else {
                System.out.println("树无此结点");
                return false;
            }
        } else if (node.val == val) {
            if (node == root) {//只有第一次会进入
                if (root.left != null && root.right != null) {
                    root.right.add(root.left);
                    root = root.right;
                } else if (root.left != null)
                    root = root.left;
                else root = root.right;
                return true;
            } else {
                if (node.left != null && node.right != null)
                    node.right.add(node.left);
                return true;
            }
        }
        return false;
    }

    /**
     * 与删除一致无法找到多个值相同节点，甚至于如不删除则每次都找到同一节点
     *
     * @param val
     * @return
     */
    public tNode getByValue(int val) {
        tNode node = root;
        while (node.val != val) {
            if (node.val > val)
                node = node.left;
            else node = node.right;
        }
        return node;
    }

    public static void main(String[] args) {
        int[] a = {1, 6, 3, 7, 4, 3, 3};
        BinarySortTree binarySortTree = new BinarySortTree(a);
        tNode root = binarySortTree.root;
        root.midOrder();
        binarySortTree.delete(1, root);
        System.out.println();
        binarySortTree.root.midOrder();
        System.out.println(binarySortTree.getByValue(4));

    }
}

class tNode {
    int val;
    tNode left;
    tNode right;

    public void add(tNode node) {
        if (this.val < node.val) {
            if (this.right == null)
                this.right = node;
            else this.right.add(node);
        } else {
            if (this.left == null)
                this.left = node;
            else this.left.add(node);
        }
    }

    public void midOrder() {
        if (this.left != null)
            this.left.midOrder();
        System.out.print(this.val + " ");
        if (this.right != null)
            this.right.midOrder();
    }

    public tNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "tNode{" +
                "val=" + val +
                '}';
    }
}