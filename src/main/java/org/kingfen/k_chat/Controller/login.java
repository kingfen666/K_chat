package org.kingfen.k_chat.Controller;

import com.alibaba.fastjson.JSON;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

import static org.kingfen.k_chat.KChatApplication.userMap;

@Controller
@CrossOrigin
public class login {
    @RequestMapping("pwdLogin")
    @ResponseBody
    public String pwd_login(String username,String password){
        System.out.println(username);
        System.out.println(DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
        User login = userMap.login(username, DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
        if (login!=null){
            return JSON.toJSONString(login);
        }
        return "";
    }
}
