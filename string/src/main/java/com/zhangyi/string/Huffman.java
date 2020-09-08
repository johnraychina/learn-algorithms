package com.zhangyi.string;

import algs4.BinaryStdOut;
import algs4.MinPQ;

/**
 * Huffman data compression
 *
 * <pre>
 * 构造一颗单词查找树（编码解码字典），出现频率越高的字符距离根节点（编码）越短:
 * 1、将节点按频率排序（用优先级队列实现）
 * 2、将评率最小的两个节点合并，生成一个父节点，父节点频率=子节点频率相加。
 * 3、将父节点放回去，以便下一轮排序
 * </pre>
 *
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class Huffman {

    private int R = 256; //ASCII radix
    private Node root; //单词查找树


    //压缩
    public void compress(String text) {
        if (text.length() == 0) {
            return;
        }


        //计算字符对应频率
        char[] freqTable = new char[R];
        for (int i = 0; i < text.length(); i++) {
            freqTable[text.charAt(i)]++;
        }

        //构造单词查找树
        Node root = buildTrie(freqTable);

        //遍历树 构造编码表 'A' - "0101"
        String[] st = new String[R];
        buildCode(root, st, "");

        //将按编码表压缩后输出
        for (int i = 0; i < text.length(); i++) {
            String code = st[text.charAt(i)];
            System.out.print(code);
        }
        System.out.println();
    }

    /**
     * 遍历树构造编码表
     * @param node 当前节点
     * @param st 符号表  char->code path
     * @param code 根节点到当前节点的 编码路径
     */
    private void buildCode(Node node, String[] st, String code) {
        if (node.isLeaf()) {
            st[node.ch] = code;
            return;
        }

        buildCode(node.left, st, code + "0");
        buildCode(node.right, st, code + "1");
    }

    private Node buildTrie(char[] freqTable) {

        //出现过的字符构造成节点塞到优先级队列中排序
        MinPQ<Node> pq = new MinPQ<>();
        for (char i = 0; i < R; i++) {
            if (freqTable[i] > 0) {
                Node node = new Node(freqTable[i], i, null, null);
                pq.insert(node);
            }
        }

        //合并+放入队列排序 循环执行，直到最后合成一棵树
        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node(left.freq + right.freq, '\0', left, right);
            pq.insert(parent);
        }
        return pq.delMin();
    }


    //解压
    public void expand() {

    }
}

class Node implements Comparable<Node>{
    int freq; //频率
    char ch; //字符
    Node left, right;

    public Node(int freq, char ch, Node left, Node right) {
        this.freq  = freq;
        this.ch = ch;
        this.left = left;
        this.right = right;
    }


    @Override
    public int compareTo(Node that) {
        return this.freq - that.freq;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
