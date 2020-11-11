package com.zhangyi.algorithm.sort.tree;

import algs4.Queue;

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
public class BinarySearchTree<K extends Comparable<K>, V> {

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
        if (node == null) { return new Node<K, V>(key, value, 1); }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.val = value;
        }

        node.count = 1 + size(node.left) + size(node.right);
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

    public int size() {
        if (root == null) {return 0;} else { return root.count; }
    }

    public int size(Node<K, V> node) {
        if (node == null) {return 0;} else { return node.count; }
    }

    //how many keys < k
    public int rank(K k) {
        return rank(k, root);
    }

    public int rank(K k, Node<K, V> node) {
        if (node == null) { return 0; }

        int cmp = node.key.compareTo(k);
        //当前节点key == k，则小于k的节点数=左子树个数
        if (cmp == 0) { return size(node.left); }
        //当前节点key太大，往左子树找小的
        else if (cmp > 0) { return rank(k, node.left); }
        //当前节点key太小，往右子树找大的
        // 注意：小于key的节点数=左子树个数+当前节点+右边的顺序
        else { return 1 + size(node.left) + rank(k, node.right); }
    }

    public void deleteMin() {
        deleteMin(root);
    }

    private Node<K, V> deleteMin(Node<K, V> node) {

        //Go left until finding a node with a null left link
        //replace the node with right node
        //update subtree counts
        if (node.left == null) { return node.right; }
        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    // Hibbard deletion:  to delete a node with key k, search for node t containing key k.
    // problem: Not symmetric
    //删除key对应节点
    public void delete(K key) {
        root = delete(key, root);
    }

    public Node<K, V> delete(K key, Node<K, V> node) {
        if (node == null) { return null; }

        int cmp = node.key.compareTo(key);

        //如果相等，则准备删除当前节点，根据children个数，判断如何替换当前节点
        //对如果没有subtree，直接摘除
        //case 0 s[0 children]: delete t by setting parent link to null

        //对于只有一个subtree的节点，直接将指向此节点的链接改为指向subtree
        //case 1 [1 children]: delete t by replacing parent link

        //要删除的节点如果有两个subtree，从中选择合适的中间节点替代此节点：右子树最小节点 or 左子树最大节点
        //可以复用方法deleteMin实现删除右子树最小节点
        //case 2 [2 children]: replace t with min node of the right subtree

        //当前节点key太大则查找左子树
        if (cmp > 0) { node.left = delete(key, node.left);}
        //当前节点key太小右则查找子树
        else if (cmp < 0) {node.right = delete(key, node.right);}
        //当前节点key与搜索的key相等
        else {
            if (node.left == null) {
                return node.right; //没有右子树，返回左子树作为新的子节点
            }
            if (node.right == null) { return node.left; }

            Node<K, V> t = node; //准备删除节点
            node = min(t.right); //替换为新节点：右子树中最小节点
            node.right = deleteMin(t.right); //重建右子树链接
            node.left = t.left; //重建左子树链接
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    public Node<K, V> min(Node<K, V> node) {
        //一直找左子树
        if (node.left != null) {return min(node.left);}
        return node;
    }

    public Iterable<K> iterator() {
        Queue<K> queue = new Queue<>();
        inOrder(root, queue);
        return queue;
    }

    //Inorder traversal: left subtree, enqueue key, right subtree
    private void inOrder(Node<K, V> node, Queue<K> queue) {
        if (node == null) { return; }
        inOrder(node.left, queue);
        queue.enqueue(node.key);
        inOrder(node.right, queue);
    }

    static class Node<K, V> {
        int count;
        K key;
        V val;
        Node<K, V> left, right;

        public Node(K key, V val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }
}
