package java0.conc0303.homework;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 应用阻塞队列
 */
public class AsyncResultImpl10 implements AsyncResult {
    private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(1);
    @Override
    public int getResult() throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                blockingQueue.offer(sum());
            }
        }).start();

        return blockingQueue.take();
    }
}
