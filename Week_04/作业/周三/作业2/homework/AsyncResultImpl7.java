package java0.conc0303.homework;

import java.util.concurrent.CyclicBarrier;

/**
 * 应用 CyclicBarrier 机制
 */
public class AsyncResultImpl7 implements AsyncResult {
    private volatile int result = -1;
    @Override
    public int getResult() throws Exception {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = sum();
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        cyclicBarrier.await();
        return result;
    }
}
