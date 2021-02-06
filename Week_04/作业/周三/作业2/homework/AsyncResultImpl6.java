package java0.conc0303.homework;

import java.util.concurrent.CountDownLatch;

/**
 * 应用CountDownLatch机制
 */
public class AsyncResultImpl6 implements AsyncResult{
    private volatile int result = -1;
    @Override
    public int getResult() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
                countDownLatch.countDown();
            }
        }).start();
        countDownLatch.await();
        return result;
    }
}
