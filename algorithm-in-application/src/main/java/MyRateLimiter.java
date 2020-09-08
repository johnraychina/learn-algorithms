import com.google.common.util.concurrent.RateLimiter;

/**
 * 限流算法实现
 * @author 张义 reed.zy@alibaba-inc.com
 */
public class MyRateLimiter {
    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(3);

        for (int i = 0; i < 6; i++) {
            // time spent sleeping to enforce rate, in seconds; 0.0 if not rate-limited
            double slept = rateLimiter.acquire(1);
            System.out.printf("%d: slept %f seconds \n", i, slept);
            doSomeLimitedOperation();
        }


    }

    private static void doSomeLimitedOperation() {
        System.out.println("do something:" + System.currentTimeMillis() );
    }
}
