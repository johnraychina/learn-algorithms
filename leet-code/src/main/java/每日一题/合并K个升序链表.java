package 每日一题;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <pre>
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * </pre>
 */
public class 合并K个升序链表 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() { }
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //方案1
    //合并2个链表不难，合并的时候对比两个元素
    //合并k个链表可以做分治，两辆合并，形成一棵merge树，最后合并到一个链表

    //方案2
    //临时对比k个节点，是top K问题，可以用优先级队列
    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        ListNode head = new ListNode(); //dummy head
        ListNode cur = head;

        //初始化优先级队列：每个list第一个节点放入queue
        for (ListNode first : lists) {
            if (first != null) {
                queue.offer(first);
            }
        }

        //遍历，退出条件：所有链表都已经被合并到新的链表中，也就是queue为空
        while(!queue.isEmpty()) {
            //take queue
            ListNode listNode = queue.poll();
            cur.next = listNode;
            cur = cur.next;

            //offer next one to queue
            if (listNode.next != null) {
                queue.offer(listNode.next);
            }
        }

        return head.next;
    }

    public static void main(String[] args) {
        ListNode l1 = toList(new int[]{1, 4, 5});
        ListNode l2 = toList(new int[]{1, 3, 4});
        ListNode l3 = toList(new int[]{2,6});
        ListNode[] lists = new ListNode[]{l1, l2, l3};
        ListNode mergedList = mergeKLists(lists);
        System.out.println("ok");
    }

    public static ListNode toList(int[] arr) {
        ListNode head = new ListNode();
        ListNode node = head;
        for (int i : arr) {
            node.next = new ListNode(i);
            node = node.next;
        }

        return head.next;
    }
}
