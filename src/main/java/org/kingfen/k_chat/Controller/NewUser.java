package org.kingfen.k_chat.Controller;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.kingfen.k_chat.KChatApplication.smsmap;
import static org.kingfen.k_chat.KChatApplication.userMap;
@Controller
public class NewUser {
    @Autowired
    public void auto(login login){
        this.login=login;
    }
    private login login;
    @RequestMapping("newUser")
    @ResponseBody
    public boolean newUser(String username,String password,String name,String mail,String code){
        String s = login.sms_login(mail, code);
        if (s.equals("notExists")){
            User user = new User();
            user.setMail(mail);
            user.setUid(userMap.getID());
            user.setPassword(password);
            user.setUsername(username);
            user.setUsersname(name);
            userMap.insert(user);
            return true;
        }else {
            return false;
        }

    }
}
