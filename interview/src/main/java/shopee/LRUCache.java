package shopee;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public static void main(String[] args) {

    }


    private int size;
    private final int capacity;
    private DNode head, tail;
    private Map<Integer, DNode> map;

    class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;
        DNode(){}
        DNode(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.map = new HashMap<>(capacity);
        this.head = new DNode();
        this.tail = new DNode();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        DNode node = map.get(key);
        if (node == null) return -1;

        // move to first: remove and add before first
        remove(node);
        addFirst(node);

        return node.key;
    }

    public void put(int key, int value) {
        DNode node = map.get(key);
        if (node != null) {
            node.value = value;
            moveFirst(node);
        } else {
            node = new DNode(key, value);
            addFirst(node);
            map.put(key, node);
            size++;
            if (size > capacity) {
                DNode last = removeLast();
                map.remove(last.key);
                size--;
            }
        }
    }

    public void moveFirst(DNode node) {
        remove(node);
        addFirst(node);
    }
    public void remove(DNode node) {
        DNode prev = node.prev;
        DNode next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public DNode removeLast() {
        DNode last = tail.prev;
        remove(last);
        return last;
    }

    public void addFirst(DNode node) {
        DNode first = this.head.next;
        head.next = node;
        node.prev = head;
        first.prev = node;
        node.next = first;
    }


}
