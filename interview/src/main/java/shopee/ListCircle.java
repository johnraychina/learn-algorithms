package shopee;

import common.ListNode;

public class ListCircle {
    public boolean hasCircle(ListNode node) {

        if (node == null || node.next == null)
            return false;

        //fast-slow pointer
        ListNode slow = node;
        ListNode fast = node.next;
        //结束条件: fast 到终点，肯定没环
        while (slow != fast) {

            if (fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
