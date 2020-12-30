package 剑指offer;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge
 * -jie-dian-b-61/
 *
 * @author Zhang Yi
 */
public class 删除链表的倒数第N个节点 {
    public static void main(String[] args) {

    }

    //public ListNode removeNthFromEnd(ListNode head, int n) {
    //    if (head == null) { return head; }
    //
    //    Stack<ListNode> stack = new Stack<ListNode>();
    //    ListNode cur = head;
    //    do {
    //        stack.push(cur);
    //    } while ((cur = cur.next) != null);
    //
    //    //如果是删除第一个节点
    //    if (n == stack.size()) {
    //        head = head.next;
    //        return head;
    //    }
    //
    //    //pop n次，stack顶部是前驱节点
    //    for (int i = 0; i < n; i++) {
    //        stack.pop();
    //    }
    //    ListNode prev = stack.peek();
    //    prev.next = prev.next.next;
    //    return head;
    //}

    /**
     * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     * <p>
     * 由于我们需要找到倒数第 nn 个节点，因此我们可以使用两个指针 \textit{first}first 和 \textit{second}second 同时对链表进行遍历，并且 \textit{first}first 比
     * \textit{second}second 超前 nn 个节点。当 \textit{first}first 遍历到链表的末尾时，\textit{second}second 就恰好处于倒数第 nn 个节点。
     * <p>
     * 具体地，初始时 \textit{first}first 和 \textit{second}second 均指向头节点。我们首先使用 \textit{first}first 对链表进行遍历，遍历的次数为
     * nn。此时，\textit{first}first 和 \textit{second}second 之间间隔了 n-1n−1 个节点，即 \textit{first}first 比 \textit{second} second
     * 超前了 nn 个节点。
     * <p>
     * 在这之后，我们同时使用 \textit{first}first 和 \textit{second}second 对链表进行遍历。当 \textit{first}first 遍历到链表的末尾（即 \textit{first
     * }first 为空指针）时，\textit{second}second 恰好指向倒数第 nn 个节点。
     * <p>
     * 根据方法一和方法二，如果我们能够得到的是倒数第 nn 个节点的前驱节点而不是倒数第 nn 个节点的话，删除操作会更加方便。因此我们可以考虑在初始时将 \textit{second}second
     * 指向哑节点，其余的操作步骤不变。这样一来，当 \textit{first}first 遍历到链表的末尾时，\textit{second}second 的下一个节点就是我们需要删除的节点。
     * <p>
     * 作者：LeetCode-Solution 链接：https://leetcode-cn
     * .com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di
     * -nge-jie-dian-b-61/ 来源：力扣（LeetCode） 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
