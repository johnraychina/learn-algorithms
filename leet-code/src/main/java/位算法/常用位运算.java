package 位算法;

/**
 * @author Zhang Yi
 */
public class 常用位运算 {
    public static void main(String[] args) {
        // 或与非: | & ~
        // 判断奇偶     x & 1 == 1 or 0
        // 最低1位清零   x & (x-1)
        // 得到最低1位   x & -x
        // 将最右边的n位清零   x & (~0 << n)
        // 将最高位到第n位（含）清零 x & ((1<<n) -1)
        // 将第n位至第0位（含）清零  x & (~((1<<(n+1)) - 1))
        // 获取x的第n位值     (x >> n) & 1
        // 获取x的第n位的幂值   x & (1 << (n-1))
        // 仅将第n位置为1     x | (1 << n)
        // 仅将第n位置为0     x & (~(1<< n))
    }

    public static void exch(int a, int b) {
        a = a ^ b; // a^b -> a
        b = a ^ b; // a^b^b = a ->b
        a = a ^ b; // a^b^a = b -> a
        System.out.printf("a=%d, b=%d \n",a, b);
    }
}
