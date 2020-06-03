//public class LockDemo {
//
//    //  private static Object lock = new Object(); // static确保只有一把锁
//    private int i = 0;
//
//    public void increaseI() {
////      synchronized (lock) {
//        for(int k=0;k<10;k++) {  // 对i执行10次增1操作
//            i++;
//        }
//        System.out.println(Thread.currentThread().getName() + "线程，i现在的值：" + i);
////      }
//    }
//
//    public static void main(String[] args) {
//        LockDemo ld = new LockDemo();
//
//        int threadNum = 1000;   // 选择1000个线程让结果更加容易观测到
//        MyThread[] threads = new MyThread[threadNum];
//        for(int i=0;i<threads.length;i++) {
//            threads[i] = new MyThread(ld);  // 所有线程共用一个LockDemo对象
//            threads[i].start();
//        }
//
//    }
//
//}
//
//class MyThread extends Thread {
//    LockDemo ld;
//
//    public MyThread(LockDemo ld) {
//        this.ld = ld;
//    }
//
//    public void run() {
//        ld.increaseI();
//    }
//}

import java.util.Date;

public class LockDemo {

    private static Object lock = new Object(); // static确保只有一把锁
    private int i = 0;

    public void increaseI() {
        synchronized (lock) {
            for (int k = 0; k < 10; k++) {  // 对i执行10次增1操作
                i++;
            }
            System.out.println(Thread.currentThread().getName() + "线程，i现在的值：" + i);
        }
    }

    public static void main(String[] args) {
        LockDemo ld = new LockDemo();

        int threadNum = 1000;   // 选择1000个线程让结果更加容易观测到
        MyThread[] threads = new MyThread[threadNum];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(ld);  // 所有线程共用一个LockDemo对象
            threads[i].start();
        }

    }

}

class MyThread extends Thread {
    LockDemo ld;

    public MyThread(LockDemo ld) {
        this.ld = ld;
    }

    public void run() {
        ld.increaseI();
    }


}
 class Wait {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (Wait.class) {
                try {
                    System.out.println(new Date() + " Thread1 is running");
                    Wait.class.wait();
                    System.out.println(new Date() + " Thread1 ended");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (Wait.class) {
                try {
                    System.out.println(new Date() + " Thread2 is running");
                    Wait.class.notify();
                    // Don't use sleep method to avoid confusing
                    for (long i = 0; i < 200000; i++) {
                        for (long j = 0; j < 100000; j++) {
                        }
                    }
                    System.out.println(new Date() + " Thread2 release lock");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            for (long i = 0; i < 200000; i++) {
                for (long j = 0; j < 100000; j++) {
                }
            }
            System.out.println(new Date() + " Thread2 ended");
        });

        // Don't use sleep method to avoid confusing
        for (long i = 0; i < 200000; i++) {
            for (long j = 0; j < 100000; j++) {
            }
        }
        thread2.start();
    }
}
