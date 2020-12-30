package huawei.finalRound;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Zhang Yi
 */
public class TestB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        //n个格子，n个字符填进来, n<=10
        //字符char[0~n-1], 待填充字符[0~n-1]
        char[] chars = line.toCharArray();
        Set<Integer> result = new HashSet<>();

        fill(result, chars, 0);

        System.out.println(result.size());
    }

    private static void fill(Set<Integer> result, char[] chars, int first) {
        if (first == chars.length && chars[0] != '0') {
            result.add(Integer.parseInt(new String(chars)));
            return;
        }

        for (int i = first; i < chars.length; i++) {
            exch(chars, i, first);

            fill(result, chars, first + 1);

            exch(chars, i, first);
        }
    }

    private static void exch(char[] array, int a, int b) {
        char temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
