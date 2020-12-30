package huawei.roundA;

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

    public ScheduleFramework(int maxTask) {
        //利用延时优先级队列特性，时间小的先take到，时间未到则会等
        //只是这里泛型兼容有问题：线程池参数要求BlockingQueue<Runnable> 与 延时队列DelayQueue<Delayed> 无法转换
        //就算加中间类 DelayedTask (Runnable, Delayed)，也无法构成继承关系
        //要么放开线程池参数类型限制
        //要么放开延时队列类型限制
        //ScheduledThreadPoolExecutor 里面用的是新写的一个 DelayedWorkQueue，放开了Delayed类型限制，但是实现了delay的效果
        //taskQueue = new DelayQueue<Runnable>();
        taskQueue = new PriorityBlockingQueue<>(10, (o1, o2) -> {
            DelayedTask t1 = (DelayedTask)o1;
            DelayedTask t2 = (DelayedTask)o2;
            return t1.compareTo(t2);
        });

        executor = new ThreadPoolExecutor(4, 4, 1, TimeUnit.MINUTES, taskQueue);
    }

    public static void main(String[] args) throws InterruptedException {

        ScheduleFramework framework = new ScheduleFramework(10);
        framework.start();

        Date task1Date = new Date();
        Date task2Date = new Date(task1Date.getTime() + 1000);
        Date task3Date = new Date(task1Date.getTime() + 2000);

        DelayedTask task1 = new DelayedTask("第一个任务", task1Date);
        DelayedTask task2 = new DelayedTask("第二个任务", task2Date);
        DelayedTask task3 = new DelayedTask("第三个任务", task3Date);
        framework.executor.submit(task1);
        framework.executor.submit(task2);
        framework.executor.submit(task3);

        framework.executor.awaitTermination(3, TimeUnit.SECONDS);
        framework.executor.shutdown();
        framework.executor.shutdownNow();
    }

    public void start() {
        executor.prestartAllCoreThreads();
    }

}
