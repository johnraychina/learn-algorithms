package 基本数据结构;

public class 反转链表 {
    public static void main(String[] args) {

        ListNode head5 = new ListNode(5, null);
        ListNode head4 = new ListNode(4, head5);
        ListNode head3 = new ListNode(3, head4);
        ListNode head2 = new ListNode(2, head3);
        ListNode head1 = new ListNode(1, head2);
        reverseList(head1);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = head.next;
        head.next = null;
        while (cur != null) {
            ListNode temp = cur.next;

            cur.next = head;
            head = cur;
            cur = temp;
        }
        return head;
    }
}



