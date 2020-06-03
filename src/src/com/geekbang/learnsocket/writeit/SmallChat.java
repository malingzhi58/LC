package src.com.geekbang.learnsocket.writeit;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SmallChat {
    private String greeting;
    private Socket socket;
    private String from;
    private final String BYE = "bye";

    public SmallChat(String greeting, Socket socket, String from) {
        this.greeting = greeting;
        this.socket = socket;
        this.from = from;
    }
    public void chat() throws IOException {
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if(greeting!=null){
            printWriter.println(from + ":"+greeting);
            printWriter.flush();
        }
        while (true){
            if(bufferedReader.readLine().equalsIgnoreCase(BYE)){
                printWriter.println("bybybyb");
                printWriter.flush();
                break;
            }else {
                Scanner scanner = new Scanner(System.in);
                String line = scanner.nextLine();
                printWriter.println(line);
                printWriter.flush();
            }
        }
    }
}
