package 每日一题;

/**
 * 猜数字游戏的规则如下：
 * <p>
 * 每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * <p>
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
 * 返回我选出的数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @hint 二分查找
 */
public class 猜数字大小374 {

    public static void main(String[] args) {
        System.out.println(guessNumber(10));
    }

    private static int pick = 7;

    public static int guessNumber(int n) {

        //guess [1~n]
        int l = 1, r = n;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            int guess = guess(mid);
            if (guess == -1) {
                r = mid;
            } else if (guess == 1) {
                l = mid + 1; //由于mid是向下取整，为了避免死循环，应该这里加1
            } else {
                return mid;
            }
        }

        return l;
    }

    public static int guess(int num) {
        if (pick < num) return -1;
        else if (pick > num) return 1;
        else return 0;
    }
}
