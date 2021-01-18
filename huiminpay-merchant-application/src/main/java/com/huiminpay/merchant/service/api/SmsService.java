package com.huiminpay.merchant.service.api;

/**
 * ClassName: SmsService
 * Description:
 * date: 2021/1/15 16:23
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
public interface SmsService {
    //获取验证码key
    public String getSmsKey(String mobile);
    //校验验证码
    public void checkVerifyCode(String verifyKey,String verifyCode);
}
