package java0.conc0303.homework;

import java.util.concurrent.Semaphore;

/**
 * 应用Semaphore机制
 */
public class AsyncResultImpl8 implements AsyncResult {
    private volatile int result = -1;

    @Override
    public int getResult() throws Exception {
        final Semaphore semaphore = new Semaphore(2);
        semaphore.acquire(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    result = sum();
                }finally {
                    semaphore.release(1);
                }
            }
        }).start();

        semaphore.acquire(2);
        return result;
    }
}
