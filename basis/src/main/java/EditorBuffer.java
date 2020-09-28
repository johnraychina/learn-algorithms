import algs4.Stack;

/**
 * <pre>
 * 1.3.44 文本编辑器缓冲区
 * 使用左栈 + 右栈实现，光标在中间，
 * delete删除的是右栈顶部，insert插入的左栈顶部
 * 移动光标意味着pop一个push到另外一个
 * </pre>
 *
 * @author Zhang Yi
 */
public class EditorBuffer {

    Stack<Character> leftStack;
    Stack<Character> rightStack;

    public EditorBuffer() {
        leftStack = new Stack<>();
        rightStack = new Stack<>();
    }

    public static void main(String[] args) {
        EditorBuffer editorBuffer = new EditorBuffer();

        String text = "千里江山，万里雪飘，望长城内外...";
        char[] array = text.toCharArray();
        for (char c : array) {
            //mock type characters one by one
            editorBuffer.insert(c);
        }

        editorBuffer.left(10);
        editorBuffer.outputRight();
    }

    public void insert(char c) {
        leftStack.push(c);
    }

    public char delete() {
        return rightStack.pop();
    }

    public void left(int k) {
        assureCapacity(leftStack, k);
        for (int i = 0; i < k; i++) {
            rightStack.push(leftStack.pop());
        }
    }

    public void right(int k) {
        assureCapacity(rightStack, k);
        for (int i = 0; i < k; i++) {
            leftStack.push(rightStack.pop());
        }
    }

    private void assureCapacity(Stack<Character> stack, int k) {
        if (stack.size() < k) {
            throw new IllegalArgumentException("k must little than " + stack.size());
        }
    }

    public void outputRight() {
        char[] out = new char[rightStack.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = rightStack.pop();
        }

        System.out.println(String.valueOf(out));
    }
}
