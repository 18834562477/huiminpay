package com.huiminpay.merchant.service.impl;

import com.alibaba.fastjson.JSON;
import com.huiminpay.merchant.service.api.SmsService;
import jdk.internal.instrumentation.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: SmsService
 * Description: 验证码服务
 * date: 2021/1/15 16:05
 *
 * @author ZhangJie
 * @since JDK 1.8
 */

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RestTemplate restTemplate;

    //sailing地址
    @Value("${sms.url}")
    private String sms_url;

    //验证码有效时间
    @Value("${sms.effectiveTime}")
    private String sms_effectiveTime;


    //验证码获取
    public String getSmsKey(String mobile){
        //请求内容
        Map body = new HashMap();
        body.put("mobile",mobile);
        //请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        //设置数据格式为json
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //封装请求实体
        HttpEntity<Map> httpEntity = new HttpEntity<>(body,httpHeaders);

        //post请求,获取response实体
        ResponseEntity<Map> responseEntity = restTemplate.exchange(sms_url+"/generate?name=sms&effectiveTime="+sms_effectiveTime, HttpMethod.POST, httpEntity, Map.class);
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

       return keyCode;

    }



    @Override
    public void checkVerifyCode(String verifyKey, String verifyCode) {
        //实现校验验证码的逻辑
        String url = sms_url + "/verify?name=sms&verificationCode=" + verifyCode + "&verificationKey=" + verifyKey;
        Map responseMap = null;

        try {
        //请求校验验证码
            ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, Map.class);
            responseMap = exchange.getBody();
            //log.info("校验验证码，响应内容：{}", JSON.toJSONString(responseMap));
        } catch (Exception e) {
            e.printStackTrace();
            //log.info(e.getMessage(), e);
            throw new RuntimeException("验证码错误");
        }

        if (responseMap == null || responseMap.get("result") == null || !(Boolean) responseMap.get("result")) {
            throw new RuntimeException("验证码错误");
        }
    }
}
