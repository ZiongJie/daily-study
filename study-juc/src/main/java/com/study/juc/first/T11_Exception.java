package com.study.juc.first;

import java.util.concurrent.TimeUnit;

public class T11_Exception {
    int count = 0;
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + "start");
        while (true) {
            count ++;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {
                int i = 1/0; // 此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        T11_Exception t11_exception = new T11_Exception();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t11_exception.m();
            }
        };
        new Thread(r, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r, "t2").start();
    }
}
