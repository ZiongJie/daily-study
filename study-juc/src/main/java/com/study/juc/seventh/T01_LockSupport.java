package com.study.juc.seventh;

import java.util.concurrent.locks.LockSupport;

public class T01_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) throws Exception {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aI) {
                System.out.println(c);
                LockSupport.unpark(t2); //叫醒t2
                LockSupport.park(); //t1阻塞
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : aC) {
                LockSupport.park(); //t2阻塞
                System.out.println(c);
                LockSupport.unpark(t1); //侥幸t1
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
