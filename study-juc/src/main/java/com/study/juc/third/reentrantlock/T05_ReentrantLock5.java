package com.study.juc.third.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5 extends Thread{

    // 参数为true表示为公平锁，请对比输出结果
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 rl = new T05_ReentrantLock5();
        Thread th1 = new Thread(rl);
        Thread th2 = new Thread(rl);
        th1.start();
        th2.start();
    }
}

/**
 * 1、ReentrantLock可以替代synchronized这是没问题的，他也可以重入，可以锁定的。本身的底层是cas
 * 2、trylock：自己来控制，我锁不住该怎么办
 * 3、lockInterruptibly：这个类，中间你还可以被打断
 * 4、它还可以公平和非公平的切换
 */

