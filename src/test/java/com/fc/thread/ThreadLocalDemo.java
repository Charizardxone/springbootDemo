package com.fc.thread;

/**
 * @author yfc
 * @date 2023/3/1 15:34
 */
public class ThreadLocalDemo {

    static class ThreadA implements Runnable{

        private ThreadLocal<String> threadLocal;
        public ThreadA(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("AAAA");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ThreadA: " + threadLocal.get());
        }
    }

    static class ThreadB implements Runnable{

        private ThreadLocal<String> threadLocal;
        public ThreadB(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("BBBB");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("ThreadB: " + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        new Thread(new ThreadA(local)).start();
        new Thread(new ThreadB(local)).start();

        /** // 输出：
        ThreadA输出：A
        ThreadB输出：B
         可以看到，虽然两个线程使用的同一个ThreadLocal实例（通过构造方法传入），但是它们各自可以存取自己当前线程的一个值。
         */
    }

}
