import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 模拟Linux操作系统CFS调度器
 *
 * @author Zhang Yi
 */
public class CompleteFairScheduler {

    public static void main(String[] args) {

    }

    public void submit(Runnable task) {
        //todo
        Queue<Runnable> queue = new PriorityQueue<>();
        queue.offer(task);
    }

    public void schedule() {
        //todo
    }
}
