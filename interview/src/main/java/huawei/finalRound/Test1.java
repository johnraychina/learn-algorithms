package huawei.finalRound;

import java.util.Scanner;

/**
 * @author Zhang Yi
 */
public class Test1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        char[] a = s.toCharArray();

        //2个指针，一个start, 一个end
        //1:i从字母到空格or结束时，设置end=i，触发reverse(start,end)
        //2:i从空格到字母时，设置start=end=i
        //3:prev,i都是字母，end=i
        //4:prev,i都是空格，继续遍历
        //初始时，设置start=end=0
        int start = 0;
        int end = 0;
        char prev = a[0];
        for (int i = 1; i < a.length; i++) {

            if ((a[i] == ' ' || i == a.length - 1) && prev != ' ') {
                if (i == a.length - 1) { end = i; }
                reverse(a, start, end);
            } else if (a[i] != ' ' && prev == ' ') {
                start = end = i;
            } else if (a[i] != ' ' && prev != ' ') {
                end = i;
            } else if (a[i] == ' ' && prev == ' ') {
                //继续遍历
            }

            prev = a[i];
        }

        String result = new String(a);
        System.out.println(result);
    }

    private static void reverse(char[] a, int left, int right) {
        while (left < right) {
            char temp = a[left];
            a[left] = a[right];
            a[right] = temp;
            left++;
            right--;
        }
    }
}
