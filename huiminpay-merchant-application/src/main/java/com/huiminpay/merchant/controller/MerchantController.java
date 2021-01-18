package com.huiminpay.merchant.controller;

import com.huiminpay.merchant.convert.MerchantRegisterConvert;
import com.huiminpay.merchant.dto.MerchantDTO;
import com.huiminpay.merchant.service.MerchantServiceApi;
import com.huiminpay.merchant.service.impl.SmsServiceImpl;
import com.huiminpay.merchant.vo.MerchantRegisterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: MerchantController
 * Description: 前端控制器
 * date: 2021/1/14 18:52
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
@Api(tags = "商户管理Controller tags", description = "商户管理Controller")
@RestController
public class MerchantController {

    @Reference
    private MerchantServiceApi merchantService;
    @Autowired
    SmsServiceImpl smsServiceImpl;

    /**
     * 根据id获取商户信息
     *
     * @param id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商户Id", name = "id", paramType = "path", dataType = "Long")
    })
    @GetMapping("/merchants/{id}")
    @ApiOperation(value = "根据id获取商户信息")
    public MerchantDTO findMerchantById(@PathVariable("id") Long id) {
        MerchantDTO merchantDTO = merchantService.findMerchantById(id);
        return merchantDTO;
    }

    /**
     * 给商户发送验证码
     *
     * @param mobile
     * @return
     */
    @ApiOperation(value = "发送验证码")//  get/sms/code?mobile=1111111
    @ApiImplicitParam(value = "手机号", name = "mobile", dataType = "String", paramType = "query", required = true)
    @GetMapping("/merchants/sms")
    public String getSmsKey(@RequestParam("mobile") String mobile) {
        //
        String smsKey = smsServiceImpl.getSmsKey(mobile);
        return smsKey;
    }

    @ApiOperation("注册商户")
    @ApiImplicitParam(name = "merchantRegister", value = "注册信息", required = true, dataType =
            "MerchantRegisterVO", paramType = "body")
    @PostMapping("/merchants/register")
    public MerchantRegisterVO registerMerchant(@RequestBody MerchantRegisterVO merchantRegister) {
        //校验验证码
        smsServiceImpl.checkVerifyCode(
                merchantRegister.getVerifiykey(),
                merchantRegister.getVerifiyCode()
        );
        //注册商户
        //MerchantDTO merchantDTO = new MerchantDTO();
        // Vo→DTO
        //BeanUtils.copyProperties(merchantRegister,merchantDTO);

        MerchantDTO merchantDTO = MerchantRegisterConvert.INSTANCE.vo2dto(merchantRegister);
        MerchantDTO merchantDtoDb = merchantService.createMerchant(merchantDTO);

        // 返回的DTO → Vo
        //BeanUtils.copyProperties(merchantDtoDb,merchantRegister);
        MerchantRegisterVO merchantRegisterVO = MerchantRegisterConvert.INSTANCE.dto2vo(merchantDtoDb);

        return merchantRegisterVO;
    }


}
