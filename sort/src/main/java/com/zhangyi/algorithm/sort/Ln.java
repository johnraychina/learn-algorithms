package com.zhangyi.algorithm.sort;

/**
 * 1.1.20 编写一个递归的静态方法计算ln(N!)的值
 *
 * @author Zhang Yi
 */
public class Ln {

    double[] cache;

    public Ln(int N) {
        cache = new double[N + 1];
    }

    public double lnFactorial(int N) {
        if (N == 1) { return 0.0; }

        //加缓存版本
        //double logN = (cache[N] != 0.0) ? cache[N] : (cache[N] = Math.log(N));
        //不加缓存版本
        double logN = Math.log(N);

        return logN + lnFactorial(N - 1);
    }

    public static void main(String[] args) {

        Ln lnFunc = new Ln(1000);
        for (int i = 1; i < 1000; i++) {
            System.out.println(i + ":" + lnFunc.lnFactorial(i));
        }

    }
}
