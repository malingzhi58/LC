//package src.com.src.com.geekbang.src.com.geekbang.learnthrread;
//
//public class MultiThreadChaos {
//    public static void main(String[] args) {
//        // TODO 同样的运算，安排在两个线程里做，结果就不一样了
////        DataHolder dataHolder = new DataHolder();
////        DataHolder dataHolder2 = new DataHolder();
//        Thread increaseThread = new Thread(new ChangeData(-2, Integer.MAX_VALUE/50, null));
//        Thread decreaseThread = new Thread(new ChangeData(2, Integer.MAX_VALUE/50, null));
//        System.out.println("执行开始");
//        increaseThread.start();
//        decreaseThread.start();
//        Thread sdfl = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println();
//            }
//        });
//        Thread sdf = new Thread(()->{
//            while(true){
//
//            }
//        });
//        Thread run = new Thread(new PrintRunn("lal"));
//
//    }
//    class PrintRunn implements Runnable{
//
//        String ms;
//
//        public PrintRunn(String ms) {
//            this.ms = ms;
//        }
//
//        @Override
//        public void run() {
//            System.out.println(ms);
//        }
//    }
//
//
//}
