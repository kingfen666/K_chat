package org.kingfen.k_chat;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.junit.jupiter.api.Test;
import org.kingfen.k_chat.ChatGpt.MakeChat;
import org.kingfen.k_chat.ChatGpt.ResponseJson.Entity;
import org.springframework.boot.test.context.SpringBootTest;


import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.UUID;

@SpringBootTest
public class KchatApplicationTests {
    public static void main(String[] args) throws Exception{
        Scanner scanner=new Scanner(System.in);
        while (true){

            System.out.println(MakeChat.chat(scanner.next()));
        }


    }
    @Test
    public void test() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://127.0.0.1:7860/run/predict");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Cookie","_gid=GA1.2.1383047020.1702119163; _gat_gtag_UA_156449732_1=1; _ga_R1FN4KJKJH=GS1.1.1702134962.2.1.1702135547.0.0.0; _ga=GA1.1.1749057500.1702119163");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String session_hash=uuid.substring(0, 10);
        StringEntity stringEntity=new StringEntity("{\"data\":[\"你好呀，旅行者\",\"神里绫华_ZH\",4,0.2,0.5,0.9,1.3,\"ZH\",null],\"event_data\":null,\"fn_index\":2,\"session_hash\":\""+session_hash+"\"}", StandardCharsets.UTF_8);
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        byte[] bytes = entity.getContent().readAllBytes();

        String x = new String(bytes);
        x=x.replace("{\"data\":[\"Success\",{\"name\":\"","");
        x=x.substring(0,x.indexOf(".wav"))+".wav";
        System.out.println(x);
        // 创建AudioInputStream对象，加载音频流

    }
    @Test
    public void test2(){
    }
}
