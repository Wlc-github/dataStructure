package listlink;

/**
 * 约瑟夫环问题
 * 使用单向循环链表
 */
public class Josephu {
    public static void main(String[] args) {
            DoubleLink doubleLink=new DoubleLink(25);
            doubleLink.toPrint();
            doubleLink.orderBy(1,5);
    }
}
class DoubleLink {//单向循环链表类
    private int number;
    public LinkNode first;

    public DoubleLink(int number) {
        this.number = number;
        if (number < 1)
            return;
        LinkNode temp = null;
        for (int i = 0; i < number; i++) {
            LinkNode linkNode = new LinkNode(i);
            if (i == 0) {
                first = linkNode;
                temp = first;
                first.next = first;
            } else {
                temp.next = linkNode;
                linkNode.next = first;
                temp = temp.next;
            }
        }
    }
    public void toPrint() {
        if(first==null){
            System.out.println("链表为空");
            return;
        }
        LinkNode temp=first;
        do {
            System.out.println(temp.no);
            temp=temp.next;
        }while (temp!=first);
    }
    public void orderBy(int start, int m) {

        if (m > number || m < 1 || start < 1 || start > number) {
            System.out.println("error:数据错误");
            return;
        }
        if (first == null) {
            System.out.println("error:链表为空");
            return;
        }
        LinkNode helper;
        helper=first;
        while(helper.next!=first){
            helper=helper.next;
        }
        for(int i=1;i<start;i++){
            first=first.next;
            helper=helper.next;
        }
        while(helper!=first){
            for(int i=1;i<m;i++){
                first=first.next;
                helper=helper.next;
            }
            System.out.println("out:"+first.no);
            first=first.next;
            helper.next=first;
        }
        System.out.println("the last:"+first.no);
    }
}
class LinkNode{
    public int no;
    public LinkNode next;
    public LinkNode(int no){
        this.no=no;
        this.next=null;
    }

    @Override
    public String toString() {
        return "LinkNode{" +
                "no=" + no +
                '}';
    }
}
