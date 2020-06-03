package Management.src.com.geekbang.learnfile.src.com.geekbang.Mychat;


import java.util.Objects;

import static Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.Constants.MESSAGE_BREAK;
import static Management.src.com.geekbang.learnfile.src.com.geekbang.chatroom.common.Constants.MESSAGE_SEP;

public class Chatmsg {
    private String from;
    private String to;
    private String message;

    public Chatmsg(String from, String to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public Chatmsg() {
    }

    public static Chatmsg buildFrom(String message) {
        Chatmsg ret = new Chatmsg();
        int fromEnd = message.indexOf(MESSAGE_SEP);
        ret.from = message.substring(0, fromEnd);
        int toEnd = message.indexOf(MESSAGE_SEP, fromEnd + 1);
        ret.to = message.substring(fromEnd + 1, toEnd);
        ret.message = message.substring(toEnd + 1).trim();
        return ret;
    }
    public String toMessageString() {
        StringBuilder ret = new StringBuilder();
        ret.append(from).append(MESSAGE_SEP).append(to).append(MESSAGE_SEP).append(message).append(MESSAGE_BREAK);
        return ret.toString();
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chatmsg)) return false;
        Chatmsg that = (Chatmsg) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, message);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
