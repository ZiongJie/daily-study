package com.study.juc.fourth.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 以前我们要阻塞和唤醒一个具体的线程有很多限制比如：
 * 1、因为wait()方法需要释放锁，所以必须在synchronized中使用，否则会抛出异常IllegalMonitorStateException
 * 2、notify()方法也必须在synchronized中使用，并且应该指定对象。
 * 3、synchronized()、wait()、notify()对象必须一致，一个synchronized()代码块中只能有一个线程调用wait()或notify()
 */
public class T13_TestLockSupport {
    public static void main(String[] args) {
        // 使用lamdba表达式创建一个线程t
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    // 使用LockSupport得park()方法阻塞当前线程t
                    LockSupport.park();
                }
                try {
                    // 使当前线程t休眠1秒
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 启动当前线程t
        t.start();
        // 唤醒线程t
        LockSupport.unpark(t);
    }
}

/**
 * 1、LockSupport不需要synchronized加锁就可以实现线程的阻塞和唤醒
 * 2、LockSupport.unpark()可以先于LockSupport.park()执行，并且线程不会阻塞
 * 3、如果一个线程处于等待状态，连续调用了两次park()方法，就会使该线程永远无法被唤醒
 */
