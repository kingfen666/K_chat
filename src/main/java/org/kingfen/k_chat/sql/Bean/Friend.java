package org.kingfen.k_chat.sql.Bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("friends")
public class Friend {
    Integer myself;
    String another;
}
