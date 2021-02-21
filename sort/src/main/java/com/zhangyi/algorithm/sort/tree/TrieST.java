package com.zhangyi.algorithm.sort.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * 字典树
 * 典型应用场景：google suggest, auto-complete, spell-checking, longest-prefix
 * 注意，这个实现是基于char做匹配的树， 意味着只针对ASCII有用。
 * char作为node数组的下标位置，而node中的value可以是任意对象，null表示临时节点，非null表示put进来的完整链路。
 *
 * Tries: from retrieval, but pronounced "try"
 * - Store characters in nodes(not keys).
 * - Each node has R children, one for each possible character.
 * - For now, we do not draw null links.
 *
 * Search:
 * following links corresponding to each character in the key.
 * - search hit: node where search ends has a non-null value.
 * - search miss: reach null link or node where search ends has null value.
 * non-null value这里意味着从根节点遍历到此节点，是一个合法的词。
 *
 * Insert:
 * follow links corresponding to each character in the key.
 * - encounter a null link: create new node.
 * - encounter the last character of the key: set value in that node.
 *
 * Performance:
 * Search hist: need to examine all L characters for equality.
 *
 * Search miss:
 * - Could have mismatch on first character.
 * - Typical case: examine only a few characters(sublinear).
 *
 * Space:
 * R null links at each leaf.
 * (but sublinear space possible if many short strings share common prefixes)
 *
 * ・Method of choice for small R.
 * ・Too much memory for large R.
 *
 * Challenge. Use less memory, e.g., 65,536-way trie for Unicode!
 * 挑战：使用更少的内存，比如Unicode 6万多路的字典树
 *
 * 思考：之前是预分配数组，数组下标作为key路由匹配，所以才会浪费那么多空间，
 * 我们可以把路由信息存在node基本字段中即可（缺点是不如数组下标定位快速）。
 *
 * ・Store characters and values in nodes (not keys).
 * ・Each node has 3 children: smaller (left), equal (middle), larger (right).
 * </pre>
 *
 * @author Zhang Yi
 */
public class TrieST<Value> {

    private static final int R = 256; //ASCII
    private Node root = new Node();

    public static void main(String[] args) {
        TrieST<Integer> tst = new TrieST<>();
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
        for (String key : tst.keyWithPrefix("fo")) {
            System.out.println(key);
        }
    }

    public void put(String key, Value val) {
        root = put(key, val, root, 0);
    }

    /**
     * <pre>
     * encounter a null link: create new node,  and do recursive put.
     * encounter the last character of the key: set value in that node, return the node.
     * </pre>
     *
     * @param key string key
     * @param val value
     * @param x   parent node
     * @param d   depth
     */
    public Node put(String key, Value val, Node x, int d) {

        //null link 判断放最前，否则容易NPE
        if (x == null) { x = new Node(); }

        //递归结束条件：已经匹配上了x
        if (d == key.length()) {
            x.value = val;
            return x;
        }

        //向下一层递归
        char c = key.charAt(d);
        x.next[c] = put(key, val, x.next[c], d + 1);
        return x;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Value get(String key) {
        return (Value)get(key, root, 0);
    }

    public Node get(String key, Node x, int d) {

        //递归结束条件：null link
        if (x == null) {
            return null;
        }
        //递归结束条件：完全匹配上了
        if (key.length() == d) {
            return x;
        }

        //向下一层递归
        char c = key.charAt(d);
        return get(key, x.next[c], d + 1);
    }

    /**
     * To delete a key-value pair: - find the node corresponding to key and set value to null - if node has null value
     * and null links, remove that node(and recur).
     *
     * @param key
     */
    public void delete(String key) {
        delete(key, root, 0);
    }

    /**
     * @param key
     * @param x
     * @param d
     * @return 被删节点向上递归删除，分叉点（有子节点或者本身有值）。
     */
    public Node delete(String key, Node x, int d) {

        //递归查找，结束条件：没找到完全匹配的key
        if (x == null) {
            return null;
        }

        //递归查找结束条件：完全匹配上了
        if (key.length() == d) {
            //真正触发删除的地方：如果找到key对应的node，则节点value置为null
            x.value = null;
        } else {
            //递归查找，继续
            char c = key.charAt(d);
            x = delete(key, x.next[c], d + 1); //delete返回null表示递归删除x
        }

        //下面的代码，都是匹配成功，delete返回才会走到这里
        //完全匹配上后，向上递归删除
        //如果父节点x如果没有value而且没有叶子节点，则return null表示递归删除x
        //否则return x，不做删除。
        if (x.value != null) { return x; }
        for (int i = 0; i < R; i++) {
            if (x.next[i] != null) {
                return x;
            }
        }
        return null;
    }

    public Iterable<String> keyWithPrefix(String prefix) {
        Node node = get(prefix, root, 0);
        if (node == null) {
            return Collections.emptyList();
        }

        //目标：得到所有前缀匹配的，而且有value的节点。
        //从node.mid开始向下遍历
        //递归dfs遍历，这样可以得到从根节点到当前节点的路径
        List<String> list = new LinkedList<>();
        collect(node, prefix, list);

        return list;
    }

    //todo 优化： StringBuilder通过回退的方式复用
    public void collect(Node n, String prefix, List<String> list) {
        if (n == null) { return; }

        //如果前缀本身匹配到的节点就有value，说明前缀就有对应的key.
        if (n.value != null) {
            list.add(prefix);
        }

        for (int i = 0; i < R; i++) {
            collect(n.next[i], prefix + ((char)i), list);
        }
    }

    public static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

}
