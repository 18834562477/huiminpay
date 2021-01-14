package com.huiminpay.merchant.service;

import com.huiminpay.merchant.dto.MerchantDTO;

/**
 * ClassName: MerchantServiceApi
 * Description: 服务接口
 * date: 2021/1/14 18:27
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
public interface MerchantServiceApi {
    //根据id获取商户信息
    public MerchantDTO findMerchantById(Long id);
}
