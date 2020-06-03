package Management.src.com.geekbang.learnfile.src.com.geekbang.Mychat;


import Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.ChatMessage;

import java.io.*;
import java.net.Socket;

public class DataExchanger {
    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private boolean isClosed = false;

    public DataExchanger(Socket socket) throws IOException {
        this.socket = socket;
        init(socket);
    }

    private void init(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }
    public Chatmsg receive() throws IOException {
        String line = null;
        while (true) {
            line = bufferedReader.readLine();
            if (line != null && line.length() > 0) {
                break;
            }
        }
        return Chatmsg.buildFrom(line);
    }
    public void send(Chatmsg chatMessage) {
        printWriter.println(chatMessage.toMessageString());
        printWriter.flush();
    }
    public void close() {
        try {
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            printWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isClosed = true;
    }
}
