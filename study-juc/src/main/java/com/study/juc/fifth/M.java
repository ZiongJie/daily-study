package com.study.juc.fifth;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
