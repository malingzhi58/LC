package com.geekbang.learnfile.geekbang.learnthrread.src.com.geekbang.learnconcurrent;


import sun.misc.Unsafe;

public class LearnConcurrentAppMain {
    public static void main(String[] args) {
        Unsafe unsafe = null;
        unsafe.park(true, 1);
        unsafe.unpark(null);
    }
}
