package java0.conc0303.homework;

import org.junit.Test;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */

public class Homework {

   /* public static void main(String[] args) throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl5();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }*/
    @Test
    public void  test1() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl1();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }

    @Test
    public void test2() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl2();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }

    @Test
    public void test3() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl3();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }

    @Test
    public void test4() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl4();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }

    @Test
    public void test6() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl6();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }
    @Test
    public void test7() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl7();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }
    @Test
    public void test8() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl8();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }
    @Test
    public void test9() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl9();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }
    @Test
    public void test10() throws Exception {
        long start=System.currentTimeMillis();
        AsyncResult asyncResult = new AsyncResultImpl10();
        System.out.println("异步计算结果为："+ asyncResult.getResult());
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        System.out.println("退出main线程");
    }

}
