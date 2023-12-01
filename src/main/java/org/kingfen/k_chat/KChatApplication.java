package org.kingfen.k_chat;

import org.kingfen.k_chat.sql.UserMap;
import org.kingfen.k_chat.sql.smsMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class KChatApplication {
   public static smsMap smsmap;
   public static UserMap userMap;

    @Autowired
    public void auto(smsMap smsmap,UserMap userMap){
        KChatApplication.smsmap= smsmap;
        KChatApplication.userMap=userMap;
    }
    public static void main(String[] args) {

        SpringApplication.run(KChatApplication.class, args);
    }

}

