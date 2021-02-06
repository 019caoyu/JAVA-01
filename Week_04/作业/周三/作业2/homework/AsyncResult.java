package java0.conc0303.homework;

public interface AsyncResult {

    int getResult() throws Exception;

    default int sum() {
        return fibo(36);
    }

    default int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
