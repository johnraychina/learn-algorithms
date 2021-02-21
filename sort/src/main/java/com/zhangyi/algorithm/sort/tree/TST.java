package com.zhangyi.algorithm.sort.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * 字典树挑战：使用更少的内存，比如Unicode 6万多路的字典树
 * Challenge. Use less memory, e.g., 65,536-way trie for Unicode!
 *
 * 思考：之前是预分配数组，数组下标作为key路由匹配，所以才会浪费那么多空间，
 * 我们可以把路由信息存在node基本字段中即可（缺点是不如数组下标定位快速）。
 *
 * ・Store characters and values in nodes (not keys).
 * ・Each node has 3 children: smaller (left), equal (middle), larger (right).
 *
 * Search:
 * Follow links corresponding to each character in the key.
 * - if less, take the left link; if greater, take the right link.
 * - if equal, take the middle link and move to the next key character.
 *
 * Search hit: Node where search ends has a non-null value.
 * Search miss: Reach a null link or node where search ends has null value.
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class TST<Value> {
    private Node root = new Node();

    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();
        tst.put("brown", 1);
        tst.put("fox", 2);
        tst.put("jump", 3);
        tst.put("juice", 9);
        tst.put("fool", 4);
        tst.put("bread", 5);
        tst.put("quick", 6);
        tst.put("query", 7);

        //get
        //System.out.println(tst.get("fool"));
        //System.out.println(tst.get("jump"));

        //keyWithPrefix
        for (String key : tst.keyWithPrefix("qu")) {
            System.out.println(key);
        }
    }

    public void put(String key, Value val) {
        root = put(key, val, root, 0);
    }

    /**
     * @param key key
     * @param val node value
     * @param x   current node
     * @param d   match depth: 0~key.length
     * @return
     */
    public Node put(String key, Value val, Node x, int d) {
        //递归过程中如果发现中间节点为null，需要创建，有匹配字符，但是没有value
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
            print(key, x, d);
        }

        //向下递归，需要注意的是，字符匹配才移动d+1， 另外left/middle/right节点可能为null，按需要补充
        //停止递归条件：search key ends，d从0开始匹配到了length-1，说明匹配
        if (c < x.c) {
            x.left = put(key, val, x.left, d);
        } else if (c > x.c) {
            x.right = put(key, val, x.right, d);
        } else if (d < key.length() - 1) {
            x.mid = put(key, val, x.mid, d + 1);
        } else {
            x.value = val;
        }
        return x;
    }

    public Node get(String key, Node x, int d) {

        //停止递归条件：null link
        if (x == null) {
            return null;
        }

        //向下递归，需要注意的是，字符匹配才移动d+1
        //停止递归条件：search key ends，d从0开始匹配到了length-1，说明匹配
        char c = key.charAt(d);
        if (c < x.c) {
            return get(key, x.left, d);
        } else if (c > x.c) {
            return get(key, x.right, d);
        } else if (d < key.length() - 1) {
            return get(key, x.mid, d + 1);
        } else {
            return x;
        }
    }

    private void print(String key, Node x, int d) {
        //StringBuilder sb = new StringBuilder();
        //for (; d > 0; d--) {
        //    sb.append("--");
        //}
        //sb.append(key);
        //sb.append(":");
        //sb.append(x == null ? null : x.c);
        //System.out.println(sb.toString());
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * @param key
     * @return
     */
    public Value get(String key) {
        Node node = get(key, root, 0);
        if (node == null) { return null; } else { return (Value)node.value; }
    }

    //all keys
    public Iterable<String> keys() {
        return null; //todo
    }

    /**
     * @param prefix
     * @return
     */
    public Iterable<String> keyWithPrefix(String prefix) {
        Node node = get(prefix, root, 0);
        if (node == null) {
            return Collections.emptyList();
        }

        //目标：得到所有前缀匹配的，而且有value的节点。
        //从node.mid开始向下遍历
        //递归dfs遍历，这样可以得到从根节点到当前节点的路径
        List<String> list = new LinkedList<>();

        if (node.value != null) {
            list.add(prefix);
        }

        collect(node.mid, prefix, list);

        return list;
    }

    //keys having pattern as  prefix

    public void collect(Node n, String prefix, List<String> list) {
        if (n == null) { return; }

        //如果前缀本身匹配到的节点就有value，说明前缀就有对应的key.
        if (n.value != null) {
            list.add(prefix + n.c);
        }

        collect(n.left, prefix, list);
        collect(n.mid, prefix + n.c, list);
        collect(n.right, prefix, list);

    }

    public Iterable<String> keysThatMatch(String pattern) {
        return null;
    }

    public String longestPrefixOf(String query) {
        //寻找前缀为query的最长字符串
        return null;
    }

    public static class Node {
        private Object value;
        private char c;
        private Node left;
        private Node mid;
        private Node right;
    }
}
