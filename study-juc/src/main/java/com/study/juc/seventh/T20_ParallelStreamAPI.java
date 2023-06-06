/**
 * 线程池，我们回顾一下有两种
 * 1、ThreadPoolExecutor
 * 2、ForkJoinPool
 * 他们两个的区别，前面这个ThreadPoolExecutor多个线程共享同一个任务队列；
 * 下面这个ForkJoinPool每个线程有自己的任务队列。
 */
package com.study.juc.seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class T20_ParallelStreamAPI {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(1000000 + r.nextInt(1000000));

        //System.out.println(nums);

        long start = System.currentTimeMillis();
        nums.forEach(v -> isPrime(v));
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        // 使用parallel stream api

        start = System.currentTimeMillis();
        nums.parallelStream().forEach(T20_ParallelStreamAPI::isPrime);
        end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
