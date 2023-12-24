package org.kingfen.k_chat;

import lombok.extern.slf4j.Slf4j;
import org.kingfen.k_chat.sql.FriendMap;
import org.kingfen.k_chat.sql.UserMap;
import org.kingfen.k_chat.sql.smsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@Slf4j
public class KChatApplication {
   public static smsMap smsmap;
   public static UserMap userMap;
   public static FriendMap friendMap;

    @Autowired
    public void auto(smsMap smsmap, UserMap userMap, FriendMap friendMap){
        KChatApplication.friendMap=friendMap;
        KChatApplication.smsmap= smsmap;
        KChatApplication.userMap=userMap;
    }
    public static void main(String[] args) {

        SpringApplication.run(KChatApplication.class, args);
    }
    @Bean
    public ServerEndpointExporter bean(){
        return new ServerEndpointExporter();
    }
}

