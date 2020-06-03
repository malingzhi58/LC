package src.com.geekbang.learnsocket.writeit;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AdviceServer {
    String[] adviceList = {"Take smaller bites", "Go for the tight jeans. No they do NOT make you look fat",
            "One word: inappropriate", "Just for today, be honest.  Tell your boss what you *really* think",
            "You might want to rethink that haircut"};
    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4200);

            while(true) {

                Socket socket = serverSocket.accept();

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                String advice = getAdvice();
                for(int i=0;i<adviceList.length;i++){
                    printWriter.println(adviceList[i]);
                }



                printWriter.close();

                System.out.println(advice);
            }

        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        AdviceServer adviceServer = new AdviceServer();
        adviceServer.go();
    }
}
