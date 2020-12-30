package huawei.finalRound;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zhang Yi
 */
public class TestA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String ans = Stream.of(line.split(" ")).map(word -> reverse(word)).collect(Collectors.joining(" "));

        System.out.println(ans);
    }

    public static String reverse(String s) {
        char[] r = new char[s.length()];
        for (int i = 0; i < s.length(); i++) {
            r[i] = s.charAt(s.length() - 1 - i);
        }
        return new String(r);
    }
}
