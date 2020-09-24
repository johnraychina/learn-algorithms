package com.zhangyi.algorithm.sort;

/**
 * @author Zhang Yi
 */
public class Fibonacci {
    public static long F(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return F(N-1) + F(N-2);
    }

    private long[] cache;
    public Fibonacci(int N) {
        cache = new long[N+1];
    }
    public long F_V2(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;

        long N1 = cache[N-1] > 0 ? cache[N-1] : (cache[N-1] = F_V2(N-1));
        long N2 = cache[N-2] > 0 ? cache[N-2] : (cache[N-2] = F_V2(N-2));
        return N1 + N2;
    }

    public static void main(String[] args) {

        //递归版本
        //for (int N = 0; N < 100; N++) {
        //    System.out.println(N + ":" + F(N));
        //}

        //记忆版本
        Fibonacci fb = new Fibonacci(100);
        for (int N = 1; N < 100; N++) {
            System.out.println(N + ":" + fb.F_V2(N));
        }

    }
}
