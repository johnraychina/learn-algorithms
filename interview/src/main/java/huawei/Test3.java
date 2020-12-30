package huawei;

/**
 * @author Zhang Yi
 */
public class Test3 {
    public static void main(String[] args) {
        int num = 123;
        System.out.println(reverse(num));
        num = -123;
        System.out.println(reverse(num));
        num = 120;
        System.out.println(reverse(num));
    }

    public static int reverse(int num) {
        //反转思路1：使用stack
        //反转思路2：mod 10得到余数，然后除10，继续下来一轮取模
        int r = 0;
        while (num != 0) {
            int remain = num % 10;
            r = r * 10 + remain;

            num = num / 10;
        }
        return r;
    }
}
