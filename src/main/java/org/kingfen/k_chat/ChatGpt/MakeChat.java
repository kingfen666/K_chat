package org.kingfen.k_chat.ChatGpt;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.kingfen.k_chat.ChatGpt.ResponseJson.Entity;
import org.kingfen.k_chat.util.Util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

public class MakeChat {
    private static final String url = "https://api.chatanywhere.com.cn/v1/chat/completions";//访问地址
    ArrayList<Dialogue> list;

    public MakeChat() {
        list=new ArrayList<>();
    }

    public  String chat(String message) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            list.add(new Dialogue("user", message));//添加记录
            // 设置请求头信息
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer sk-5UH81xbNL5s3y9K4a82g6TGWkAO5gMofuAt2A9SJTFFo53Nd");
            // 设置请求体
            StringEntity entity = new StringEntity("{\"model\":\"gpt-3.5-turbo\",\"messages\":" + JSON.toJSONString(list)/*生成记录*/ + ",\"temperature\":0.7}", StandardCharsets.UTF_8);
            httpPost.setEntity(entity);
            // 发送请求
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 处理响应
            HttpEntity entity1 = response.getEntity();
            String s = new String(entity1.getContent().readAllBytes(), StandardCharsets.UTF_8);
            Entity x = JSON.parseObject(s, Entity.class);
            httpClient.close();
            String content = x.getChoices().getMessage().getContent();
            list.add(new Dialogue("assistant", content));//添加ai回答记录
            //textToSpeech(content.replace("/n", ""));
            return content;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static String textToSpeech(String text) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://v2.genshinvoice.top/run/predict");
            //设置请求参数
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Cookie", "_ga_R1FN4KJKJH=GS1.1.1702656427.7.0.1702656427.0.0.0; _ga=GA1.2.1749057500.1702119163; _gid=GA1.2.271007359.1702656428; _gat_gtag_UA_156449732_1=1");
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String session_hash = "z" + uuid.substring(0, 11);

            String format = Util.format("{\"data\":[\"{}\",\"神里绫华_ZH\",0.5,0.7,0.8,1.6,\"auto\",null,\"Happy\",\"Text prompt\",\"\",\"1.0\"],\"event_data\":null,\"fn_index\":0,\"session_hash\":\"h72v358qsup\"}", text, session_hash);
            StringEntity stringEntity = new StringEntity(format, StandardCharsets.UTF_8);
            httpPost.setEntity(stringEntity);
            CloseableHttpResponse execute = httpClient.execute(httpPost);
            HttpEntity entity = execute.getEntity();
            byte[] bytes = entity.getContent().readAllBytes();
            String x = new String(bytes);

            x = x.replace("{\"data\":[\"Success\",{\"name\":\"", "");
            x = x.substring(0, x.indexOf(".wav")) + ".wav";//获取文件地址
            //
            return "https://v2.genshinvoice.top/file="+x;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void play(String url) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new URL(url));

            // 获取音频流的格式
            AudioFormat audioFormat = audioInputStream.getFormat();

            // 创建DataLine.Info对象，用于描述音频格式
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            // 根据音频格式创建SourceDataLine对象
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);

            // 打开SourceDataLine
            sourceDataLine.open(audioFormat);

            // 启动音频播放线程
            sourceDataLine.start();

            // 定义缓冲区
            byte[] buffer = new byte[1024];
            int bytesRead = 0;

            // 循环读取音频数据并播放
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                sourceDataLine.write(buffer, 0, bytesRead);
            }

            // 停止播放，关闭资源
            sourceDataLine.drain();
            sourceDataLine.close();
            audioInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
