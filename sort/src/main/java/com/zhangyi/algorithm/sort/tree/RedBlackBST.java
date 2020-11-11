package com.zhangyi.algorithm.sort.tree;

/**
 * <pre>
 * 平衡树：all of the null links are the same distance from the root.
 * 平衡二叉树有非常好的性质：稳定的logN的查找、插入
 * 但是2-3的删除操作非常复杂和困难，用 Hibbard deletion需要判断很多case
 *
 * 采用红黑树实现
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
 * 但是最后都会通过"旋转节点"和"颜色翻转"被调整为合法结构：
 *
 * 颜色翻转：
 * 旋转后，可能会出现一个黑色的父节点，连接2个红色子节点，需要翻转为：红色父节点，黑色子节点。
 *
 *
 * </pre>
 *
 * @author 张义 johnraychina@163.com
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color; //true-red, false-black

    }
}
