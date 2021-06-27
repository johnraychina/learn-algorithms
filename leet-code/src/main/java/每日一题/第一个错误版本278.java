package 每日一题;

public class 第一个错误版本278 {
    public static void main(String[] args) {
        System.out.println(firstBadVersion(5));
    }


    //在不知道错误版本的情况下，只能通过试错达到目的
    //通过折半查找进行探测
    // 最开始的肯定是正确的，最末尾肯定是错误的版本，从中间开始找
    public static int firstBadVersion(int n) {
        int l = 1;
        int r = n;
        while (l < r) { //循环结束条件：找到
            int mid = l/2 + r/2 + ((l % 2) + (r % 2))/2;
            if (isBadVersion(mid)) {
                r = mid;
            } else {
                l = mid;
            }
        }

        return isBadVersion(l) ? l : r;
    }

    private static boolean isBadVersion(int mid) {
        return mid >= 4;
    }
}
