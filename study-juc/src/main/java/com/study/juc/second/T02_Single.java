package com.study.juc.second;

import java.util.ArrayList;
import java.util.List;

public class T02_Single {
    volatile int count = 0;
    synchronized void m() {
        for (int i = 0; i < 10000; i++) count++;
    }

    public static void main(String[] args) {
        T02_Single t = new T02_Single();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.forEach((o) -> {
                try {
                    o.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t.m();
            System.out.println(t.count);
        }
    }
}
