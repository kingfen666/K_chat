package org.kingfen.k_chat.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("sms")
public class SmS {
    private String mail;
    private Integer code;
    private Long time;
}
