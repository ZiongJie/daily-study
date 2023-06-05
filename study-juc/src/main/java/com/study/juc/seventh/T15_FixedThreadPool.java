/**
 * 线程池 = 你有多少个CPU乘以CPU期望利用率 乘以 (1+W/C)。W除以C是等待时间与计算时间的比率。
 *
 * cache vs fixed
 * cache：来一个就new一个线程，无界
 * fixed：固定线程数，核心线程和最大线程都是固定的
 *
 * 假如你这个任务不确定他的量平稳与否，就像是任务来的时候他可能忽高忽低，但是我们要保证这个任务来时有人做这个事儿，
 * 那么可以用cache，，当然你要保证这个任务不会堆积。那Fixed的话就是这个任务来的比较平稳，我们大概的估算了一个值，
 * 就是这个值完全可以处理他，我就直接new这个值的线程来扔在这就ok了。（阿里是都不用，自己估算，进行精确定义）
 */
package com.study.juc.seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class T15_FixedThreadPool {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        getPrime(1, 200000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        final int cpuCoreNum = 4;

        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);

        MyTask t1 = new MyTask(1, 80000); //1-5 5-10 10-15 15-20
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);

        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }


        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num/2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) results.add(i);
        }
        return results;
    }
}
