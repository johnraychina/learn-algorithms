/**
 * @author Zhang Yi
 */
public class 二分查找框架 {

    public static void main(String[] args) {

        int low = 0, hi = 999999;
        while (low <= hi) {
            int mid = (low + hi) / 2;

            //根据中间值处理，得到一个结果，可以判断继续处理左边还是右边
            boolean result = processWith(mid);

            if (result) {
                hi = mid;
            } else {
                low = mid + 1;
            }
        }
    }

    private static boolean processWith(int mid) {
        return false;
    }
}
