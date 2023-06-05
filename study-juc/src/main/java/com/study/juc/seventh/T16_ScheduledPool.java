/**
 * ScheduledPool定时任务线程池，就是我们原来学过一个定时任务
 */
package com.study.juc.seventh;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T16_ScheduledPool {

    public static void main(String[] args) {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }
}
