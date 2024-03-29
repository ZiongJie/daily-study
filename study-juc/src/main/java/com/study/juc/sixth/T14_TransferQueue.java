package com.study.juc.sixth;

import java.util.concurrent.LinkedTransferQueue;

public class T14_TransferQueue {

    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

        // strs.put("aaa");

        /*new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }


}
