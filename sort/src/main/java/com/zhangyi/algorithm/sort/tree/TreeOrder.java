package com.zhangyi.algorithm.sort.tree;

import algs4.Queue;

/**
 * <pre>
 * 树节点遍历：
 * 先序遍历
 * 中序遍历
 * 后续遍历
 * </pre>
 *
 * @author Zhang Yi
 */
public class TreeOrder {

    public static void main(String[] args) {
        Node n1 = new Node(3);
        Node n2 = new Node(9);
        Node n3 = new Node(20);
        Node n4 = new Node(15);
        Node n5 = new Node(17);
        n1.left = n2;
        n1.right = n3;
        n3.left = n4;
        n3.right = n5;
        Queue<Node> q = preOrder(n1);
        System.out.println(q);
    }

    private static Queue<Node> preOrder(Node n1) {
        Queue<Node> q = new Queue<>();
        preOrder(n1, q);
        return q;
    }

    private static void preOrder(Node n1, Queue<Node> q) {
        if (n1 == null) {
            return;
        }
        q.enqueue(n1);
        preOrder(n1.left, q);
        preOrder(n1.right, q);
    }

    private static Queue<Node> inOrder(Node n1) {
        Queue<Node> q = new Queue<>();
        preOrder(n1, q);
        return q;
    }

    private static void inOrder(Node n1, Queue<Node> q) {
        if (n1 == null) {
            return;
        }
        preOrder(n1.left, q);
        q.enqueue(n1);
        preOrder(n1.right, q);
    }

    private static Queue<Node> postOrder(Node n1) {
        Queue<Node> q = new Queue<>();
        preOrder(n1, q);
        return q;
    }

    private static void postOrder(Node n1, Queue<Node> q) {
        if (n1 == null) {
            return;
        }
        preOrder(n1.left, q);
        preOrder(n1.right, q);
        q.enqueue(n1);
    }

    static class Node {
        Node left;
        Node right;
        int val;

        Node(int val) {
            this.val = val;
        }
    }
}


