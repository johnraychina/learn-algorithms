/**
 * @author Zhang Yi
 */
public class Fibonacci {
    public static void main(String[] args) {
        //f1 = f2 = 1
        //f(n) = f(n-1) + f(n-2)

        //T(n) = T(n-1) + T(n-2) + O(1)
        //T(n) >= 2T(n-2) = O(2~n/2)

        int n = 4;
        int f[] = new int[n + 1];
        f[1] = f[2] = 1;
        for (int i = 3; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        System.out.printf("f[%d]=%d \n", n, f[n]);
    }
}
