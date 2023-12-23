package org.kingfen.k_chat;

import com.alibaba.fastjson.JSON;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.kingfen.k_chat.ChatGpt.MakeChat;
import org.kingfen.k_chat.Controller.aiChat;
import org.springframework.stereotype.Controller;

import java.util.HashMap;

@ServerEndpoint("/each")
@Controller
public class Websocket {
    HashMap<Session, MakeChat> map=new HashMap<>();
    @OnOpen
    public void open( Session session){
        map.put(session,new MakeChat());
    }
    @OnMessage
    public void message(String message,Session session){
        String chat = map.get(session).chat(message);
        String s = MakeChat.textToSpeech(chat);
        session.getAsyncRemote().sendText(JSON.toJSONString(new result(chat,s)));
    }

}
@Data
@AllArgsConstructor
class result{
    private String message;
    private String path;
}
