package com.study.juc.third.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier，意思是循环栅栏，这有一个栅栏，什么时候人满了就把栅栏推倒
 *  对于一个复杂操作，需要访问数据库，需要访问网络，需要访问文件，有一种方式是顺序执行
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
