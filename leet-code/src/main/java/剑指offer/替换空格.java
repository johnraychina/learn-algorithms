package 剑指offer;

/**
 * @author Zhang Yi
 */
public class 替换空格 {

    public static String replaceSpace(String s) {

        int whiteSpaces = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                whiteSpaces++;
            }
        }

        char[] array = new char[s.length() + whiteSpaces * 2];

        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                array[j++] = '%';
                array[j++] = '2';
                array[j++] = '0';
            } else {
                array[j++] = s.charAt(i);
            }
        }

        return new String(array);
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
    }
}
