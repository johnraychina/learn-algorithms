package huawei.roundA;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个调度框架 不断接受任务 每个任务有期望的执行时间 调度距离当前最近的任务
 *
 * @author Zhang Yi
 */
public class DelayScheduleFramework {

    public static void main(String[] args) throws InterruptedException {

        ScheduledThreadPoolExecutor framework = new ScheduledThreadPoolExecutor(4);
        framework.prestartAllCoreThreads();

        Date task1Date = new Date();
        Date task2Date = new Date(task1Date.getTime() + 1000);
        Date task3Date = new Date(task1Date.getTime() + 2000);

        Runnable task1 = new DelayedTask("第一个任务", task1Date);
        Runnable task2 = new DelayedTask("第二个任务", task2Date);
        Runnable task3 = new DelayedTask("第三个任务", task3Date);
        framework.schedule(task1, 100, TimeUnit.MILLISECONDS);
        framework.schedule(task2, 200, TimeUnit.MILLISECONDS);
        framework.schedule(task3, 300, TimeUnit.MILLISECONDS);

        framework.awaitTermination(3, TimeUnit.SECONDS);
        framework.shutdown();
        framework.shutdownNow();
    }

}
