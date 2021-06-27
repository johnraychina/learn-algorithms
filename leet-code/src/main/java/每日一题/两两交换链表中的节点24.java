package 每日一题;

import 基本数据结构.ListNode;

public class 两两交换链表中的节点24 {
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //dummy head
        ListNode dummy = new ListNode();
        dummy.next = head.next;

        //cur-next之间swap，prev要重新指向next
        ListNode prev = dummy;
        ListNode cur = head;
        ListNode next = head.next;

        //退出条件：next==null
        while (next != null) {
            //swap cur-next
            cur.next = next.next;
            next.next = cur;
            //update prev->next
            prev.next = next;

            //slide window
            prev = cur;
            cur = cur.next;
            next = cur != null ? cur.next : null;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = toList(new int[]{1,2,3,4});
        ListNode listNode = swapPairs(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    private static ListNode toList(int[] arr) {
        ListNode head = new ListNode();
        ListNode node = head;
        for (int i : arr) {
            node.next = new ListNode(i);
            node = node.next;
        }

        return head.next;
    }
}
