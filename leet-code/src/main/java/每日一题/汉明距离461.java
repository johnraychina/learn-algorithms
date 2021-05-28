package 每日一题;

/**
 * 461. 汉明距离
 * <p>
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 */
public class 汉明距离461 {
    public static int hammingDistance(int x, int y) {

        int a = x ^ y;
        //count bits
//        int bits = 0;
//        while (a != 0) {
//            if ((a & 1) == 1) bits++;
//            a >>= 1;
//        }

        //count bits 优化：使用 Brian Kernighan 算法 The C programming language (Second Edition)
        int bits = 0;
        while (a != 0) {
            a &= (a - 1);
            bits++;
        }

        return bits;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }
}
