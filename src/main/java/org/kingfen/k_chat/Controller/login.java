package org.kingfen.k_chat.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.kingfen.k_chat.sql.Bean.SmS;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;

import static org.kingfen.k_chat.KChatApplication.smsmap;
import static org.kingfen.k_chat.KChatApplication.userMap;

@Controller
@CrossOrigin
public class login {
    @RequestMapping("pwdLogin")
    @ResponseBody
    public String pwd_login(String username,String password){
        User login = userMap.login(username, DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)));
        if (login!=null){
            return JSON.toJSONString(login);
        }
        return "";
    }
    @RequestMapping("smsLogin")
    @ResponseBody
    public String sms_login(String username,String password){
        try {
            if (password.matches("[0-9]{8}")) {
                SmS smS = smsmap.selectOne(new QueryWrapper<SmS>().eq("mail", username));//查询邮箱验证码
                if (System.currentTimeMillis() + 15 * 60 * 1000 < smS.getTime()) {
                    return "error";
                }
                if (smS.getCode().equals(password)) {
                    User user = userMap.selectOne(new QueryWrapper<User>().eq("mail", username));
                    if (user != null) {
                        smS.setCode("0");
                        smsmap.update(smS, new QueryWrapper<SmS>().eq("mail", username));
                        return JSON.toJSONString(user);
                    } else return "notExists";
                }
            }
        } catch (Exception ignored) {
        }

        return "error";
    }
}
