package org.kingfen.k_chat;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.kingfen.k_chat.ChatGpt.MakeChat;
import org.kingfen.k_chat.Controller.aiChat;
import org.kingfen.k_chat.sql.Bean.Friend;
import org.kingfen.k_chat.sql.Bean.History;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.stereotype.Controller;

import java.rmi.server.UID;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

import static org.kingfen.k_chat.KChatApplication.*;

@ServerEndpoint("/each/{user}")
@Controller
public class Websocket {
    static HashMap<Integer,Session > map=new HashMap<>();
    @OnOpen
    public void open(@PathParam("user") String user,Session session){
        User user1 = JSON.parseObject("{"+user+"}", User.class);
        map.put(user1.getUid(),session);
    }
    @OnMessage
    public void message(String message,Session session){
        History x = JSON.parseObject(message, History.class);
        historyMap.insert(x);
        int key = Integer.parseInt(x.getObject().replace(x.getWho().toString(), "").replace("&", ""));
        Session session1 = map.get(key);
        if (session1!=null){
            session1.getAsyncRemote().sendText(message);
        }

    }

}
@Data
@AllArgsConstructor
class result{
    private String message;
    private String path;
}
