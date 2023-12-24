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
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

import static org.kingfen.k_chat.KChatApplication.friendMap;
@ServerEndpoint("/each/{user}")
@Controller
public class Websocket {
    HashMap<Session, MakeChat> map=new HashMap<>();
    @OnOpen
    public void open(@PathParam("user") String user,Session session){
        User user1 = JSON.parseObject(user, User.class);
        List<Friend> mysel = friendMap.selectList(new QueryWrapper<Friend>().eq("mysel", user1.getUid()));
        String jsonString = JSON.toJSONString(mysel);
        System.out.println(jsonString);
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
