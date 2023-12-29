package org.kingfen.k_chat.Controller;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

import static org.kingfen.k_chat.KChatApplication.smsmap;
import static org.kingfen.k_chat.KChatApplication.userMap;
@Controller
@CrossOrigin("*")
public class NewUser {
    @Autowired
    public void auto(login login){
        this.login=login;
    }
    private login login;
    @RequestMapping("newUser")
    @ResponseBody
    public String newUser(String username,String password,String name,String mail,String code){
        if (userMap.exists(new QueryWrapper<User>().eq("username",username))){
            System.out.println("xe");
            return "false";
        }
        String s = login.sms_login(mail, code);
        if (s.equals("notExists")){
            User user = new User();
            user.setMail(mail);
            user.setUid(userMap.getID());
            user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
            user.setUsername(username);
            user.setUsersname(name);
            userMap.insert(user);
            return JSON.toJSONString(user);
        }else {
            return "false";
        }

    }
}
