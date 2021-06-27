package 每日一题;

public class 最长公共前缀14 {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
    }

    public static String longestCommonPrefix(String[] strs) {

        String prev = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String cur = strs[i];

            //iterate chars of prev and cur string
            int charCnt = Math.min(prev.length(), cur.length());
            if (charCnt == 0) return "";
            //if prefix matched
            int j = 0;
            for (; j < charCnt; j++) {
                if (prev.charAt(j) != cur.charAt(j)) {
                    break;
                }
            }
            prev = prev.substring(0, j);
        }

        return prev;
    }
}
