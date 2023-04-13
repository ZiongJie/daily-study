package com.study.juc.sixth;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class T13_SynchronusQueue { // 容器为0
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 阻塞等待消费者消费
        strs.put("aaa");

//        strs.put("bbb");
//        strs.add("aaa");
        System.out.println(strs.size());
    }
}
