package huawei;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个调度框架 不断接受任务 每个任务有期望的执行时间 调度距离当前最近的任务
 *
 * @author Zhang Yi
 */
public class ScheduleFramework {

    private PriorityBlockingQueue<Runnable> taskQueue;
    private ThreadPoolExecutor executor;

    //todo 初始化
    public ScheduleFramework(int maxTask) {
        taskQueue = new PriorityBlockingQueue<Runnable>(maxTask);
        executor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.MINUTES, taskQueue);
    }

    public static void main(String[] args) throws InterruptedException {

        ScheduleFramework framework = new ScheduleFramework(10);

        Date task1Date = new Date();
        Date task2Date = new Date(task1Date.getTime() + 1000);
        Date task3Date = new Date(task1Date.getTime() + 2000);

        Task task1 = new Task("第一个任务", task1Date);
        Task task2 = new Task("第二个任务", task2Date);
        Task task3 = new Task("第三个任务", task3Date);
        framework.executor.invokeAll(Arrays.asList(task1, task2, task3));

        framework.executor.shutdown();
    }

    //todo
    public void submitTask(Task task) {
        executor.submit(task);
    }

    public void start() {
        executor.prestartAllCoreThreads();
    }
}
