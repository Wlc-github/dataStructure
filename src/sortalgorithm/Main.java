package sortalgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int[] a = {49, 38, 35, 76, 13, 49, 34, 12, 64, 1};
        int[] b = new int[80000000];
        for (int i = 0; i < b.length; i++) {
            b[i] = (int) (Math.random() * 10000000);
        }

        ShellSort shellSort = new ShellSort();
        BubbleSort bubbleSort = new BubbleSort();
        MergeSort mergeSort = new MergeSort();
        Heapsort heapsort = new Heapsort();
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(date1));
        //print(a);
        //shellSort.shell(b);
        //bubbleSort.bubblepre(b);
        //bubbleSort.bothWay(b);
        //mergeSort.cutOff(b,0,b.length-1,new int[b.length]);
        //  Tree tree=new Tree(b);
        //  Heapsort.heapsort(tree);
        // bubbleSort.quicksort(b,0,b.length-1);
        BucketSort.radixSort(b);
        //print(a);
        //heapsort.test(b);
        //Test.heapSort(b);
        Date date2 = new Date();
        System.out.println(simpleDateFormat.format(date2));
    }

    public static void print(int[] a) {
        System.out.println();
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            if ((i + 1) % 10 == 0)
                System.out.println();
        }
    }
}
