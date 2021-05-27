package com.zhangyi.string;

/**
 *
 * 字符串s（收尾相接）循环移动任意位置之后得到另外一个字符串t，s就称为t的回环变位(circular rotation).
 *
 * ACTGACG
 *   ^
 * TGACGAC
 *
 * @author Zhang Yi
 */
public class CircularRotation {
    public static void main(String[] args) {
        String s = "ACTGACG";
        String t = "TGACGAC";

        int i = (s + s).indexOf(t);
        if (i >=0) {
            System.out.println("found circular rotation:" + i);
        }

        System.out.println("s:" + s);
        System.out.println("mystery(s):" + mystery(s));

        s.split("\\s+");

    }


    public static String mystery(String s) {
        int N = s.length();
        if (N <= 1) return s;
        String a = s.substring(0, N/2);
        String b = s.substring(N/2, N);
        return mystery(b) + mystery(a);
    }
}
