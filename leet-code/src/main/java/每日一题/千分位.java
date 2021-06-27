package 每日一题;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class 千分位 {



    public static void main(String[] args) {

        String input = "-1234444.56";

        // validation
        Pattern p = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        Matcher matcher = p.matcher(input);
        if (!matcher.matches()) {
            System.out.println("非法输入");
            return;
        }


        StringBuilder b = new StringBuilder();
        int end = input.indexOf(".");
        int start = input.charAt(0) == '-' ? 1 : 0;

        if (end == -1) {
            //无.的情况
            end = input.length();
        } else {
            //有.的情况, 先pass
            b = new StringBuilder(input.substring(end));
        }


        for (int i = end - 1; i >= start; i = i - 3) {
            String s = input.substring(Math.max(start, i - 2), i + 1);
            b.insert(0, s);
            b.insert(0, ",");
        }

        b.deleteCharAt(0);
        b.insert(0, input.substring(0, start));

        String out = b.toString();
        System.out.println(out);


    }
}
