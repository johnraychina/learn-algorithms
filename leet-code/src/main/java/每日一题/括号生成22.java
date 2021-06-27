package 每日一题;

import java.util.LinkedList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，
 * 用于能够生成所有可能的并且 有效的 括号组合。
 */
public class 括号生成22 {
    //有效括号的校验：指针从左向右扫描，保证当前 ( 数量大于等于 )
    public static boolean isValid(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }

        return true;
    }

    //枚举组合，通常用回溯算法
    public static List<String> generateParenthesis(int n) {
        List<String> ans = new LinkedList<>();
        backtrack(0, 0, n, "", ans);
        return ans;
    }

    //回溯 + 剪枝优化，将2^n ==> 第n 个卡特兰数
    public static void backtrack(int open, int close, int n, String s, List<String> ans) {

        if (s.length() == (n<<1)) {
            ans.add(s);
            return;
        }

        //添加(, 先检查(个数
        if (open < n) {
            backtrack(open+1, close, n, s + "(", ans);
        }

        //记录)个数
        if (close < open) {
            backtrack(open, close+1, n, s + ")", ans);
        }
    }

    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        list.forEach(System.out::println);
    }
}
