package com.study.juc.seventh;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T09_TransferQueue {

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        TransferQueue<Character> queue = new LinkedTransferQueue<Character>();
        new Thread(() -> {
            try {
                for (char c : aI) {
                    System.out.println(queue.take());
                    queue.transfer(c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                for (char c : aC) {
                    queue.transfer(c);
                    System.out.println(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
