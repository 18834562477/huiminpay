package com.huiminpay.merchant.controller;

import com.huiminpay.merchant.dto.MerchantDTO;
import com.huiminpay.merchant.service.MerchantServiceApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: MerchantController
 * Description: 前端控制器
 * date: 2021/1/14 18:52
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
@Api(tags = "商户管理Controller tags",description = "商户管理Controller")
@RestController
public class MerchantController {

    @Reference
    private MerchantServiceApi merchantService;
    @ApiImplicitParams({
            @ApiImplicitParam(value ="商户Id",name ="id",paramType ="path" ,dataType = "Long")
    })
    @GetMapping("/merchants/{id}")
    @ApiOperation(value = "根据id获取商户信息")
    public MerchantDTO findMerchantById(@PathVariable("id") Long id){
        MerchantDTO merchantDTO = merchantService.findMerchantById(id);
        return merchantDTO;
    }
}
