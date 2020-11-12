package com.zhangyi.algorithm.sort.tree;

/**
 * <pre>
 * 平衡树：all of the null links are the same distance from the root.
 * 平衡二叉树有非常好的性质：稳定的logN的查找、插入
 * 但是2-3的删除操作非常复杂和困难，用 Hibbard deletion需要判断很多case
 *
 * 采用红黑树实现（与2-3树一一对应）
 * 红色链接的两端组成了一个3节点的2个key-value对
 * 黑色链接代表节点的父子关系
 *
 * 一个2-3节点内，保持红色链接向左倾斜left-leaning
 * 一个2-3节点内，较大值指向parent
 *
 * 颜色表达：由于一个节点只能被（父节点）引用一次，所以我们把颜色保存在节点本身上。
 * 用一个boolean足以表示颜色，红色为true，黑色为false
 * 空链接默认为黑色
 *
 * same code handles all cases(transform one case into another):
 * left-rotate
 * right-rotate
 * flip colors
 *
 * 向右倾斜right-leaning（父节点指向左边节点）
 * 或者两个连续红色链接 可能会出现在操作当中，
 * 但是最后都会通过"旋转节点"和"颜色翻转"被调整为合法结构:
 *
 * 旋转节点：
 * 左旋转left rotate: 一条红色边在右边
 * 右旋转right rotate: 两条连续红色边在左边
 *
 * 颜色翻转：
 * 旋转后，可能会出现一个黑色的父节点，连接2个红色子节点
 * 翻转为（相当于平级有3个节点，需要分裂升级）：红色父节点，2个黑色子节点。
 *
 * </pre>
 *
 * @author 张义 johnraychina@163.com
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    public RedBlackBST() {

    }

    public Value get(Key key) {
        return get(root, key);
    }

    //dfs递归向下查找，分别对比left key和right key
    private Value get(Node node, Key key) {
        if (node == null) { return null; }

        int cmp = node.key.compareTo(key);
        if (cmp > 0) { return get(node.left, key); } else if (cmp < 0) { return get(node.right, key); } else {
            return node.val;
        }
    }

    public void put(Key key, Value value) {
        if (value == null) {
            delete(key);
        }

        root = put(root, key, value);
        root.color = BLACK;
    }

    private void delete(Key key) {
        //todo
    }

    //目的：从Left leaning red-black tree找到位置，插入后保证原来leaf leaning性质。
    //关键实现1：dfs递归向下找到合适的位置插入
    //关键实现2：rotate旋转
    //关键实现3：flip colors颜色翻转
    public Node put(Node node, Key key, Value value) {
        if (node == null) { return new Node(key, value, RED, 1); }

        int cmp = node.key.compareTo(key);
        //太大，往左子树找
        if (cmp > 0) { node.left = put(node.left, key, value); }
        //太小，往右子树找
        else if (cmp < 0) { node.right = put(node.right, key, value); }
        //相等，替换值
        else { node.val = value; }

        //rotate+flip调整，使得红黑树保持原来性质
        // fix-up any right-leaning links
        //左右子树都是红色，翻转颜色
        if (isRed(node.left) && isRed(node.right)) { flipColors(node); }
        //红色边向右倾斜，需要做左旋转，变为红色边向左倾斜
        else if (!isRed(node.left) && isRed(node.right)) {node = rotateLeft(node); }
        //连续两个的红色边可能出现在左边，需要向右旋转
        else if (isRed(node.left) && isRed(node.left.left)) { node = rotateRight(node); }
        return node;
    }

    private Node rotateRight(Node node) {
        //left变为parent，parent的右子树挂node左边，node转到右边
        Node parent = node.left;
        node.left = parent.right;
        parent.right = node;

        parent.color = parent.right.color;
        parent.right.color = RED;
        return parent;
    }

    private Node rotateLeft(Node node) {
        Node parent = node.right;
        node.right = parent.left;
        parent.left = node;

        parent.color = parent.left.color;
        parent.left.color = RED;
        return parent;
    }

    private void flipColors(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    private boolean isRed(Node node) {
        return node.color;
    }

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color; //true-red, false-black
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

}
