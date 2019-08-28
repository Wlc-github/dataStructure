package graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 采用数组加链表的储存方式
 */
public class Graph {
    private Linklist[] graph;
    private boolean[] isvisited;


    public static void main(String[] args) {
        int[][] a = {
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0}
        };
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        //Graph graph1 = new Graph(a, nums);
        //graph1.bfs(graph1.graph[0]);
    }

    public Graph(int[][] a, int[] nums) {//将邻接表法转换为数组加链表
        int len = nums.length;
        graph = new Linklist[len];
        for (int i = 0; i < len; i++)
            graph[i] = new Linklist(nums[i], i);
        isvisited = new boolean[len];
        transform(a);
    }

    private void transform(int[][] a) {
        int len = a.length;
        Linklist temp;
        for (int i = 0; i < len; i++) {
            temp = graph[i];
            for (int j = 0; j < len; j++) {
                if (a[i][j] == 1) {
                    temp.next = new Linklist(graph[j].data, j);
                    temp = temp.next;
                }
            }
        }
    }

    /**
     * 非递归实现，使用栈
     *
     * @param node 调用时传入图的起点
     */
    public void dfsStack(Linklist node) {
        Stack<Linklist> stack = new Stack<>();
        do {
            if (!isvisited[node.no]) {
                System.out.print(node.data + " ");
                stack.push(node);
                isvisited[node.no] = true;
            }
            while (node.next != null) {
                node = node.next;
                if (!isvisited[node.no]) {
                    node = graph[node.no];
                    break;
                }
            }
            if (node.next == null && isvisited[node.no]) {
                stack.pop();
                if (!stack.isEmpty())
                    node = graph[stack.peek().no];
            }
        } while (!stack.isEmpty());
    }

    /**
     * 使用递归
     *
     * @param node 调用时传入图的起点
     */
    public void dfs(Linklist node) {
        System.out.print(node.data + " ");
        isvisited[node.no] = true;
        while (node.next != null) {
            node = node.next;
            if (!isvisited[node.no]) {
                dfs(graph[node.no]);
            }
        }
    }

    /**
     * 使用队列实现广度搜索
     *
     * @param node
     */
    public void bfs(Linklist node) {
        Queue<Linklist> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (!isvisited[node.no]) {
                System.out.print(node.data + " ");
                isvisited[node.no] = true;
            }
            while (node.next != null) {
                node = node.next;
                if (!isvisited[node.no]) {
                    queue.add(graph[node.no]);
                }
            }
        }
    }
}

class Linklist {
    int data;
    int no;
    Linklist next;

    public Linklist(int data, int no) {
        this.data = data;
        this.no = no;
    }

    @Override
    public String toString() {
        return "Linklist{" +
                "data=" + data +
                ", no=" + no + ", next=" + next +
                '}';
    }
}
