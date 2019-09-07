package com.zhangyi.algorithm.sort.excercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * 返回一个有序的String数组，并且去除重复项。
 *
 * @author 张义 johnraychina@163.com
 */
public class Dedup {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] inputArray = line.split(" ");

        LinkedList<String> output = new LinkedList<>();
        Stream.of(inputArray).sorted().forEach(input -> {
            String last = output.peekLast();
            if (last == null || !last.equals(input)) {
                output.add(input);
            }
        });

        output.forEach(System.out::println);
    }
}
