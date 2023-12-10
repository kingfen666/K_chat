package org.kingfen.k_chat.ChatGpt.ResponseJson;

import lombok.Data;

@Data
public class Chat {
    private String finish_reason;
    private String index;
    private Message message;
}
