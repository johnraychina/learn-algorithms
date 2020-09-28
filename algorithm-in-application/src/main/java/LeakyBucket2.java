/**
 * <pre>
 * bucket以一定速率滴水，相当于增加桶容量
 * bucket有其容量限制，请求过来时bucket满，则直接被抛弃
 * 请求到来时，如果bucket不满，则放入bucket，相当于放行
 * </pre>
 *
 * @author Zhang Yi
 */
public class LeakyBucket2 {

    private final long capacity;
    private final int ratePerSecond;
    private long used;
    private long lastLeakTime;

    public LeakyBucket2(long capacity, int rate) {
        this.capacity = capacity;
        this.used = 0;

        this.ratePerSecond = rate;
        this.lastLeakTime = System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {

        LeakyBucket2 bucket = new LeakyBucket2(3, 200);
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
        // 先执行漏水
        leak();

        // 满了
        if (used + drop > capacity) {
            return false;
        }
        // 未满
        used += drop;
        return true;
    }

    private void leak() {
        long currentTime = System.currentTimeMillis();
        long leaks = (currentTime - lastLeakTime) * ratePerSecond / 1000;
        if (leaks > 0) {
            used = Math.max(0, used - (currentTime - lastLeakTime) * ratePerSecond / 1000);
            lastLeakTime = currentTime;
        }
    }
}
