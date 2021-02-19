/**
 * <pre>
 * bucket以一定速率滴水，相当于占用桶空间
 * bucket有其容量限制，请求过来时bucket满，则直接被抛弃
 * 请求到来时，如果bucket不满，则放入bucket，相当于放行
 * </pre>
 *
 * @author Zhang Yi
 */
public class LeakyBucket {

    private final long leaksIntervalInMillis;
    private final long capacity;
    private long used;
    private long lastLeakTime;

    public LeakyBucket(long capacity, long leaksIntervalInMillis) {
        this.capacity = capacity;
        this.used = 0;

        this.leaksIntervalInMillis = leaksIntervalInMillis;
        this.lastLeakTime = System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {

        LeakyBucket bucket = new LeakyBucket(3, 200);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100L); //模拟发送请求的间隔
            if (bucket.tryDrop(1)) {
                System.out.println("Request accepted " + i);
            } else {
                System.out.println("Request rejected " + i);
            }
        }
    }

    private static void doBizLogic() {
    }

    /**
     * 尝试滴入请求
     *
     * @param drop number of coming requests
     * @return true-drop success，false-rejected
     */
    public synchronized boolean tryDrop(int drop) {
        leak();

        System.out.println("Used=" + used);
        if (used + drop > capacity) {
            return false;
        }
        used += drop;
        return true;
    }

    public synchronized void leak() {
        long currentTime = System.currentTimeMillis();
        long leaks = (currentTime - lastLeakTime) / leaksIntervalInMillis;
        if (leaks > 0) {
            lastLeakTime = currentTime;
            used = Math.max(0, used - leaks);
        }
    }
}
