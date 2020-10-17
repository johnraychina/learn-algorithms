import algs4.Queue;

/**
 * <pre>
 * Josephus problem.
 * In the Josephus problem from antiquity,
 * N people are in dire straits and agree to the following strategy to reduce the population.
 * They arrange themselves in a circle (at positions numbered from 0 to N-1) and proceed around the circle,
 * eliminating every Mth person until only one person is left.
 * Legend has it that Josephus figured out where to sit to avoid being eliminated.
 * Write a Queue client Josephus.java that takes M and N from the command line
 * and prints out the order in which people are eliminated
 * (and thus would show Josephus where to sit in the circle).
 *
 * 1、需要注意的是m必须大于等于2，如果m=1就不会跳着来了，而是每个人都会干掉。
 *
 * 2、如果要找出这个幸运位置，可以用回溯法来推断：
 * 把位置按0~N-1编号，m=2时：
 * 最后剩2人时，会干掉位置1的人，所以幸运位置是0
 * 最后剩3人时，幸运位置1
 * </pre>
 *
 * @author Zhang Yi
 */
public class Josephus {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java Josephus <m> <n>");
            return;
        }
        int m = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        Queue<Integer> circle = new Queue<>();
        for (int i = 0; i < n; i++) {
            circle.enqueue(i);
        }

        // 不断出队列入队列，直到最后只有一个值
        int position = 1;
        while (circle.size() > 1) {
            if (position == m) {
                int index = circle.dequeue(); // 第m个人出队列，打印，并重置pos为1
                System.out.println("吃掉 " + index);
                position = 1;
            } else {
                circle.enqueue(circle.dequeue()); // 重新入队列
                position++; // 位置后移
            }
        }

        int josephusIndex = circle.dequeue();
        System.out.println("幸运数字:" + josephusIndex);
    }
}
