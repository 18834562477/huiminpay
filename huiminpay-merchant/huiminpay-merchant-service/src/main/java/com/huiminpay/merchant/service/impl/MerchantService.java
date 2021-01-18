package com.huiminpay.merchant.service.impl;

import com.huiminpay.merchant.dto.MerchantDTO;
import com.huiminpay.merchant.entity.Merchant;
import com.huiminpay.merchant.mapper.MerchantMapper;
import com.huiminpay.merchant.service.MerchantServiceApi;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ClassName: MerchantService
 * Description: 服务实现类
 * date: 2021/1/14 18:33
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
@Service
public class MerchantService implements MerchantServiceApi {


    @Autowired
    private MerchantMapper merchantMapper;
    @Override
    public MerchantDTO findMerchantById(Long id) {
        Merchant merchant = merchantMapper.selectById(id);
        MerchantDTO merchantDTO = new MerchantDTO();
        //..............
        BeanUtils.copyProperties(merchant,merchantDTO);
        return merchantDTO;
    }


    //注册商户
    @Override
    public MerchantDTO createMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = new Merchant();
        //设置审核状态0‐未申请,1‐已申请待审核,2‐审核通过,3‐审核拒绝
        merchant.setAuditStatus("0");
        //设置手机号
        merchant.setMobile(merchantDTO.getMobile());

        //将商户信息存入merchant表
        merchantMapper.insert(merchant);

        //将新增商户id返回
        merchantDTO.setId(merchant.getId());

        return merchantDTO;

    }
}
