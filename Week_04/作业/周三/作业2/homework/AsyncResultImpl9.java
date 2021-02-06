package java0.conc0303.homework;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 应用Future机制
 */
public class AsyncResultImpl9 implements AsyncResult{
    @Override
    public int getResult() throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                return sum();
            }
        });
        // 注意关闭线程池，否则main线程不会结束，会一直等待，直到线程池被关闭。
        executorService.shutdown();
        return future.get();
    }
}
