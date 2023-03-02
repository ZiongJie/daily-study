package com.study.juc.third.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier，意思是循环栅栏，这有一个栅栏，什么时候人满了就把栅栏推倒
 *  对于一个复杂操作，需要访问数据库，需要访问网络，需要访问文件，有一种方式是顺序执行，挨个的都执行完，效率非常低
 *  这是一种方式，还有一种可能性就是并发执行，原来是1、2、3顺序执行，并发执行是不同线程去执行不同的操作，有的线程
 *  去数据库找，有的线程去网络访问，有的线程去读文件，必须是这三个线程全部到位了我才能去进行，这个时候我们就可以用
 *  CyclicBarrier
 */
public class T07_TestCyclicBarrier {
    public static void main(String[] args) {
        // CyclicBarrier barrier = new CyclicBarrier(20);

        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("满人"));

        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车");
            }
        });*/

        for (int i = 0 ; i < 100; i++) {
            new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
