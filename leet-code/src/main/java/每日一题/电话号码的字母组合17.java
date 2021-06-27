package 每日一题;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 电话号码的字母组合17 {

    private static final Map<Character, String> map = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz"
    );

    public List<String> letterCombinations(String digits) {

        // 暴力算法遍历: for each digits, for each characters
        // 需要记住已经遍历过的路径，所以需要用回溯
        StringBuffer combination = new StringBuffer(digits.length());
        List<String> ans = new ArrayList<>();
        backTrack(combination, 0, digits, ans);

        //todo
        return null;
    }

    private void backTrack(StringBuffer combination, int depth, String digits, List<String> ans) {

        //dfs结束条件
        if (combination.length() == digits.length()) {
            ans.add(combination.toString());
            return;
        }

        char digit = digits.charAt(depth);
        String chars = map.get(digit);

        for (int i = 0; i < chars.length(); i++) {
            //dfs 状态回溯
            combination.append(chars.charAt(i));
            backTrack(combination, depth + 1, digits, ans);
            combination.deleteCharAt(i);
        }
    }
}
