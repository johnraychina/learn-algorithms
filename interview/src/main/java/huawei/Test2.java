package huawei;

/**
 * @author Zhang Yi
 */
public class Test2 {

    public static void main(String[] args) {
        System.out.println(longestSub("babad"));
        System.out.println(longestSub("cbbd"));
        System.out.println(longestSub("abcde"));
    }

    public static String longestSub(String input) {
        //子序列问题，考虑动态规划解法
        //问题：最长回文
        //拆解子问题： 如果P[i:j]表示i到j是回文，那么P[i-1:j+1] = P[i:j] and s[i-1]==s[j+1]
        //初始条件：每个字符都是回文，即P[i:i] = true
        //两个字符：P[i,i+1] = s[i]==s[i+1]
        int len = input.length();
        boolean[][] p = new boolean[len][len];

        //默认值为第一个
        String answer = input.substring(0, 1);

        for (int subLen = 0; subLen < len; subLen++) {
            for (int i = 0; i + subLen < len; i++) {
                int j = i + subLen;
                if (subLen == 0) {
                    p[i][j] = true;
                } else if (subLen == 1) {
                    p[i][j] = input.charAt(i) == input.charAt(j);
                } else {
                    p[i][j] = p[i + 1][j - 1] && input.charAt(i) == input.charAt(j);
                }

                if (p[i][j] && subLen + 1 > answer.length()) {
                    answer = input.substring(i, j + 1);
                }
            }
        }

        return answer;
    }
}
