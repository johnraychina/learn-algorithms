package 每日一题;

import java.util.HashMap;
import java.util.Map;

/**
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 整数转罗马数字12 {
//    字符          数值
//    I             1
//    V             5
//    X             10
//    L             50
//    C             100
//    D             500
//    M             1000

    public static final Map<Integer, String> map = new HashMap<>(13);

    static {
        map.put(1, "I");
        map.put(2, "II");
        map.put(3, "III");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(6, "VI");
        map.put(7, "VII");
        map.put(8, "VIII");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(20, "XX");
        map.put(30, "XXX");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(60, "LX");
        map.put(70, "LXX");
        map.put(80, "LXXX");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(200, "CC");
        map.put(300, "CCC");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(600, "DC");
        map.put(700, "DCC");
        map.put(800, "DCCC");
        map.put(900, "CM");
        map.put(1000, "M");
        map.put(2000, "MM");
        map.put(3000, "MMM");
    }

    public static String intToRoman(int num) {

        Integer radix = 1000;
        StringBuilder ans = new StringBuilder();

        //拆分数字
        while (num > 0) {
            while (num < radix) {
                radix = radix / 10;
            }

            String s = map.get((num / radix) * radix);
            ans.append(s);
            num = num % radix;
        }

        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
    }
}
