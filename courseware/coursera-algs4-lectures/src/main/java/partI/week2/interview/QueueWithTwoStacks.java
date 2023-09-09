package partI.week2.interview;

import java.util.NoSuchElementException;

import algs4.Stack;
import algs4.StdOut;

/**
 * KEY IDEA: 绳命在于折腾，堆栈在于倒腾.
 * <p>
 * Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized
 * number of stack operations.
 * <p>
 * Hint: If you push elements onto a stack and then pop them all, they appear in reverse order. If you repeat this
 * process, they're now back in order.
 *
 * @author 张义 johnraychina@163.com
 */
public class QueueWithTwoStacks<Item> {

    // YY一下：
    // Robert教授嫌弃java.util.Stack是基于Vector，Vector基于数组实现，需要调整大小，性能差。
    // 另外教学场景用不上synchronized, 他们自己用linked list简单实现了一个Stack。
    // 自己手撸每种数据结构，便于了解每个细节，便于教学。
    // 不会有兼容性问题，随时间推移，到时候java要是调整类库什么的，用自己实现的这些数据结构仍然能工作，很有远见.
    // 代码自主可控，自己想怎么玩就怎么玩，不会被专利流氓oracle之流告上法庭。
    private Stack<Item> inputStack;
    private Stack<Item> outputStack;

    // convert stack LIFO to queue FIFO by two stack: inputStack, outputStack
    // push: inputStack
    // pop: outputStack: when empty, then borrow all elements from inputStack and push into outputStack in reverse
    // order.

    public QueueWithTwoStacks() {
        inputStack = new Stack<>();
        outputStack = new Stack<>();
    }

    public static void main(String[] args) {
        StdOut.println("Test stack");
        testStack();

        StdOut.println("Test queue with two stacks");

        testQueueWithTwoStacks();
    }

    private static void testStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        StdOut.println(stack.pop());
        StdOut.println(stack.pop());

        stack.push(6);

        StdOut.println(stack.pop());

    }

    private static void testQueueWithTwoStacks() {

        QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<Integer>();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        StdOut.println(queue.dequeue());
        StdOut.println(queue.dequeue());

        queue.enqueue(6);

        StdOut.println(queue.dequeue());
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return inputStack.size() + outputStack.size();
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        inputStack.push(item);
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        if (outputStack.isEmpty()) {
            borrowFromInput();
        }
        return outputStack.pop();
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (size() == 0) {
            throw new NoSuchElementException("Queue is empty");
        }

        if (outputStack.isEmpty()) {
            borrowFromInput();
        }
        return outputStack.peek();
    }

    private void borrowFromInput() {
        for (Item item : inputStack) {
            outputStack.push(item);
        }
    }
}
