package 基本数据结构;
//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1:
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
//
//
// 示例 2:
//
// 输入: s = "rat", t = "car"
//输出: false
//
// 说明:
//你可以假设字符串只包含小写字母。
//
// 进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
// Related Topics 排序 哈希表
// 👍 370 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Zhang Yi
 */
public class 有效的字母异位词242 {
    public boolean isAnagram_map(String s, String t) {
        //两个hashmap的size，每个key的size相同
        HashMap<Character, Integer> m1 = new HashMap<>(s.length());
        HashMap<Character, Integer> m2 = new HashMap<>(t.length());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            m1.merge(c, 1, Integer::sum);
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            m2.merge(c, 1, Integer::sum);
        }

        for (Character c : m1.keySet()) {
            if (!Objects.equals(m1.get(c), m2.get(c))) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram_sort(String s, String t) {
        //先quick sort, O(NlogN), 再对比 O(N)
        char[] schars = s.toCharArray();
        char[] tchars = t.toCharArray();
        Arrays.sort(schars);
        Arrays.sort(tchars);
        return Arrays.equals(schars, tchars);
    }
}
