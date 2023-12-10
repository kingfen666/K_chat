package org.kingfen.k_chat.ChatGpt.ResponseJson;

import lombok.Data;

@Data
public class Entity {
    private String id;
    private String object;
    private String created;
    private String model;
    private Chat choices;
    private String usage;

}
