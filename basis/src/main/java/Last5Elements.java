import algs4.Queue;
import algs4.Stack;

/**
 * <pre>
 * Interview question. Given a stack of an unknown number of strings,
 * print out the 5th to the last one. It's OK to destroy the stack in the process.
 * <p>
 * Hint: use a queue of 5 elements.
 * </pre>
 *
 * @author Zhang Yi
 */
public class Last5Elements {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // 因为我们并不知道stack大小，无法提前预知哪个是倒数第5个元素
        // 所以需要用一个数据结构保存最近读取到的5个元素
        Queue<Integer> queue = new Queue<>();
        while (!stack.isEmpty()) {
            if (queue.size() == 5) {
                queue.dequeue();
            }
            queue.enqueue(stack.pop());
        }
    }
}
