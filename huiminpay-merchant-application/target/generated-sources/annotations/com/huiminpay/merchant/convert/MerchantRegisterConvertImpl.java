package com.huiminpay.merchant.convert;

import com.huiminpay.merchant.dto.MerchantDTO;
import com.huiminpay.merchant.vo.MerchantRegisterVO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-18T17:36:50+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"
)
public class MerchantRegisterConvertImpl implements MerchantRegisterConvert {

    @Override
    public MerchantDTO vo2dto(MerchantRegisterVO vo) {
        if ( vo == null ) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();

        merchantDTO.setUsername( vo.getUsername() );
        merchantDTO.setPassword( vo.getPassword() );
        merchantDTO.setMobile( vo.getMobile() );

        return merchantDTO;
    }

    @Override
    public MerchantRegisterVO dto2vo(MerchantDTO merchantDTO) {
        if ( merchantDTO == null ) {
            return null;
        }

        MerchantRegisterVO merchantRegisterVO = new MerchantRegisterVO();

        merchantRegisterVO.setMobile( merchantDTO.getMobile() );
        merchantRegisterVO.setUsername( merchantDTO.getUsername() );
        merchantRegisterVO.setPassword( merchantDTO.getPassword() );

        return merchantRegisterVO;
    }
}
