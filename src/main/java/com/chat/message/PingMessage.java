package com.chat.message;

public class PingMessage extends AbstMessage {
    @Override
    public int getMessageType() {
        return PingMessage;
    }
}
