package kmp;

public class KMP {
    public static void main(String[] args) {
        String ss = "BBS ABCDABCDABD";
        String s = "ABCDABD";
        int a = kmpMatch(ss, s);
        System.out.println(a);
    }

    /**
     * 求出匹配数组
     *
     * @param temp 匹配串
     * @return 返回匹配决定数组
     */
    private static int[] getMatchArr(String temp) {
        int len = temp.length();
        int[] array = new int[len + 1];
        array[0] = -1;
        for (int i = 1, j = 0; i < len; i++) {
            while (j > 0 && temp.charAt(j) != temp.charAt(i)) {
                j = array[j - 1];
            }
            if (temp.charAt(i) == temp.charAt(j)) {
                j++;
            }
            array[i + 1] = j;
        }
        return array;
    }

    /**
     * @param source 被匹配字符串
     * @param temp   匹配串
     * @return 返回匹配到的位置或者-1
     */
    public static int kmpMatch(String source, String temp) {
        int slen = source.length();
        int tlen = temp.length();
        int i = 0, j = 0;
        int[] next = getMatchArr(temp);
        while (i < slen && j < tlen) {
            if (source.charAt(i) == temp.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - next[j];
                j = 0;
            }
        }
        if (j == tlen)
            return i - j;
        else return -1;
    }
}
