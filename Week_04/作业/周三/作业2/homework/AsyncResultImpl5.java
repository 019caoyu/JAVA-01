package java0.conc0303.homework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AsyncResultImpl5 implements AsyncResult {
    private volatile int result = -1;

    @Override
    public int getResult() throws Exception {
        final ReentrantLock lock = new ReentrantLock(false);
        Condition hadCompute = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    result = sum();
                    hadCompute.signalAll();
                }finally {
                    lock.unlock();
                }
            }
        }).start();

        lock.lock();
        try {
            hadCompute.await();
            return result;
        }finally {
            lock.unlock();
        }
    }
}
