package org.kingfen.k_chat.ChatGpt;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JSONType(orders = {"role","message"})
public class Dialogue {
    private String role;
    private String content;
}
