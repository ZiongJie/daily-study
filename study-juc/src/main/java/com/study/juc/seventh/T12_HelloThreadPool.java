/**
 * 线程池七个参数：
 * 1、核心线程数
 * 2、最大线程数
 * 3、生存时间
 * 4、生存时间单位
 * 5、任务队列
 * 6、线程工厂
 * 7、拒绝策略
 */
package com.study.juc.seventh;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T12_HelloThreadPool {

    static class Task implements Runnable {

        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "Task" + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }

        public static void main(String[] args) {
            ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                    2,
                    4,
                    60,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<Runnable>(4),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.CallerRunsPolicy());

            for (int i = 0; i < 8; i++) {
                tpe.execute(new Task(i));
            }
            System.out.println(tpe.getQueue());
            tpe.execute(new Task(100));
            System.out.println(tpe.getQueue());
            tpe.shutdown();
        }
    }
}
