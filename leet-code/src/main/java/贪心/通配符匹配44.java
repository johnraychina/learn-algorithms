package 贪心;

/**
 * @author Zhang Yi
 */
public class 通配符匹配44 {
}
//给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
//
// '?' 可以匹配任何单个字符。
//'*' 可以匹配任意字符串（包括空字符串）。
//
//
// 两个字符串完全匹配才算匹配成功。
//
// 说明:
//
//
// s 可能为空，且只包含从 a-z 的小写字母。
// p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
//
//
// 示例 1:
//
// 输入:
//s = "aa"
//p = "a"
//输出: false
//解释: "a" 无法匹配 "aa" 整个字符串。
//
// 示例 2:
//
// 输入:
//s = "aa"
//p = "*"
//输出: true
//解释: '*' 可以匹配任意字符串。
//
//
// 示例 3:
//
// 输入:
//s = "cb"
//p = "?a"
//输出: false
//解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
//
//
// 示例 4:
//
// 输入:
//s = "adceb"
//p = "*a*b"
//输出: true
//解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
//
//
// 示例 5:
//
// 输入:
//s = "acdcb"
//p = "a*c?b"
//输出: false
// Related Topics 贪心算法 字符串 动态规划 回溯算法
// 👍 665 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }

    public boolean isMatch(String s, int i, String p, int j) {
        //模式p中的*号，需要向右多看一位来决定如何匹配，充当一个"卡子"的作用
        //遍历s 和 p进行匹配
        while (i < s.length() && j < p.length()) {

            //todo
        }

        return i == s.length() && j == p.length();
    }
    //
    //public boolean isMatch(String s, int i, String p, int j) {
    //    //模式p中的*号，需要向右多看一位来决定如何匹配，充当一个"卡子"的作用
    //    //遍历s 和 p进行匹配
    //    while (i < s.length() && j < p.length()){
    //
    //        char c = p.charAt(j);
    //
    //        if (c == '?' || c == s.charAt(i)) {
    //            j++; //匹配上了，i和j都要向右移动
    //        } else if (c == '*') { //模式字符是*，向右多看一位，递归处理
    //            j++;
    //            while (i < s.length() && s.charAt(i) != p.charAt(j)) {
    //                i++;
    //                if(isMatch(s, i, p, j)) {
    //                    return true;
    //                }
    //            }
    //        }
    //        else {
    //            return false;
    //        }
    //    }
    //
    //    return i == s.length() && j == p.length();
    //}
}
//leetcode submit region end(Prohibit modification and deletion)
