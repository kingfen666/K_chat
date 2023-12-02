package org.kingfen.k_chat.sql.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class User {
    private Integer uid;
    private String username;
    private String usersname;
    private String mail;
    private String password;
}
