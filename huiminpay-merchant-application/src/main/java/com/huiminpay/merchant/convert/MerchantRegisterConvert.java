package com.huiminpay.merchant.convert;

import com.huiminpay.merchant.dto.MerchantDTO;
import com.huiminpay.merchant.vo.MerchantRegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * ClassName: MerchantRegisterConvert
 * Description: 对象转换
 * date: 2021/1/18 15:41
 *
 * @author ZhangJie
 * @since JDK 1.8
 */

@Mapper
public interface MerchantRegisterConvert {
    MerchantRegisterConvert INSTANCE =  Mappers.getMapper(MerchantRegisterConvert.class);

    public MerchantDTO vo2dto(MerchantRegisterVO vo);

    public MerchantRegisterVO dto2vo(MerchantDTO merchantDTO);

    public static void main(String[] args) {
        MerchantRegisterVO merchantRegisterVO = new MerchantRegisterVO();
        merchantRegisterVO.setMobile("55555");
        System.out.println(MerchantRegisterConvert.INSTANCE.vo2dto(merchantRegisterVO));

    }
}
