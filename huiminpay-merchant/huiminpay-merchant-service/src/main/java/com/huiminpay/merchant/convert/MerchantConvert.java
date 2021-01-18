package com.huiminpay.merchant.convert;

import com.huiminpay.merchant.dto.MerchantDTO;
import com.huiminpay.merchant.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MerchantConvert
 * Description: 对象转换
 * date: 2021/1/18 15:57
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
@Mapper
public interface MerchantConvert {
    MerchantConvert INSTANCE = Mappers.getMapper(MerchantConvert.class);

    public Merchant dto2entity(MerchantDTO merchantDTO);

    public MerchantDTO entity2dto(Merchant merchant);

    public List<MerchantDTO> listEntity2ListDto(List<Merchant> merchants);

    public static void main(String[] args) {
        List<Merchant> merchants = new ArrayList<>();
        Merchant merchant = new Merchant();
        merchant.setMobile("111");
        merchants.add(merchant);

        Merchant merchant1 = new Merchant();
        merchant1.setMobile("222");
        merchants.add(merchant1);
        List<MerchantDTO> merchantDTOS = MerchantConvert.INSTANCE.listEntity2ListDto(merchants);
        System.out.println(merchantDTOS);
    }
}
