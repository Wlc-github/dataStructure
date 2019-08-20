package huffmanCoding;

import java.io.*;
import java.util.*;

/**
 * 哈夫曼编码，压缩字符串,压缩文件
 */
public class HuffmanCode {
    public static void main(String[] args) {
        //测试压缩文件
        //zipFile("G://11.png", "G://test.zip");
        //测试解压文件
        dezipFile("G://img.zip", "G://22.png");
//        String s = "我是你爸爸";
//        byte[] zip = huffmanEncoding(s);
//        System.out.println(Arrays.toString(zip));
//        System.out.println(huffmanCoding(zip));

    }

    private static Map<Byte, String> HUFFMANCODES = new HashMap<>();//哈夫曼字典

    /**
     * 压缩文件
     *
     * @param src 被压缩文件
     * @param dir 压缩文件目录
     */
    public static void zipFile(String src, String dir) {
        FileInputStream is = null;//读取文件
        FileOutputStream os = null;//写入文件
        ObjectOutputStream oos = null;
        try {
            is = new FileInputStream(src);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            os = new FileOutputStream(dir);
            oos = new ObjectOutputStream(os);
            oos.writeObject(zip(transform(bytes)));
            oos.writeObject(HUFFMANCODES);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void dezipFile(String src, String dir) {
        FileInputStream is = null;//读取文件
        FileOutputStream os = null;//写入文件
        ObjectInputStream ois = null;
        try {
            is = new FileInputStream(src);
            ois = new ObjectInputStream(is);
            os = new FileOutputStream(dir);
            byte[] bytes = (byte[]) ois.readObject();
            HUFFMANCODES = (Map<Byte, String>) ois.readObject();
            os.write(coding(decode(bytes)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                ois.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 集成编码操作
     *
     * @param s
     * @return 返回编码数组
     */
    public static byte[] huffmanEncoding(String s) {
        if (s == null || s.length() < 1)
            return null;
        byte[] bytes = s.getBytes();
        return zip(transform(bytes));
    }

    /**
     * 集成哈夫曼解码过程
     *
     * @param bytes
     * @return
     */
    public static String huffmanCoding(byte[] bytes) {
        return new String(coding(decode(bytes)));
    }

    /**
     * 编码：构建哈夫曼树（使用HashMap储存字符和出现个数）
     *
     * @param bytes 需要编码的字符串对应的byte数组
     * @return 哈夫曼树根节点
     */
    public static Node createTree1(byte[] bytes) {
        List<Node> list = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>(128);
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null)
                map.put(b, 1);
            else map.put(b, count + 1);
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.right = right;
            parent.left = left;
            list.remove(1);//使用位置删除，比使用值删除更高效
            list.remove(0);
            list.add(parent);
        }
        return list.get(0);
    }

    /**
     * 编码：(只能识别英文不能只别中文）按照传入的字符串构建哈夫曼树
     * 使用byte[]数组临时存储
     *
     * @param bytes 需要编码的字符串对应的byte数组
     * @Return 返回哈夫曼树的根节点
     */
    public static Node createTree(byte[] bytes) {
        byte[] bytes1 = new byte[128];//使用byte数组统计，缺点只能统计英文字符
        List<Node> list = new ArrayList<>();
        for (byte b : bytes) {
            bytes1[b]++;
        }
        for (int i = 0; i < 128; i++) {
            if (bytes1[i] > 0) {
                list.add(new Node((byte) i, bytes1[i]));
            }
        }
        while (list.size() > 1) {
            Collections.sort(list);
            Node left = list.get(0);
            Node right = list.get(1);
            Node parent = new Node(null, left.weight + right.weight);
            parent.right = right;
            parent.left = left;
            list.remove(1);//使用位置删除，比使用值删除更高效
            list.remove(0);
            list.add(parent);
        }
        return list.get(0);
    }

    /**
     * 编码：计算哈夫曼字典
     *
     * @param node 哈夫曼树的根节点
     * @param sb   传入的字符串
     *             该程序结束时，编码后的字符串存入静态stringBuilder中，哈夫曼字典也存入了map中
     */
    public static void encoding(Node node, StringBuilder sb) {
        StringBuilder stringBuilder1 = new StringBuilder(sb);
        if (node.left != null) {
            stringBuilder1.append(0);
            encoding(node.left, stringBuilder1);
        }
        if (node.right != null) {

            sb.append(1);
            encoding(node.right, sb);
        }
        if (node.b != null) {
            HUFFMANCODES.put(node.b, stringBuilder1.toString());
        }
    }

    /**
     * 编码：将字符串转换为哈夫曼码
     *
     * @param bytes 转换的bytes数组
     * @return
     */
    public static String transform(byte[] bytes) {
        Node root = createTree1(bytes);
        encoding(root, new StringBuilder());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HUFFMANCODES.get(b));
        }
        return sb.toString();
    }

    /**
     * 编码：将哈夫曼码放入byte数组
     *
     * @param s 传入编码后的字符串
     * @return
     */
    public static byte[] zip(String s) {
        int len = s.length(), i;
        byte[] bytes = new byte[(len + 7) / 8 + 1];
        for (i = 0; i < len - 8; i += 8) {
            bytes[i / 8] = (byte) Integer.parseInt(s.substring(i, i + 8), 2);
        }
        String s1 = s.substring(i);
        if (s1.charAt(0) == '0') {
            s1 = "1" + s1.substring(1);
            bytes[i / 8 + 1] = 0;
        } else bytes[i / 8 + 1] = 1;
        bytes[i / 8] = (byte) Integer.parseInt(s1, 2);
        return bytes;
    }

    /**
     * 解码：将byte数组中存放的哈夫曼码转换为字符串
     *
     * @param bytes 存储哈夫曼码的数组
     * @return 哈夫曼码
     */
    public static String decode(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int i, len;
        for (i = 0, len = bytes.length; i < len - 2; i++) {
            String str = Integer.toBinaryString(bytes[i] | 256);//在字符中间的需要补零
            sb.append(str.substring(str.length() - 8));//int有32位故只取最后八位
        }
        if (bytes[len - 1] == 0) {
            sb.append(0);
            sb.append(Integer.toBinaryString(bytes[i] & 255).substring(1));
        } else
            sb.append(Integer.toBinaryString(bytes[i] & 255));//数组最后一个元素不需要补零
        return sb.toString();
    }

    /**
     * 解码：将哈夫曼码按照字典还原成字符串
     *
     * @param s
     * @return
     */
    public static byte[] coding(String s) {
        Map<String, Byte> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        List<Byte> list = new ArrayList<>();
        for (Map.Entry<Byte, String> entry : HUFFMANCODES.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        for (int i = 0, count, len = s.length(); i < len; ) {
            count = 1;
            while (true) {
                String str = s.substring(i, i + count);
                Byte b = map.get(str);
                if (b != null) {
                    list.add(b);
                    i += count;
                    break;
                } else count++;
            }
        }
        int len = list.size();
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++)
            b[i] = list.get(i);
        return b;
    }
}

/**
 * 构造树节点
 */
class Node implements Comparable<Node> {
    Byte b;
    int weight;
    Node left;
    Node right;

    public Node(Byte b, int weight) {
        this.b = b;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "b=" + b +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public void backOrder(Node node) {

        if (node.left != null)
            backOrder(node.left);
        if (node.right != null)
            backOrder(node.right);
        System.out.print(node.b);
    }
}