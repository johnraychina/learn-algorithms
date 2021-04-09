/**
 * <pre>
 * 76.最小覆盖子串（困难）
 * 567.字符串的排列（中等）
 * 438.找到字符串中所有字母异位词（中等）
 * 3.无重复字符的最长子串（中等）
 *
 * <pre>
 * 滑动窗口算法的思路是这样：
 * 1、我们在字符串 S 中使用双指针中的左右指针技巧，初始化 left = right = 0，把索引左闭右开区间 [left, right) 称为一个「窗口」。
 * 2、我们先不断地增加 right 指针扩大窗口 [left, right)，直到窗口中的字符串符合要求（包含了 T 中的所有字符）。
 * 3、此时，我们停止增加 right，转而不断增加 left 指针缩小窗口 [left, right)，直到窗口中的字符串不再符合要求（不包含 T 中的所有字符了）。同时，每次增加 left，我们都要更新一轮结果。
 * 4、重复第 2 和第 3 步，直到 right 到达字符串 S 的尽头。
 *
 * 套模板，只需要思考以下四个问题：
 * 1、当移动 right 扩大窗口，即加入字符时，应该更新哪些数据？
 * 2、什么条件下，窗口应该暂停扩大，开始移动 left 缩小窗口？
 * 3、当移动 left 缩小窗口，即移出字符时，应该更新哪些数据？
 * 4、我们要的结果应该在扩大窗口时还是缩小窗口时进行更新s？
 * </pre>
 * </pre>
 *
 * @author Zhang Yi
 */
public class 滑动窗口 {

    public void slidingWindow(String s, String t) {

        //维护一个窗口，不断滑动，然后判断和更新
        int left = 0, right = 0;
        int valid = 0;

        //need表示需要凑齐的目标字符和对应的字符数，window表示当前[left, right)左闭右开窗口字符和对应数量
        //对窗口滑动时，需要更新他们
        int[] window = {left, right};

        while (right < s.length()) {

            //c 是即将移入窗口的字符
            char c = s.charAt(right);
            //增大窗口
            right++;

            //todo 对窗口内的数据进行一系列更新

            System.out.printf("window: [%d, %d)\n", left, right);

            //如果窗口需要收缩
            while (needShrink(window)) {
                //d 是即将移出窗口的字符
                char d = s.charAt(left);
                //左移窗口
                left--;
                //todo 对窗口内的数据进行一系列更新
            }
        }

    }

    public boolean needShrink(Object window) {
        return true;
    }

    public static void main(String[] args) {
        Integer count = 0;
    }
}
