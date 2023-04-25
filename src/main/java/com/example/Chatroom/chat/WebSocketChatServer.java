package com.example.Chatroom.chat;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint("/chat/{username}")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();
    private static Map<String, String> onlineUsers = new ConcurrentHashMap<>();

    private static void sendMessageToAll(String msg) {
        // send message method.
        System.out.println("---sendMsgToEachSession---");
        System.out.println("sendMsgToEachSession msg=" + msg);

        onlineSessions.forEach((id, session) -> {
            try{
                session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        // open connection.
        System.out.println("---Open---");
        System.out.println("OnOpen: username=" + username);
        System.out.println("OnOPen: session.getId()=" +session.getId());

        onlineSessions.put(session.getId(), session);
        onlineUsers.put(session.getId(), username);

        Message msg = new Message(username, onlineSessions.size(), "NEW OPEN", "ENTER");
        String jsonMessage = JSON.toJSONString(msg);
        sendMessageToAll(jsonMessage);

    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) {
        // send message.
        System.out.println("---onMessage---");
        System.out.println("jsonStr=" + jsonStr);
        System.out.println("session=" + session.getId());

        Message m = JSON.parseObject(jsonStr, Message.class);
        System.out.println("msg=" + m.getMsg());
        System.out.println("username+" + m.getUsername());

        String msg = m.getMsg();
        String username = m.getUsername();

        Message mesg = new Message(username, onlineSessions.size(), msg, "SPEAK");
        String jsonMessage = JSON.toJSONString(mesg);
        sendMessageToAll(jsonMessage);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        // close connection.
        System.out.println("---onClose---");

        onlineSessions.remove(session.getId());
        onlineUsers.remove(session.getId());

        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Message mesg = new Message("LEAVE", onlineSessions.size(), "LEAVE", "LEAVE");
        String jsonMessage = JSON.toJSONString(mesg);
        sendMessageToAll(jsonMessage);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
