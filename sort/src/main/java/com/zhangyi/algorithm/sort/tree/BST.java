package com.zhangyi.algorithm.sort.tree;

/**
 * <pre>
 * Binary search tree.
 *
 * A BST is a binary tree in symmetric order.
 *
 * A binary tree is either:
 * - empty
 * - two disjoint binary trees(left and right)
 *
 * Symmetric order:
 * Each node has a key and every node's key is:
 * - Larger than all keys in its left subtree.
 * - Smaller than all keys in its right subtree.
 * </pre>
 *
 * @author 张义 johnraychina@163.com
 */
public class BST<K extends Comparable<K>, V> {

    //todo Hibbard deletion
    // problem: Not symmetric

    // root of BST
    private Node<K, V> root;

    // search. if less go left; if greater go right; if equal, search hit.
    public V get(K key) {
        Node<K, V> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    //insert. if less, go left; if greater, go right; if null insert.
    public Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) { return new Node<K, V>(key, value); }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.val = value;
        }

        return node;
    }

    /**
     * <pre>
     * Floor: Largest key <= a given key
     * <p>
     * node.left -- node.key -- node.right
     * <p>
     * if key at root == k, the floor of k is key.
     * if key at root > k, the floor of k is in the left subtree.
     * if key at root < k:
     *      the floor of k is in the right subtree(if there is any key<=k in the right subtree);
     *      otherwise it is the key  in the root.
     * </pre>
     *
     * @param key
     * @return
     */
    public K floor(K key) {
        Node<K, V> x = floor(root, key);
        if (x == null) { return null; }
        return x.key;
    }

    public Node<K, V> floor(Node<K, V> root, K key) {
        int cmp = root.key.compareTo(key);
        if (cmp == 0) {
            return root;
        }
        if (cmp > 0) {
            return floor(root.left, key);
        }

        Node<K, V> r = floor(root.right, key);
        if (r != null) { return r; } else { return root; }
    }

    //Ceiling: Smallest key >= a given key
    public K ceiling(K key) {
        Node<K, V> x = ceiling(root, key);
        if (x == null) { return null; }
        return x.key;
    }

    public Node<K, V> ceiling(Node<K, V> root, K key) {
        int cmp = root.key.compareTo(key);
        if (cmp == 0) {
            return root;
        }
        //如果当前节点比要找的key小，需要右边子树找大一点的
        if (cmp < 0) {
            return ceiling(root.right, key);
        }

        //如果当前节点比要找的key大，需要再找找，如果找不着就是它了。
        Node<K, V> l = ceiling(root.left, key);
        if (l != null) { return l; } else { return root; }
    }

    public void delete(K key) {

    }

    public Iterable<K> iterator() {

    }

    static class Node<K, V> {
        K key;
        V val;
        Node<K, V> left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
}
