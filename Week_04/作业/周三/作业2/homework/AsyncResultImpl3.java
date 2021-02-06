package java0.conc0303.homework;

/**
 * 应用join 机制
 */
public class AsyncResultImpl3 implements AsyncResult {
    private volatile int result = -1;
    @Override
    public int getResult() throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
            }
        });
        t1.start();
        t1.join();

        return result;
    }
}
