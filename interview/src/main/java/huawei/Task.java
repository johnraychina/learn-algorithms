package huawei;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author Zhang Yi
 */
public class Task implements Callable<Object>, Comparable<Task> {

    //任务名称
    private String name;
    //下次执行时间
    private Date nextExecTime;

    public Task(String name, Date nextExecTime) {
        this.nextExecTime = nextExecTime;
        this.name = name;
    }

    public Object call() {
        //模拟任务执行
        System.out.println("Task running," + name + "：" + nextExecTime);
        return null;
    }

    public int compareTo(Task o) {
        return this.nextExecTime.compareTo(o.nextExecTime);
    }

    private Date getNextExecTime() {
        return this.nextExecTime;
    }
}
