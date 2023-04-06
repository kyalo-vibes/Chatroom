package com.example.Chatroom.chat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * WebSocket message model
 */
public class Message {
    // TODO: add message model.
    @JSONField(name="username", ordinal = 1)
    private String username;
    @JSONField(name="onlineCount", ordinal = 2)
    private int onlineCount;
    @JSONField(name="msg", ordinal = 3)
    private String msg;
    @JSONField(name="type", ordinal = 4)
    private String type;

    public Message(String username, int onlineCount, String msg, String type) {
        this.username = username;
        this.onlineCount = onlineCount;
        this.msg = msg;
        this.type = type;
    }

    public String getUsername() {return username; }

    public void setUsername(String username) { this.username = username; }

    public int getOnlineCount() {return onlineCount; }

    public void setOnlineCount(int onlineCount) { this.onlineCount = onlineCount; }

    public String getMsg() {return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public String getType() {return type; }

    public void setType(String type) { this.type = type; }

}
