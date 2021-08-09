package 每日一题;

/**
 * 实现strStr()函数。
 * <p>
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 * 如果不存在，则返回 -1 。
 * <p>
 * 说明：
 * <p>
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这
 * 与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 实现strStr {
    public static void main(String[] args) {
        System.out.println(strStr("football team", "bal"));
        System.out.println(kmp("football team", "bal"));
    }

    public static class KMPMatcher {
        private final int[][] dfa; //dfa of pattern string
        private final int M; //length of pattern
        private static final int R = 256; //ascii radix
        public KMPMatcher(String patStr) {
            // build dfa from pattern string
            M = patStr.length();
            char[] pat = patStr.toCharArray();

            // dfa['A'][j] = next state of state j after matching 'A'
            dfa = new int[R][M];
            int x = 0;
            for (int j = 0; j < M; j++) {
                for (int c = 0; c < R; c++) { //iterate each character in radix
                    dfa[c][j] = dfa[c][x]; //if character does not match pat[j], back up state
                }
                dfa[pat[j]][j] = j + 1;  //if match pat[j], forward state j -> j+1
                x = dfa[pat[j]][x]; //next state of x
            }
        }

        public int search(String txt) {
            int n = txt.length();
            int i, j = 0;
            for (i = 0; i < n && j < M; i++) {
                j = dfa[txt.charAt(i)][j];
            }
            if (j == M) return i - M;
            return n;
        }
    }
    //KMP算法
    public static int kmp(String txtStr, String patStr) {

        KMPMatcher matcher = new KMPMatcher(patStr);
        return matcher.search(txtStr);
    }

    public static int strStr(String longStr, String shortStr) {

        if (shortStr.length() == 0) {
            return 0;
        }
        int len = longStr.length();
        int sLen = shortStr.length();
        for (int i = 0; i < len - sLen; i++) {
            int j = 0;
            while (j < sLen && longStr.charAt(i + j) == shortStr.charAt(j)) {
                j++;
            }
            if (j == sLen) {
                return i;
            }
        }

        return -1;
    }
}
