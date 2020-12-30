package 剑指offer;

/**
 * 剑指 Offer 19. 正则表达式匹配
 * <pre>
 * 请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，
 * 而'*'表示它前面的字符可以出现任意次（含0次）。
 *
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。
 * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 *
 * @author Zhang Yi
 */
public class 正则匹配 {

    public static void main(String[] args) {

    }

    /**
     * @param s string text
     * @param p pattern text
     * @return
     */
    public boolean isMatch(String s, String p) {

        //第一种思路：从尾部向前匹配，这就变成后缀匹配问题 suffix
        //dp[i][j]代表s[i~尾部]与p[j~尾部]是否匹配
        //初始dp[m][n]=true
        //目标是dp[0][0]
        //状态转移：
        // 我们从尾部开始扫描进行匹配，假设有2个指针分别指向s和p尾部字符，用s[i]和p[j]表示指向的字符
        // 如果p[j]=='*' 则需要向左多看一位p[j-1]（意味后续不匹配时要移动到j-2）作为一个整体来对s[i]进行匹配。
        //      如果p[j-1]=='.'，则可以匹配任意字符，任意次数（0~x)。
        //      如果p[j-1]==普通字符，则可以匹配普通字符，任意次数
        // 如果p[j]=='.', 匹配成功一次直接是true
        // 如果p[j]是普通字符，则直接文本匹配一次p[j]==s[i];
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j++) {
                //todo
            }
        }

        return dp[0][0];
    }

}
