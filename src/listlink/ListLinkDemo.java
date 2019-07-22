package listlink;

/**
 * 单向链表
 */
public class ListLinkDemo {


    public static void main(String[] args) {
        ListNode test1=new ListNode(1,"1");
        ListNode test2=new ListNode(2,"2");
        ListNode test3=new ListNode(3,"3");
        ListNode test4=new ListNode(4,"4");
        ListNode test44=new ListNode(4,"44");
        ListLink listLink=new ListLink();
        ListLink listLink1=new ListLink();
        listLink.add(test1);
        listLink.add(test2);
        listLink.add(test3);
        listLink.add(test4);
        listLink.toPrint();
        listLink.inversion(listLink.getHead());
//        listLink.delete(3);
//        listLink.toPrint();
//        listLink.delete(4);
//        listLink.update(test44);
//        listLink.toPrint();
    }
}

class ListLink{
        public ListNode head;
        private ListNode temp;
        public ListLink(){
            this.head=new ListNode(0,null);
        }
        public void add(ListNode listNode){
            if(head.next==null) {
                head.next = listNode;
                return;
            }
            temp=head;
            while(temp.next!=null){
                if(listNode.no<temp.next.no){
                    listNode.next=temp.next;
                    temp.next=listNode;
                    return;
                }
                else if(listNode.no==temp.next.no){
                    System.out.println("错误！："+ listNode.no+"该顺序已有值");
                    return;
                }
                else temp=temp.next;
            }
            temp.next=listNode;
        }
        public ListNode getHead(){
            return head;
        }
        public void delete(int no){
            temp=head;
            if(head.next==null){
                System.out.println("链表为空");
                return;
            }
            while(temp.next!=null){
                if(temp.next.no==no){
                    temp.next=temp.next.next;
                    return;
                }
                temp=temp.next;
            }
            System.out.println("暂无该元素");
        }
        public void update(ListNode listNode){
            temp=head;
            if(head.next==null){
                System.out.println("链表为空");
                return;
            }
            while(temp.next!=null){
                if(temp.next.no==listNode.no){
                    listNode.next=temp.next.next;
                    temp.next=listNode;
                    return;
                }
                temp=temp.next;
            }
            System.out.println("暂无该元素");
        }
        public void toPrint() {
            if(head.next==null){
                System.out.println("链表为空");
                return;
            }
            temp=head;
            while (temp != null) {
                System.out.println(temp.toString());
                temp=temp.next;
            }
        }
        public void inversion(ListNode test){
            if(test!=null){
                if(test.next!=null){
                    inversion(test.next);
                }
                System.out.println(test);
            }

        }
    }
class ListNode{
        public int no;
        public String info;
        public ListNode next;
        public ListNode(int no,String info){
            this.no=no;
            this.info=info;
            this.next=null;
        }
        @Override
        public String toString() {
            return "ListNode{" +
                    "no=" + no +
                    ", info='" + info + '\'' +
                    '}';
        }

}
