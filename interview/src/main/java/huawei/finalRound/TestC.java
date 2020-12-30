package huawei.finalRound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Zhang Yi
 */
public class TestC {
    public static final int ADD = 0;
    public static final int MULTIPLY = 1;
    public static final int SUBTRACT = 2;
    public static final int DIVIDE = 3;
    public static int TARGET = 24;
    public static double EPSILON = 1e-6;

    public static void main(String[] args) {
        Map<String, Double> map = new HashMap<>();
        map.put("A", 1.0);
        map.put("2", 2.0);
        map.put("3", 3.0);
        map.put("4", 4.0);
        map.put("5", 5.0);
        map.put("6", 6.0);
        map.put("7", 7.0);
        map.put("8", 8.0);
        map.put("9", 9.0);
        map.put("T", 10.0);
        map.put("J", 11.0);
        map.put("Q", 12.0);
        map.put("K", 13.0);

        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] cards = line.split(" ");
        if (cards.length != 4) {
            System.out.println("No"); //非法参数
            return;
        }

        List<Double> nums = new ArrayList<>(cards.length);
        for (String card : cards) {
            nums.add(map.get(card));
        }

        //每个操作将2个数字变为一个结果数字，如果是4个数字，则用3个操作符号
        //每个符号有4中选择：加减乘除
        //首先从4个数字挑选2个: A(4, 2) * 4
        //然后从3个数字中挑选2个：A(3,2) * 4
        //然后从2个数字中挑选2个：A(2,2) * 4
        boolean ans = solve(nums);
        ;

        System.out.println(ans ? "Yes" : "No");
    }

    public static boolean solve(List<Double> list) {
        if (list.size() == 0) { return false; }
        if (list.size() == 1) { return Math.abs(list.get(0) - TARGET) < EPSILON; }

        //从list中选择2个数字进行操作
        //注意：浮点数除0的问题
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j) {

                    List<Double> newList = new ArrayList<>();
                    for (int k = 0; k < list.size(); k++) {
                        if (k != i && k != j) {
                            newList.add(list.get(k));
                        }
                    }

                    double a = list.get(i);
                    double b = list.get(j);

                    for (int k = 0; k < 4; k++) {
                        if (k == ADD) {
                            newList.add(a + b);
                        } else if (k == MULTIPLY) {
                            newList.add(a * b);
                        } else if (k == SUBTRACT) {
                            newList.add(a - b);
                        } else if (k == DIVIDE) {
                            if (Math.abs(b) < EPSILON) { continue; }
                            newList.add(a / b);
                        }

                        if (solve(newList)) {
                            return true;
                        }
                        newList.remove(newList.size() - 1);
                    }
                }
            }
        }

        return false;
    }

}
