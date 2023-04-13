package com.study.juc.sixth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T07_SynchronizedList {

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        List<String> strsSync = Collections.synchronizedList(strs);
    }
}
