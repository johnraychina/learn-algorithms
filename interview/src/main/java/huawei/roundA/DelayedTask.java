package huawei.roundA;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author Zhang Yi
 */
public class DelayedTask implements Runnable, Comparable<DelayedTask> {

    //任务名称
    private String name;
    //下次执行时间
    private Date nextExecTime;

    public DelayedTask(String name, Date nextExecTime) {
        this.nextExecTime = nextExecTime;
        this.name = name;
    }

    @Override
    public void run() {
        //模拟任务执行
        System.out.println("Task running," + name + "：" + nextExecTime.getTime());
    }

    public int compareTo(DelayedTask o) {
        return this.nextExecTime.compareTo(o.nextExecTime);
    }

    public int compareTo(Delayed o) {
        return (int)(getDelay(MILLISECONDS) - o.getDelay(MILLISECONDS));
    }

    private Date getNextExecTime() {
        return this.nextExecTime;
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(nextExecTime.getTime() - System.currentTimeMillis(), MILLISECONDS);
    }

}
