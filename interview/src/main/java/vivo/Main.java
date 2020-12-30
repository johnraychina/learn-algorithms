package vivo;

import java.util.concurrent.CyclicBarrier;

/**
 * @author Zhang Yi
 */
public class Main {

    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        //大任务拆分为10个小任务，任务无依赖，执行时间不同
        //效果：开始，结束时间，希望总耗时最小

        //限制：64 core
        //CyclicBarrier
        CyclicBarrier barrier = new CyclicBarrier(10,
            () -> System.out.println("End of whole task:" + System.currentTimeMillis()));

        for (int i = 0; i < 10; i++) {
            new Thread("Thread" + i) {
                @Override
                public void run() {
                    try {
                        long cost = System.currentTimeMillis();
                        Thread.sleep((int)(Math.random() * 100));
                        cost = System.currentTimeMillis() - cost;
                        System.out.println("cost:" + cost);
                        barrier.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        System.out.println("Hello World!");
    }

}
