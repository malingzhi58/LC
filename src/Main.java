
//public class Main {
//
//    public static void main(String[] args) {
//        Producer p=new Producer();
//        Consumer c=new Consumer();
//        Thread pp=new Thread(p);
//        Thread cp=new Thread(c);
//        pp.start();
//        cp.start();
//    }
//}
//
//class Global{
//    static syn empty=new syn(8);
//    static syn full=new syn(0);
//    static int buffer []=new int[8];//缓冲区
//}
//
////生产者类
//class Producer implements Runnable{
//    int count=0;
//    public void run(){
//        while(count<20){
//            Global.empty.Wait();
//            //临界区
//            int index=count%8;
//            Global.buffer[index]=count;
//            System.out.println("生产者在缓冲区"+index+"中生产了物品"+count);
//            count++;
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            // end of 临界区
//            Global.full.Signal();
//        }
//    }
//}
//
////消费者类
//class Consumer implements Runnable{
//    int count=0;
//    public void run(){
//        while(count<20){
//            Global.full.Wait();
//            //临界区
//            int index=count%8;
//            int value=Global.buffer[index];
//            System.out.println("消费者在缓冲区"+index+"中消费了物品"+value);
//            count++;
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            // end of 临界区
//            Global.empty.Signal();
//        }
//    }
//}
public class Main {

    public static void main(String[] args) {
        Producer p[]=new Producer[3];//3个生产者
        Consumer c[]=new Consumer[3];
        int i;

        for(i=0;i<3;i++){
            p[i]=new Producer(i+1);
        }
        for(i=0;i<3;i++){
            c[i]=new Consumer(i+1);
        }

        Thread pp[]=new Thread[3];
        Thread cp[]=new Thread[3];

        for(i=0;i<3;i++){
            pp[i]=new Thread(p[i]);
        }
        for(i=0;i<3;i++){
            cp[i]=new Thread(c[i]);
        }

        for(i=0;i<3;i++){
            pp[i].start();
        }
        for(i=0;i<3;i++){
            cp[i].start();
        }

    }
}

class Global{
    static syn empty=new syn(8);
    static syn full=new syn(0);
    static syn pMutex=new syn(1);//保证生产者之间互斥
    static syn cMutex=new syn(1);//保证消费者之间互斥
    static int buffer []=new int[8];//缓冲区
    static int pCount=0;
    static int cCount=0;
}

//生产者类
class Producer implements Runnable{
    int ID=0;
    Producer(){}
    Producer(int id){ID=id;}
    public void run(){
        while(Global.pCount<20){
            Global.empty.Wait();
            Global.pMutex.Wait();
            //临界区
            int index=Global.pCount%8;
            Global.buffer[index]=Global.pCount;
            System.out.println("生产者"+ID+"在缓冲区"+index+"中生产了物品"+Global.pCount);
            Global.pCount++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // end of 临界区
            Global.pMutex.Signal();
            Global.full.Signal();
        }
    }
}

//消费者类
class Consumer implements Runnable{
    int ID=0;
    Consumer(){}
    Consumer(int id){ID=id;}
    public void run(){
        while(Global.cCount<20){
            Global.full.Wait();
            Global.cMutex.Wait();
            //临界区
            int index=Global.cCount%8;
            int value=Global.buffer[index];
            System.out.println("消费者"+ID+"在缓冲区"+index+"中消费了物品"+value);
            Global.cCount++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // end of 临界区
            Global.cMutex.Signal();
            Global.empty.Signal();
        }
    }
}

//class syn{//PV操作类
//    int count=0;//信号量
//    syn(){}
//    syn(int a){count=a;}
//    public synchronized void Wait(){ //关键字 synchronized 保证了此操作是一条【原语】
//        count--;
//        if(count<0){//等于0 ：有一个进程进入了临界区
//            try {         //小于0：abs(count)=阻塞的进程数目
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public synchronized void Signal(){   //关键字 synchronized 保证了此操作是一条【原语】
//        count++;
//        if(count<=0){//如果有进程阻塞
//            this.notify();//All
//        }
//    }
//}
class syn{//PV操作类
    int count=0;//信号量
    syn(){}
    syn(int a){count=a;}
    public synchronized void Wait(){ //关键字 synchronized 保证了此操作是一条【原语】
        count--;
        if(count<0){//等于0 ：有一个进程进入了临界区
            try {         //小于0：abs(count)=阻塞的进程数目
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void Signal(){   //关键字 synchronized 保证了此操作是一条【原语】
        count++;
        if(count<=0){//如果有进程阻塞
            this.notify();
        }
    }
}