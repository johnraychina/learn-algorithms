package com.zhangyi.algorithm.sort.tree;

/**
 * Maintains symmetric order and perfect balance.
 * <pre>
 * 2-node:  one key and two links
 * 3-node: with two keys(and associated values) and three links,
 * a left link to a 2-3 search tree with smaller keys,
 * a middle link to a 2-3 search tree with keys between node's keys,
 * and a right link to a 2-3 search tree with larger keys.
 * </pre>
 * <p>
 * <p>
 * 如何保持平衡？ 通过合并拆分保持平衡
 * <p>
 * 让我们看看如何实现搜索、插入：
 * <p>
 * 搜索：To determine whether a key is in a 2-3tree, we compare it against the keys at the root: if it is equal to any of
 * them, we have a search hit; otherwise, we follow the link from the root to the subtree corresponding to the interval
 * of key that could contain the search key, and then recursively in that subtree.
 * <p>
 * <p>
 * 插入: Insert into a 2-node.
 *
 * @author 张义 johnraychina@163.com
 */
public class TwoThreeSearchTree {
    //直接用2-3节点表示法可以做到，但是实现比较麻烦

}
