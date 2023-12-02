package org.kingfen.k_chat.Controller.interceptor;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;


@Slf4j
public class CodeInterceptor implements HandlerInterceptor {
    public HashSet<String> hashSet = new HashSet<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String address = request.getParameter("address");
        System.out.println(address);
        if (hashSet.contains(address)) {
            log.info("拦截ip地址:{},邮箱地址:{},原因:频繁发送验证码", request.getRemoteAddr(),address);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write("false".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
            return false;
        }

        hashSet.add(address);
        new Thread(new Deleter(address, hashSet)).start();
        return true;
    }
}


class Deleter implements Runnable{

    private final String addr;
    private final HashSet<String> hashSet;
    public Deleter(String addr,HashSet<String> hashSet){
        this.addr=addr;
        this.hashSet=hashSet;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000*55);
            hashSet.remove(addr);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
