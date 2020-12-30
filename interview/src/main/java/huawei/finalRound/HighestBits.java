package huawei.finalRound;

/**
 * @author Zhang Yi
 */
public class HighestBits {
    public static void main(String[] args) {

        //int x = 2;
        print(1000);
        print(24 * 3600); //按秒作为最小计数单位，则一天的计数需要17位
        print(24 * 3600 * 365); //按秒作为最小计数单位，则一年的计数需要25位
        print(24 * 3600 * 365 * 110L); //

    }

    public static void print(long x) {
        int bit = 0;
        while (x != 0) {
            bit++;
            x = x >>> 1;
        }

        System.out.println("highest bit is:" + bit);
    }
}
