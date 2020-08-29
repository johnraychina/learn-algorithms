package com.zhangyi.string;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin-Karp fingerprint search
 *
 * <pre>
 *  Basic idea = modular hashing.
 *  - compute a hash of pattern characters 0 to M-1
 *  - For each i, compute a hash of text characters i to i+(M-1)
 *  - If pattern has = text substring hash, check for a match.
 *
 * 精髓1：hash化将多次字符匹配简化为一次数字比对
 * 精髓2：Horner's method: 加一个字符时，重新计算hash时复用前面的hash，减少就计算量
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class RabinKarp {

    private int R; // radix of ascii
    private int M; // length of pattern string
    private long Q; // modulo Q, need to be large enough to avoid too much collision.
    private long RM; // R^(M-1) % Q
    private long patHash; // pattern string hash code

    public RabinKarp(String pat) {
        R = 256;
        Q = 997;
        M = pat.length();

        Q = longRandomPrime(); // a large prime (but avoid overflow)
        // pre-compute R^(m-1) % Q
        long RM = 1;
        for (int i = 0; i <= M - 1; i++) {
            RM = (R * RM) % Q;
        }
        long patHash = hash(pat, M);
    }

    private long hash(String key, int M) {
        long hash = 0;
        for (int j = 0; j < M; j++) {
            hash = (R * M + key.charAt(j)) % Q;
        }
        return hash;
    }

    // a random 31-bit prime
    private static long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    //Efficiently computing the hash function
    //Horner's method: Linear-time method to evaluate degree-M polynomial.
    // challenge: how to efficiently compute x_i+1 given x_i
    // x_{i+1} = (x_i - t_i R^{M-1}) R + t_{i+M}
    // 利用上次计算的值，去头添尾，节省计算
    public int search(String txt) {
        int N = txt.length();
        long txtHash = hash(txt, M);
        if (patHash == txtHash) { return 0; }
        for (int i = M; i < N; i++) {
            // leading digit
            long h = RM * txt.charAt(i - M) % Q;
            // remove leading digit and add trailing digit, check for match
            txtHash = (txtHash + Q - h) % Q;

            // 两个博彩胜地的风格不一样
            // Monte Carlo version: return match if hash match
            // Las Vegas version: Check for substring match if hash match
            if (patHash == txtHash) { return i - (M - 1); }
        }

        return N;
    }
}
