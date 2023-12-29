package org.kingfen.k_chat.sql.Bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("chathistory")
public class History {
    String Object;
    Integer who;
    String message;
}
