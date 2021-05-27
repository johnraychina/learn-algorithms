package com.zhangyi.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import algs4.In;

/**
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class Harvester {
    public static void main(String[] args) {

        String pattern = args[0];
        In in = new In(args[1]);
        String input = in.readAll();
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(input);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
