package com.fc.thread;

/**
 * @author yfc
 * @date 2023/3/1 16:33
 */
public class VolatileExample {
    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        a = 1; // step 1
        flag = true; // step 2

    }

    public void reader() {
        if (flag) { // step 3
            System.out.println(a); // step 4
        }
    }


    public static void main(String[] args) {

        VolatileExample example = new VolatileExample();

        new Thread(() -> {
            example.writer();
        }).start();

        new Thread(() -> {
            example.reader();
        }).start();


    }
}
