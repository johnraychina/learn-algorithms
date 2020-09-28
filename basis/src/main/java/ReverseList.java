/**
 * @author Zhang Yi
 */
public class ReverseList {
    //非递归方式：3个指针，reverse为逆链表头，first为原链表头，second为原链表第二个节点
    public Node reverse(Node x) {
        Node first = x;
        Node reverse = null;
        while (first != null) {
            Node second = first.next;
            first.next = reverse;
            reverse = first;
            first = second;
        }

        return reverse;
    }

    //递归方式：reverse后面的节点(second)，然后将first放入逆链表队尾
    public Node reverseRecursive(Node first) {
        if (first == null) { return null; }
        if (first.next == null) { return first; }
        Node second = first.next;
        Node rest = reverseRecursive(second);
        second.next = first;
        first.next = null;
        return rest;
    }
}

class Node {
    Object val;
    Node next;
}
