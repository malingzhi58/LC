package Management.src.com.geekbang.learnfile.src.com.geekbang.Mychat;

import Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.ChatMessage;
import Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.DataExchange;
import Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.Utils;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.Constants.*;
import static Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.Constants.ADMIN_NAME;

public class Client {
    private String server;
    public DataExchanger dataExchanger;
    private String userName;
    private String currentToName;
    private Scanner input = new Scanner(System.in);

    public Client(String server) throws IOException {
        System.out.println("客户端连接中……");
        this.server = server;
        Socket socket = new Socket(server, SERVER_PORT);
        System.out.println("客户端已连接到服务器：" + socket.getRemoteSocketAddress());
        dataExchanger = new DataExchanger(socket);
        this.server = server;
    }
    public void start() throws IOException {
        initName();
        Thread readThread = new Thread(() -> {
            while (true) {
                try {
                    Chatmsg chatMessage = dataExchanger.receive();
                    if (chatMessage.getFrom().equalsIgnoreCase(ADMIN_NAME)
                            && chatMessage.getMessage().trim().equalsIgnoreCase(BYE)) {
                        dataExchanger.close();
                        System.out.println("已经离开聊天室，程序结束");
                        System.exit(0);
                    }
                    System.out.println("from \"" + chatMessage.getFrom() + "\":" + chatMessage.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    dataExchanger.close();
                    System.exit(-2);
                }
            }
        });
        readThread.start();
        Thread writeThread = new Thread(() -> {
            while (true) {
                try {
                    dataExchanger.send(createChatMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    dataExchanger.close();
                    System.exit(-3);
                }
            }

        });
        writeThread.start();
    }
    private Chatmsg createChatMessage() {
        while (true) {
            String line = input.nextLine().trim();
            String to = null;
            String message = null;
            try {
                if (line.startsWith(CHAT_WITH_START)) {
                    to = line.substring(1, line.indexOf(SPACE_STRING)).trim();
                    String error = Utils.isValidToUserName(to);
                    if (error == null) {
                        currentToName = to;
                        message = line.substring(line.indexOf(SPACE_STRING) + 1);
                    } else {
                        System.out.println("用户名\"" + to + "\"不合法：" + error);
                        continue;
                    }
                } else {
                    if (currentToName == null) {
                        System.out.println("请使用@输入想和谁聊天，以后如果和同一个人聊天，就可以不用@啦");
                        continue;
                    }
                    message = line;
                }

            } catch (Exception ex) {
                System.out.println("输入的消息不对：" + line);
                continue;
            }
            return new Chatmsg(userName, currentToName, message);

        }
    }

    private void initName() throws IOException {
        String typedName = null;
        while (true) {
            Chatmsg chatMessage = dataExchanger.receive();
            String serverMessage = chatMessage.getMessage();
            if (serverMessage.equalsIgnoreCase(USER_NAME_PASS)) {
                userName = typedName;
                break;
            } else {
                System.out.println(serverMessage);
                typedName = input.nextLine();
                dataExchanger.send(new Chatmsg(NO_NAME, ADMIN_NAME, typedName.trim()));
            }

        }
    }
}
