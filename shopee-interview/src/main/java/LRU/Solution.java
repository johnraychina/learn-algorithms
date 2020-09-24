package LRU;

/**
 * @author Zhang Yi
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        int[][] operators = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        int[] lru = new Solution().LRU(operators, 3);
        String lruString = Arrays.stream(lru).mapToObj(String::valueOf).collect(Collectors.joining(","));
        System.out.println(lruString);
    }

    private int capacity;

    class Node {
        int key;
        int val;
    }

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        // write code here
        capacity = k;
        // get操作，如果有Node，需要额外调整Node到list最新位置
        // set操作，如果有Node，直接替换value，然后调整Node到list最新位置
        //cache: key=operators[i][1], value=Node(operators[i][2]);
        Map<Integer, Node> cache = new HashMap<>(k);

        LinkedList<Node> list = new LinkedList<>();

        ArrayList<Integer> output = new ArrayList<>();

        for (int i = 0; i < operators.length; i++) {
            int opt = operators[i][0];
            int key = operators[i][1];

            if (opt == 1) {//set
                int val = operators[i][2];
                set(cache, list, key, val);
            } else {//get
                get(cache, list, output, key);
            }
        }

        int[] result = new int[output.size()];
        for (int i = 0; i < output.size(); i++) {
            result[i] = output.get(i);
        }

        return result;
    }

    public void get(Map<Integer, Node> cache, LinkedList<Node> list, ArrayList<Integer> ouput, int key) {
        Node node = cache.get(key);
        if (node != null) {
            //refresh position
            list.remove(node);
            list.addFirst(node);
            //output to array
            ouput.add(node.val);
        } else {
            ouput.add(-1);
        }

        print(list);
    }

    private void print(LinkedList<Node> list) {
        String listStr = list.stream().map(e -> String.valueOf(e.val)).collect(Collectors.joining(","));
        System.out.println(listStr);
    }

    public void set(Map<Integer, Node> cache, LinkedList<Node> list, int key, int val) {
        //refresh position
        Node node = cache.get(key);
        if (node != null) {
            node.val = val;
            list.remove(node);
            list.addFirst(node);
        } else {
            //add node
            //if over limit, remove first(least recently used node)
            node = new Node();
            node.key = key;
            node.val = val;
            list.addFirst(node);
            cache.put(key, node);

            if (list.size() > capacity) {
                Node last = list.removeLast();
                cache.remove(last.key);
            }
        }

        print(list);
    }

}
