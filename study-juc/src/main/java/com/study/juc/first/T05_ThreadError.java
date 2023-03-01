package com.study.juc.first;

public class T05_ThreadError implements Runnable{

    private /*volatile*/ int count = 500;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T05_ThreadError t = new T05_ThreadError();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
