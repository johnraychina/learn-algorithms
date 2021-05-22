package 每日一题;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * @hint 滑动窗口
 * </pre>
 */
public class 无重复字符的最长子串3 {

    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // window = s[start, end]
        int start = 0;
        int end = 0;
        int longest = 0;
        Map<Character, Integer> windowStrMap = new HashMap<>(26);

        // end of s
        while (end < s.length()) {

            Integer index = windowStrMap.get(s.charAt(end));
            if (index == null) {
                //if not repeat, put into window and calculate window length
                windowStrMap.put(s.charAt(end), end);
                int windowLen = end - start + 1;
                longest = Math.max(longest, windowLen);

            } else {
                //if has repeat, shrink window until no repeat
                while (start != index + 1) {
                    windowStrMap.remove(s.charAt(start));
                    start++;
                }
                //update index of char to end, 保证map中维护最靠右的下标
                windowStrMap.put(s.charAt(end), end);
            }

            end++;
        }

        return longest;
    }

    // 滑动窗口做遍历，map用来辅助去重
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // window = s[start, end]
        int start = 0;
        int longest = 0;
        Map<Character, Integer> windowStrMap = new HashMap<>(26);

        // end of s
        for (int end = 0; end < s.length(); end++) {
            Integer index = windowStrMap.get(s.charAt(end));
            if (index != null) {
                // shrink window lazily(without removing elements in map),
                // we may get index of elements to the left of start, to fix this, we need apply max() function.
                start = Math.max(start, index + 1);
            }
            longest = Math.max(longest, end - start + 1);
            //insert or update index of char to end, 保证map中维护最靠右的下标
            windowStrMap.put(s.charAt(end), end);
        }

        return longest;
    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
//        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("tmmzuxt"));
    }
}
