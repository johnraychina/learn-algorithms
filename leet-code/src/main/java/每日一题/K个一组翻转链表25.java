package 每日一题;

import 基本数据结构.ListNode;

import java.util.Stack;

/**
 * <pre>
 * 25. K 个一组翻转链表
 *

 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 *
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 */
public class K个一组翻转链表25 {

    //思路：stack反转 + slide window
    public static ListNode reverseKGroup(ListNode head, int k) {

        //dummy previous node
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = prev.next;

        Stack<ListNode> stack = new Stack<>();

        //退出条件：next window is empty
        while (cur != null) {
            //push k nodes
            int cnt = 0;
            while(cnt < k && cur != null) {
                stack.push(cur);
                cnt++;
                cur = cur.next;
            }
            //退出循环的时候，cur已经指向窗口右侧外面的一个节点
            //pop k nodes(reversed order) and link to the list
            if (stack.size() == k) {
                //pop node and hook to the list
                while(!stack.isEmpty()) {
                    prev.next = stack.pop();
                    prev = prev.next;
                }
                prev.next = null; //break cycle
            } else {
                //corner case: do not reverse if less than k nodes in the stack
                break;
            }
        }
        //corner case: do not reverse if less than k nodes in the stack
        if (!stack.isEmpty()) {
            //get the bottom node and link back just works
            while (stack.size() > 1) stack.pop();
            prev.next = stack.pop();
        }
        return dummy.next;
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

    public static void main(String[] args) {

        ListNode listNode = reverseKGroup(toList(new int[]{1, 2, 3, 4, 5}), 3);
        while (listNode!= null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
