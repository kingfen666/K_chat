package org.kingfen.k_chat.Controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.kingfen.k_chat.sql.Bean.Friend;
import org.kingfen.k_chat.sql.Bean.History;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.kingfen.k_chat.KChatApplication.*;

@Controller
@CrossOrigin("*")
public class Chat {
    @RequestMapping("history/{myself}/{another}")
    @ResponseBody
    public String history(@PathVariable Integer another, @PathVariable Integer myself) {
        String object = Math.max(another, myself) + "&" + Math.min(another, myself);
        List<History> object1 = historyMap.selectList(new QueryWrapper<History>().eq("Object", object));
        return "{\"list\":" + JSON.toJSONString(object1) + "}";
    }

    @RequestMapping("init/{Myuid}")
    @ResponseBody
    public String init(@PathVariable Integer Myuid) {
        List<Friend> myself = friendMap.selectList(new QueryWrapper<Friend>().eq("myself", Myuid));
        myself.forEach((F) -> {
            User uid = userMap.selectOne(new QueryWrapper<User>().eq("uid", F.getAnother()));
            uid.setPassword("");
            F.setAnother(JSON.toJSONString(uid));
        });
        return "{\"friend\":" + JSON.toJSONString(myself) + "}";

    }
}
