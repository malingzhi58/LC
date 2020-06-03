package src.com.src.com.geekbang.src.com.geekbang.learnthrread;

public class DataHolder {

    private Object lockObj = new Object();

    private long number = 0;
    private static long numberStatic = 0;

    // TODO 一个synchronized解决问题
    public synchronized void change(long delta) {
        number += delta;
    }

    public void changeSyncBlock(long delta){
        int abc = 99;
        synchronized (lockObj){
            number += delta;
        }
        int cde = 987;
    }
    public static void method2(){
//        synchronized (DataHolder.class){
//
//        }
        synchronized (DataHolder.class){

        }
    }

    public synchronized static void method(){
        numberStatic ++;
    }

    // TODO 一个synchronized解决问题
    public synchronized static void changeStatic(long delta) {
        numberStatic += delta;
    }

    public void print() {
        System.out.println("Number=" + number);
    }

    public static void printStatic() {
        System.out.println("static Number=" + numberStatic);
    }

}
