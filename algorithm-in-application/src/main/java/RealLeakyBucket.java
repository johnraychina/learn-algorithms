import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * bucket以一定速率滴水，相当于占用桶空间
 * bucket有其容量限制，请求过来时bucket满，则直接被抛弃
 * 请求到来时，如果bucket不满，则放入bucket，相当于放行
 * </pre>
 *
 * @author Zhang Yi
 */
public class RealLeakyBucket {

    private final long gapInMicroseconds;
    private final long capacity;
    private long used;
    private long lastLeakTime;
    private ScheduledThreadPoolExecutor executor;

    public RealLeakyBucket(long capacity, long gapInMicroseconds) {
        this.capacity = capacity;
        this.used = 0;

        this.gapInMicroseconds = gapInMicroseconds;
        this.lastLeakTime = System.currentTimeMillis();
        this.executor = new ScheduledThreadPoolExecutor(4);
    }

    public static void main(String[] args) throws InterruptedException {

        RealLeakyBucket bucket = new RealLeakyBucket(3, 200);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100L); //模拟发送请求的间隔
            if (bucket.addRequest(1)) {
                System.out.println("Request accepted " + i);
            } else {
                System.out.println("Request rejected " + i);
            }
        }
    }

    private static void doBizLogic() {
    }

    public synchronized boolean addRequest(int requestId) {
        System.out.println("Used=" + used);
        if (used + 1 > capacity) {
            return false;
        }

        //todo
        used++;
        executor.schedule(() -> doBizLogic(), gapInMicroseconds, TimeUnit.MICROSECONDS);

        return true;
    }

}
