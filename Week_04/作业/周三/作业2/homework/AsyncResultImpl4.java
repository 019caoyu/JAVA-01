package java0.conc0303.homework;

/**
 * 应用传统的synchronized锁通知机制
 */
public class AsyncResultImpl4 implements AsyncResult {
    private volatile int result = -1;
    private final Object getResultLock = new Object();

    @Override
    public int getResult() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (getResultLock) {
                    result = sum();
                    getResultLock.notifyAll();
                }
            }
        }).start();

        synchronized (getResultLock) {
            getResultLock.wait();
            return result;
        }
    }
}
