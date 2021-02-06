package java0.conc0303.homework;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 应用CAS机制
 */
public class AsyncResultImpl2 implements AsyncResult {

    private volatile int result = -1;

    private AtomicInteger tag = new AtomicInteger(1);

    @Override
    public int getResult() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
                tag.getAndDecrement();
            }
        }).start();

        while (tag.get() > 0){
            Thread.yield();
        }
        return result;
    }
}
