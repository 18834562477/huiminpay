package com.huiminpay.merchant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: TestRestTemplate
 * Description: restTempplate
 * date: 2021/1/15 14:49
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
@SpringBootTest(classes = MerchantApplicationBootstrap.class)
@RunWith(SpringRunner.class)
public class TestRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void fun(){
        ResponseEntity<String> entity = restTemplate.getForEntity("http://www.baidu.com", String.class);
        String body = entity.getBody();
        System.out.println("---------"+body);
    }

    //测试验证码获取
    @Test
    public void fun1(){
        //请求内容
        Map body = new HashMap();
        body.put("mobile","11111111111");
        //请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        //设置数据格式为json
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //封装请求实体
        HttpEntity<Map> httpEntity = new HttpEntity<>(body,httpHeaders);

        //post请求,获取response实体
        ResponseEntity<Map> responseEntity = restTemplate.exchange("http://localhost:56085/sailing/generate?effectiveTime=600&name=sms", HttpMethod.POST, httpEntity, Map.class);
        /**
         * {
         *   "code": 0,
         *   "msg": "正常",
         *   "result": {
         *     "key": "sms:6fd5de08c7c14fffb59c7ee3da85bd0d",
         *     "content": null
         *   }
         * }
         */
        //通过response实体获取验证码
        Map responseEntityBody = responseEntity.getBody();
        Map result =(Map) responseEntityBody.get("result");
        String keyCode = result.get("key").toString();

        System.out.println("验证码=========="+keyCode);

    }
}
