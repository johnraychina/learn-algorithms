import java.util.Iterator;
import java.util.NoSuchElementException;

import algs4.StdIn;
import algs4.StdOut;

/**
 * <pre>
 * Bounded stack.
 * A bounded stack is a stack that has a capacity of at most N.
 * (Applications: undo or history with finite buffer.)
 *
 * </pre>
 *
 * @author Zhang Yi
 */
public class BoundedStack<Item> implements Iterable<Item> {

    private Node<Item> last;     // bottom of stack
    private Node<Item> first;     // top of stack
    private int n;                // size of the stack
    private int capacity;         // capacity of the stack

    /**
     * Initializes an empty stack.
     */
    public BoundedStack(int capacity) {
        last = null;
        first = null;
        n = 0;
        this.capacity = capacity;
    }

    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BoundedStack<String> stack = new BoundedStack<String>(3);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                stack.push(item);
            } else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
        }
        StdOut.println("(" + stack.size() + " left on stack)");
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return n;
    }

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        if (last == null) {
            last = first;
        }
        n++;

        // remove last if over capacity
        if (n > capacity) {
            Node<Item> beforeLast = first;
            while (beforeLast.next != last) { beforeLast = beforeLast.next; }
            beforeLast.next = null;
            n--;
            last = beforeLast;
        }
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) { throw new NoSuchElementException("Stack underflow"); }
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        if (first == null) {
            last = null;
        }
        n--;
        return item;                   // return the saved item
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) { throw new NoSuchElementException("Stack underflow"); }
        return first.item;
    }

    /**
     * Returns a string representation of this stack.
     *
     * @return the sequence of items in this stack in LIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * Returns an iterator to this stack that iterates through the items in LIFO order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO order
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
