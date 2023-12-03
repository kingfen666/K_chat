package org.kingfen.k_chat;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.format.DataFormatMatcher;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.jupiter.api.Test;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.SocketTimeoutException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

@SpringBootTest
@Slf4j
class KChatApplicationTests {
    @Test
    void contextLoads()throws Exception {
    }


}
