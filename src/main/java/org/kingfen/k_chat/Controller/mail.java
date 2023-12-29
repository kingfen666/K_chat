package org.kingfen.k_chat.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.kingfen.k_chat.sql.Bean.SmS;
import org.kingfen.k_chat.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.kingfen.k_chat.KChatApplication.smsmap;

import java.util.HashMap;
import java.util.Random;

@Controller
@CrossOrigin
@Slf4j
public class mail {
    JavaMailUtils javaMailUtils;

    @Autowired
    public void auto(JavaMailUtils javaMailUtils) {
        this.javaMailUtils = javaMailUtils;
    }

    @RequestMapping("mail")
    @ResponseBody
    public String sendMail(String address, HttpServletRequest httpServletRequest) {
        try {
            log.info("ip地址为:{}的用户,向邮箱:{}发送了验证码",httpServletRequest.getRemoteAddr(),address);
            Random random = new Random();
            StringBuilder code = new StringBuilder();
            for (int i1 = 0; i1 < 8; i1++) {
                code.append(random.nextInt(10));
            }
            String code1 = code.toString();

            SmS smS1 = smsmap.selectOne(new QueryWrapper<SmS>().eq("mail", address));

            if (smS1 == null) {
                SmS smS = new SmS();
                smS.setMail(address);
                smS.setCode(code1);
                smS.setTime(System.currentTimeMillis());
                 smsmap.insert(smS);
                javaMailUtils.send(address, code1);
                return "true";
            }else {
                SmS smS = new SmS();
                smS.setMail(address);
                smS.setCode(code1);
                smS.setTime(System.currentTimeMillis());
                smsmap.update(smS, new QueryWrapper<SmS>().eq("mail", address));
                javaMailUtils.send(address, code1);
                return "true";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
