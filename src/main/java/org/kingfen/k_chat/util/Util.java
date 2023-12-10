package org.kingfen.k_chat.util;

public class Util {
    public static String format(String s,Object...objects){
        for (Object object : objects) {
            s=s.replaceFirst("\\{}",object.toString());
        }
        return s;
    }
}
