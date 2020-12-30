package huawei.finalRound;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Zhang Yi
 */
public class Test2 {
    public static final int ADD = 0;
    public static final int MULTIPLY = 1;
    public static final int SUBTRACT = 2;
    public static final int DIVIDE = 4;
    public static int TARGET = 24;
    public static double EPSILON = 1e-6;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String arrayString = input.nextLine();
        String[] array = arrayString.split(",");
        List<Double> nums = Stream.of(array).map(String::trim).map(Double::valueOf).collect(Collectors.toList());

        //每个操作将2个数字变为一个结果数字，如果是4个数字，则用3个操作符号
        //每个符号有4中选择：加减乘除
        //首先从4个数字挑选2个: A(4, 2) * 4
        //然后从3个数字中挑选2个：A(3,2) * 4
        //然后从2个数字中挑选2个：A(2,2) * 4

    }

    public static boolean solve(List<Double> list) {
        if (list.size() == 0) { return false; }
        if (list.size() == 1) { return Math.abs(list.get(0) - TARGET) < EPSILON; }

        //从list中选择2个数字进行操作
        //注意：浮点数除0的问题
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                List<Double> newList = new ArrayList<>(list.size() - 1);
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
                    } else {
                        //todo
                    }

                    if (solve(newList)) {
                        return true;
                    }
                    newList.remove(newList.size() - 1);
                }
            }
        }

        return false;
    }

}
