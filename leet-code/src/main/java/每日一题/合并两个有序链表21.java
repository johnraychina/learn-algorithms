package 每日一题;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class 合并两个有序链表21 {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head = new ListNode();
        ListNode cur = head;
        while (l1 != null || l2 != null) {

            if (l1 != null && (l2 == null || l1.val < l2.val)) {
                cur.next = l1;
                l1 = l1.next;
            } else{
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        return head.next;
    }
}
