package com.zhangyi.algorithm.sort.tree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * <pre>
 * 字典树，英文拼写检查。
 * 基数：26个字母
 * 典型应用场景：google suggest, auto-complete, spell-checking, longest-prefix
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class TrieEnglishSpellChecker<Value> {

    private static final int R = 26; //a-z
    private Node root = new Node();

    public static void main(String[] args) throws IOException {
        //todo 载入字典
        Stream<String> lines = Files.lines(Paths.get("", "dict.txt"));
        TrieEnglishSpellChecker<Boolean> checker = new TrieEnglishSpellChecker<>();
        lines.forEach(e -> checker.put(e.trim(), Boolean.TRUE));

        //todo 测试
        Scanner in = new Scanner(System.in);
        while (!in.hasNext()) {
            checker.contains(in.next());
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

    public static class Node {
        private Object value;
        private Node[] next = new Node[R];
    }

}
