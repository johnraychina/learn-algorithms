package 每日一题;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 30. 串联所有单词的子串
 *
 * 给定一个字符串s和一些 长度相同 的单词words 。
 * 找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 *
 * 注意子串要与words 中的单词完全匹配，中间不能有其他字符 ，
 * 但不需要考虑words中单词串联的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 *
 * </pre>
 */
public class 串联所有单词的子串30 {

    public static void main(String[] args) {
//        String s = "barfoothefoobarman";
//        String words[] = new String[]{"foo", "bar"};
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word","good","best","good"};
        System.out.println(findSubstring(s, words));

    }

    // 从words角度来考虑，把words排列 A(n,n)，然后 side window滑动窗口匹配，一次滑动 len(words[0)]个字符
    // 换个角度来看问题：从s的角度来看， n个长度为k的word拼起来，长度是n*k，滑动窗口大小就是n*k
    // s滑动窗口内的匹配逻辑：按长度k切分为有n个字符串数组，然后与words匹配即可。
    // 时间： window滑动：s.length/k， window内调整windowWords：2, window内匹配words：nk
    // 时间复杂度：O( s.length * n )
    public static List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> baseWords = new HashMap<>();
        Map<String, Integer> windowWords;
        List<Integer> matchBeginList = new LinkedList<>();

        //init base words map with words
        for (String w : words) {
            baseWords.put(w, baseWords.getOrDefault(w, 0) + 1);
        }

        int len = s.length();
        int n = words.length;
        int k = words[0].length();
        int windowLen = n * k;
        int l = 0, r = windowLen; //window[l, r)
        while (l + windowLen <= s.length()) {

            //split words
            windowWords = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                String w = s.substring(l + (i - 1) * k, l + i * k);
                windowWords.put(w, windowWords.getOrDefault(w, 0) + 1);
            }

            //match
            if (baseWords.equals(windowWords)) {
                matchBeginList.add(l);
            }

            //slide
            l++;
        }

        return matchBeginList;
    }
}
