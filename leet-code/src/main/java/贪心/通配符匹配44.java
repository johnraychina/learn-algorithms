package 贪心;

/**
 * 44. 通配符匹配
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * <p>
 * 思路：
 * 1. 暴力递归，超时了
 * 2. 动态规划：如果把暴力递归的执行轨迹画出一个图，那就是一棵树，子树之间存在重复之处，可以用动态规划构造子树之间的关系，提高性能。
 * 3. 贪心算法：
 */
public class 通配符匹配44 {
    public static boolean isMatch(String s, String p) {
        if (s.equals(p)) {
            return true;
        }

        // dp[i][j]: sub problem whether  string [0~i) match pattern [0~j)

        // 状态转移方程
        // p[j] is a-z; dp[i][j] = dp[i-1][j-1] && s[i - 1] == p[j - 1],
        // p[j] is ?:   dp[i][j] = dp[i-1][j-1]
        // p[j] is *:   dp[i][j] = dp[i][j-1] 匹配一个s字符 || dp[i-1][j]  *号匹配0个s字符

        // 初始化
        // dp[i][j] 默认为false，只需更新true的。
        // init first one dp[0 ][0 ] : true
        // init first col dp[..][0 ] : false
        // init first row dp[0 ][..] : dp[0][j] = true where pattern 0~j are *, else false

        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (p.charAt(j - 1) == '?' || (s.charAt(i - 1) == p.charAt(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[s.length()][p.length()];

//        return recursiveMatch(s, 0, p, 0);
    }

    /**
     * Match string s with pattern s recursively.
     *
     * @param s      a string
     * @param sIndex start match at sIndex
     * @param p      as pattern
     * @param pIndex start match at pIndex
     * @return is match
     */
    public static boolean recursiveMatch(String s, int sIndex, String p, int pIndex) {

        // edge case: sIndex out of bound, pIndex out of bound
        if (sIndex >= s.length()) {
            // both end, match
            if (pIndex >= p.length()) {
                return true;
            }

            // s end, p not end, increase pattern index, go on match
            if (p.charAt(pIndex) == '*') {
                return recursiveMatch(s, sIndex, p, pIndex + 1);
            } else {
                return false;
            }
        } else if ((pIndex >= p.length())) {
            // s end, p not end, not match
            return false;
        }

        switch (p.charAt(pIndex)) {
            case '*':
                // if * is the last pattern char, then match
                if (pIndex == p.length() - 1) {
                    return true;
                }

                // else iterate all cases: sIndex + 0, 1, 2, ... end
                boolean anyMatch = false;
                while (sIndex < s.length()) {
                    anyMatch = anyMatch || recursiveMatch(s, sIndex, p, pIndex + 1);
                    sIndex++;
                }
                return anyMatch;
            case '?':
                return recursiveMatch(s, sIndex + 1, p, pIndex + 1); // matched, match next char
            default:
                if (s.charAt(sIndex) == p.charAt(pIndex)) {
                    return recursiveMatch(s, sIndex + 1, p, pIndex + 1);
                } else {
                    return false;
                }
        }
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "*";
//        String s = "abdceb";
//        String p = "*a*b";

//        String s = "acdcb";
//        String p = "a*c?b";

//        String s = "acdceb";
//        String p = "a*c?b";

        System.out.println(isMatch(s, p));
        System.out.println("end");
    }
}


