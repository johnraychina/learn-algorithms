import algs4.Stack;

/**
 * Stack with max.
 * <p>
 * Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum
 * operation. Assume the elements are real numbers so that you can compare them.
 * <p>
 * Hint: Use two stacks, one to store all of the items and a second stack to store the maximums.
 *
 * @author 张义 johnraychina@163.com
 */
public class StackWithMax {

    private Stack<Integer> originalStack;
    private Stack<Integer> maxStack;

    public StackWithMax() {
        originalStack = new Stack<>();
        maxStack = new Stack<>();
    }

    public static void main(String[] args) {
        StackWithMax swm = new StackWithMax();
        swm.push(2);
        swm.push(1);
        swm.push(4);
        swm.push(3);

        while (!swm.isEmpty()) {
            System.out.println(swm.max() + ":" + swm.pop());
        }
    }

    public Integer max() {
        return maxStack.peek();
    }

    // 1. we want keep the max value on top of the maxStack
    // so we push maxStack only when t > maxStack.peek(),
    // which makes the size of maxStack always little than or equal to the size of originalStack
    public void push(Integer t) {
        originalStack.push(t);
        if (maxStack.isEmpty() || t >= maxStack.peek()) {
            maxStack.push(t);
        }
    }

    // 2. pop: the value  from originalStack
    // in case the popped value is in the maxStack, we need pop the maxStack too.
    // (we can safely assume that the popped value is on top of the maxStack, thanks for the rule.1).
    public Integer pop() {
        Integer top = originalStack.pop();
        if (!maxStack.isEmpty() && top.equals(maxStack.peek())) {
            maxStack.pop();
        }
        return top;
    }

    private boolean isEmpty() {
        return originalStack.isEmpty();
    }
}
