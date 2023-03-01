package com.study.juc.first;

public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(this.getState());

            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        }

        public static void main(String[] args) {
            Thread t = new MyThread();
                // 怎么样得到这个线程的状态呢？就是通过getState()这个方法
            System.out.println(t.getState());// 他是一个new状态

            t.start();

            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                // 然后join之后，结束了是一个Terminated状态
            System.out.println(t.getState());
        }
    }
}
