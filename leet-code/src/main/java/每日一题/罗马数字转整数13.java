package 每日一题;

import java.util.HashMap;
import java.util.Map;

/**
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/roman-to-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 罗马数字转整数13 {
    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
    }

    //        字符          数值
    //        I             1
    //        V             5
    //        X             10
    //        L             50
    //        C             100
    //        D             500
    //        M             1000

    // 重复次数
    // 规则：右加左減，
    // 如何拆分：量级作为peak，按peak切分，从左到右上升则切分
    // 有限的组合4和9，一个小数在大数左边
    // 10以内：IV, IX,
    // 100内：XL, XC
    // 1000内：CD, CM
    // 1994: MCMXCIV
    // 向右多看一个，升级则做减法
    public static int romanToInt(String s) {
        int ans = 0;
        int len = s.length();
        s = s + " ";
        for (int i = 0; i < len; i++) {
            char cur = s.charAt(i);
            int curNum = num(cur);
            char next = s.charAt(i + 1);
            int nextNum = num(next);
            if (curNum >= nextNum) { //下降则做加法
                ans += curNum;
            } else { //上升则做减法
                ans -= curNum;
            }
        }

        return ans;
    }
    private final static int[] map = new int[256];
    static {
        map[' '] = 0;
        map['I'] = 1;
        map['V'] = 5;
        map['X'] = 10;
        map['L'] = 50;
        map['C'] = 100;
        map['D'] = 500;
        map['M'] = 1000;
    }

    public static Integer num(char c) {
        return map[c];
    }

}
